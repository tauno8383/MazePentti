package model.algorithm;

import UI.MazeDrawable;
import model.Position;
import model.TraverseAlgorithmProceededListener;

/**
 * An interface that every maze traversal algorithm should implement.
 * Traveller navigates in the MazeMap by asking where to go next to this algorithm.
 */
public interface MazeTraverseAlgorithm extends MazeDrawable {

    /**
     * The algorithm figures out where the traveller should go next based on the given travellersPosition.
     *
     * @return the position where traveller should go next.
     */
    Position nextPosition();

    /**
     * This function runs the algorithm with given maximum moves in case the exit is not found before that.
     *
     * @param moves number of maximum moves.
     */
    void run(int moves);

    /**
     * Those classes that wish to know when traverse algorithm proceeds (for example UI for live display) should
     * implement the TraverseAlgorithmProceededListener interface and register here as a listener.
     * This function together with the TraverseAlgorithmProceededListener interface provides the Observer Pattern.
     *
     * @param traverseAlgorithmProceededListener a litterer for every traverse step that the algorithm takes.
     */
    void registerListener(TraverseAlgorithmProceededListener traverseAlgorithmProceededListener);

}
