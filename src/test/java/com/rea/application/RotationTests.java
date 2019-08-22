package com.rea.application;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.rea.application.Application.CommandType.*;


public class RotationTests {

    @Test
    public void givenRightResultsInRightCommandExecution() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,NORTH"});

        tuple = RIGHT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("EAST", tuple.getB().name());
    }


    @Test
    public void givenLeftResultsInLeftCommandExecution() {


        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,NORTH"});

        tuple = LEFT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("WEST", tuple.getB().name());
    }

    @Test
    public void givenSubsequentLeftCommandsResultInProperLeftRotation() {


        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,NORTH"});

        tuple = LEFT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("WEST", tuple.getB().name());

        tuple = LEFT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("SOUTH", tuple.getB().name());

        tuple = LEFT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("EAST", tuple.getB().name());

        tuple = LEFT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("NORTH", tuple.getB().name());
    }

    @Test
    public void givenSubsequentRightCommandsResultInProperRightRotation() {


        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,NORTH"});

        tuple = RIGHT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("EAST", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("SOUTH", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("WEST", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals("NORTH", tuple.getB().name());
    }

    @After
    public void runAfterTestMethod() {
        RESET.execute(new String[]{});
    }


}
