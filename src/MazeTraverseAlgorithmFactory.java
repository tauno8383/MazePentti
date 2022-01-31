/**
 * This class provides a factory method for instantiating MazeTraverseAlgorithm{}'s.
 * MazeTraverseAlgorithm{}'s should not be instantiated anywhere else.
 * This structure ensures "Loose coupling" and "Dependency injection" design principles.
 * In future more MazeTraverseAlgorithm{}'s.can easily be added to this app from here.
 */
public class MazeTraverseAlgorithmFactory {

    private final MazeControl mMazeControl;

    public MazeTraverseAlgorithmFactory(MazeControl mazeControl) {
        mMazeControl = mazeControl;
    }

    /**
     * A factory method for instantiating MazeTraverseAlgorithm. This function finds out form the MazeController which
     * concrete to return.
     * @return an concrete instantiation of MazeTraverseAlgorithm.
     */
    MazeTraverseAlgorithm newInstance() {
        if (mMazeControl.shouldUseTremauxsAlgorithm())
            return new TremauxsMazeTraverseAlgorithm(mMazeControl);
        else if (mMazeControl.shouldUseTaunosAlgorithm())
            return new TaunoMazeTraverseAlgorithm(mMazeControl);
        else if (mMazeControl.shouldUseRandomAlgorithm())
            return new RandomMazeTraverseAlgorithm(mMazeControl);
        return new TremauxsMazeTraverseAlgorithm(mMazeControl); // default
    }
}
