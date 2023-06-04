package com.backend.store.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.backend.store.config.CloudinaryConfig;

import java.io.IOException;
import java.util.Map;

public class CloudinaryService {
    private Cloudinary cloudinary;

    public CloudinaryService(CloudinaryConfig cloudinaryConfig) {
        this.cloudinary = cloudinaryConfig.cloudinary();
    }

    public String upload(MultipartFile file, String folderName) throws IOException {
        // Chuyển đổi MultipartFile thành byte array
        byte[] fileBytes = file.getBytes();

        // Tạo một đối tượng Map để chứa các thông số cần thiết cho việc tải lên
        Map<String, Object> params = ObjectUtils.asMap("folder", folderName);

        // Sử dụng phương thức `cloudinary.uploader().upload()` để tải lên file
        Map<?, ?> result = cloudinary.uploader().upload(fileBytes, params);

        // Trả về publicId của file đã tải lên
        return (String) result.get("public_id");
    }

    public boolean deleteImage(String publicId) {
        try {
            // Sử dụng phương thức `cloudinary.uploader().destroy()` để xóa file
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getImageUrl(String publicId) {
        if (publicId != null) {
            // Sử dụng phương thức `cloudinary.url().generate()` để tạo URL của hình ảnh
            return cloudinary.url().generate(publicId);
        }

        return "https://res.cloudinary.com/cake-shop/image/upload/v1662910949/default-image_n5nxby.jpg";
    }
}
