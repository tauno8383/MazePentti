

/**
 * Is a position in 2D. Position{} can shift left, up, right and down.
 */
public class Position implements Comparable<Position> {

    private int mRow;
    private int mCol;
    private int mVisited; // Number of times visited.

    /**
     * Constructor
     *
     * @param row is the row number of the position.
     * @param col is the column number of the position.
     */
    public Position(int row, int col) {
        mRow = row;
        mCol = col;
    }

    /**
     * Copy constructor.
     *
     * @param position a position to copy.
     */
    public Position(Position position) {
        mRow = position.getRow();
        mCol = position.getCol();
        mVisited = position.mVisited;
    }

    @Override
    public int compareTo(Position position) {
        return mVisited - position.getVisited() ;
    }

    /**
     * Shift this this position one step left.
     */
    public void shiftLeft() {
        --mCol;
    }

    /**
     * Shift this this position one step up.
     */
    public void shiftUp() {
        --mRow;
    }

    /**
     * Shift this this position one step right.
     */
    public void shiftRight() {
        ++mCol;
    }

    /**
     * Shift this this position one step down.
     */
    public void shiftDown() {
        ++mRow;
    }

    public Position newPositionUpOfThis() {
        return new Position(mRow + 1, mCol);
    }

    public Position newPositionDownOfThis() {
        return new Position(mRow - 1, mCol);
    }

    public Position nepPositionRightOfThis() {
        return new Position(mRow, mCol + 1);
    }

    public Position newPositionLeftOfThis() {
        return new Position(mRow, mCol - 1);
    }

    /**
     * Increase the count of visited in this Position.
     */
    public void incrementVisited() {
        ++mVisited;
    }

    /**
     * Getter for number of times visited in this position.
     *
     * @return the number of times visited in this position.
     */
    public int getVisited() {
        return mVisited;
    }


    /**
     * Determines if this position is same as the argument position. The condition is that row and col is same.
     *
     * @param position to compare.
     * @return if same position.
     */
    public boolean samePosition(Position position) {
        return position.mRow == mRow && position.mCol == mCol;
    }

    /**
     * Getter fo a row index.
     * @return the row index.
     */
    public int getRow() {
        return mRow;
    }

    /**
     * Getter for a column index.
     * @return the column index.
     */
    public int getCol() {
        return mCol;
    }


}
