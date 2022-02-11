package UI;

/**
 * An interface for deriving inputs from an user.
 */
public interface MazeInput {

    /**
     * Provides an info which maze user desires to use.
     * @return which maze to use.
     */
    String getInputMaze();

    /**
     * Provides an info which algorithm user desires to use.
     * @return the user chosen algorithm.
     */
    String getAlgorithm();

    /**
     * Gives the array that determines by its length how many times to try the maze and by each element of the array it
     * determines how many moves sold be used in maximum.
     * @return the array of max number of moves. The array length determines how many times to move.
     */
    int[] getMaxMoves();

}
