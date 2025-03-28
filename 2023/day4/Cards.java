
import java.util.ArrayList;

public class Cards
{
    private ArrayList<String> cards;
    private String[] splitID;
    private String[] splitNumbers;
    private int matches = 0;
    public Cards()
    {
        this.cards = new ArrayList<>();
    }

    public void addContents(String temp)
    {
        this.splitID = temp.split(":");
       
        this.splitNumbers = splitID[1].split("\\|");
        
        this.cards.add(this.splitID[0]);
        this.cards.add(this.splitNumbers[0]);
        this.cards.add(this.splitNumbers[1]);
    }
    public String getWinningNumbers()
    {
        return this.cards.get(1);
    }
    public String getListedNumbers()
    {
        return this.cards.get(2);
    }
    public String getID()
    {
        return this.cards.get(0);
    }
    public int getMatches()
    {
        return this.matches;
    }
    public void setMatches(int m)
    {
        this.matches = m;
    }
}
