package edu.eating_philosophers;

import java.util.concurrent.locks.ReentrantLock;

public class BambooStick extends ReentrantLock {

    private int number;

    public BambooStick(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "BambooStick with number " + number;
    }
}
