package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void findsValueInHashMapByAnotherBookWithSameIsbn() {
        Map<Book, String> shelfByBook = new HashMap<>();
        Book putKey = new Book("978-0134685991", "Effective Java");
        Book lookupKey = new Book("978-0134685991", "Effective Java (3rd Edition)");

        shelfByBook.put(putKey, "Полка A3");

        assertEquals("Полка A3", shelfByBook.get(lookupKey));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book first = new Book("978-0134685991", "Effective Java");
        Book second = new Book("978-0132350884", "Clean Code");

        assertNotEquals(first, second);
    }
}