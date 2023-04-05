package com.abhijit.FileUpload.service;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDataDTO;
import com.abhijit.FileUpload.entity.FileData;
import com.abhijit.FileUpload.entity.UploadedFiles;
import com.abhijit.FileUpload.mapper.FileMapper;
import com.abhijit.FileUpload.repo.FileDataRepo;
import com.abhijit.FileUpload.repo.UploadedFileRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadImpl implements FileUpload {
    @Autowired
    private UploadedFileRepo uploadedFileRepo;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileNumberService fileNumberService;
    @Autowired
    private FileDataRepo fileDataRepo;

    @Value("${file-report-url}")
    private String file_download_url;


    @Override
    public String createFile() throws IOException {
        List<FileData> fileList = fileDataRepo.findAll();

        String filename = UUID.randomUUID().toString().replace("-", "");
        String filePath = file_download_url + filename;
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE_NEW);
            for (FileData fileData : fileList) {
                FileDataDTO fileDataDTO = fileMapper.convertFileDataToFileDataDTO(fileData);
                bufferedWriter.write(fileDataDTO.print());
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public FileDataDTO add(AddFileDetailDTO addFileDetailDTO) {
        FileData fileData = fileMapper.convertAddFileDetailDTOToFileData(addFileDetailDTO);
        fileData.setFileNumber(fileNumberService.getNewFileNumber());
        FileData fileData1 = fileDataRepo.save(fileData);
        return fileMapper.convertFileDataToFileDataDTO(fileData1);

    }

    @Override
    public void SaveInDB(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                String fileNumber = split[0];
                String alphabet = split[1];
                int count = Integer.parseInt(split[2]);
                UploadedFiles uploadedFiles=new UploadedFiles(fileNumber,alphabet,count);
                uploadedFileRepo.save(uploadedFiles);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
