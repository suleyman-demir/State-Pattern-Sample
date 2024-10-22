package com.delivery.cargo.chain;

import com.delivery.cargo.model.PackageEntity;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface PackageState {

    void next(PackageEntity packageEntity);
    void prev(PackageEntity packageEntity);
    void printStatus();
}
