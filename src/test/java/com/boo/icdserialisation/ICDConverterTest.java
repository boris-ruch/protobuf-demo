package com.boo.icdserialisation;

import com.boo.icdserialisation.lona.LonaEvent;
import okio.ByteString;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ICDConverterTest {

    @Test
    void convertFrom_byte_to_java() throws IOException {

        // arrange
        ByteString icdValueEncoded = ByteString.decodeBase64("CgpUaW1lckV2ZW50EMPE7OEBGMupyyghzmvvVmSJ10E=");

        // act
        final LonaEvent lonaEvent = ICDConverter.convertFrom(icdValueEncoded.toByteArray());

        // assert
        assertThat(lonaEvent.name).isEqualTo("TimerEvent");
        assertThat(lonaEvent.gps_lat).isEqualTo(473637443);
        assertThat(lonaEvent.gps_lon).isEqualTo(85120203);
        assertThat(lonaEvent.server_timestamp).isEqualTo(1579520347.7409549);
    }

    @Test
    void testConvertFrom_java_to_byte() {

        // arrange
        LonaEvent timerEvent = new LonaEvent.Builder()
                .name("TimerEvent")
                .gps_lat(473637443)
                .gps_lon(85120203)
                .server_timestamp(1579520347.7409549)
                .build();

        // act
        final byte[] icdValue = ICDConverter.convertFrom(timerEvent);

        // assert
        final String byteString = ByteString.of(icdValue).base64();
        assertThat(byteString).isEqualTo("CgpUaW1lckV2ZW50EMPE7OEBGMupyyghzmvvVmSJ10E=");

    }
}