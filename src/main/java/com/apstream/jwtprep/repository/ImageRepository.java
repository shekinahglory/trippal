package com.apstream.jwtprep.repository;

import com.apstream.jwtprep.domain.ImageUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageUrls, Long> {
}
