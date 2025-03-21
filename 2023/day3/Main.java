import java.io.*;
import java.util.ArrayList;

public class Main 
{
    static ArrayList<NumIndex> deltas = new ArrayList<NumIndex>();

    public static void main(String args[])
    {
        
        deltas.add(new NumIndex(-1,0)); //Up
        deltas.add(new NumIndex(-1,1)); //UpRight
        deltas.add(new NumIndex(0,1)); //Right
        deltas.add(new NumIndex(1,1)); //Downright
        deltas.add(new NumIndex(1,0));//Down
        deltas.add(new NumIndex(1,-1)); //DownLeft
        deltas.add(new NumIndex(0,-1)); //Left
        deltas.add(new NumIndex(-1,-1)); //UpLeft
        

        File file = new File("input.txt");
        Main go = new Main();
        ArrayList<NumIndex> numIndex = new ArrayList<NumIndex>();
        
        ArrayList<Number> numbers;
        String[] tempList = new String[140];
        char[][] list = new char[140][140];
        int count = 0;
        //Read File
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            do
            {
                if(count == 140)
                {
                    break;
                }

                tempList[count] = br.readLine();

                for(int i = 0; i < 140; i++)
                {
                    list[count][i] = tempList[count].charAt(i);
                }
                count++;

            }while(list[count - 1] != null);
            numbers = go.findNumbers(list);
            System.out.print(numbers.size());
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }

    public int findParts(char[][] list)
    {
        

        for(int i = 0; i < 140; i++)
        {
            for(int j = 0; j < 140; j++)
            {
                if(list[i][j] < 65 && list[i][j] > 34 && list[i][j] != '.') 
                {
                    if(list[i][j] < 58 && list[i][j] > 47)
                    {
                        
                    }
                    else
                    {

                        System.out.print(list[i][j]);
                    }
                    
                }
                
            }
            System.out.println();
        }

        return 0;
    }

    public ArrayList<Number> findNumbers(char[][] list)
    {
        
        int height = 140;
        int width = 140;
        ArrayList<Number> numbers = new ArrayList<>();
        boolean readingNumber = false;
        for(int i = 0; i < height; i++) 
        {
            Number newNumber = new Number();
            
            for(int j = 0; j < width; j++)
            {
                
                if(list[i][j] - '0' < 10)
                {
                    readingNumber = true;
                    newNumber.addDigits(new NumIndex(i, j));
                    
                }
                else if(readingNumber && list[i][j] - '0' >= 10)
                {
                    numbers.add(newNumber);
                    readingNumber = false;
                    newNumber = new Number();
                }
                if(list[i][j] - '0' < 10 && j == width - 1)
                {
                    numbers.add(newNumber);
                }
                
            }
            
        }
        return numbers;
    }

}
