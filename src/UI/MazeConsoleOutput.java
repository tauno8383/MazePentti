package UI;

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
            println("");
        }
    }

    @Override
    public void println(String string) {
        /*
        Since this is a simple command line output we jus print staff on a console.
         */
        System.out.println(string);
    }

}
