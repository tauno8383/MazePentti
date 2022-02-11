package model;

/**
 * In case some class wants to listen every step of travel then here is the interface to implement for doing that that.
 * This interface provides an "observer pattern" for keeping for example UI up to date.
 */
public interface TraverseAlgorithmProceededListener {

    /**
     * A function for informing that the traverse has happened.
     * Most likely use is for updating a Travellers position or updating UI.
     */
    void algorithmProceeded();

}