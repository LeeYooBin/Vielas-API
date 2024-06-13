package com.programarcomvoce.vielas.entrypoint.dto;

import java.util.Date;

public record ImageDTO(
  Long id,
  String uuid,
  String name,
  String author,
  String imageUrl,
  Date date
) {}
