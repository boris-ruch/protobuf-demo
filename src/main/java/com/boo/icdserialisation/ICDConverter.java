package com.boo.icdserialisation;

import com.boo.icdserialisation.lona.LonaEvent;

import java.io.IOException;

public class ICDConverter {

    public static LonaEvent convertFrom(final byte[] icdValue) throws IOException {
        return LonaEvent.ADAPTER.decode(icdValue);
    }

    public static byte[] convertFrom(final LonaEvent lonaEvent) {
        return LonaEvent.ADAPTER.encode(lonaEvent);
    }
}
