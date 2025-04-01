import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main 
{

    static Map <String, Point> deltas = Map.ofEntries(
        Map.entry("^", new Point(-1, 0)),
        Map.entry("v", new Point(1, 0)),
        Map.entry(">", new Point(0,1)),
        Map.entry("<", new Point(0,-1))
    );
    record Point(int row, int col)
    {
        Point add(Point other)
        {
            int new_row = row + other.row;
            int new_col = col + other.col;
            return new Point(new_row, new_col);
        }
    }

    public static void main(String[] args) throws Exception
    {
        String[] instructions;
        ArrayList<String> instructionsList = new ArrayList<>();
        try
        {
            
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                instructions = sc.nextLine().split("");
                for(int i = 0; i < instructions.length; i++)
                {
                    instructionsList.add(instructions[i]);
                }
            }

            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Map <Point, Boolean> houses = new HashMap<>();
        Map <Point, Boolean> housesEvil = new HashMap<>();
        getHouses(instructionsList, houses);
        getHousesEvil(instructionsList, housesEvil);
        System.out.println(houses.entrySet().size());
        System.out.println(housesEvil.entrySet().size());

    }
    public static void getHouses(List<String> instructionList, Map<Point, Boolean> houses)
    {
        Point currentPoint = new Point(0,0);
        houses.put(currentPoint, true);
        for(String direction: instructionList)
        {
            currentPoint = currentPoint.add(deltas.get(direction));
            
            if(houses.containsKey(currentPoint))
            {
                
            }
            else
            {
                houses.put(currentPoint, true);
            }

        }

    }
    public static void getHousesEvil(List<String> instructionList, Map<Point, Boolean> houses)
    {
        Point santaCurrentPoint = new Point(0, 0);
        Point roboSantaCurrentPoint = new Point(0, 0);
        houses.put(santaCurrentPoint, true);
        for(int i = 0; i < instructionList.size(); i++)
        {
            String direction = instructionList.get(i);
            if(i % 2 == 0)
            {
                santaCurrentPoint = santaCurrentPoint.add(deltas.get(direction));
            }
            else
            {
                roboSantaCurrentPoint = roboSantaCurrentPoint.add(deltas.get(direction));
            }

            if(!houses.containsKey(santaCurrentPoint))
            {
                houses.put(santaCurrentPoint, true);
            }
            if(!houses.containsKey(roboSantaCurrentPoint))
            {
                houses.put(roboSantaCurrentPoint, true);
            }
        }

    }
}
