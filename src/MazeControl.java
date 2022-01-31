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


    public boolean shouldUseTremauxsAlgorithm() {
        return mUI.getAlgorithm().contentEquals(MazeConsoleInput.TREAMAUX_ALGORITHM);
    }


    public boolean shouldUseTaunosAlgorithm() {
        return mUI.getAlgorithm().contentEquals(MazeConsoleInput.TAUNOS_ALGORITHM);
    }

    public boolean shouldUseRandomAlgorithm() {
        return mUI.getAlgorithm().contentEquals(MazeConsoleInput.RANDOM_ALGORITHM);
    }

    @Override
    public void mazeTraverseHappened() {
        mUI.displayMaze(mMazeMap.getMazeDrawable());
    }

    public MazeMap getMazeMap() {
        return mMazeMap;
    }

    public boolean inExit(MazeTraveller mazeTraveller) {
        return mMazeMap.inExit(mazeTraveller);
    }

    public MazeTraverseAlgorithm getMazeTraverseAlgorithm() {
        return mMazeTraverseAlgorithm;
    }

    public MazeTraveller getMazeTraveller() {
        return mMazeTraveller;
    }
}
