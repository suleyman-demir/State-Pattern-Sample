package com.delivery.cargo.model;

import com.delivery.cargo.chain.AcceptedState;
import com.delivery.cargo.chain.PackageState;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
public class PackageEntity {

    @Id
    private Long id;

    private String gondericiAdi;
    private String gondericiAdresi;
    private int desi;
    private String aliciAdi;
    private String aliciAdresi;
    private PackageState state;

    public PackageEntity(String gondericiAdi, String gondericiAdresi, Long id, int desi, String aliciAdi, String aliciAdresi, PackageState state) {
        this.gondericiAdi = gondericiAdi;
        this.gondericiAdresi = gondericiAdresi;
        this.id = id;
        this.desi = desi;
        this.aliciAdi = aliciAdi;
        this.aliciAdresi = aliciAdresi;
        this.state=state;
    }
    
    public PackageEntity() {
        // Başlangıç durumu kabul edilmiş olarak belirleniyor.
        this.state = new AcceptedState();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGondericiAdi() {
        return gondericiAdi;
    }

    public void setGondericiAdi(String gondericiAdi) {
        this.gondericiAdi = gondericiAdi;
    }

    public String getGondericiAdresi() {
        return gondericiAdresi;
    }

    public void setGondericiAdresi(String gondericiAdresi) {
        this.gondericiAdresi = gondericiAdresi;
    }

    public int getDesi() {
        return desi;
    }

    public void setDesi(int desi) {
        this.desi = desi;
    }

    public String getAliciAdi() {
        return aliciAdi;
    }

    public void setAliciAdi(String aliciAdi) {
        this.aliciAdi = aliciAdi;
    }

    public String getAliciAdresi() {
        return aliciAdresi;
    }

    public void setAliciAdresi(String aliciAdresi) {
        this.aliciAdresi = aliciAdresi;
    }

    public PackageState getState() {
        return state;
    }

    public void setState(PackageState state) {
        this.state = state;
    }
    public void nextState() {
        this.state.next(this);
    }

    public void prevState() {
        this.state.prev(this);
    }

    public void printStatus() {
        this.state.printStatus();
    }
}
