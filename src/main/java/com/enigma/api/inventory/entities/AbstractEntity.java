package com.enigma.api.inventory.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;

// mapped super class agar anotasi masuk ke turunannya
@MappedSuperclass
public abstract class AbstractEntity<ID> {

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    public abstract ID getId();

    public abstract void setId(ID id);

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

// anotasi prepersist dipanggil otomatis ketika dibuat
    // anotasi preupdate dipanggil otomatis ketika diupdate

    @PrePersist
    public void prePersist() {
        createdDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        modifiedDate = LocalDate.now();
    }
}
