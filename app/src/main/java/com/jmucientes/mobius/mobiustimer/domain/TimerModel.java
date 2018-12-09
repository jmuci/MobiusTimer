package com.jmucientes.mobius.mobiustimer.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class TimerModel {
    public abstract int counter();

    public TimerModel increase() {
        return create(counter() + 1);
    }

    public TimerModel decrease() {
        return create(counter() - 1);
    }

    public static TimerModel create(int counter) {
        return new AutoValue_TimerModel(counter);
    }
}
