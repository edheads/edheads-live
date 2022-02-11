package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Stream;


public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image store(MultipartFile file, String caption, Long parentId, String parentType, String imagePosition) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(fileName, file.getContentType(), file.getBytes(),
                Base64.getEncoder().encodeToString(file.getBytes()), caption, parentId, parentType, imagePosition);

        return imageRepository.save(image);
    }

    public Optional<Image> getFile(String id) {
        return imageRepository.findById(id);
    }
}
