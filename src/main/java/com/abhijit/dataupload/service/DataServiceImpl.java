package com.abhijit.dataupload.service;

import com.abhijit.dataupload.dto.DataDTO;
import com.abhijit.dataupload.dto.FileDataDTO;
import com.abhijit.dataupload.entity.Data;
import com.abhijit.dataupload.entity.UploadedFile;
import com.abhijit.dataupload.mapper.DataMapper;
import com.abhijit.dataupload.repo.FileDataRepo;
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
    private FileDataRepo fileDataRepo;

    @Value("${file-report-url}")
    private String file_download_url;


    @Override
    public String createFile() throws IOException {
        List<Data> fileList = fileDataRepo.findAll();

        String filename = UUID.randomUUID().toString().replace("-", "");
        String filePath = file_download_url + filename;
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE_NEW);
            for (Data data : fileList) {
                FileDataDTO fileDataDTO = dataMapper.convertFileDataToFileDataDTO(data);
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
        Data data = dataMapper.convertDataDTOToFileData(dataDTO);
        data.setFileNumber(fileNumberService.getNewFileNumber());
        Data data1 = fileDataRepo.save(data);
        return dataMapper.convertFileDataToFileDataDTO(data1);

    }

    @Override
    public void SaveInDB(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                String fileNumber = split[0];
                String variable = split[1];
                int value = Integer.parseInt(split[2 ]);
                UploadedFile uploadedFile =new UploadedFile(fileNumber,variable,value);
                uploadedFileRepo.save(uploadedFile);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
