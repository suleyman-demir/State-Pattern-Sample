package com.delivery.cargo.service;

import com.delivery.cargo.dto.KargoDto;
import com.delivery.cargo.model.PackageEntity;
import com.delivery.cargo.repository.KargoRepository;
import com.delivery.cargo.repository.KargoRepository1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KargoService {

    private final KargoRepository kargoRepository;
    private final KargoRepository1 kargoRepository1;


    public KargoService(KargoRepository kargoRepository, KargoRepository1 kargoRepository1) {
        this.kargoRepository = kargoRepository;
        this.kargoRepository1 = kargoRepository1;

    }

    private static final Logger logger = LoggerFactory.getLogger(KargoService.class);

    public PackageEntity addKargo(KargoDto kargoDto) {
        logger.info("Yeni kargo ekleniyor: {}", kargoDto.id());
        PackageEntity packageEntity = KargoDto.convert(kargoDto);
        return kargoRepository.save(packageEntity);
    }

    public ResponseEntity<PackageEntity> updateKargoStatus(Long id) throws Exception {
        PackageEntity packageEntity = kargoRepository.findById(id)
                .orElseThrow(() -> new Exception("Package not found with ID: " + id));

        packageEntity.nextState();
        packageEntity.printStatus();

        kargoRepository.save(packageEntity);

        return ResponseEntity.ok(packageEntity);
    }


    public ResponseEntity<PackageEntity> getKargoById(Long id) throws Exception {
        logger.info("ID'si {} olan kargo getiriliyor.", id);
        PackageEntity packageEntity = kargoRepository.findById(id)
                .orElseThrow(() -> new Exception("Bu ID'ye sahip kargo bulunamadı: " + id));
        return ResponseEntity.ok(packageEntity);
    }

    public List<KargoDto> getAllCargos() {
        logger.info("Tüm kargolar getiriliyor.");
        return kargoRepository.findAll().stream()
                .map(KargoDto::convert)
                .collect(Collectors.toList());
    }

    public List<KargoDto> getCargosByIds(List<Long> idd) {
        List<PackageEntity> cargos = kargoRepository1.findAllByIdIn(idd);
        return cargos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private KargoDto convertToDto(PackageEntity packageEntity) {
        return new KargoDto(
                packageEntity.getId(),
                packageEntity.getGondericiAdi(),
                packageEntity.getGondericiAdresi(),
                packageEntity.getDesi(),

                packageEntity.getAliciAdi(),
                packageEntity.getAliciAdresi(),
                packageEntity.getState()
                );
    }


}
