package com.enigma.api.inventory.models;

import com.enigma.api.inventory.models.validations.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadRequest {

    @ImageFile
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
