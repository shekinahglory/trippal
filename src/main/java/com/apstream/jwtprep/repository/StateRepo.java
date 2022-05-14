package com.apstream.jwtprep.repository;

import com.apstream.jwtprep.domain.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepo extends JpaRepository<States, Integer> {

    List<States> findByCountryId(String countryId);
}
