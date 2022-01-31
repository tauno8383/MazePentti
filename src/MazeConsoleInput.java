import java.io.*;
import java.util.Scanner;

/**
 * It provides a simple way of deriving information from users via console as a user text input.
 */
public class MazeConsoleInput implements MazeInput {

    private static final String DEFAULT_MAZE_1 = "#######E########E####################\n" +
            "# ### #   ###### #    #     #     # E\n" +
            "# ### ### #      #  #    #     #    #\n" +
            "# ### # # # ###### ##################\n" +
            "#            #       #    #   #   # #\n" +
            "#  # ##      # ##### #  # # # # # # #\n" +
            "#  #         #   #   #  # # # # #   #\n" +
            "#  ######   ###  #  ### # # # # ### #\n" +
            "#  #    #               #   #   #   #\n" +
            "#  # ## ########   ## ###########   #\n" +
            "#    ##          ###                #\n" +
            "# ## #############  ###   ####   ## #\n" +
            "#  ### ##         #  #  #           #\n" +
            "#  #   ## ####     #    #      ###  #\n" +
            "#  # #### #  #     #    #####       #\n" +
            "#  #      #      ###           ##   #\n" +
            "#  #####           #   ##   #   #   #\n" +
            "#                                   #\n" +
            "##################^##################";

    private static final String DEFAULT_MAZE_2 = "######################################\n" +
            "#       # ###      ##    ###  #      #\n" +
            "# ### # #     ### #### #  ##  # ###  #\n" +
            "#   # # # ##### #       #  ## # # #  #\n" +
            "# ### #   ##    #######  # #  # # #  #\n" +
            "#   # # # #  ## #      # # #    # #  #\n" +
            "# ### # # # #   # #### # # # ## # #  #\n" +
            "#   # # # # # ###    # # # #         #\n" +
            "# # # # ### # # #### # # #   #########\n" +
            "#   # #   # # #   ^  # # # # #       #\n" +
            "# # # ## ## # ## ##### # # #   ##### #\n" +
            "#   #     # #    #   # # # #####     #\n" +
            "# #########  ##### # ##  #       #####\n" +
            "#         ##       #    ## ####### # E\n" +
            "######### ################ #       # #\n" +
            "#         #            #     ####### #\n" +
            "# ######### ###### # # # #####       #\n" +
            "#   #   #   #      # # ### # # #######\n" +
            "# #   #   # # #### # #               #\n" +
            "######################################\n";

    public static final String TREAMAUX_ALGORITHM = "Trémaux's algorithm";
    public static final String TAUNOS_ALGORITHM = "Taunos algorithm";
    public static final String RANDOM_ALGORITHM = "Random algorithm";

    String mInputMaze = DEFAULT_MAZE_1; // default
    String mAlgorithm = TAUNOS_ALGORITHM; // default
    int mMaxMoves = 20; // default

    public MazeConsoleInput() {
        determineInputMaze();
        determineAlgorithm();
        defineMoves();
    }

    @Override
    public String getInputMaze() {
        return mInputMaze.toString();
    }

    @Override
    public String getAlgorithm() {
        return mAlgorithm;
    }

    @Override
    public int getMoves() {
        return mMaxMoves;
    }

    /**
     * Communicate with a user to get the number of moves.
     */
    private void defineMoves() {
        System.out.println("Please:");
        System.out.println("\t1. Press enter to use 20 moves");
        System.out.println("\t2. or type number of moves between [1,1000] you desire.");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        if (inputLine != null && inputLine.length() > 0)
            try {
                int moves = Integer.parseInt(inputLine);
                if (moves > 0 && moves < 1000) {
                    mMaxMoves = moves;
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        System.out.println("You selected to use " + mMaxMoves + " moves.");
    }

    /**
     * Communicate with the user in order to find out which algorithm to use
     */
    private void determineAlgorithm() {
        System.out.println("Please:");
        System.out.println("\t1. Press enter to use Taunos algorithm ");
        System.out.println("\t2. or type 2 and enter to ose random walker algorithm");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        if (inputLine == null || inputLine.length() == 0)
            mAlgorithm = TAUNOS_ALGORITHM;
        else if (inputLine.charAt(0) == '2')
            mAlgorithm = RANDOM_ALGORITHM;
        else
            mAlgorithm = TAUNOS_ALGORITHM;
        System.out.println(mAlgorithm + " will be used.");
    }

    /**
     * Communicate with the user to find out which maze to use.
     */
    private void determineInputMaze() {
        System.out.println("Please:");
        System.out.println("\t1. Press enter to use a default maze (maze-task-first.txt)");
        System.out.println("\t2. or type 2 to use (maze-task-second.txt) ");
        System.out.println("\t3. or type the filepath to .txt file.");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        if (inputLine == null || inputLine.length() == 0)
            mInputMaze = DEFAULT_MAZE_1;
        else if (inputLine.charAt(0) == '2')
            mInputMaze = DEFAULT_MAZE_2;
        else if (!readMazeFromFile(inputLine))
            mInputMaze = DEFAULT_MAZE_1;
        System.out.println("You are using map:");
        System.out.println(mInputMaze);
    }

    /**
     * Read the maze from the give .txt file
     *
     * @param filePath is the full filepath to the file.
     * @return true if the read succeed. False otherwise.
     */
    private boolean readMazeFromFile(String filePath) {
        File file = new File(filePath);
        BufferedReader br;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
                stringBuilder.append(line + "\n");
        } catch (IOException e) {
            System.out.println("Unable to open the file: " + filePath);
            return false;
        }
        if (MazeMap.isValidMaze(stringBuilder.toString())) {
            mInputMaze = stringBuilder.toString();
            return true;
        }
        System.out.println("Invalid input maze. The default one will be used.");
        return false;
    }

}
