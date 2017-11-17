package com.kodilla.stream.forumuser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Forum {

    private List<ForumUser> forumUsers = new ArrayList<>();

    public Forum() {
        forumUsers.add(new ForumUser(1,"user1", 'M',
                LocalDate.of(1997,2,28),200));
        forumUsers.add(new ForumUser(2, "user2", 'M',
                LocalDate.of(2007,12,31),0));
        forumUsers.add(new ForumUser(3, "user3", 'M',
                LocalDate.of(1975,3,30),15));
        forumUsers.add(new ForumUser(4, "user4", 'F',
                LocalDate.of(1954,1,15),0));
        forumUsers.add(new ForumUser(5, "user5", 'M',
                LocalDate.of(1999,3,30),0));
        forumUsers.add(new ForumUser(6, "user6", 'M',
                LocalDate.of(2008,3,3),4));
        forumUsers.add(new ForumUser(7, "user7", 'F',
                LocalDate.of(2008,3,3),4));
    }

    public List<ForumUser> getUserList() {
        return new ArrayList<>(forumUsers);
    }
}
