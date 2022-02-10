package UI;

import control.MazeControl;

/*
Simple command line implementation for UI.
 */
public class CommandlineMazeUI implements MazeUI {

    MazeOutput mMazeOutput = new MazeConsoleOutput();
    MazeInput mMazeInput;

    /**
     * Constructor
     * @param mazeControl is the control of MVC.
     */
    public CommandlineMazeUI(MazeControl mazeControl) {
        mMazeInput = new MazeConsoleInput(mazeControl);
    }

    @Override
    public String getInputMaze() {
        return mMazeInput.getInputMaze();
    }

    @Override
    public String getAlgorithm() {
        return mMazeInput.getAlgorithm();
    }

    @Override
    public int[] getMaxMoves() {
        return mMazeInput.getMaxMoves();
    }

    @Override
    public void displayMaze(char[][] symbols) {
        mMazeOutput.displayMaze(symbols);
    }

    @Override
    public void println(String string) {
        mMazeOutput.println(string);
    }

}
