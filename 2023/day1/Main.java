import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        File file = new File("input.txt");
        int totalVal = 0;
        Main go = new Main();
        int counter = 0;


        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String tempString = "";
            tempString = br.readLine(); 
            do
			{
                counter++; 
                tempString = go.convertReligion(tempString);
                System.out.println(counter +":" + tempString);
                totalVal += go.locateValues(tempString);
               
				tempString = br.readLine();
				

			}while(tempString != null);
            System.out.println(totalVal);
        }

        catch(IOException e)
		{
			e.printStackTrace();
		}
        
    }
    public int locateValues(String temp)
    {
        int num = 0;
        String numberTemp = "";
        String numberFinal = "";
        char chr;
        for(int i =0; i < temp.length(); i++)
        {
            chr = temp.charAt(i);
            if(Character.isDigit(chr))
            {
                numberTemp = numberTemp + chr;
            }
            
        }
        numberFinal = numberFinal + numberTemp.charAt(0);
        numberFinal = numberFinal + numberTemp.charAt(numberTemp.length()-1);

        num = Integer.parseInt(numberFinal);
        return num;
    }
    //convert the found words into numbers
    public String convertReligion(String temp)
    {
        int index = 0;
        StringBuilder sb = new StringBuilder(temp);
        String[] arr = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] arrNums = {1,2,3,4,5,6,7,8,9};
        for(int i = 0; i < arr.length; i++)
        {
            
            while(sb.indexOf(arr[i]) != -1)
            {

                index = sb.indexOf(arr[i]);
                sb.delete(index+1, arr[i].length()-1+index);
                sb.insert(index+1, arrNums[i]);
                
                
                
            }
        }

        return sb.toString();
    }
}