import java.util.ArrayList;

public class Number 
{
    ArrayList<NumIndex> digits = new ArrayList<>();

    public ArrayList<NumIndex> getDigits()
    {
        return digits;
    }
    public void addDigits(NumIndex p)
    {
        this.digits.add(p);
    }
}
