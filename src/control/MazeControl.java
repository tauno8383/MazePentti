package control;

import UI.CommandlineMazeUI;
import UI.MazeUI;
import model.Position;
import model.algorithm.DepthFirstSearchTraverseAlgorithm;
import model.algorithm.MazeTraverseAlgorithm;
import model.algorithm.MazeTraverseAlgorithmFactory;
import model.MazeMap;
import model.Traveller;
import model.Pentti;

/**
 * This class is a kind of control in this MVC architecture.
 * It holds reference on many MazeMap, Traveller, and MazeTraverseAlgorithm and works as an intermediate between communication of some objects.
 */
public class MazeControl {

    private final MazeMap mMazeMap;
    MazeUI mUI;

    /**
     * Constructor.
     */
    public MazeControl() {
        /*
        Greetings
         */
        mUI = new CommandlineMazeUI(this);
        output("Buutti maze test - Tauno Toikka");
        output("A simple command line maze app for Buutti.");
        output("Special feature of this app is that neither the traverse algorithm or the traveller in the maze do not know the actual position in the maze neither the real sizes of the maze.");
        output("The travel path will be presented like this:");
        output(MazeMap.START_SYMBOL +
                String.valueOf(DepthFirstSearchTraverseAlgorithm.VISITED_SYMBOL).repeat(38) +
                Pentti.PENTTIS_FACE_SYMBOL);

        /*
        Determine important components: the map, the traveller and the algorithm.
         */
        MazeTraverseAlgorithm mazeTraverseAlgorithm = MazeTraverseAlgorithmFactory.newInstance(this);
        // MazeTraveller has a algorithm.
        Traveller mazeTraveller = new Pentti(mazeTraverseAlgorithm);
        // MazeMap has a traveller.
        mMazeMap = new MazeMap(mazeTraveller, mUI.getInputMaze());

        /*
        Execute the travelling.
         */
        int[] maxMoves = mUI.getMaxMoves();
        for (int maxMove : maxMoves) {
            output("\nTravel with max " + maxMove + " moves.");
            mazeTraveller.travel(maxMove);
            mUI.displayMaze(mMazeMap.getMazeDrawable());
        }
    }

    /**
     * The program starts here
     * @param args Arguments currently not utilized.
     */
    public static void main(String[] args) {
        new MazeControl();
    }

    /**
     * Determines weather the given position is the exit of the maze.
     *
     * @param position is the Position to examine
     * @return true if the given Position is in the exit.
     */
    public boolean inExit(Position position) {
        return mMazeMap.inExit(position);
    }

    /**
     * Determines weather it is possible to travel to given Position. Basically a block/wall permits for traveling.
     *
     * @param position is the Position examined weather to able to travel.
     * @return true if able to travel to given Position.
     */
    public boolean isPossibleToTravel(Position position) {
        return mMazeMap.isPossibleToTravel(position);
    }

    /**
     * Prints out the given String.
     *
     * @param s is the String to output.
     */
    public void output(String s) {
        mUI.println(s);
    }

}
