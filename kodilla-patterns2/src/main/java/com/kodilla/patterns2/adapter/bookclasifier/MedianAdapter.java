package com.kodilla.patterns2.adapter.bookclasifier;

import com.kodilla.patterns2.adapter.bookclasifier.librarya.Classifier;
import com.kodilla.patterns2.adapter.bookclasifier.libraryb.BookSignature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MedianAdapter extends MedianAdaptee implements Classifier {

    private com.kodilla.patterns2.adapter.bookclasifier.libraryb.Book mapBookA2B(
            com.kodilla.patterns2.adapter.bookclasifier.librarya.Book bookA) {

        return new com.kodilla.patterns2.adapter.bookclasifier.libraryb.Book(
                bookA.getAuthor(),
                bookA.getTitle(),
                bookA.getPublicationYear()
        );
    }

    @Override
    public int publicationYearMedian(Set<com.kodilla.patterns2.adapter.bookclasifier.librarya.Book> bookSet) {

        Map<BookSignature, com.kodilla.patterns2.adapter.bookclasifier.libraryb.Book> books =
                bookSet.stream()
                        .collect(Collectors.toMap(
                                bookA -> new BookSignature(bookA.getSignature()),
                                bookA -> mapBookA2B(bookA))
                        );
        return medianPublicationYear(books);
    }
}
