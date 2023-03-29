package com.abhijit.FileUpload.repo;

import com.abhijit.FileUpload.entity.FileNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileNumberRepo extends MongoRepository<FileNumber,String> {
}
