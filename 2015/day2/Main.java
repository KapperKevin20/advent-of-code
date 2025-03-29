import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    //A record creats constant objects/immutable, meaing the variables are final/unchangeable.
    record Box(int length, int width, int height)
    {
        int getSurfaceArea()
        {
            int surfaceArea = 2*length*width + 2*width*height + 2*length*height;
            return surfaceArea;
        }
        int getSmallestArea()
        {
            int SmallestArea = length*width;
            if(SmallestArea > width*height)
            {
                SmallestArea = width*height;
            }
            if(SmallestArea > length*height)
            {
                SmallestArea = length*height;
            }

            return SmallestArea;
        }
        int getSmallestPerimeter()
        {
            int smallestPerimeter = length + length + width + width;
            if (smallestPerimeter > 2*width + 2*height)
            {
                smallestPerimeter = 2*width + 2*height;
            }
            if(smallestPerimeter > 2*length + 2*height)
            {
                smallestPerimeter = 2*length + 2*height;
            }
            return smallestPerimeter;
        }
        int getVolume()
        {
            int volume = length * width * height;
            return volume;
        }
    }
    public static void main(String[] args) throws Exception
    {
        ArrayList<Box> boxes = new ArrayList<>();
        int totalWrapping = 0;
        int totalRibbon = 0;
        try
        {
            String tempString;
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                String[] measurements;
                tempString = sc.nextLine();
                measurements = tempString.split("x");
                int l = Integer.parseInt(measurements[0]);
                int w = Integer.parseInt(measurements[1]);
                int h = Integer.parseInt(measurements[2]);
                boxes.add(new Box(l, w, h));
                
            }
            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < boxes.size(); i++)
        {
            totalWrapping += (boxes.get(i)).getSurfaceArea() + (boxes.get(i).getSmallestArea());
        }
        System.out.println(totalWrapping);
        for(int i = 0; i < boxes.size(); i++)
        {
            totalRibbon += (boxes.get(i)).getVolume() + (boxes.get(i).getSmallestPerimeter());
        }
        System.out.println(totalRibbon);
    }
}
