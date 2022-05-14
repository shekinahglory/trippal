package com.apstream.jwtprep.repository;

import com.apstream.jwtprep.domain.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Countries, Integer> {
}
