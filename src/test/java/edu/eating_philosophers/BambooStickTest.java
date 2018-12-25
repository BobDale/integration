package edu.eating_philosophers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BambooStickTest {

    @Test
    public void testCreatingStick() {
        int n = 5;
        BambooStick stick = new BambooStick(n);

        assertAll(
                () -> assertNotNull(stick),
                () -> assertEquals(n, stick.getNumber())
        );
    }

//    @Test
//    public void testFail() {
//        BambooStick stick = null;
//        assertNotNull(stick);
//    }

    @Test
    public void testFail() {
        String state = System.getProperty("makeTestFail", "false");

        if (Boolean.valueOf(state)) {
            fail("property -DmakeTestFail sets in 'true'");
        } else {
            assertNull(null);
        }
    }

}