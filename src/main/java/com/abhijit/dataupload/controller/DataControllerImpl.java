package com.abhijit.dataupload.controller;

import com.abhijit.dataupload.dto.DataDTO;
import com.abhijit.dataupload.dto.FileDataDTO;
import com.abhijit.dataupload.exception.FileDownloadException;
import com.abhijit.dataupload.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@Slf4j
public class DataControllerImpl implements DataController {
    @Autowired
    private DataService dataService;

    @Value("${file.upload-url}")
    private String fileDirectory;


    @Override
    public ResponseEntity<FileDataDTO> add(DataDTO dataDTO) {
        FileDataDTO fileDataDTO = dataService.add(dataDTO);
        return new ResponseEntity<>(fileDataDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAccFile() throws FileDownloadException {
        try {
            String file = dataService.createFile();
            String[] split = file.split("\\\\");
            String fileName = split[split.length - 1];
            log.info("fileName is" + fileName);

            Path path = Paths.get(file);
            InputStream inputStream = Files.newInputStream(path, StandardOpenOption.READ);
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache,no-store,must-revalidate");
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileName + "\"");
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);


        } catch (IOException e) {
            String msg = "file download Failed";
            log.error(msg);
            throw new FileDownloadException(msg);
        }
    }

    @Override
    public ResponseEntity<String> fileUpload(MultipartFile uploadFile) throws IOException {
        Path path = Paths.get(fileDirectory, uploadFile.getOriginalFilename());
        if (Files.exists(path)) {
            Files.delete(path);
        }
        try {
            OutputStream outputStream = Files.newOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            log.info("before bos write", bos);
            bos.write(uploadFile.getBytes());
            log.info("After bos write", bos);
            bos.flush();
            System.out.println("File written");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to write a file");
        }
        dataService.SaveInDB(path);
        return new ResponseEntity<>("File Uploaded successfully", HttpStatus.OK);

    }
}
