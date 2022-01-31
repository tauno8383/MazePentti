import java.util.*;

/**
 * This algorithm keeps track of Positions in which MazeTraveller has been visited and also keeps track how many times
 * in each Position. Imagine that a traveller has a pencil and he/she rule a line on the block every time he/she wists there.
 * Algorithm favours blocks that have low visit rate and favours to traveler where have not been yet.
 */
public class TaunoMazeTraverseAlgorithm implements MazeTraverseAlgorithm{

    MazeControl mMazeControl;
    List<Position> mVisitedPositions = new ArrayList<>();

    public TaunoMazeTraverseAlgorithm(MazeControl mazeControl) {
        mMazeControl = mazeControl;
    }

    @Override
    public Position popNextPosition(Position travellersPosition) {
        List<Position> positionsInOrder = new ArrayList<>();
        positionsInOrder.add(getCorrespondingVisitedPosition(travellersPosition.newPositionLeftOfThis()));
        positionsInOrder.add(getCorrespondingVisitedPosition(travellersPosition.newPositionUpOfThis()));
        positionsInOrder.add(getCorrespondingVisitedPosition(travellersPosition.nepPositionRightOfThis()));
        positionsInOrder.add(getCorrespondingVisitedPosition(travellersPosition.newPositionDownOfThis()));

        Collections.sort(positionsInOrder);

        // Iterate from least visited to most.
        for (Position position : positionsInOrder) {
            if (mMazeControl.getMazeMap().isPossibleToTravel(position)){
                addVisitedPosition(position);
                return position;
            }
        }
        return null;
    }

    @Override
    public List<Marker> getMarkers() {
        List<Marker> markers = new ArrayList<>();
        for (Position visitedPosition : mVisitedPositions) {
            String markerString = "" + visitedPosition.getVisited();
            char markerChar = markerString.charAt(0);
            markers.add(new Marker(markerChar, visitedPosition));
        }
        return markers;
    }

    /**
     * If there is a visited Position at the same location as the given Position the the visited one will be returner
     * otherwise the original.
     * @param position to compare.
     * @return either the visited Position as the same location as the give one, or the give on if there is no corresponding.
     */
    private Position getCorrespondingVisitedPosition(Position position) {
        for (Position visitedPosition : mVisitedPositions) {
            if (visitedPosition.samePosition(position))
                return visitedPosition;
        }
        return position;
    }

    /**
     * A the new as a part of the visited ones list.
     * @param position is the position to be attend.
     */
    private void addVisitedPosition(Position position) {
        for (Position visitedPosition : mVisitedPositions) {
            if (visitedPosition.samePosition(position)) {
                visitedPosition.incrementVisited();
                return;
            }
        }
        position.incrementVisited();
        mVisitedPositions.add(position);
    }

}
