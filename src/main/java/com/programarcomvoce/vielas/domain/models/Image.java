package com.programarcomvoce.vielas.domain.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String uuid;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String author;
  @Column(nullable = false)
  private String imageUrl;
  @Column(nullable = false)
  private Date date;
  
  public Long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Date getDate() {
    return date;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}

