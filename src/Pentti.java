import java.util.ArrayList;
import java.util.List;

/**
 * Is the one that traverses the maze. Pentti{} has a Position{}.
 * Pentti{} will always go to the position that MazeTraverseAlgorithm{} gives to him via popNextPosition().
 * Pentti has got his MazeTraverseAlgorithm{} from MazeTraverseAlgorithmFactory{}.
 * It is Penttis{} responsibility to inform MazeController{} that mazeTraverseHappened().
 * Pentti{} waits between steps. Pentti{} always smiles.
 */
public class Pentti implements MazeTraveller {

    static final char PENTTIS_FACE = '☺'; // Penttis portrait.
    private static final int SLEEP_TIME = 250; // Sleep time (in ms) between notifying listeners

    MazeControl mMazeControl;
    /*
     * The Position{} where pentti locates. This Position{} is relative to the starting Position{},
     * since Pentti itself does not where it starts relative to the map, and thus starting position is (0, 0)
     */
    Position mPosition = new Position(0, 0);
    MazeTraverseAlgorithm mMazeTraverseAlgorithm;

    List<MazeTraverseHappenedListener> mMazeTraverseHappenedListeners = new ArrayList<>();
    private int mMovesLeft;

    public Pentti(MazeControl mazeControl) {
        mMazeControl = mazeControl;
        mMazeTraverseAlgorithm = mMazeControl.getMazeTraverseAlgorithm();
    }

    @Override
    public void start(int moves) {
        mMovesLeft = moves;
        traverse();
    }

    @Override
    public Position getPosition() {
        return mPosition;
    }

    @Override
    public Marker getMarker() {
        return new Marker(PENTTIS_FACE, new Position(mPosition));
    }

    /**
     * Pentti knows when he travels and inform its action to MazeTraverseHappenedListeners.
     *
     * @param listener will be noticed of the travel action.
     */
    public void registerListener(MazeTraverseHappenedListener listener) {
        mMazeTraverseHappenedListeners.add(listener);
    }

    /**
     * Traverse in the maze with this function via recursive function cals.
     * Pentti wall have a rest between the recursive function cals.
     */
    private void traverse() {
        if (mMazeControl.inExit(this)) {
            System.out.println("I fount the exit :)");
            return;
        }
        if (mMovesLeft-- == 0) {
            System.out.println("I am out of moves :(");
            return;
        }
        mPosition = mMazeTraverseAlgorithm.popNextPosition(mPosition);
        notifyTraverseHappenedListeners();
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        traverse();
    }

    /**
     * Notify traverseHappened listeners.
     */
    private void notifyTraverseHappenedListeners() {
        for (MazeTraverseHappenedListener mazeTraverseHappenedListener : mMazeTraverseHappenedListeners) {
            mazeTraverseHappenedListener.mazeTraverseHappened();
        }
    }


}
