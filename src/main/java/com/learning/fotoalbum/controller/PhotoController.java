package com.learning.fotoalbum.controller;

import com.learning.fotoalbum.exceptions.PhotoNotFoundException;
import com.learning.fotoalbum.model.CrudMessage;
import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.service.CategoryService;
import com.learning.fotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("photo", new Photo());
        //associo al model di photo un attributo Listacategoria con tutte le categorie prese dal metodo getAll nel service
        model.addAttribute("categoryList", categoryService.getAll());
        return "/photos/create";
    }


    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult br, RedirectAttributes redirectAttributes, Model model){
        if(br.hasErrors()){
            //per far lasciare i campi selezionati in caso di altri campi con errore e ricaricamento pagina
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/create";
        }
        redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "New Photo successfully created"));
        photoService.createPhoto(formPhoto);
        return "redirect:/photos";
    }


}
