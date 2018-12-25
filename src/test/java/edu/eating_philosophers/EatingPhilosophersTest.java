package edu.eating_philosophers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EatingPhilosophersTest {

    @Test
    @DisplayName("Test success binding")
    public void test_setSticksToPhilosophers() {
        int size = 3;
        BambooStick[] sticks = EatingPhilosophers.createSticks(size);
        Philosopher[] ps = new Philosopher[size];

        assertTrue(EatingPhilosophers.bindSticksWithPhilosophers(sticks, ps));
        assertAll(
                () -> assertEquals(size, ps.length),
                () -> assertEquals(10, ps[0].getRollItems()),
                () -> assertEquals(10, ps[1].getRollItems()),
                () -> assertEquals(10, ps[2].getRollItems()),
                () -> assertEquals(sticks[0].getNumber(), ps[0].getLeftStick().getNumber()),
                () -> assertEquals(sticks[1].getNumber(), ps[0].getRightStick().getNumber()),
                () -> assertEquals(sticks[1].getNumber(), ps[1].getLeftStick().getNumber()),
                () -> assertEquals(sticks[2].getNumber(), ps[1].getRightStick().getNumber()),
                () -> assertEquals(sticks[2].getNumber(), ps[2].getLeftStick().getNumber()),
                () -> assertEquals(sticks[0].getNumber(), ps[2].getRightStick().getNumber())
        );
    }

    @Test
    @DisplayName("Test no binding")
    public void testWrongBinding() {
        BambooStick[] sticks = new BambooStick[3];
        Philosopher[] philosophers = new Philosopher[4];

        assertFalse(EatingPhilosophers.bindSticksWithPhilosophers(sticks, philosophers));
    }

    @Test
    @DisplayName("Test size of created array")
    void test_createSticks() {
        int size = 5;
        BambooStick[] sticks = EatingPhilosophers.createSticks(size);

        assertEquals(size, sticks.length);
    }

}