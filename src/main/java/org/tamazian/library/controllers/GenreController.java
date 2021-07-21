package org.tamazian.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tamazian.library.dao.GenreDao;
import org.tamazian.library.entity.Genre;

import javax.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private final GenreDao genreDao;

    @Autowired
    public GenreController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", genreDao.findAll());
        return "genres/show";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("genre", genreDao.findById(id));
        return "genres/index";
    }

    @GetMapping("/new")
    public String newGenre(@ModelAttribute("genre") Genre genre) {
        return "genres/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("genre") @Valid Genre genre,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "genres/new";

        genreDao.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("genre", genreDao.findById(id));
        return "genres/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("genre") @Valid Genre genre, BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors())
            return "genres/edit";

        genreDao.update(id, genre);
        return "redirect:/genres";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        genreDao.delete(id);
        return "redirect:/genres";
    }

}
