package com.kodilla.testing.forum.statistics;

public class ForumStatisticsCalculator {

    Statistics statistics;
    int usersCount;
    int postsCount;
    int commentsCount;
    double postsPerUser;
    double commentsPerUser;
    double commentsPerPost;

    public ForumStatisticsCalculator(Statistics statistics) {
        this.statistics = statistics;
    }

    public void calculateAdvStatistics() {
        usersCount = statistics.usersNames().size(); // I assume the list cannot be null
        postsCount = statistics.postsCount();
        commentsCount = statistics.commentsCount();
        /* I will not go into questions whether it is possible to have posts or comments without users
         * or comments without posts. I assume it's possible and in that case some averages will be set
         * artificially to 0
         */
        if (usersCount == 0) {
            postsPerUser = 0;
            commentsPerUser = 0.0;
        } else {
            postsPerUser = (double)postsCount / (double)usersCount;
            commentsPerUser = (double)commentsCount / (double)usersCount;
        }
        if (postsCount == 0) {
            commentsPerPost = 0.0;
        } else {
            commentsPerPost = (double)commentsCount / (double)postsCount;
        }
    }

    public void showStatistics() {
        System.out.println("Number of users: " + usersCount);
        System.out.println("Number of posts: " + postsCount);
        System.out.println("Number of comments: " + commentsCount);
        System.out.println("Average number of posts per user: " + postsPerUser);
        System.out.println("Average number of comments per user: " + commentsCount);
        System.out.println("Average number of comments per post: " + commentsPerPost);
    }

    public double getPostsPerUser() {
        return postsPerUser;
    }

    public double getCommentsPerUser() {
        return commentsPerUser;
    }

    public double getCommentsPerPost() {
        return commentsPerPost;
    }
}
