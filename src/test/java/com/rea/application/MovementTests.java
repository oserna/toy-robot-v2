package com.rea.application;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.rea.application.Application.CommandType.*;

public class MovementTests {


    @Test
    public void givenNorthMoveResultsInExpectedPosition() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,NORTH"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(1, tuple.getA().getY());
        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals("NORTH", tuple.getB().name());

    }

    @Test
    public void givenEastMoveResultsInExpectedPosition() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,EAST"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals(1, tuple.getA().getX());
        Assert.assertEquals("EAST", tuple.getB().name());

    }

    @Test
    public void givenSouthtMoveResultsInExpectedPosition() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "4,4,SOUTH"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(3, tuple.getA().getY());
        Assert.assertEquals(4, tuple.getA().getX());
        Assert.assertEquals("SOUTH", tuple.getB().name());

    }

    @Test
    public void givenWestMoveResultsInExpectedPosition() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "4,0,WEST"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals(3, tuple.getA().getX());
        Assert.assertEquals("WEST", tuple.getB().name());

    }

    @Test
    public void givenMoveInputResultsInMoveCommandExecution() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,EAST"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals(1, tuple.getA().getX());
        Assert.assertEquals("EAST", tuple.getB().name());

    }

    @Test
    public void givenMoveCommandsResultIsWithinHeightBoundaries() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "0,0,SOUTH"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals("SOUTH", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(0, tuple.getA().getY());
        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals("WEST", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(1, tuple.getA().getY());
        Assert.assertEquals(0, tuple.getA().getX());
        Assert.assertEquals("NORTH", tuple.getB().name());
    }

    @Test
    public void givenMoveCommandsResultIsWithinWidthBoundaries() {

        Application.Tuple<Application.Position, Application.Direction> tuple = PLACE.execute(new String[]{"PLACE", "4,4,NORTH"});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(4, tuple.getA().getY());
        Assert.assertEquals(4, tuple.getA().getX());
        Assert.assertEquals("NORTH", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(4, tuple.getA().getY());
        Assert.assertEquals(4, tuple.getA().getX());
        Assert.assertEquals("EAST", tuple.getB().name());

        tuple = RIGHT.execute(new String[]{});

        tuple = MOVE.execute(new String[]{});

        Assert.assertEquals(3, tuple.getA().getY());
        Assert.assertEquals(4, tuple.getA().getX());
        Assert.assertEquals("SOUTH", tuple.getB().name());

    }


    @After
    public void runAfterTestMethod() {
        RESET.execute(new String[]{});
    }

}
