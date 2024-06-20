package com.programarcomvoce.vielas.entrypoint.dto;

import java.util.Date;
public record ImageDTO(
  Long id,
  String url,
  String title,
  String description,
  String category,
  Date date,
  String owner,
  String licenseType,
  String use
) {}
