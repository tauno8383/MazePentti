import java.util.List;
import java.util.Queue;

/**
 * An interface that every maze traversal algorithm should implement.
 * Pentti navigates in the MazeMap by asking where to go next to this algorithm.
 * The algorithm trusts that Pentti will indeed go where directed.
 * The algorithm does not know the maze structure
 * but it knows the MazeTraveller{}.
 */
public interface MazeTraverseAlgorithm {

//    /**
//     * Generates 2D array of symbols that this algorithm desires to be displayed. This can be for example the path
//     * it has been traversed, or ded ends it has been found.
//     * @return the 2D array of symbols.
//     */
//    char[][] getDrawable();
//
//    /**
//     * MazeTraverseAlgorithm may keep track of visited Positions, but it is not mandatory.
//     * @return return Positions in the order this algorithm has visited.
//     */
//    Queue<Position> getVisitedPositions();

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
