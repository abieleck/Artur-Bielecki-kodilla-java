package com.kodilla.testing.library;

import com.google.common.collect.HashMultiset;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookDirectoryTestSuite {

    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<Book>();
        for(int n = 1; n <= booksQuantity; n++){
            Book theBook = new Book("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }

    @Test
    public void testListBooksWithConditionsReturnList() {
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<Book>();
        Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
        Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
        Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
        Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
        resultListOfBooks.add(book1);
        resultListOfBooks.add(book2);
        resultListOfBooks.add(book3);
        resultListOfBooks.add(book4);
        when(libraryDatabaseMock.listBooksWithCondition("Secret"))
                .thenReturn(resultListOfBooks);

        // When
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        // Then
        assertEquals(4, theListOfBooks.size());
    }


    @Test
    public void testListBooksWithConditionMoreThan20() {
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf0Books = new ArrayList<Book>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);
        when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                .thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks"))
                .thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("FourtyBooks"))
                .thenReturn(resultListOf40Books);

        // When
        List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
        List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
        List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FourtyBooks");
        // Then

        assertEquals(0, theListOfBooks0.size());
        assertEquals(15, theListOfBooks15.size());
        assertEquals(0, theListOfBooks40.size());
    }

    @Test
    public void testListBooksWithConditionFragmentShorterThan3() {
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf10Books = generateListOfNBooks(10);
        when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                .thenReturn(resultListOf10Books);

        // When
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");

        // Then
        assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());
    }


    private BookLibrary generateBookLibrary(List<Book> resultBookList) {
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        when(libraryDatabaseMock.listBooksInHandsOf(any(LibraryUser.class))).thenReturn(resultBookList);
        return bookLibrary;
    }

    @Test
    public void testListBooksInHandsOfNoBooks() {
        //Given
        BookLibrary bookLibrary = generateBookLibrary(generateListOfNBooks(0));
        LibraryUser libraryUser = new LibraryUser("Jan", "Kowalski", "78092001876");
        //When
        List<Book> resultListOfBooks = bookLibrary.listBooksInHandsOf(libraryUser);
        //Then
        Assert.assertTrue(resultListOfBooks.isEmpty());
    }

    @Test
    public void testListBooksInHandsOf1Book() {
        //Given
        List<Book> listOfBooks = generateListOfNBooks(1);
        BookLibrary bookLibrary = generateBookLibrary(listOfBooks);
        LibraryUser libraryUser = new LibraryUser("Jan", "Kowalski", "78092001876");
        //When
        List<Book> resultListOfBooks = bookLibrary.listBooksInHandsOf(libraryUser);
        //Then
        Assert.assertEquals(listOfBooks, resultListOfBooks);
    }

    @Test
    public void testListBooksInHandsOf5Books() {
        //Given
        List<Book> listOfBooks = generateListOfNBooks(5);
        listOfBooks.set(1, listOfBooks.get(0)); // test duplicate books
        BookLibrary bookLibrary = generateBookLibrary(listOfBooks);
        LibraryUser libraryUser = new LibraryUser("Jan", "Kowalski", "78092001876");
        //When
        List<Book> resultListOfBooks = bookLibrary.listBooksInHandsOf(libraryUser);
        //Then
        //use MultiSet to ignore permutations but do not ignore duplicates
        Assert.assertEquals(HashMultiset.create(listOfBooks), HashMultiset.create(resultListOfBooks));
    }

}
