package guru.springframework.spring6webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import guru.springframework.spring6webapp.services.AuthorService;
import guru.springframework.spring6webapp.domain.Author;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Tag(name = "Author Controller", description = "API for managing authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Returns a list of all authors")
    public List<Author> getAuthors() {
        return (List<Author>) authorService.findAll();
    }
}