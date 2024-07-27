package org.hanumoka.sample.graphql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Author(Integer id, String name) {
    public static List<Author> authors = Arrays.asList(
            new Author(1, "J.K. Rowling"),
            new Author(2, "test2 author"),
            new Author(3, "test3 author"));

    public static Optional<Author> getAuthorById(Integer id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst();
    }
}
