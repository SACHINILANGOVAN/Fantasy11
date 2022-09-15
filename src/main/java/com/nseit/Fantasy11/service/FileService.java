package com.nseit.Fantasy11.service;

import com.nseit.Fantasy11.model.File;
import com.nseit.Fantasy11.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File uploadFile(File file){
        return fileRepository.save(file);
    }

}


