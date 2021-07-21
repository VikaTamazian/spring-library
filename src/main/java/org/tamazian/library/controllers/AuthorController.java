package org.tamazian.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tamazian.library.dao.AuthorDao;
import org.tamazian.library.entity.Author;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorDao authorDao;

    @Autowired
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        return "authors/show";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("author", authorDao.findById(id));
        return "authors/index";
    }

    @GetMapping("/new")
    public String newAuthor(@ModelAttribute("author") Author author) {
        return "authors/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("author") @Valid Author author,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "authors/new";

        authorDao.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("author", authorDao.findById(id));
        return "authors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult,
                         @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors())
            return "authors/edit";

        authorDao.update(id, author);
        return "redirect:/authors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        authorDao.delete(id);
        return "redirect:/authors";
    }

}