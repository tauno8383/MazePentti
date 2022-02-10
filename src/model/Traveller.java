package model;

import UI.MazeDrawable;

/**
 * MazeTraveller represents the one that travels in maze.
 * Traveller can be displayed and thus it is MazeDrawable.
 * Traveller wats to know where and when to proceed and thus it is TraverseAlgorithmProceededListener.
 */
public interface Traveller extends MazeDrawable, TraverseAlgorithmProceededListener {

    /**
     * Starts the maze travelling process.
     * @param moves is the number of allowed moves.
     */
    void travel(int moves);

}
