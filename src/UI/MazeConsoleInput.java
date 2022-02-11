package UI;

import control.MazeControl;
import model.MazeMap;
import model.algorithm.DepthFirstSearchTraverseAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    private static final int[] MAX_MOVES = new int[]{20, 150, 200};
    private final MazeControl mMazeControl;

    public MazeConsoleInput(MazeControl mazeControl) {
        mMazeControl = mazeControl;
    }

    @Override
    public String getInputMaze() {
        return determineInputMaze();
    }

    @Override
    public String getAlgorithm() {
        /*
        Currently only one algorithm. In the future if more algorithms then the user interaction can be implemented here
        for selecting the one to use.
         */
        return DepthFirstSearchTraverseAlgorithm.DFS_ALGORITHM; // default
    }

    @Override
    public int[] getMaxMoves() {
        return MAX_MOVES;
    }


    /**
     * Communicate with the user to find out which maze to use.
     */
    private String determineInputMaze() {
        mMazeControl.output("\nPlease:");
        mMazeControl.output("\t1. Type 1 to use a default maze (maze-task-first.txt)");
        mMazeControl.output("\t2. or type 2 to use (maze-task-second.txt) ");
        mMazeControl.output("\t3. or type the full filepath to the maze (.txt) file");
        mMazeControl.output("\t4. or paste the whole maze as a text here.");
        System.out.print(">>>");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        if (inputLine == null || inputLine.length() == 0)
            return determineInputMaze();
        else if (inputLine.charAt(0) == '1') // This is for developers for testing.
            return DEFAULT_MAZE_1;
        else if (inputLine.charAt(0) == '2') // This is for developers for testing.
            return DEFAULT_MAZE_2;
        else if (inputLine.charAt(0) == MazeMap.BLOCK_SYMBOL) {
            String pastedMaze = readPastedMaze(inputLine, scanner);
            if (pastedMaze == null)
                return determineInputMaze();
            return pastedMaze;
        } else {
            String mazeFromFile = readMazeFromFile(inputLine);
            if (mazeFromFile == null)
                return determineInputMaze();
            return mazeFromFile;
        }
    }

    /**
     * If user pastes a maze text into the console then this method is able to read it.
     * @param firstLine is the first line that have already red.
     * @param scanner is the Scanner for reading the rest of the maze.
     * @return the maze string or null if the given maze is invalid.
     */
    private String readPastedMaze(String firstLine, Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder(firstLine);
        for (;;){
            String line = scanner.nextLine();
            if (line == null || line.length() < 2)
                break;
            stringBuilder.append('\n');
            stringBuilder.append(line);
        }
        if (MazeMap.isValidMaze(stringBuilder.toString())) {
            return stringBuilder.toString();
        } else {
            mMazeControl.output("Invalid input maze!");
            return null;
        }
    }

    /**
     * Read the maze from the give .txt file
     *
     * @param filePath is the full filepath to the file.
     * @return the maze in string form. Return value null indicates read failure or invalid maze.
     */
    private String readMazeFromFile(String filePath) {
        File file = new File(filePath);
        BufferedReader br;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
                stringBuilder.append(line).append("\n");
        } catch (IOException e) {
            mMazeControl.output("Unable to open the file: " + filePath);
            return null;
        }
        if (MazeMap.isValidMaze(stringBuilder.toString())) {
            return stringBuilder.toString();
        } else {
            mMazeControl.output("Invalid input maze!");
            return null;
        }
    }

}
