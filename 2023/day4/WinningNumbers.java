import java.util.ArrayList;

public class WinningNumbers
{
    private ArrayList<String> winnigNumbers;
    public WinningNumbers()
    {
        this.winnigNumbers = new ArrayList<>();
    }
    public void addWinningNumbers(String temp)
    {
        String[] splitTemp = temp.split(" ");
        for(String s : splitTemp)
        {
            this.winnigNumbers.add(s);
        }
        
    }

}
