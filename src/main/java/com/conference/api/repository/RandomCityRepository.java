package com.conference.api.repository;

import com.conference.api.domain.RandomCity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alex Sharman 22 Nov 2019
 */
public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
