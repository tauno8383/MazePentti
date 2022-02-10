package model;

import UI.Marker;
import model.algorithm.MazeTraverseAlgorithm;

import java.util.List;

/**
 * Pentti is the one that traverses the maze. Pentti has a Position.
 * Pentti will always go to the position that MazeTraverseAlgorithm gives to him by nextPosition().
 * Pentti has got his MazeTraverseAlgorithm as a parameter from constructor (Dependency Injection).
 * Pentti do not know where he locates in terms of absolut coordinates in the maze
 * but he keeps track of places he has travelled relative to the starting position.
 * Pentti always smiles ☺
 */
public class Pentti implements Traveller {

    public static final char PENTTIS_FACE_SYMBOL = '☺'; // Pentti's portrait.

    /*
     * The Position where pentti locates. This Position is relative to the starting Position,
     * since Pentti itself does not where it starts relative to the map, and thus the starting position is (0, 0)
     */
    Position mPosition = new Position();
    MazeTraverseAlgorithm mMazeTraverseAlgorithm;

    /**
     * Constructor
     * @param mazeTraverseAlgorithm is the MazeTraverseAlgorithm give to Pentti in order to apply the Dependency Injection
     *                              principle. Pentti will navigate with this algorithm.
     */
    public Pentti(MazeTraverseAlgorithm mazeTraverseAlgorithm) {
        mMazeTraverseAlgorithm = mazeTraverseAlgorithm;
    }

    @Override
    public void travel(int moves) {
        mMazeTraverseAlgorithm.registerListener(this);
        mMazeTraverseAlgorithm.run(moves);
    }

    @Override
    public List<Marker> getMarkers() {
        List<Marker> markers = mMazeTraverseAlgorithm.getMarkers(); // Pentti want to show the path of he has travelled.
        Marker pentti = new Marker(PENTTIS_FACE_SYMBOL, new Position(mPosition));
        markers.add(pentti);
        return markers;
    }

    @Override
    public void algorithmProceeded() {
        // Update Pentti's own position.
        mPosition = mMazeTraverseAlgorithm.nextPosition();
    }

}
