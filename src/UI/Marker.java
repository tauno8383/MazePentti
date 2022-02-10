package UI;

import model.Position;

/**
 * A marker for marking some Position for example for displaying in final output. This class binds one char symbol
 * (the actual visual marker) to some position.
 */
public class Marker {

    private final char mSymbol; // The character that visually symbolizes this marker.
    private final Position mPosition; // The position of this marker.

    public Marker(char symbol, Position position) {
        mSymbol = symbol;
        mPosition = position;
    }

    /**
     * Getter for the char that symbolises this marker.
     * @return the symbol char.
     */
    public char getSymbol() {
        return mSymbol;
    }

    /**
     * Getter for the Position of this marker.
     * @return the position for this marker.
     */
    public Position getPosition() {
        return mPosition;
    }

}
