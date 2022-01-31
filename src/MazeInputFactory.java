/**
 * Is a factory method for providing a loose coupling method to implement MazeInput{}'s
 * which should not be implemented anywhere else.
 * In case in the future more powerful and impressive MazeInput{} will be made, they can be put here.
 */
public class MazeInputFactory {

    /**
     * Creates and returns a concrete implementation of MazeInput.
     * @return a new instance of MazeInput.
     */
    static MazeInput newInstance() {
        return new MazeConsoleInput();
    }
}
