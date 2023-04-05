package com.abhijit.FileUpload.controller;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDataDTO;
import com.abhijit.FileUpload.exception.FileDownloadException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "/file", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated
@Tag(name = "File management Api", description = "API for all fileRelated operation")
public interface FileController {
    @PostMapping
    @Operation(summary = "create new file")
    ResponseEntity<FileDataDTO> add(@RequestBody @Validated AddFileDetailDTO addFileDetailDTO);

    @GetMapping("/file")
    @Operation(summary = "Download all file data")
    ResponseEntity<Object> getAccFile() throws IOException, FileDownloadException;

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "File upload")
    ResponseEntity<String> fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException;

}
