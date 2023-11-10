package com.example.nottwitter;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DumbPasswordEncoderTest {

    @Test
    public void encode() {
        DumbPasswordEncoder encoder = new DumbPasswordEncoder();

        assertEquals("secret: 'mypwd'", encoder.encode("mypwd"));
        Assert.assertThat(encoder.encode("mypwd"), Matchers.containsString("mypwd"));
    }
}