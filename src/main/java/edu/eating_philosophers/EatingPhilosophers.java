package edu.eating_philosophers;

public class EatingPhilosophers {

    private static final int DEFAULT_ROLLS_VALUE = 10;

    public static void main(String... args) {
        BambooStick[] sticks = createSticks(5);
        Philosopher[] philosophers = new Philosopher[sticks.length];

        bindSticksWithPhilosophers(sticks, philosophers);
        eatIt(philosophers);
    }

    public static boolean bindSticksWithPhilosophers(BambooStick[] sticks, Philosopher[] philosophers) {
        if (sticks.length != philosophers.length) {
            return false;
        }

        int l = sticks.length;
        Names[] names = Names.values();
        int n = 0; //names iterator
        int s = 0; //sticks iterator

        for (int i = 0; i < l; i++) {
            philosophers[i] = new Philosopher(
                    names[n++ % names.length].name(),
                    sticks[s % l],
                    sticks[++s % l],
                    DEFAULT_ROLLS_VALUE
            );
        }

        return true;
    }

    public static BambooStick[] createSticks(int size) {
        BambooStick[] sticks = new BambooStick[size];

        for (int i = 0; i < size; i++) {
            sticks[i] = new BambooStick(i);
        }

        return sticks;
    }

    public static void eatIt(Philosopher[] philosophers) {
        for (Philosopher p : philosophers) {
            new Thread(p).start();
        }
    }
}
