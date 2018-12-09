package com.jmucientes.mobius.mobiustimer.effecthandlers;

import android.util.Log;

import com.jmucientes.mobius.mobiustimer.domain.Effect;
import com.jmucientes.mobius.mobiustimer.domain.Event;
import com.spotify.mobius.Connection;
import com.spotify.mobius.functions.Consumer;


public class TimerEffectHandler {
    private static final String TAG = TimerEffectHandler.class.getName();

    public static Connection<Effect> effectHandler(Consumer<Event> eventConsumer) {

        return new Connection<Effect>() {
            @Override
            public void accept(Effect effect) {
                if (effect == Effect.REPORT_ERROR_NEGATIVE) {
                    Log.e(TAG, "Tried to subtract 1, but counter was 0.");
                }
            }

            @Override
            public void dispose() {
                // ...
            }
        };
    }
}
