public class NumIndex
{
    int row;
    int col;

    public NumIndex(int row, int col)
    {
        this.col = col;
        this.row = row;
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public NumIndex add(NumIndex other)
    {
        int newRow = this.getRow() + other.getRow();
        int newCol = this.getCol() + other.getCol();
        return new NumIndex(newRow, newCol);
    }
}
