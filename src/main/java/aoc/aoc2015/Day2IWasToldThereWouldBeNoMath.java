package aoc.aoc2015;

import java.util.ArrayList;
import java.util.List;

public class Day2IWasToldThereWouldBeNoMath {
    int getSquareFeet(List<String> inputLines) throws Exception {
        int totalArea = 0;
        for (String line : inputLines) {
            String[] parts = line.split("x");
            int length = Integer.parseInt(parts[0]);
            int width = Integer.parseInt(parts[1]);
            int height = Integer.parseInt(parts[2]);

            int side1 = 2 * length * width;
            int side2 = 2 * width * height;
            int side3 = 2 * height * length;
            List<Integer> sides = new ArrayList<>();
            sides.add(side1);
            sides.add(side2);
            sides.add(side3);
            int smallest = sides.stream().mapToInt(i -> i).min().orElseThrow(Exception::new);
            sides.add(smallest/2);

            totalArea += sides.stream().mapToInt(i -> i).sum();
        }
        return totalArea;
    }
}
