package com.backend.store.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.backend.store.models.Cloud;

import java.io.IOException;
import java.util.Map;

public class CloudinaryService {
    private Cloudinary cloudinary;

    public CloudinaryService(Cloud cloud) {
        this.cloudinary = cloud.cloudinary();
    }

    public String upload(MultipartFile file, String folderName) throws IOException {
        byte[] fileBytes = file.getBytes();
        Map<String, Object> params = ObjectUtils.asMap("folder", folderName);
        Map<?, ?> result = cloudinary.uploader().upload(fileBytes, params);
        return (String) result.get("public_id");
    }

    public boolean deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getImageUrl(String publicId) {
        if (publicId != null) {
            return cloudinary.url().generate(publicId);
        }

        return "https://res.cloudinary.com/cake-shop/image/upload/v1662910949/default-image_n5nxby.jpg";
    }
}
