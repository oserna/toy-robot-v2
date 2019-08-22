package com.rea.application;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.rea.application.Application.CommandType.PLACE;
import static com.rea.application.Application.CommandType.RESET;

public class InitializationTests {

    @Test
    public void givenPlaceInputProperlyFormedResultsInPlaceCommandExecution() {

        Application.Tuple<Application.Position, Application.Direction> tuple =
                PLACE.execute(new String[]{"PLACE", "1,2,NORTH"});

        Assert.assertEquals(1, tuple.getA().getX());
        Assert.assertEquals(2, tuple.getA().getY());
        Assert.assertEquals("NORTH", tuple.getB().name());

    }

    @Test
    public void givenAnotherPlaceResultsInNoPlaceCommandExecution() {

        Application.Tuple<Application.Position, Application.Direction> tuple =
                PLACE.execute(new String[]{"PLACE", "1,2,NORTH"});

        Assert.assertEquals(1, tuple.getA().getX());
        Assert.assertEquals(2, tuple.getA().getY());
        Assert.assertEquals("NORTH", tuple.getB().name());

        tuple = PLACE.execute(new String[]{"PLACE", "0,0,NORTH"});

        Assert.assertEquals(1, tuple.getA().getX());
        Assert.assertEquals(2, tuple.getA().getY());
        Assert.assertEquals("NORTH", tuple.getB().name());

    }

    @Test
    public void givenPlaceNotProperlyFormedResultsInNoPlaceCommandExecution() {


        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "1"});

        Assert.assertNull(tuple);

        tuple = PLACE.execute(new String[]{"PLACE",});

        Assert.assertNull(tuple);

        tuple = PLACE.execute(new String[]{"PLACE", "1,2,WRONG"});

        Assert.assertNull(tuple);

        tuple = PLACE.execute(new String[]{"PLACE", "1,WRONG"});

        Assert.assertNull(tuple);

        tuple = PLACE.execute(new String[]{"PLACE", "WRONG"});

        Assert.assertNull(tuple);

    }

    @After
    public void runAfterTestMethod() {
        RESET.execute(new String[]{});
    }

}
