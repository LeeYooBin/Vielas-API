package com.programarcomvoce.vielas.entrypoint.controllers;

import com.programarcomvoce.vielas.domain.models.Image;
import com.programarcomvoce.vielas.entrypoint.dto.ImageDTO;
import com.programarcomvoce.vielas.infra.services.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  private ImageDTO convertToDTO(Image image) {
    return new ImageDTO(
      image.getId(),
      image.getUuid(),
      image.getName(),
      image.getAuthor(),
      image.getImageUrl(),
      image.getDate()
    );
  }
}
