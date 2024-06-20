package com.programarcomvoce.vielas.entrypoint.controllers;

import com.programarcomvoce.vielas.domain.models.Image;
import com.programarcomvoce.vielas.entrypoint.dto.ImageDTO;
import com.programarcomvoce.vielas.entrypoint.dto.ImageUploadDTO;
import com.programarcomvoce.vielas.infra.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images")
public class ImageController {
  private final ImageService imageService;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @GetMapping
  public List<ImageDTO> getAllImages() {
    List<Image> images = imageService.getAllImages();
    return images.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<?> uploadImage(@RequestBody ImageUploadDTO formData) {
    if (formData.getUrl().isEmpty() || formData.getTitle() == null || formData.getOwner() == null) {
      return ResponseEntity.badRequest().body("Image URL, title, and owner are required");
    }

    try {
      Image img = imageService.uploadImage(formData.getUrl(), formData.getTitle(), formData.getDescription(), formData.getCategory(), formData.getOwner(), formData.getLicenseType(), formData.getUse());
      return ResponseEntity.ok(convertToDTO(img));
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
    }
  }


  private ImageDTO convertToDTO(Image image) {
    return new ImageDTO(
      image.getId(),
      image.getUrl(),
      image.getTitle(),
      image.getDescription(),
      image.getCategory(),
      image.getDate(),
      image.getOwner(),
      image.getLicenseType(),
      image.getUse()
    );
  }
}

