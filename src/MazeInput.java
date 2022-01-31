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
     * Provides an info how many moves user desires to MazeTraveller to make maximum.
     * @return maximum number of moves allowed by MazeTraveller.
     */
    int getMoves();

}
