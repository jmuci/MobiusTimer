package com.jmucientes.mobius.mobiustimer.domain;

import android.support.annotation.NonNull;

import com.spotify.mobius.First;
import com.spotify.mobius.Next;

import javax.annotation.Nonnull;

import static com.spotify.mobius.Effects.effects;
import static com.spotify.mobius.First.first;
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
    @NonNull
    public static Next<TimerModel, Effect> update(TimerModel model, Event event) {
        return event.map(
                up -> next(model.increase()),

                down -> {
                    if (model.counter() > 0) {
                        return next(model.decrease());
                    }
                    return dispatch(effects(Effect.reportErrorNegative()));
                }
        );
    }

}
