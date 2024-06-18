package com.programarcomvoce.vielas.infra.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.programarcomvoce.vielas.domain.models.Image;
import com.programarcomvoce.vielas.infra.repository.ImageRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
  private final ImageRepository imageRepository;

  @Value("${firebase.bucket.name}")
  private String bucketName;

  @Value("${firebase.credentials.path}")
  private String credentialsPath;

  private Storage storage;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  @PostConstruct
  public void init() throws IOException {
    try (FileInputStream serviceAccount = new FileInputStream(credentialsPath)) {
      GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
      storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
  }
  
  public Image uploadImage(
    MultipartFile file, 
    String title, 
    String description, 
    String category,
    String owner, 
    String licenseType, 
    String use
    ) throws IOException {
      String uuid = UUID.randomUUID().toString();
      String fileName = "images/" + uuid + "-" + file.getOriginalFilename();

      BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                                  .setContentType(file.getContentType())
                                  .build();

      storage.create(blobInfo, file.getBytes());

      String imageUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, fileName.replace("/", "%2F"));

      Image image = new Image();
      image.setUrl(imageUrl);
      image.setTitle(title);
      image.setDescription(description);
      image.setCategory(category);
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

