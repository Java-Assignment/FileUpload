package com.abhijit.FileUpload.controller;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDTO;
import com.abhijit.FileUpload.exception.AccountFileGenException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "/files",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@Validated
@Tag(name = "File management Api",description = "API for all filerelated operation")
public interface FileController {
    @PostMapping
    @Operation(summary = "create new file")
    ResponseEntity<FileDTO>add(@RequestBody @Validated AddFileDetailDTO addFileDetailDTO);

    @GetMapping("/file")
    @Operation(summary = "Download all filedata")
    ResponseEntity<Object>getAccFile() throws IOException, AccountFileGenException;

    @PostMapping(value="/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "File upload")
    ResponseEntity<String>fileUpload(@RequestParam("uploadfile")MultipartFile uploadFile) throws IOException;

}
