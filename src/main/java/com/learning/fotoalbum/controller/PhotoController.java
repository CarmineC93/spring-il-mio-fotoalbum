package com.learning.fotoalbum.controller;

import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.service.CategoryService;
import com.learning.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
