
package de.berlin.jobst.bowling;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MassScoreTest {

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void testNumber(String str, int[] expected) {
        assertArrayEquals(expected, Game.transformSymbols(str));
    }

    private static Stream<Arguments> testDataProvider() {
        return Stream.of(
                arguments("-", new int[]{0}),
                arguments("1", new int[]{1}),
                arguments("2", new int[]{2}),
                arguments("X", new int[]{10}),
                arguments("-/", new int[]{0, 10}),
                arguments("5/", new int[]{5, 5}),
                arguments("87", new int[]{8, 7}),
                arguments("XX", new int[]{10, 10})
        );
    }

    @ParameterizedTest
    @CsvSource({"'XXXXXXXXXXXX','300'", "'9-9-9-9-9-9-9-9-9-9-','90'", "'5/5/5/5/5/5/5/5/5/5/5','150'", "'X7/9-X-88/-6XXX81', '167'"})
    void testWithCsvSource(String rollsAsString, String score) {
        int[] rolls = Game.transformSymbols(rollsAsString);
        assertEquals(Game.getScore(rolls), Integer.parseInt(score));
    }

    @ParameterizedTest
    @Disabled // the given test data file is unfortunately erroneous - maybe later fixed
    @CsvFileSource(resources = "/bowling_data_5000.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String rollsAsString, String score) {
        int[] rolls = Game.transformSymbols(rollsAsString);
        assertEquals(Game.getScore(rolls), Integer.parseInt(score));
    }
}

