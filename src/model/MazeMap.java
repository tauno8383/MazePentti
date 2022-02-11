package model;

import UI.Marker;

import java.util.List;

/**
 * This class knows the structure of the maze: where are walls, where is the starting point, and where are exits (if any).
 * Maze knows (has a reference of) also to the Traveller.
 * This class is also able to produce a symbolic 2D array that contains the info where everything is located:
 * i.e. combines the actual maze and the traveller.
 * This class is the only class that know where everything actually locates and the private class AbsolutePosition
 * exists for the task.
 */
public class MazeMap {

    /**
     * A character symbol symbolizing the starting position.
     */
    public static final char START_SYMBOL = '^';
    /**
     * A character symbol symbolizing a block/wall.
     */
    public static final char BLOCK_SYMBOL = '#';
    private static final char EXIT_SYMBOL = 'E';
    private static final char SPACE_SYMBOL = ' ';
    private final Traveller mMazeTraveller; // The one who wonders around the maze.

    char[][] mMazeSymbols; // A 2D array symbolizing the whole maze visually.

    private Position mStartingPosition;

    /**
     * Constructor
     * @param mazeTraveller is the Traveller who is seeking the way out in the maze.
     * @param mazeString is the maze in the string representation typically rad from the .txt file
     */
    public MazeMap(Traveller mazeTraveller, String mazeString) {
        mMazeTraveller = mazeTraveller;
        // Determine the maze with
        int mazeWith = 0;
        for (int i = 0; i < mazeString.length(); ++i) {
            if (mazeString.charAt(i) != '\n')
                ++mazeWith;
            else
                break;
        }
        // Determine the maze height
        int mazeHeight = mazeString.length() / mazeWith;
        // Make an array for holding all maze characters
        mMazeSymbols = new char[mazeHeight][mazeWith];
        // Fill the array with characters
        int row = 0;
        int column = 0;
        for (int k = 0; k < mazeString.length(); ++k) {
            char c = mazeString.charAt(k);
            if (c != '\n') {
                if (c == START_SYMBOL)
                    mStartingPosition = new Position(row, column); // start position
                mMazeSymbols[row][column++] = c;
            } else {
                ++row;
                column = 0;
            }
        }
    }

    /**
     * Determinate weather the given maze in String format is a valid one for creating a map.
     * NOTE: The function is not exhaustive and needs improvements in the future.
     * @param mazeString is the string representation of a maze that will be tested
     * @return weather the given maze string is valid for generating an instance of this MazeMap.
     */
    public static boolean isValidMaze(String mazeString) {
        if (mazeString == null)
            return false;
        if (mazeString.length() < 3)
            return false;
        if (mazeString.charAt(0) != BLOCK_SYMBOL)
            return false;
        if (mazeString.charAt(mazeString.length() - 2) != BLOCK_SYMBOL)
            return false;
        if (!mazeString.contains("" + START_SYMBOL))
            return false;
        return true;
    }

    /**
     * Generates a symbolic 2D array for which to display. In this array this map is combined with the Traveller.
     *
     * @return return a 2D char array representing maze symbols combined with the symbols of the Traveller.
     */
    public char[][] getMazeDrawable() {
        int ROWS = mMazeSymbols.length;
        int COLS = mMazeSymbols[0].length;

        // Maze for displaying.
        char[][] drawable = new char[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row)
            for (int col = 0; col < COLS; ++col)
                drawable[row][col] = mMazeSymbols[row][col];

        // The staff that the traveller wants to display
        List<Marker> markers = mMazeTraveller.getMarkers();
        if (markers != null)
            for (Marker marker : markers) {
                AbsolutePosition markerAbsolutePosition = new AbsolutePosition(marker.getPosition());
                int row = markerAbsolutePosition.getRow();
                int col = markerAbsolutePosition.getCol();
                if (drawable[row][col] != START_SYMBOL) // We want to keep the start symbol.
                    drawable[row][col] = marker.getSymbol();
            }

        return drawable;
    }

    /**
     * Determines weather the given position stands on the exit
     *
     * @param position is the position of examination.
     * @return true if the given position is on the exit.
     */
    public boolean inExit(Position position) {
        AbsolutePosition absolutePosition = new AbsolutePosition(position);
        if (absolutePosition.outsideMazeBoundaries())
            return false;
        int row = absolutePosition.getRow();
        int col = absolutePosition.getCol();
        return mMazeSymbols[row][col] == EXIT_SYMBOL;
    }

    /**
     * Determines weather it is possible to travel to given position. For example if there is a wall in that position
     * then it is not possible to travel in the position.
     *
     * @param position is the position to examine.
     * @return weather to the given position can be travelled.
     */
    public boolean isPossibleToTravel(Position position) {
        AbsolutePosition absolutePosition = new AbsolutePosition(position);
        if (absolutePosition.outsideMazeBoundaries())
            return false;
        int row = absolutePosition.getRow();
        int col = absolutePosition.getCol();
        char mazeElement = mMazeSymbols[row][col];
        return mazeElement == SPACE_SYMBOL || mazeElement == EXIT_SYMBOL || mazeElement == START_SYMBOL;
    }

    // CLASSES

    /**
     * This class presents a Position mapped relative to the starting position. The fundamental idea in this maze app
     * is that only the MazeMap itself knows the absolute position in the map and no other class in the app. This is a
     * natural situation with maze since it is reasonable to assume that one travelling in the maze do not know where
     * he/she is in absolute coordinates, neither one now the actual size of the map.
     */
    private class AbsolutePosition {

        Position mPosition;

        public AbsolutePosition(Position position) {
            int newRow = position.getRow() + mStartingPosition.getRow();
            int newCol = position.getCol() + mStartingPosition.getCol();
            mPosition = new Position(newRow, newCol);
        }

        public int getRow() {
            return mPosition.getRow();
        }

        public int getCol() {
            return mPosition.getCol();
        }

        /**
         * Determines weather the given position is outside the MazMap.
         *
         * @return true if this position is outside the maze map.
         */
        public boolean outsideMazeBoundaries() {
            int row = mPosition.getRow();
            int col = mPosition.getCol();
            return row < 0 || row >= mMazeSymbols.length || col < 0 || col >= mMazeSymbols[0].length;
        }
    }

}
