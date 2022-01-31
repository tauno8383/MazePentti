/**
 * MazeTraveller represents the one that travels in maze. The traveller do not know where he/she is but
 * traveller keeps track of places he/she has travelled relative to the starting position.
 */
public interface MazeTraveller {

    /**
     * Starts the maze travelling process.
     */
    void start(int moves);

    /**
     * Getter for current position of the traveller.
     * @return the current position of the traveller.
     */
    Position getPosition();

    /**
     * A method for registering a listener for listening maze travel action.
     * @param listener to be notified.
     */
    void registerListener(MazeTraverseHappenedListener listener);

    /**
     * Getter for the Marker of the traveller. This is usually called for displaying the traveller.
     * @return the Marker by witch the traveller wat to be displayed
     */
    Marker getMarker();
}
