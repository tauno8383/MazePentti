import java.util.List;
import java.util.Queue;

/**
 * Trémaux's algorithm, invented by Charles Pierre Trémaux,
 * is an efficient method to find the way out of a maze that requires drawing lines on the floor to mark a path,
 * and is guaranteed to work for all mazes that have well-defined passages,
 * but it is not guaranteed to find the shortest route. [Wikipedia]
 * See more https://en.wikipedia.org/wiki/Maze-solving_algorithm
 */
public class TremauxsMazeTraverseAlgorithm implements MazeTraverseAlgorithm{

    static final char VISITED = '·';
    static final char DOUBLE_VISITED = '☠';

    private MazeControl mMazeControl;

    public TremauxsMazeTraverseAlgorithm(MazeControl mazeControl) {
        mMazeControl = mazeControl;
    }

    @Override
    public Position popNextPosition(Position travellersPosition) {
        // UNFINISHED
        return null;
    }

    @Override
    public List<Marker> getMarkers() {
        // UNFINISHED
        return null;
    }
}
