package com.testing;

import com.coding.demo.DataFilterer;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertTrue;

public class DataFiltererTest {

    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }

    @Test
    public void shouldReturnEmptyCollection_WhenDEResponseTimeShouldBeNotBiggerThan414() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "DE", 550).isEmpty());
    }

    @Test
    public void shouldReturnNotEmptyCollection_WhenUSResponseTimeAbove700() throws FileNotFoundException {
        assertTrue(!DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 700).isEmpty());
    }

    @Test
    public void shouldNotReturnEmptyCollection_WhenLoadingMultiLinesFile() throws FileNotFoundException {
        assertTrue(!DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")).isEmpty());
    }

    @Test
    public void shouldNotReturnEmptyCollection_WhenReadingGBFromMultiLinesFile() throws FileNotFoundException {
        assertTrue(!DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "GB").isEmpty());
    }

    @Test
    public void shouldReturnEmptyCollection_WhenLoadingZZCountrFromMultiLinesFiley() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "ZZ").isEmpty());
    }
}
