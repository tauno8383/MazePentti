/**
 * A marker for marking some Position for example for displaying in final output
 */
public class Marker {

    private char mMarker;
    private Position mPosition;

    public Marker(char marker, Position position) {
        mMarker = marker;
        mPosition = position;
    }

    public char getMarkerChar() {
        return mMarker;
    }

    public Position getPosition() {
        return mPosition;
    }

}
