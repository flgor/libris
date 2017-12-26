package xyz.libris.api.book.google.dto;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleAuthorsTransformers {

    private final List<String> authors;


    public GoogleAuthorsTransformers(List<String> authors) {
        this.authors = authors;
    }

    public String transform() {
        return authors.stream()
                .collect(Collectors.joining(","));
    }
}
