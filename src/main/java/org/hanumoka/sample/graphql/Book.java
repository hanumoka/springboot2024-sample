package org.hanumoka.sample.graphql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Book(Integer id, String name, Integer pageCount, Integer authorId) {
    public static List<Book> books = Arrays.asList(
            new Book(1, "Harry Potter and the Philosopher's Stone", 100, 1),
            new Book(2, "test2 book", 223, 1),
            new Book(3, "test3 book", 300, 2),
            new Book(4, "test4 book", 400, 3)

    );

    public static Optional<Book> getBookById(Integer id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst();
    }
}
