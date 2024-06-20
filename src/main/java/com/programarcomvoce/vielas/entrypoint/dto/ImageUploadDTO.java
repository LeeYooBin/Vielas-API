package com.programarcomvoce.vielas.entrypoint.dto;

public class ImageUploadDTO {
  private String url;
  private String title;
  private String description;
  private String category;
  private String owner;
  private String licenseType;
  private String use;


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getLicenseType() {
    return licenseType;
  }

  public void setLicenseType(String licenseType) {
    this.licenseType = licenseType;
  }

  public String getUse() {
    return use;
  }

  public void setUse(String use) {
    this.use = use;
  }
}

