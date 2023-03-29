package com.abhijit.FileUpload.service;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDTO;
import com.abhijit.FileUpload.entity.File;
import com.abhijit.FileUpload.entity.FileData;
import com.abhijit.FileUpload.mapper.FileMapper;
import com.abhijit.FileUpload.repo.FileDataRepo;
import com.abhijit.FileUpload.repo.FileRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class FileUploadImpl implements FileUpload {

    private FileRepo fileRepo;
    private FileMapper fileMapper;


    private FileNumberService fileNumberService;

    private FileDataRepo fileDataRepo;

    @Value("$file-report-url")
    private String file_report_url;



    public FileUploadImpl(FileRepo fileRepo, FileMapper fileMapper, FileNumberService fileNumberService, FileDataRepo fileDataRepo) {
        this.fileRepo = fileRepo;
        this.fileMapper = fileMapper;
        this.fileNumberService = fileNumberService;
        this.fileDataRepo = fileDataRepo;
    }

    @Override
    public String createFile() {
        List<File> fileList = fileRepo.findAll();

        String filename = fileNumberService.getNewFileNumber();
        String filePath = "C:\\\\Users\\\\mishr\\\\OneDrive\\\\Desktop\\\\FileUpload\\\\" + filename;
        log.info(filePath);
        Path path = Paths.get(filePath);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE_NEW);
            for (File file : fileList) {
                bufferedWriter.write(file.print().toString());
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public FileDTO add(AddFileDetailDTO addFileDetailDTO) {
//        fileRepo.deleteAll();
        File file = fileMapper.convertaddFileDetailDTOToFile(addFileDetailDTO);
        file.setFileNumber(fileNumberService.getNewFileNumber());
        File file1 = fileRepo.save(file);
        FileDTO fileDTO = fileMapper.convertfileToFileDTO(file1);
        return fileDTO;
    }

    @Override
    public void SaveinDB(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                String fileNumber=split[0];
                String version=split[1];
                String alphabet=split[2];
                int count= Integer.parseInt(split[3]);
                FileData fileData=new FileData(fileNumber,version,alphabet,count);
                fileDataRepo.save(fileData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
