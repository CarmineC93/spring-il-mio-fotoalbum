package com.learning.fotoalbum.repository;

import com.learning.fotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    public List<Photo> findByTitleContainingIgnoreCase(String title);

    //to show on frontend, better filter now than on JavaScript
    public List<Photo> findByVisibleTrue();

    //to show in research on frontend
    public List<Photo> findByTitleContainingIgnoreCaseAndVisibleTrue(String title);

}
