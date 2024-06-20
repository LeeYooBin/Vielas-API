package com.programarcomvoce.vielas.infra.services;

import com.programarcomvoce.vielas.domain.models.Image;
import com.programarcomvoce.vielas.infra.repository.ImageRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {
  private final ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }
  
  public Image uploadImage(
    String url, 
    String title, 
    String description, 
    String category,
    Date date,
    String owner, 
    String licenseType, 
    String use
  ) throws IOException {
    Image image = new Image();
    image.setUrl(url);
    image.setTitle(title);
    image.setDescription(description);
    image.setCategory(category);
    image.setDate(date);
    image.setDate(new Date());
    image.setOwner(owner);
    image.setLicenseType(licenseType);
    image.setUse(use);

    return imageRepository.save(image);
}

  public List<Image> getAllImages() {
    return imageRepository.findAll();
  }
}
