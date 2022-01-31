

/**
 * Simply prints stuff out on the console.
 */
public class MazeConsoleOutput implements MazeOutput {
    @Override
    public void displayMaze(char[][] obstacles2D) {
        for (char[] obstacles : obstacles2D) {
            for (char obstacle : obstacles) {
                System.out.print(obstacle);
            }
            System.out.println();
        }
    }
}
