package com.apstream.jwtprep.repository;

import com.apstream.jwtprep.domain.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiRepo extends JpaRepository<Cities, Integer> {

    List<Cities> findAllByStateId(String stateId);
}
