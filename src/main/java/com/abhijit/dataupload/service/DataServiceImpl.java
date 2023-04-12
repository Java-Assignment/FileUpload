package com.abhijit.dataupload.service;

import com.abhijit.dataupload.dto.DataDTO;
import com.abhijit.dataupload.dto.FileDataDTO;
import com.abhijit.dataupload.entity.Data;
import com.abhijit.dataupload.entity.UploadedFile;
import com.abhijit.dataupload.mapper.DataMapper;
import com.abhijit.dataupload.repo.DataRepo;
import com.abhijit.dataupload.repo.UploadedFileRepo;
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
public class DataServiceImpl implements DataService {
    @Autowired
    private UploadedFileRepo uploadedFileRepo;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private FileNumberService fileNumberService;
    @Autowired
    private DataRepo dataRepo;

    @Value("${file-report-url}")
    private String file_download_url;


    @Override
    public String createFile() throws IOException {
        List<Data> fileList = dataRepo.findAll();

        String filename = UUID.randomUUID().toString().replace("-", "");
        String filePath = file_download_url + filename;
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE_NEW);
            for (Data data : fileList) {
                FileDataDTO fileDataDTO = dataMapper.convertDataToFileDataDTO(data);
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
    public FileDataDTO add(DataDTO dataDTO) {
        Data data = dataMapper.convertDataDTOToFileDataDTO(dataDTO);
        data.setFileNumber(fileNumberService.getNewFileNumber());
        Data data1 = dataRepo.save(data);
        return dataMapper.convertDataToFileDataDTO(data1);

    }

    @Override
    public void SaveInDB(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                String fileNumber = split[0];
                String variable1 = split[1];
                int value1 = Integer.parseInt(split[2]);
                String variable2 = split[3];
                int value2 = Integer.parseInt(split[4]);
                String variable3 = split[5];
                int value3 = Integer.parseInt(split[6]);
                UploadedFile uploadedFile = new UploadedFile(fileNumber, variable1, value1, variable2, value2, variable3, value3);
                uploadedFileRepo.save(uploadedFile);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
