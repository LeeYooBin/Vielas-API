package com.programarcomvoce.vielas.entrypoint.controllers;

import com.programarcomvoce.vielas.domain.models.Image;
import com.programarcomvoce.vielas.entrypoint.dto.ImageDTO;
import com.programarcomvoce.vielas.infra.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
  public ResponseEntity<?> uploadImage
  (
    @RequestParam("image") MultipartFile image,
    @RequestParam("title") String title,
    @RequestParam("description") String description,
    @RequestParam("category") String category,
    @RequestParam("owner") String owner,
    @RequestParam("licenseType") String licenseType,
    @RequestParam("use") String use
  ) {
    if (image.isEmpty() || title == null || owner == null) {
      return ResponseEntity.badRequest().body("File, title, and owner are required");
    }

    try {
      Image img = imageService.uploadImage(image, title, description, category, owner, licenseType, use);
      return ResponseEntity.ok(convertToDTO(img));
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
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
