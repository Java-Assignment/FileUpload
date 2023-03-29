package com.abhijit.FileUpload.service;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDTO;

import java.io.IOException;
import java.nio.file.Path;

public interface FileUpload {
    String createFile() throws IOException;

    FileDTO add(AddFileDetailDTO addFileDetailDTO);


    void SaveinDB(Path path);
}
