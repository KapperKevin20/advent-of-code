import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {	
	
    private static final List<Point> DELTAS = Arrays.asList(
        new Point(-1, 0), // UP 
        new Point(-1, 1), // UP RIGHT
        new Point(0, 1),  // RIGHT
        new Point(1, 1),  // DOWN RIGHT
        new Point(1, 0),  // DOWN
        new Point(1, -1), // DOWN LEFT
        new Point(0, -1), // LEFT
        new Point(-1, -1) // UP LEFT
    );

	public static void main(String[] args) throws Exception {
        
        // Reading input...
        List<List<String>> grid = new ArrayList<>(); // Declare an empty 2D List object
        int sumPartNumbers = 0;
        int sumGearRatios = 0;
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> grid_row = new ArrayList<>(); // New empty row for grid
				String[] splitLine = line.split(""); // Split every symbol into its own string

                for (String s : splitLine) { // Add symbols to the new grid row (enhanced for-loop)
                    grid_row.add(s);
                }

                grid.add(grid_row);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        // Parsing numbers...
        List<Number> partNumbers = getPartNumbers(grid);
        List<Point> gears = getGears(grid);
        for(Number num : partNumbers)
        {
            sumPartNumbers += num.toInt();
        }
        System.out.println("Part 1: "+sumPartNumbers);
        for(Point gear : gears)
        {
            sumGearRatios += getGearRatio(gear, partNumbers);
        }
        System.err.println("Part 2: "+sumGearRatios);

	}

    // Returns all Numbers found in a grid. A Number is composed of consecutive Digits (left to right)
    public static List<Number> getNumbers(List<List<String>> grid) {
        List<Number> numbers = new ArrayList<>();
        
        int height = grid.size(); // Number of rows
        int width = grid.get(0).size(); // Number of columns
        
        for (int i = 0; i < height; i++) {
            Number newNumber = new Number(); 
            boolean readingNumber = false; // Will be true if the previous symbol in the row was an integer
            for (int j = 0; j < width; j++) {
                String symbol = grid.get(i).get(j);

                if (symbol.matches("\\d{1}")) { // If symbol is a single-digit integer
                    readingNumber = true;
                    newNumber.addDigit(new Digit(new Point(i, j), symbol));

                    // If we are on a digit at the end of a row, add the newNumber to the numbers list
                    if (j == width - 1) {
                        numbers.add(newNumber);
                    }

                } else if (readingNumber) { // Add newNumber to the numbers list if we passed its last digit
                    readingNumber = false;
                    numbers.add(newNumber);
                    newNumber = new Number();
                }
            }
        }

        // Set the parentNumber field for each digit
        for (Number num : numbers) {
            for (Digit digit : num.getDigits()) {
                digit.setParentNumber(num);
            }
        }

        return numbers;
    }

    // Returns a list of Numbers which have at least one Digit adjacent to a non-numeric symbol (except ".")
    public static List<Number> getPartNumbers(List<List<String>> grid) {
        List<Number> partNumbers = new ArrayList<>();
        List<Number> numbers = getNumbers(grid);
        int height = grid.size();
        int width = grid.get(0).size();

        for(Number num : numbers)
        {
            digitLoop:
            for(Digit digit : num.getDigits())
            {
                for(Point deltaPoint : DELTAS)
                {
                    Point adjPoint = (digit.getPosition()).add(deltaPoint);
                    if(adjPoint.getRow() < 0 || adjPoint.getRow() >= height || adjPoint.getColumn() < 0 || adjPoint.getColumn() >= width)
                    {
                        continue;
                    }
                    String symbol = grid.get(adjPoint.getRow()).get(adjPoint.getColumn());
                    if(!symbol.matches("[\\d.]"))
                    {
                        partNumbers.add(num);
                        break digitLoop;
                    }

                }
            }
        }
        
        return partNumbers;
    }
    public static List<Point> getGears(List<List<String>> grid)
    {
        List<Point> gears = new ArrayList<>();
        int height = grid.size();
        int width = grid.get(0).size();
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if((grid.get(i).get(j)).equals("*"))
                {
                    gears.add(new Point(i, j));
                }
            }
        }
        return gears;
    }
    public static int getGearRatio(Point gear, List<Number> partNumbers)
    {
        int count = 0;
        List<Number> adjNumbers = new ArrayList<>();
        for(Number num : partNumbers)
        {
            digitLoop:
            for(Digit digit : num.getDigits())
            {
                for(Point deltaPoint : DELTAS)
                {
                    Point adjPoint = gear.add(deltaPoint);
                    Point digitPoint = digit.getPosition();
                    if(adjPoint.getRow() == digitPoint.getRow() && adjPoint.getColumn() == digitPoint.getColumn())
                    {
                        count++;
                        adjNumbers.add(num);
                        if(count > 2)
                        {
                            return 0;
                        }
                        break digitLoop;
                    }
                    
                }
            }
        }
      
        if(count < 2)
        {
            return 0;
        }
        return (adjNumbers.get(0)).toInt() * (adjNumbers.get(1)).toInt();
    }
}