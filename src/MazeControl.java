/**
 * This class is a kind of control in this semi MVC architecture.
 * It holds reference on many things and works as an intermediate between communication of some objects.
 * When MazeControl{} gets the info that mazeTraverseHappened() it wil update MazeUI{}.
 */
public class MazeControl implements MazeTraverseHappenedListener {

    private final MazeUI mUI;
    private final MazeMap mMazeMap;
    private final MazeTraverseAlgorithm mMazeTraverseAlgorithm;
    private final MazeTraveller mMazeTraveller;

    public MazeControl() {
        mUI = new CommandlineMazeUI();
        mMazeMap = new MazeMap(this, mUI.getInputMaze());
        mMazeTraverseAlgorithm = new MazeTraverseAlgorithmFactory(this).newInstance();
        mMazeTraveller = new Pentti(this);
        mMazeTraveller.registerListener(this);
        mMazeTraveller.start(mUI.getMoves());
    }

    public static void main(String[] args) {
        System.out.println("Buutti maze test - Tauno Toikka");
        new MazeControl();
    }

    @Override
    public void mazeTraverseHappened() {
        mUI.displayMaze(mMazeMap.getMazeDrawable());
    }

    /**
     * Determines weather to use Tremauxs algorithm.
     * @return true if should use Tremauxs algorithm.
     */
    public boolean shouldUseTremauxsAlgorithm() {
        return mUI.getAlgorithm().contentEquals(MazeConsoleInput.TREAMAUX_ALGORITHM);
    }

    /**
     * Determines weather to use Tauno's algorithm.
     * @return true if should use Tauno's algorithm.
     */
    public boolean shouldUseTaunosAlgorithm() {
        return mUI.getAlgorithm().contentEquals(MazeConsoleInput.TAUNOS_ALGORITHM);
    }

    /**
     * Determines weather to use random wanderer algorithm.
     * @return true if should use random wanderer algorithm.
     */
    public boolean shouldUseRandomAlgorithm() {
        return mUI.getAlgorithm().contentEquals(MazeConsoleInput.RANDOM_ALGORITHM);
    }

    /**
     * Getter for the MazeMap instance.
     * @return the instance of MazeMap.
     */
    public MazeMap getMazeMap() {
        return mMazeMap;
    }

    /**
     * Determines weather the MazeTraveler is standing on exit.
     * @param mazeTraveller is the traveller hose position is analyzed.
     * @return true if the MazeTraveller is standing on exit.
     */
    public boolean inExit(MazeTraveller mazeTraveller) {
        return mMazeMap.inExit(mazeTraveller);
    }

    /**
     * Getter for the MazeTraverseAlgorithm that is currently in use.
     * @return the MazeTraverseAlgorithm in use.
     */
    public MazeTraverseAlgorithm getMazeTraverseAlgorithm() {
        return mMazeTraverseAlgorithm;
    }

    /**
     * Getter for the MazeTraveller in use.
     * @return the active MazeTraveller.
     */
    public MazeTraveller getMazeTraveller() {
        return mMazeTraveller;
    }
}
