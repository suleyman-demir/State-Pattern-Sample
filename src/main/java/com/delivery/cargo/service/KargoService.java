package com.delivery.cargo.service;

import com.delivery.cargo.dto.KargoDto;
import com.delivery.cargo.model.PackageEntity;
import com.delivery.cargo.repository.KargoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KargoService {

    private final KargoRepository kargoRepository;

    public KargoService(KargoRepository kargoRepository) {
        this.kargoRepository = kargoRepository;
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
}
