import java.util.List;

/**
 * This class knows the structure of the maze: where are walls, where is the starting point, and where are exits (if any).
 * Maze knows (has a reference of) also to Pentti and MazeTraverseAlgorithm.
 * This class is also able to produce a symbolic 2D array that contains the info where everything is located:
 * i.e. combines the actual maze, Pentti, and markers of the MazeTraverseAlgorithm{}.
 * MazeMap{} figures out Penttis{} and MazeTraverseAlgorithms{} Positions
 * by using Penttis{} and MazeTraverseAlgorithm{}'s Position{} and its remap() function.
 */
public class MazeMap {

    private static final char STARTING_POSITION_SYMBOL = '^';
    static final char EXIT = 'E';
    static final char BLOCK = '#';
    static final char SPACE = ' ';

    char[][] mMazeCharacters;

    private Position mStartingPosition;

    MazeControl mMazeControl;

    public MazeMap(MazeControl mazeControl, String mazeString) {
        mMazeControl = mazeControl;
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
        mMazeCharacters = new char[mazeHeight][mazeWith];
        // Fill the array with characters
        int row = 0;
        int column = 0;
        for (int k = 0; k < mazeString.length(); ++k) {
            char c = mazeString.charAt(k);
            if (c != '\n') {
                if (c == STARTING_POSITION_SYMBOL)
                    mStartingPosition = new Position(row, column); // start position
                mMazeCharacters[row][column++] = c;
            } else {
                ++row;
                column = 0;
            }
        }
    }

    /**
     * Determinate weather the given maze in String format is a valid one for creating a map.
     *
     * @param mazeString to be tested
     * @return weather the given maze is valid as to be MazeMap.
     */
    public static boolean isValidMaze(String mazeString) {
        if (mazeString == null)
            return false;
        if (mazeString.length() < 3)
            return false;
        if (mazeString.charAt(0) != BLOCK)
            return false;
        if (mazeString.charAt(mazeString.length() - 2) != BLOCK)
            return false;
        if (!mazeString.contains("" + STARTING_POSITION_SYMBOL))
            return false;
        return true;
    }


    /**
     * Generates a symbolic 2D array for which to display. In this array this map is combined with Pentti{} and
     * potentially the travel path of MazeTraverseAlgorithm{}
     *
     * @return return a 2D char array representing maze objects, Pentti and markers of an MazeTraverseAlgorithm{}.
     */
    public char[][] getMazeDrawable() {
        int ROWS = mMazeCharacters.length;
        int COLS = mMazeCharacters[0].length;

        // Maze for displaying.
        char[][] drawable = new char[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row)
            for (int col = 0; col < COLS; ++col)
                drawable[row][col] = mMazeCharacters[row][col];

        // The staff that the algorithm wants to display
        List<Marker> algorithmMarkers = mMazeControl.getMazeTraverseAlgorithm().getMarkers();
        if (algorithmMarkers != null)
            for (Marker marker : algorithmMarkers) {
                Position markerAbsolutePosition = remap(marker.getPosition());
                drawable[markerAbsolutePosition.getRow()][markerAbsolutePosition.getCol()] = marker.getMarkerChar();
            }

        // The staff that MazeTraveller wants to display
        MazeTraveller mazeTraveller = mMazeControl.getMazeTraveller();
        Marker travellersMarker = mazeTraveller.getMarker();
        Position markersAbsolutePosition = remap(travellersMarker.getPosition());
        drawable[markersAbsolutePosition.getRow()][markersAbsolutePosition.getCol()] = travellersMarker.getMarkerChar();

        return drawable;
    }

    /**
     * Determines weather or not the traveller is standing on the exit.
     *
     * @param mazeTraveller is the MazeTraveller whose position relative to the exit we are examining.
     * @return weather the traveller is standing on the exit.
     */
    public boolean inExit(MazeTraveller mazeTraveller) {
        Position travellersAbsolutePosition = remap(mazeTraveller.getPosition());
        int row = travellersAbsolutePosition.getRow();
        int col = travellersAbsolutePosition.getCol();
        return mMazeCharacters[row][col] == EXIT;
    }

    /**
     * Determines weather it is possible to travel to given position. For example if there is a wall in that position
     * then it is not possible to travel in the position.
     *
     * @param position relative position.
     * @return weather to the given position can be travelled.
     */
    public boolean isPossibleToTravel(Position position) {
        Position absolutePosition = remap(position);
        int row = absolutePosition.getRow();
        if (row < 0 || row >= mMazeCharacters.length)
            return false;
        int col = absolutePosition.getCol();
        if (col < 0 || col >= mMazeCharacters[0].length)
            return false;
        char mazeElement = mMazeCharacters[row][col];
        return mazeElement == SPACE || mazeElement == EXIT;
    }

    /**
     * Remaps relative Position to absolute Position.
     *
     * @param relativePosition is a position relative to the starting position.
     * @return the absolute position on MazeMap
     */
    private Position remap(Position relativePosition) {
        int newRow = relativePosition.getRow() + mStartingPosition.getRow();
        int newCol = relativePosition.getCol() + mStartingPosition.getCol();
        return new Position(newRow, newCol);
    }

}
