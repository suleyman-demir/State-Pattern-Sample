package com.delivery.cargo.repository;

import com.delivery.cargo.model.PackageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KargoRepository1 extends MongoRepository<PackageEntity, Long> {
    List<PackageEntity> findAllByIdIn(List<Long> ids);
}
