package com.kodilla.patterns.strategy.social;

import org.junit.Assert;
import org.junit.Test;

public class UserTestSuite {

    @Test
    public void testDefaultSharingStrategies() {
        //Given
        User millenial = new Millenials("Millenial");
        User yGeneration = new YGeneration("yGeneration");
        User zGeneration = new ZGeneration("zGeneration");
        //When
        String millenialShare = millenial.sharePost();
        String yGenerationShare = yGeneration.sharePost();
        String  zgenerationShare = zGeneration.sharePost();
        //Then
        Assert.assertEquals("Facebook", millenialShare);
        Assert.assertEquals("Snapchat", yGenerationShare);
        Assert.assertEquals("Twitter", zgenerationShare);
    }

    @Test
    public void testIndividualSharingStrategy() {
        //Given
        User millenial = new Millenials("Millenial");
        //When
        millenial.setSocialPublisher(new SnapchatPublisher());
        //Then
        Assert.assertEquals("Snapchat", millenial.sharePost());
    }
 }
