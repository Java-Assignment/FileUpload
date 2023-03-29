package com.abhijit.FileUpload.repo;

import com.abhijit.FileUpload.entity.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepo extends MongoRepository<FileData,String> {
    void save(String line);
}
