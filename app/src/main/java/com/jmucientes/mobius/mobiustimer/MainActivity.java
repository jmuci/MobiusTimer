package com.jmucientes.mobius.mobiustimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jmucientes.mobius.mobiustimer.domain.Effect;
import com.jmucientes.mobius.mobiustimer.domain.Event;
import com.jmucientes.mobius.mobiustimer.domain.TimerLogic;
import com.jmucientes.mobius.mobiustimer.domain.TimerModel;
import com.jmucientes.mobius.mobiustimer.effecthandlers.TimerEffectHandler;
import com.spotify.mobius.Mobius;
import com.spotify.mobius.MobiusLoop;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    MobiusLoop<TimerModel, Event, Effect> mLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createMobiusLoop();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoop.dispatchEvent(Event.down());    // prints "1"
        mLoop.dispatchEvent(Event.down());    // prints "0"
        mLoop.dispatchEvent(Event.down());    // Prints error log msg
        mLoop.dispatchEvent(Event.up());      // prints "1"
        mLoop.dispatchEvent(Event.up());      // prints "2"
        mLoop.dispatchEvent(Event.down());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLoop.dispose();
    }

    private void createMobiusLoop() {
        mLoop = Mobius.loop(
                TimerLogic::update,
                TimerEffectHandler::effectHandler)
                .startFrom(TimerModel.create(2));

        // This observer will be triggered when the model (an int number) changes
        mLoop.observe(value -> Log.d(TAG, "Counter: " + value));

    }
}
