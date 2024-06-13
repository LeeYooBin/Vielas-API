package com.programarcomvoce.vielas.infra.services;

import com.programarcomvoce.vielas.domain.models.Image;
import com.programarcomvoce.vielas.infra.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
  private final ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }
}
