package com.kodilla.patterns.prototype.library;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class LibraryTestSuite {

    @Test
    public void testGetBooks() {
        //Given
        Library library = new Library("My library");
        Book book1 = new Book("Książka 1", "Autor 1", LocalDate.of(2011, 5, 12));
        Book book2 = new Book("Książka 2", "Autor 2", LocalDate.of(2012, 6, 13));
        Book book3 = new Book("Książka 3", "Autor 3", LocalDate.of(2013, 7, 14));
        library.getBooks().add(book1);
        library.getBooks().add(book2);
        library.getBooks().add(book3);
        Library libraryShallowCopied = null;
        Library libraryDeepCopied = null;
        Book book4 = new Book("book4", "author", LocalDate.of(010, 1, 1));
        Book book5 = new Book("book5", "author", LocalDate.of(010, 1, 1));
        //When
        try {
            libraryShallowCopied = library.shallowCopy();
            libraryDeepCopied = library.deepCopy();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        libraryShallowCopied.getBooks().add(book4);
        libraryDeepCopied.getBooks().add(book5);
        //Then
        Assert.assertEquals(library.getBooks(), libraryShallowCopied.getBooks());
        Assert.assertNotEquals(library.getBooks(), libraryDeepCopied.getBooks());
        Assert.assertTrue(library.getBooks().contains(book4));
        Assert.assertTrue(!library.getBooks().contains(book5));
        Assert.assertTrue(libraryDeepCopied.getBooks().contains(book5));

    }
}
