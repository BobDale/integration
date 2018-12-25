package edu.eating_philosophers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class PhilosopherTest {

    @Test
    @DisplayName("Check created Philosopher object")
    public void testCreatingPhilosopher() {

        Philosopher philosopher = new Philosopher(
                "Me",
                new BambooStick(1),
                new BambooStick(2),
                5
        );

        assertAll(
                () -> assertEquals("Me", philosopher.getName()),
                () -> assertEquals(1, philosopher.getLeftStick().getNumber()),
                () -> assertEquals(2, philosopher.getRightStick().getNumber()),
                () -> assertEquals(5, philosopher.getRollItems())
        );
    }

    @Test
    @DisplayName("Check that sticks was locked")
    public void testRun() {

        BambooStick leftStick = new BambooStick(1);
        BambooStick rightStick = new BambooStick(2);
        BambooStick leftSpy = Mockito.spy(leftStick);
        BambooStick rightSpy = Mockito.spy(rightStick);
        int items = 5;

        Philosopher philosopher = new Philosopher(
          "Me",
          leftSpy,
          rightSpy,
          items
        );

        //when
        philosopher.run();

        //then
        Mockito.verify(leftSpy, Mockito.times(items)).tryLock();
        Mockito.verify(leftSpy, Mockito.times(items)).unlock();
        Mockito.verify(rightSpy, Mockito.times(items)).tryLock();
        Mockito.verify(rightSpy, Mockito.times(items)).unlock();
    }

    @Test
    @DisplayName("Check that philosopher ate all rolls")
    public void testRolls() {

        int items = 5;
        Philosopher philosopher = new Philosopher(
                "Me",
                new BambooStick(1),
                new BambooStick(2),
                items
        );

        assertEquals(5, philosopher.getRollItems());
        philosopher.run();
        assertEquals(0, philosopher.getRollItems());
    }

}