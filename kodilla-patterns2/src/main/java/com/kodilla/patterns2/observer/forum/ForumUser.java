package com.kodilla.patterns2.observer.forum;

import com.kodilla.patterns2.observer.Observer;

public class ForumUser implements Observer<ForumTopic> {
    private final String username;
    private int updateCount;

    public ForumUser(String username) {
        this.username = username;
    }

    @Override
    public void update(ForumTopic forumTopic) {
        System.out.println(username + ": New messages in topic " + forumTopic.getName() + "\n" +
            " (total: " + forumTopic.getMessages().size() + " messages");
        updateCount++;
    }

    @Override
    public void update(ForumTopic forumTopic, String message) {
        System.out.println(username + ": Messages from topic " + forumTopic.getName() + ": " + message + "\n" +
                " (total: " + forumTopic.getMessages().size() + " messages");
        updateCount++;
    }

    public String getUsername() {
        return username;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
