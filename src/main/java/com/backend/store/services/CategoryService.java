package com.backend.store.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.store.models.Category;
import com.backend.store.repositories.CategoryRepository;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(String name, MultipartFile image) {
        try {
            String imageUrl = CloudinaryService.upload(image, "categories");
            Category newCategory = new Category();
            newCategory.setName(name);
            newCategory.setImage(imageUrl);
            Category savedCategory = categoryRepository.save(newCategory);

            Category responseDTO = new Category();
            responseDTO.setId(savedCategory.getId());
            responseDTO.setName(savedCategory.getName());
            responseDTO.setImage(savedCategory.getImage());

            return responseDTO;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public Category update(String id, String name, MultipartFile image) {
        try {
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                throw new Error("User not found!");
            }

            if (image != null) {
                CloudinaryService.deleteImage(category.getImage());

                String imageUrl = CloudinaryService.upload(image, "categories");
                category.setImage(imageUrl);
            }

            categoryRepository.save(category);

            Category responseDTO = new Category();
            responseDTO.setId(category.getId());
            responseDTO.setName(category.getName());
            responseDTO.setImage(CloudinaryService.getImageUrl(category.getImage()));

            return responseDTO;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
        }


    public Optional<Category> findCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    public Category delete(String id) {
        return null;
    }
}
