package com.enigma.api.inventory.service;

import com.enigma.api.inventory.services.impl.FileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @InjectMocks
    FileServiceImpl service;

//    @Test
//    void uploadShouldReturnFilename() throws IOException {
//        String input = "text";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        BufferedInputStream bis = new BufferedInputStream(in);
//        String filename = service.upload("test", bis);
//        Assertions.assertNotNull(filename);
//    }
//
//    @Test
//    void downloadShouldReturnText() throws IOException {
//        OutputStream os = new ByteArrayOutputStream();
//        service.download("D:/SAMPAH/test", "123.txt", os);
//        String output = os.toString();
//        Assertions.assertNotNull(output);
//    }
//}

}
