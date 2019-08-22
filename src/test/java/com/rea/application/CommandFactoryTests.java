package com.rea.application;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTests {

    @Test
    public void givenPlaceInputProperlyFormedResultsInPlaceCommand() {

        String placeInput = "PLACE 1,2,NORTH";

        String[] parts = placeInput.split(" ");

        Application.RobotCommand placeCommand = Application.CommandType.factory(parts[0]);

        Assert.assertEquals("PLACE", placeCommand.toString());

    }

    @Test
    public void givenUnrecognizedTextInputResultsInReportCommand() {

        String placeInput = "MALFORMED";

        String[] parts = placeInput.split(" ");

        Application.RobotCommand command = Application.CommandType.factory(parts[0]);

        Assert.assertEquals("REPORT", command.toString());

    }

    @Test
    public void givenEmptyTextInputResultsInReportCommand() {

        Application.RobotCommand  command = Application.CommandType.factory(null);

        Assert.assertEquals("REPORT", command.toString());

        Application.RobotCommand placeCommand = Application.CommandType.factory(" ");

        Assert.assertEquals("REPORT", placeCommand.toString());

    }

    @Test
    public void givenMoveInputProperlyFormedResultsInMoveCommand() {

        String placeInput = "MOVE";

        String[] parts = placeInput.split(" ");

        Application.RobotCommand command = Application.CommandType.factory(parts[0]);

        Assert.assertEquals("MOVE", command.toString());

    }
}
