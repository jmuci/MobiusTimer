package com.jmucientes.mobius.mobiustimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jmucientes.mobius.mobiustimer.domain.Effect;
import com.jmucientes.mobius.mobiustimer.domain.Event;
import com.jmucientes.mobius.mobiustimer.domain.TimerLogic;
import com.jmucientes.mobius.mobiustimer.effecthandlers.TimerEffectHandler;
import com.spotify.mobius.Mobius;
import com.spotify.mobius.MobiusLoop;
import com.spotify.mobius.Next;
import com.spotify.mobius.Update;
import com.spotify.mobius.extras.MobiusExtras;
import com.spotify.mobius.functions.Consumer;

import javax.annotation.Nonnull;

import static com.jmucientes.mobius.mobiustimer.domain.Event.DOWN;
import static com.jmucientes.mobius.mobiustimer.domain.Event.UP;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private MobiusLoop<Integer, Event, ?> mLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createMobiusLoop();
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoop.dispatchEvent(DOWN);    // prints "1"
        mLoop.dispatchEvent(DOWN);    // prints "0"
        mLoop.dispatchEvent(DOWN);    // prints "0"
        mLoop.dispatchEvent(UP);      // prints "1"
        mLoop.dispatchEvent(UP);      // prints "2"
        mLoop.dispatchEvent(DOWN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLoop.dispose();
    }

    private void createMobiusLoop() {
        MobiusLoop<Integer, Event, Effect> loop =
                Mobius.loop((model, event) -> TimerLogic.update(model, event), TimerEffectHandler::effectHandler).startFrom(2);

        // This observer will be triggered when the model (an int number) changes
        mLoop.observe(value -> Log.d(TAG, "Counter: " + value));

    }
}
