package com.abhijit.FileUpload.mapper;

import com.abhijit.FileUpload.dto.AddFileDetailDTO;
import com.abhijit.FileUpload.dto.FileDataDTO;
import com.abhijit.FileUpload.entity.FileData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {


    FileData convertAddFileDetailDTOToFileData(AddFileDetailDTO addFileDetailDTO);

    FileDataDTO convertFileDataToFileDataDTO(FileData fileData1);
}
