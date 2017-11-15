package com.kodilla.testing.forum.statistics;

import org.junit.*;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticsTestSuite {

    private static int testCount;

    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Start of all tests"); //DLACZEGO TO SIĘ POJAWIA NA KOŃCU?
    }

    @AfterClass
    public static void afterAllTests() {
        System.out.println("All tests finished");
    }

    @Before
    public void beforeTest() {
        System.out.println("Executing test " + testCount);
        testCount++;
    }

    private ForumStatisticsCalculator mockedCalculator(String[] usersNames, int postsCount, int commentsCount) {
        Statistics statisticsMock = mock(Statistics.class);
        when(statisticsMock.postsCount()).thenReturn(postsCount);
        when(statisticsMock.commentsCount()).thenReturn(commentsCount);
        when(statisticsMock.usersNames()).thenReturn(Arrays.asList(usersNames));
        ForumStatisticsCalculator calculator = new ForumStatisticsCalculator(statisticsMock);
        calculator.calculateAdvStatistics();
        return calculator;
    }

    @Test
    public void testNoPosts() {
        //Given
        ForumStatisticsCalculator calculator = mockedCalculator(new String[]{"Jan Nowak", "Katarzyna Kowalska"},
                0, 5);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(0.0, postsPerUser, 0.0);
        Assert.assertEquals(2.5, commentsPerUser, 0.0);
        Assert.assertEquals(0.0, commentsPerPost, 0.0);
    }

    @Test
    public void test1000Posts() {
        //Given
        ForumStatisticsCalculator calculator = mockedCalculator(new String[]{"Jan Nowak", "Katarzyna Kowalska"},
                1000, 125);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(500.0, postsPerUser, 0.0);
        Assert.assertEquals(62.5, commentsPerUser, 0.0);
        Assert.assertEquals(0.125, commentsPerPost, 0.0);
    }

    @Test
    public void testNoComments() {
        //Given
        ForumStatisticsCalculator calculator = mockedCalculator(new String[]{"Jan Nowak", "Katarzyna Kowalska"},
                1000, 0);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(500.0, postsPerUser, 0.0);
        Assert.assertEquals(0.0, commentsPerUser, 0.0);
        Assert.assertEquals(0.0, commentsPerPost, 0.0);
    }

    @Test
    public void testFewerCommentsThanPosts() {
        //Given
        ForumStatisticsCalculator calculator = mockedCalculator(new String[]{"Jan Nowak", "Katarzyna Kowalska"},
                1000, 2);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(500.0, postsPerUser, 0.0);
        Assert.assertEquals(1.0, commentsPerUser, 0.0);
        Assert.assertEquals(0.002, commentsPerPost, 0.0);
    }

    @Test
    public void testMoreCommentsThanPosts() {
        //Given
        ForumStatisticsCalculator calculator = mockedCalculator(new String[]{"Jan Nowak", "Katarzyna Kowalska"},
                4, 1000);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(2.0, postsPerUser, 0.0);
        Assert.assertEquals(500.0, commentsPerUser, 0.0);
        Assert.assertEquals(250.0, commentsPerPost, 0.0);
    }

    @Test
    public void testNoUsers() {
        //Given
        ForumStatisticsCalculator calculator = mockedCalculator(new String[]{},
                123, 123);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(0.0, postsPerUser, 0.0);
        Assert.assertEquals(0.0, commentsPerUser, 0.0);
        Assert.assertEquals(1.0, commentsPerPost, 0.0);
    }

    @Test
    public void test100Users() {
        //Given
        String[] users = new String[100];
        for(int i=0; i<users.length; i++) {
            users[i] = "User" + i;
        }
        ForumStatisticsCalculator calculator = mockedCalculator(users,
                50, 25);
        //When
        double postsPerUser = calculator.getPostsPerUser();
        double commentsPerUser = calculator.getCommentsPerUser();
        double commentsPerPost = calculator.getCommentsPerPost();
        //Then
        Assert.assertEquals(0.5, postsPerUser, 0.0);
        Assert.assertEquals(0.25, commentsPerUser, 0.0);
        Assert.assertEquals(0.5, commentsPerPost, 0.0);
    }

}


