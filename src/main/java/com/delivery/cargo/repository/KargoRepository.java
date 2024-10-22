package com.delivery.cargo.repository;

import com.delivery.cargo.model.PackageEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface KargoRepository extends MongoRepository<PackageEntity,String> {
    Optional<PackageEntity> findById(Long id);
    Optional<PackageEntity> getKargoById(Long id);
}
