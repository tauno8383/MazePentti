package UI;


import java.util.List;


/**
 * MazeDrawable is something that can be visualized. The staff to visualise is the list of markers.
 */
public interface MazeDrawable {

    /**
     * A getter method for Markers that can be visualized, what ever they happen to be. Usually for printing staff for users.
     * For example if the algorithm want to show the path one of travel.
     * @return a list of Markers that can be visualized.
     */
    List<Marker> getMarkers();

}
