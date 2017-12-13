package com.kodilla.patterns.singleton;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTestSuite {

    @Test
    public void testGetLastLog() {
        //Given

        //When
        String log = "This is a log entry";
        Logger logger = Logger.getInstance();
        logger.log(log);
        //Then
        Assert.assertEquals(log, logger.getLastLog());
    }

}
