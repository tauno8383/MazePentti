import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
Makes MazeTraveller{} to wander randomly in maze.
 */
public class RandomMazeTraverseAlgorithm implements MazeTraverseAlgorithm {
    private MazeControl mMazeControl;

    public RandomMazeTraverseAlgorithm(MazeControl mazeControl) {
        mMazeControl = mazeControl;
    }

    @Override
    public Position popNextPosition(Position travellersPosition) {
        RandomDirection sdr = new RandomDirection();
        Position nextTravelPosition;
        while (sdr.hasNext()) {
            switch (sdr.popNextDirection()) {
                case RandomDirection.LEFT:
                    nextTravelPosition = travellersPosition.newPositionLeftOfThis();
                    if (mMazeControl.getMazeMap().isPossibleToTravel(nextTravelPosition))
                        return nextTravelPosition;
                    break;
                case RandomDirection.UP:
                    nextTravelPosition = travellersPosition.newPositionUpOfThis();
                    if (mMazeControl.getMazeMap().isPossibleToTravel(nextTravelPosition))
                        return nextTravelPosition;
                    break;
                case RandomDirection.RIGHT:
                    nextTravelPosition = travellersPosition.nepPositionRightOfThis();
                    if (mMazeControl.getMazeMap().isPossibleToTravel(nextTravelPosition))
                        return nextTravelPosition;
                    break;
                case RandomDirection.DOWN:
                    nextTravelPosition = travellersPosition.newPositionDownOfThis();
                    if (mMazeControl.getMazeMap().isPossibleToTravel(nextTravelPosition))
                        return nextTravelPosition;
                    break;
            }
        }
        return null;
    }

    @Override
    public List<Marker> getMarkers() {
        // We do not want to display any markers of this algorithm, since we do not even record such ones.
        return null;
    }

    // CLASSES

    /**
     * This classes produces directions in random order.
     */
    private static class RandomDirection {

        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int RIGHT = 3;
        public static final int DOWN = 4;

        Stack<Integer> randomDirections = new Stack<>();

        public RandomDirection() {
            randomDirections.add(LEFT);
            randomDirections.add(UP);
            randomDirections.add(RIGHT);
            randomDirections.add(DOWN);
            java.util.Collections.shuffle(randomDirections); // The actual randomization.
        }

        public int popNextDirection(){
            return randomDirections.pop();
        }

        public boolean hasNext() {
            return randomDirections.size() != 0;
        }
    }
}
