import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day25FourDimensionalAdventureTest {

    @ParameterizedTest
    @CsvSource({"2, out/test/resources/day25-demo0.txt",
            "4, out/test/resources/day25-demo1.txt",
            "3, out/test/resources/day25-demo2.txt",
            "8, out/test/resources/day25-demo3.txt",
            "338, out/test/resources/day25.txt"})
    void numberOfConstellations(int expected, String fileName) throws IOException {
        assertEquals(expected, new Day25FourDimensionalAdventure(fileName).numberOfConstellations());
    }
}