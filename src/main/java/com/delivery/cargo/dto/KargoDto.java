package com.delivery.cargo.dto;


import com.delivery.cargo.chain.PackageState;
import com.delivery.cargo.model.PackageEntity;

public record KargoDto (
     Long id,
     String gondericiAdi,
     String gondericiAdresi,
     int desi,
     String aliciAdi,
     String aliciAdresi,
     PackageState state) {


    public static KargoDto convert(PackageEntity from) {
        return new KargoDto(from.getId(), from.getGondericiAdi(), from.getGondericiAdresi(), from.getDesi(), from.getAliciAdi(), from.getAliciAdresi(), from.getState());

    }

    public static PackageEntity convert(KargoDto dto) {
        return new PackageEntity(dto.gondericiAdi(), dto.gondericiAdresi(), dto.id(), dto.desi(), dto.aliciAdi(), dto.aliciAdresi(), dto.state());


    }
}
