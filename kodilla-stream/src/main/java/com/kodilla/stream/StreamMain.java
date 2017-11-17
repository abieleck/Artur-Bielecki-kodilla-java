package com.kodilla.stream;

import com.kodilla.stream.book.Book;
import com.kodilla.stream.book.BookDirectory;
import com.kodilla.stream.forumuser.Forum;
import com.kodilla.stream.forumuser.ForumUser;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {
        Forum forum = new Forum();
        LocalDate now = LocalDate.now();
        Map<Integer, ForumUser> usersMap = forum.getUserList().stream()
                .filter(u -> u.getSex()=='M')
                .filter(u -> u.getBirthDate().plusYears(20).isBefore(now.minusDays(1)))
                .filter(u -> u.getPostsCount() >= 1)
                .collect(Collectors.toMap(ForumUser::getUserID, u -> u));
        usersMap.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .forEach(System.out::println);
        }
}
