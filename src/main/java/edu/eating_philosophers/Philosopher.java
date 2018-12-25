package edu.eating_philosophers;

import org.apache.log4j.Logger;

public class Philosopher implements Runnable {

    private final Logger LOGGER = Logger.getLogger(Philosopher.class);

    private int rollItems;

    private String name;
    private BambooStick leftStick;
    private BambooStick rightStick;

    public Philosopher(String name, BambooStick leftStick, BambooStick rightStick, int rollItems) {
        this.name = name;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.rollItems = rollItems;
    }

    @Override
    public void run() {
        int eatingTimeout = 100;
        int thinkingTimeout = 50;
        try {
            while (rollItems > 0) {
                if (leftStick.tryLock()) {
                    if (rightStick.tryLock()) {

                        LOGGER.debug(name + " eating now");

                        Thread.sleep(eatingTimeout);
                        rollItems--;

                        LOGGER.info(name + " stop eating. Stay " + rollItems + " rolls");

                        rightStick.unlock();
                    } else {

                        LOGGER.debug(name + " still thinking. Stay " + rollItems + " rolls");

                        Thread.sleep(thinkingTimeout);
                    }
                    leftStick.unlock();
                } else {

                    LOGGER.debug(name + " still thinking. Stay " + rollItems + " rolls");

                    Thread.sleep(thinkingTimeout);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (leftStick.isLocked()) leftStick.unlock();
            if (rightStick.isLocked()) rightStick.unlock();
        }

        LOGGER.info(name.toUpperCase() + " ATE ALL ROLLS");
    }

    public int getRollItems() {
        return rollItems;
    }

    public String getName() {
        return name;
    }

    public BambooStick getLeftStick() {
        return leftStick;
    }

    public BambooStick getRightStick() {
        return rightStick;
    }

    @Override
    public String toString() {
        return "Philosopher " + name
             + " with stick number " + leftStick.getNumber() + " in left hand"
             + " and stick number " + rightStick.getNumber() + " in right hand";
    }
}
