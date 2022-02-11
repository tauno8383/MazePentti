package model.algorithm;

import control.MazeControl;
import UI.Marker;
import model.TraverseAlgorithmProceededListener;
import model.Position;

import java.util.*;

/**
 * Depth First Search (DFS) algorithm.
 * The algorithm tracks recursively the whole path to the exit.
 * The algorithm does not know the maze structure: i.e the aural location of itself or the Traveller or nigher the size of the maze,
 * but it can ask fair question to MazeMap, like can it be travelled to the left or is there a wall.
 * see some examples:
 * http://www.astrolog.org/labyrnth/algrithm.htm#solve
 * https://www.youtube.com/watch?v=W9F8fDQj7Ok
 * https://en.wikipedia.org/wiki/Maze-solving_algorithm
 */
public class DepthFirstSearchTraverseAlgorithm implements MazeTraverseAlgorithm {

    /**
     * The character symbol that presents a visited position.
     */
    public static final char VISITED_SYMBOL = '·';
    /**
     * The name of the algorithm.
     */
    public static final String DFS_ALGORITHM = "Depth First Search algorithm";
    Stack<Position> mVisitedPositions = new Stack<>();
    List<TraverseAlgorithmProceededListener> mTraverseAlgorithmProceededListeners = new ArrayList<>(); // A list of listeners who are interested if algorithm proceeds a step. For example if an UI would like to update it self.
    MazeControl mMazeControl;
    private int mMovesLeftToTake; // How many moves can this algorithm take.

    /**
     * Constructor.
     * @param mazeControl is the control object of MVC pattern.
     */
    public DepthFirstSearchTraverseAlgorithm(MazeControl mazeControl) {
        mMazeControl = mazeControl;
    }

    @Override
    public Position nextPosition() {
        if (mVisitedPositions == null || mVisitedPositions.size() == 0)
            return new Position();
        return mVisitedPositions.peek();
    }

    @Override
    public void run(int moves) {
        mMovesLeftToTake = moves;
        mVisitedPositions.clear();
        /*
        The algorithm does not know where it starts. I is a natural assumption since usually neither
        the traveller or the algorithm know where one is in the maze or what is the size of the maze (when starts).
         */
        Position startingPosition = new Position();
        boolean exitFound = depthFirstSearch(startingPosition); // The magic happens here!
        if (exitFound)
            mMazeControl.output("Exit found with " + (moves - mMovesLeftToTake) + " moves!");
        else if (mMovesLeftToTake <= 0)
            mMazeControl.output("Run out of moves!");
        else
            mMazeControl.output("There is no possible route available to exit!");
    }

    @Override
    public void registerListener(TraverseAlgorithmProceededListener traverseAlgorithmProceededListener) {
        mTraverseAlgorithmProceededListeners.add(traverseAlgorithmProceededListener);
    }

    @Override
    public List<Marker> getMarkers() {
        List<Marker> pathMarkers = new ArrayList<>();
        for (Position position : mVisitedPositions) {
            Marker marker = new Marker(VISITED_SYMBOL, position);
            pathMarkers.add(marker);
        }
        return pathMarkers;
    }

    /**
     * The actual Depth First Search recursive algorithm.
     * See for example:
     * https://www.youtube.com/watch?v=W9F8fDQj7Ok
     *
     * @param position is the Position to start search on.
     * @return true if there is a path to the exit from the given Position.
     */
    private boolean depthFirstSearch(Position position) {
        if (mMovesLeftToTake < 0) return false; // Return false if all moves used.
        if (mMazeControl.inExit(position)) return true; // The exit has been found.
        if (!mMazeControl.isPossibleToTravel(position) || visited(position)) return false; // The actual rule of the travel: Cant go blocked direction and can go visited direction.
        mVisitedPositions.add(position);
        notifyTraverseAlgorithmProceededListeners();
        --mMovesLeftToTake;

        /*
        Recursive calls for four different directions.
         */
        if (depthFirstSearch(position.newLeft()))  // Recalls method one to the left
            return true;
        if (depthFirstSearch(position.newUp()))  // Recalls method one to the up
            return true;
        if (depthFirstSearch(position.newRight()))  // Recalls method one to the right
            return true;
        if (depthFirstSearch(position.newDown()))  // Recalls method one to the down
            return true;
        return false;
    }

    /**
     * Checks weather the given position has been visited.
     *
     * @param position is the position under the examination.
     * @return true if the Position has peen visited.
     */
    private boolean visited(Position position) {
        for (Position visitedPosition : mVisitedPositions) {
            if (visitedPosition.same(position))
                return true;
        }
        return false;
    }

    /**
     * Notify all registered TraverseAlgorithmProceededListeners that the algorithm has proceeded.
     */
    private void notifyTraverseAlgorithmProceededListeners() {
        for (TraverseAlgorithmProceededListener traverseAlgorithmProceededListener : mTraverseAlgorithmProceededListeners) {
            traverseAlgorithmProceededListener.algorithmProceeded();
        }
    }

}
