
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    

    public static void main(String[] args) throws Exception
    {
        ArrayList<Cards> cards = new ArrayList<>();
        try
        {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                Cards newCard = new Cards();
                newCard.addContents(line);
                cards.add(newCard);
            }
            sc.close();

        }
        catch(FileNotFoundException e)
        {
        
            e.printStackTrace();
        }
        System.out.print(getPoints(cards));
        System.out.print("Total Scratch Cards: " + getTotalScratchCards(cards));
    }
    public static int getPoints(ArrayList<Cards> card)
    {
        int count = 0;
        double power = 0;
        int finalPower = 0;
        for(Cards c : card)
        {
            //Split method is trash, leaves empty string if space is in front of string.
            
            String[] winningNumbers = ((c.getWinningNumbers().trim())).split("\\s+");
            String[] listingNumbers = ((c.getListedNumbers().trim())).split("\\s+");

            for(int i = 0; i < winningNumbers.length; i++)
            {
                for(int j = 0; j < listingNumbers.length; j++)
                {
                    if(winningNumbers[i].compareTo(listingNumbers[j]) == 0)
                    {
                        count++;
                    }
                    
                }
            }
            if(count != 0)
            {
                c.setMatches(count);
                finalPower += getPower(2, count-1);
                count = 0;
            }
            
        }
        return (int)finalPower;
    }
    public static int getPower(int base, int n)
    {
        int result = 1;
        for(int i = 0; i < n; i++)
        {
            result *= base;
        }
        return result;
    }
    public static int getTotalScratchCards(ArrayList<Cards> card)
    {
        int count = 0;
        int[] cardCounts = new int[card.size()];
        for(int i = 0; i < cardCounts.length; i++)
        {
            cardCounts[i] = 1;
        }
        for(int i = 0; i < card.size(); i++)
        {
            for(int j = 0; j < (card.get(i)).getMatches(); j++)
            {
                if(i + j + 1 < cardCounts.length);
                {     
                    cardCounts[i + j + 1] = cardCounts[i + j + 1] + cardCounts[i];
                }
            }
        }
        for (int i = 0; i < cardCounts.length; i++)
        {
            count += cardCounts[i];
        }

        return count;
    }
}
