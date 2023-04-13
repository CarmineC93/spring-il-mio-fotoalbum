package com.learning.fotoalbum.controller;

import com.learning.fotoalbum.exceptions.PhotoNotFoundException;
import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.service.CategoryService;
import com.learning.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

     @Autowired
    private PhotoService photoService;

     @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword") Optional<String> keyword) {
        List<Photo> photos;
        if (keyword.isEmpty()) {
            photos = photoService.getAllPhotos();
        } else {
            photos = photoService.getFilteredPhotos(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", photos);
        return "photos/index";
    }

    @GetMapping("/{photoId}")
    public String show(@PathVariable("photoId") Integer id, Model model) {
        try {
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            return "photos/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id '" + id + "' not found");
        }
    }


}
