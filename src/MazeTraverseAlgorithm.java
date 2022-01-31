import java.util.List;
import java.util.Queue;

/**
 * An interface that every maze traversal algorithm should implement.
 * MazeTraveller navigates in the MazeMap by asking where to go next to this algorithm.
 * The algorithm trusts that MazeTraveller will indeed go where directed.
 * The algorithm does not know the maze structure but it can ask fair question to MazeMap like is wall left of me.
 */
public interface MazeTraverseAlgorithm {

    /**
     * The algorithm figures out where the traveller should go next based on the given travellersPosition.
     * The function is "pop.." function since traveller is expected to go there where instructed.
     * @param travellersPosition is the position where the the traveller is now.
     * @return the position where traveller should go next.
     */
    Position popNextPosition(Position travellersPosition);

    /**
     * Markers of the algorithm, what ever they happen to be. Usually for printing staff for users. For example if
     * the algorithm want to show the path one of travel.
     * @return Markers describing the algorithms behaviour this far.
     */
    List<Marker> getMarkers();
}
