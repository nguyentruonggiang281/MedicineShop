package com.shop.medicineshop.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.shop.medicineshop.model.Image;
import com.shop.medicineshop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    /**
 * Uploads images to Cloudinary and updates their URLs in the database.
 *
 * @return ResponseEntity with a success message
 */
@PutMapping("/chuyen-tu-long-chau-qua-cloudinary")
public ResponseEntity<String> uploadImagesToCloudinaryAndSaveUrls() throws IOException {
    List<Image> images = imageRepository.findAll();

    for (Image image : images) {
        String imageUrl = image.getUrl();

        // Get the file extension and file name from the image URL
        int lastDotIndex = imageUrl.lastIndexOf('.');
        String fileExtension = imageUrl.substring(lastDotIndex + 1);
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1, lastDotIndex);

        // Upload the image to Cloudinary and get its public URL
        Map<String, Object> uploadResult = cloudinary.uploader().upload(imageUrl, ObjectUtils.asMap("public_id", fileName, "overwrite", true));
        String cloudinaryUrl = (String) uploadResult.get("url");

        // Update the image URL in the database
        image.setUrl(cloudinaryUrl);
        imageRepository.save(image);
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
