package com.kodilla.patterns2.adapter.bookclassifier;

import com.kodilla.patterns2.adapter.bookclasifier.MedianAdapter;
import com.kodilla.patterns2.adapter.bookclasifier.librarya.Book;
import com.kodilla.patterns2.adapter.bookclasifier.librarya.Classifier;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MedianAdapterTestSuite {

    @Test
    public void publicationYearMedianTest() {
        //Given
        Set<Book> books = new HashSet<>();
        books.add(new Book("author1", "title1", 2010, "signature1"));
        books.add(new Book("author2", "title2", 2010, "signature2"));
        books.add(new Book("author3", "title3", 2011, "signature3"));
        books.add(new Book("author4", "title4", 2018, "signature4"));
        books.add(new Book("author5", "title5", 2010, "signature5"));
        Classifier classifier = new MedianAdapter();
        //When
        int medianYear = classifier.publicationYearMedian(books);
        //Then
        assertEquals(2010, medianYear);
    }
}
