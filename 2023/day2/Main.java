import java.io.*;
import java.util.Scanner;



public class Main
{
    public static int countID = 0;
    public static int totalID = 0;
    public static int totalPower = 0;
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        File file = new File("input.txt");
        String tempString = "";
        Main go = new Main();
        int possibleGames = 0;
        
    


        try
        {
            
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            tempString = br.readLine();
            do
            {
                countID++;
                if(go.identifyCubes(tempString) == true)
                {
                    totalID += countID;
                    possibleGames++;
                }
                totalPower += go.powerOfCubes(tempString);
                tempString = br.readLine();

            }while(tempString != null);
            System.out.println(totalID);
            System.out.println(totalPower);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //Calcute possibleGames.
    public boolean identifyCubes(String tempString)
    {
        int maxRed = 12;
        int maxBlue = 14;
        int maxGreen = 13;
        int indexColor = 0;
        String numString = "";
        int numInteger = 0;
        int numTotal = 0;
        String[] colors = {"red", "blue","green"};
        StringBuilder sb = new StringBuilder(tempString);
        //Loop for all Red
        while(sb.indexOf(colors[0]) != -1) 
        { 
            indexColor = sb.indexOf(colors[0]);
            numString = sb.substring(indexColor-3, indexColor-1);
            //Check for Space before Number
            if(numString.indexOf(' ') != -1);
            {
                numString = numString.trim();
            }
            sb.delete(indexColor, indexColor+3);
            numInteger = Integer.parseInt(numString);
            if(numInteger > maxRed)
            {

                return false;
            }
        }
        while(sb.indexOf(colors[1]) != -1) 
        { 
            indexColor = sb.indexOf(colors[1]);
            numString = sb.substring(indexColor-3, indexColor-1);
            if(numString.indexOf(' ') != -1);
            {
                numString = numString.trim();
            }
            sb.delete(indexColor, indexColor+3);
            numInteger = Integer.parseInt(numString);
            if(numInteger > maxBlue)
            {
                return false;
            }
        }
        while(sb.indexOf(colors[2]) != -1) 
        { 
            indexColor = sb.indexOf(colors[2]);
            numString = sb.substring(indexColor-3, indexColor-1);
            if(numString.indexOf(' ') != -1);
            {
                numString = numString.trim();
            }
            sb.delete(indexColor, indexColor+3);
            numInteger = Integer.parseInt(numString);
            if(numInteger > maxGreen)
            {
                return false;
            }
        }


        return true;
    }
    //Mutiple minumim cubes together for possibleGames.
    public int powerOfCubes(String tempString)
    {
        int maxRed = 0;
        int maxBlue = 0;
        int maxGreen = 0;
        int indexColor = 0;
        String numString = "";
        int numInteger = 0;
        int numTotal = 0;
        String[] colors = {"red", "blue","green"};
        StringBuilder sb = new StringBuilder(tempString);
        //Loop for all Red
        while(sb.indexOf(colors[0]) != -1) 
        { 
            indexColor = sb.indexOf(colors[0]);
            numString = sb.substring(indexColor-3, indexColor-1);
            //Check for Space before Number
            if(numString.indexOf(' ') != -1);
            {
                numString = numString.trim();
            }
            sb.delete(indexColor, indexColor+3);
            numInteger = Integer.parseInt(numString);
            if(numInteger > maxRed)
            {
                maxRed = numInteger;
            }

        }
        while(sb.indexOf(colors[1]) != -1) 
        { 
            indexColor = sb.indexOf(colors[1]);
            numString = sb.substring(indexColor-3, indexColor-1);
            if(numString.indexOf(' ') != -1);
            {
                numString = numString.trim();
            }
            sb.delete(indexColor, indexColor+3);
            numInteger = Integer.parseInt(numString);
            if(numInteger > maxBlue)
            {
                maxBlue = numInteger;
            }
        }
        while(sb.indexOf(colors[2]) != -1) 
        { 
            indexColor = sb.indexOf(colors[2]);
            numString = sb.substring(indexColor-3, indexColor-1);
            if(numString.indexOf(' ') != -1);
            {
                numString = numString.trim();
            }
            sb.delete(indexColor, indexColor+3);
            numInteger = Integer.parseInt(numString);
            if(numInteger > maxGreen)
            {
                maxGreen = numInteger;
            }
        }

        System.out.println("GAME " + countID + ": " + maxRed + " " + maxBlue + " " + maxGreen);
        System.out.println("POWER: " + maxRed * maxBlue * maxGreen);
        return maxRed * maxBlue * maxGreen;
    }
   
}
