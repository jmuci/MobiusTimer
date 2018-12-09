package com.jmucientes.mobius.mobiustimer.domain;

public class TimerLogic {

    private TimerLogic() { }

    public static int update(int counter, Event event) {
        switch (event) {
            case UP:
                return counter + 1;

            case DOWN:
                if (counter > 0) {
                    return counter - 1;
                }
        }
        return counter;
    }
}
