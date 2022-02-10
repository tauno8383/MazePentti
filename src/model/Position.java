package model;

/**
 * Is a position in 2D.
 */
public class Position {

    private final int mRow; // A row number in 2D matrix.
    private final int mCol; // A column number in 2D matrix.

    /**
     * Default constructor
     */
    public Position() {
        mRow = 0;
        mCol = 0;
    }

    /**
     * Constructor
     *
     * @param row is the row number of the Position.
     * @param col is the column number of the Position.
     */
    public Position(int row, int col) {
        mRow = row;
        mCol = col;
    }

    /**
     * Copy constructor.
     *
     * @param position a Position to copy.
     */
    public Position(Position position) {
        mRow = position.getRow();
        mCol = position.getCol();
    }

    /**
     * A factory method for implementing new position hat is left to this one.
     *
     * @return a new Position instance that is left to this one.
     */
    public Position newLeft() {
        return new Position(mRow, mCol - 1);
    }

    /**
     * A factory method for implementing new position hat is above to this one.
     *
     * @return a new Position instance that is above to this one.
     */
    public Position newUp() {
        return new Position(mRow + 1, mCol);
    }

    /**
     * A factory method for implementing new position hat is right to this one.
     *
     * @return a new Position instance that is right to this one.
     */
    public Position newRight() {
        return new Position(mRow, mCol + 1);
    }

    /**
     * A factory method for implementing new position hat is below to this one.
     *
     * @return a new Position instance that is below to this one.
     */
    public Position newDown() {
        return new Position(mRow - 1, mCol);
    }


    /**
     * Getter fo a row index.
     *
     * @return the row index.
     */
    public int getRow() {
        return mRow;
    }

    /**
     * Getter for a column index.
     *
     * @return the column index.
     */
    public int getCol() {
        return mCol;
    }


    /**
     * Compares weather the given position is the same as this one.
     * This method should be technically called as "equals()" but we do not want to overwrite that one here since we
     * want to avoid casting.
     *
     * @param position is the Position to compare.
     * @return ture if the given Position is the same Position as this.
     */
    public boolean same(Position position) {
        return mRow == position.mRow && mCol == position.mCol;
    }

}
