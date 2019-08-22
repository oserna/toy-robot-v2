package com.rea.application;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static com.rea.application.Application.CommandType.RESET;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InitializationTests.class,
        CommandFactoryTests.class,
        RotationTests.class,
        MovementTests.class,
})
public class TestSuite {

    @After
    public void runAfterTestMethod() {
        RESET.execute(new String[]{});
    }

}
