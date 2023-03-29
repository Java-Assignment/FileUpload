package com.abhijit.FileUpload.service;

import com.abhijit.FileUpload.entity.FileNumber;
import com.abhijit.FileUpload.repo.FileNumberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileNumberServiceImpl implements  FileNumberService{

    private FileNumberRepo fileNumberRepo;

    public FileNumberServiceImpl(FileNumberRepo fileNumberRepo) {
        this.fileNumberRepo = fileNumberRepo;
    }

    @Override
    @Retryable(value = OptimisticLockingFailureException.class,maxAttempts = 15)
    public String getNewFileNumber() {
        try {
            FileNumber fileNumber=fileNumberRepo.findAll().get(0);
            Long newfileNumber=fileNumber.getFileNumber()+1;
            fileNumber.setFileNumber(newfileNumber);
            fileNumberRepo.save(fileNumber);
            return  String.valueOf(newfileNumber);
        }catch (OptimisticLockingFailureException e){
            log.error("exception in account creation will retry.");
            throw e;
        }
    }
}
