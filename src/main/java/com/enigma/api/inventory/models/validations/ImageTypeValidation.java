package com.enigma.api.inventory.models.validations;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;

public class ImageTypeValidation implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public void initialize(ImageFile constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext cvc) {
        if(file != null) {
            Tika tika = new Tika();
            try {
                String type = tika.detect(file.getInputStream());
                if(!type.startsWith("image/")) {
                    return false;
                }
            }catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
