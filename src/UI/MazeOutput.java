package UI;

/**
 * Provides an interface for displaying all relevant to users.
 */
public interface MazeOutput {

    /**
     * Displays the given 2D char array of obstacles.
     * @param obstacles are presenting obstacles as a char symbols of the maze map for example walls, or the traveller, ar the path
     *                  that the algorithm has traveller.
     */
    void displayMaze(char[][] obstacles);

    /**
     * Display the given string so that it is visible to the user,
     * @param string is the string to display.
     */
    void println(String string);

}
