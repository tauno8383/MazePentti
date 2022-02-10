package model.algorithm;

import control.MazeControl;

/**
 * This class provides a factory method for instantiating MazeTraverseAlgorithm's.
 * MazeTraverseAlgorithm's should not be instantiated anywhere else.
 * This structure ensures "Loose coupling" and "Dependency injection" design principles.
 * In future more MazeTraverseAlgorithm's.can easily be added to this app from here.
 */
public class MazeTraverseAlgorithmFactory {

    /**
     * A factory method for instantiating MazeTraverseAlgorithm.
     *
     * @return an concrete instantiation of MazeTraverseAlgorithm.
     */
    public static MazeTraverseAlgorithm newInstance(MazeControl mazeControl) {
        return new DepthFirstSearchTraverseAlgorithm(mazeControl);
    }

}
