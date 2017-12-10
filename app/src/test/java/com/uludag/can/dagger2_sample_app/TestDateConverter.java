package com.uludag.can.dagger2_sample_app;

import com.uludag.can.dagger2_sample_app.utils.DateConverter;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestDateConverter {

    @Test
    public void testDateConverter_dateConversionReturnCorrectFormat() {

        String givenDateString = "2017-10-26T14:00:00Z";
        String expectedTimeString = "Thu, 26 Oct 2017 02:00";

        assertEquals(expectedTimeString, DateConverter.formatNewsApiDate(givenDateString));

    }

}
