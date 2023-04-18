package com.shop.medicineshop.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.shop.medicineshop.model.Image;
import com.shop.medicineshop.reponsitory.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final Cloudinary cloudinary;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(Cloudinary cloudinary, ImageRepository imageRepository) {
        this.cloudinary = cloudinary;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) throws IOException, IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");
        String name = (String) uploadResult.get("public_id");
        Image image = new Image();
        image.setName(name);
        image.setUrl(url);
        image = imageRepository.save(image);
        return ResponseEntity.ok().body(image);
    }

    @GetMapping("")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return ResponseEntity.ok().body(images);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) throws IOException {
//        Image image = imageRepository.findById(id);
//                .orElseThrow(() -> new ConfigDataResourceNotFoundException("Image not found with id " + id));
//        Map deleteResult = cloudinary.uploader().destroy(image.getName(), ObjectUtils.emptyMap());
//        imageRepository.delete(image);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/Chuyen-Tu-Long-Chau-Qua-Cloudinary")
    public ResponseEntity<?> chuyenTuLongChauQuaCloudinary() throws IOException {
        List<Image> images = imageRepository.findAll();
        for (int i = 0; i < images.size(); i++) {
            String imagePath = images.get(i).getUrl(); // Lấy đường dẫn ảnh từ danh sách

            int jpgIndex = imagePath.lastIndexOf(".jpg");
            int pngIndex = imagePath.lastIndexOf(".png");
            int jpegIndex = imagePath.lastIndexOf(".jpeg");

            // Lấy vị trí cuối cùng của các định dạng ảnh trong URL
            int lastIndex = Math.max(jpgIndex, Math.max(pngIndex, jpegIndex));

            // Lấy tên file từ URL
            String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1, lastIndex);

            // Upload ảnh lên Cloudinary và lấy thông tin về ảnh
            Map<String, Object> uploadResult = cloudinary.uploader().upload(imagePath, ObjectUtils.asMap("public_id", fileName, "overwrite", true));


            // Lấy đường dẫn của ảnh trên Cloudinary
            String cloudinaryUrl = (String) uploadResult.get("url");

            // Cập nhật đường dẫn của ảnh trong database
            images.get(i).setUrl(cloudinaryUrl);
            imageRepository.save(images.get(i)); // Lưu thông tin của bản ghi lại vào database
        }
        return ResponseEntity.ok("Images uploaded successfully");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Image> updateImage(@PathVariable("id") Long id, @RequestBody Image image) throws IOException {
//        Image existingImage = imageRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id " + id));
//        existingImage.setName(image.getName());
//        existingImage.setUrl(image.getUrl());
//        existingImage = imageRepository.save(existingImage);
//        return ResponseEntity.ok().body(existingImage);
//    }

}
