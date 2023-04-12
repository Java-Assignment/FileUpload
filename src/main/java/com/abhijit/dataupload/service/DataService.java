package com.abhijit.dataupload.service;

import com.abhijit.dataupload.dto.DataDTO;
import com.abhijit.dataupload.dto.FileDataDTO;

import java.io.IOException;
import java.nio.file.Path;

public interface DataService {
    String createFile() throws IOException;
    FileDataDTO add(DataDTO dataDTO);
    void SaveInDB(Path path);
}
