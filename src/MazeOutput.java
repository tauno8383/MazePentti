/**
 * Provides an interface for displaying all relevant to users.
 */
public interface MazeOutput {

    /**
     * Displays somehow the 2D char array of obstacles.
     * @param obstacles are presenting obstacles of the maze map for example walls, or the traveller, ar the path
     *                  that the algorithm has traveller.
     */
    void displayMaze(char[][] obstacles);

}
