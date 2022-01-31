/**
 * A marker for marking some Position for example for displaying in final output
 */
public class Marker {

    private final char mMarker;
    private final Position mPosition;

    public Marker(char marker, Position position) {
        mMarker = marker;
        mPosition = position;
    }

    /**
     * Getter for the char that symbolises this marker.
     * @return the symbol marker char.
     */
    public char getMarkerChar() {
        return mMarker;
    }

    /**
     * Getter for the Position of this marker.
     * @return the position for this marker.
     */
    public Position getPosition() {
        return mPosition;
    }

}
