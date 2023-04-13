package com.learning.fotoalbum.service;

import com.learning.fotoalbum.exceptions.PhotoNotFoundException;
import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll(Sort.by("title"));
    }

    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Photo getById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
    }


}
