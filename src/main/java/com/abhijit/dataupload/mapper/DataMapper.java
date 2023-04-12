package com.abhijit.dataupload.mapper;

import com.abhijit.dataupload.dto.DataDTO;
import com.abhijit.dataupload.dto.FileDataDTO;
import com.abhijit.dataupload.entity.Data;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper {




    FileDataDTO convertFileDataToFileDataDTO(Data data1);

    Data convertDataDTOToFileData(DataDTO dataDTO);
}
