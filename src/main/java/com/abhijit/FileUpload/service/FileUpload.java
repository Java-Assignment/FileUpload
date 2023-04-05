package com.abhijit.FileUpload.service;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDataDTO;

import java.io.IOException;
import java.nio.file.Path;

public interface FileUpload {
    String createFile() throws IOException;

    FileDataDTO add(AddFileDetailDTO addFileDetailDTO);


    void SaveInDB(Path path);
}
