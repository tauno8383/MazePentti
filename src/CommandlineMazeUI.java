
/*
Simple command line implementation for UI.
 */
public class CommandlineMazeUI implements MazeUI{

    MazeInput mMazeInput = MazeInputFactory.newInstance();
    MazeOutput mMazeOutput = MazeOutputFactory.newInstance();

    @Override
    public String getInputMaze() {
        return mMazeInput.getInputMaze();
    }

    @Override
    public String getAlgorithm() {
        return mMazeInput.getAlgorithm();
    }

    @Override
    public int getMoves() {
        return mMazeInput.getMoves();
    }

    @Override
    public void displayMaze(char[][] obstacles) {
        mMazeOutput.displayMaze(obstacles);
    }

}
