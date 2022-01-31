/**
 * Provides a loose coupling method for updating this app having a more impressive MazeOutput{} later in the future.
 * MazeOutput{}'s should not be implemented anywhere else.
 */
public class MazeOutputFactory {

    /**
     * A factory method for creating MazeOutput.
     * @return the MazeOutput implementation.
     */
    static MazeOutput newInstance(){
        return new MazeConsoleOutput();
    }
}
