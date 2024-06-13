package com.programarcomvoce.vielas.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.programarcomvoce.vielas.domain.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}