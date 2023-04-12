package com.abhijit.dataupload.repo;

import com.abhijit.dataupload.entity.FileNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileNumberRepo extends MongoRepository<FileNumber, String> {
}
