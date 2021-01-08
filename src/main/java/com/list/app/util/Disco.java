package com.list.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Disco {

    public String path;

    @Value("${people.disco.raiz}")
    private String raiz;

    @Value("${people.disco.diretory-people}")
    private String peopleDiretory;

    public void saveFile(MultipartFile file){
        this.save(this.peopleDiretory, file);
    }

    public void save(String diretory, MultipartFile file) {
        Path diretoryPath = Paths.get(this.raiz, diretory);
        Path filePath = diretoryPath.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(diretoryPath);
            file.transferTo(filePath.toFile());
            this.path = raiz + peopleDiretory + "/" + file.getOriginalFilename();

        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }
}
