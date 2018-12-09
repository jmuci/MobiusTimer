package com.jmucientes.mobius.mobiustimer.domain;

import com.spotify.mobius.Effects;
import com.spotify.mobius.Next;

import static com.spotify.mobius.Effects.effects;
import static com.spotify.mobius.Next.*;

public class TimerLogic {

    private TimerLogic() { }

    /*
     * Step 1.2
     * We change the signature so that it can tell us that an effect is supposed to happen. In Mobius, the Next<M, F> class
     * The int we have used to keep track of the current value of the counter is usually referred to as
     * the model object in Mobius, so we change that name too.
     *
     * The update function describes: "given a certain model and an event, what should happen next?"
     * The update function only declares what is supposed to occur, but it doesn't make it occur.
     * Details : https://github.com/spotify/mobius/wiki/Creating-a-loop
     */
    public static Next<Integer, Effect> update(int model, Event event) {
        switch (event) {
            case UP:
                return next(model + 1);

            case DOWN:
                if (model > 0) {
                    return next(model - 1);
                }
        }
        // No need to update the model, just dispatch effect
        return dispatch(effects(Effect.REPORT_ERROR_NEGATIVE));
    }
}
