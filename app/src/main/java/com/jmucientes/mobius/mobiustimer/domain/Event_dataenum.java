package com.jmucientes.mobius.mobiustimer.domain;

import com.spotify.dataenum.DataEnum;
import com.spotify.dataenum.dataenum_case;

@DataEnum
public interface Event_dataenum {
    dataenum_case Up();
    dataenum_case Down();
}
