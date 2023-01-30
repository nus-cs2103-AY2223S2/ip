package duke;

import javafx.application.Application;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry point of the program.
 */
public class Main {

    private enum Flag {
        CLI_MODE,
        NO_LOAD_SAVES,
    }

    /**
     * Uses varargs given via program to set flags to determine the behaviour of the application.
     *
     * @param varargs Variable number of arguments provided by the program.
     * @return List of known flags detected.
     */
    private static ArrayList<Flag> processVarArgs(String[] varargs) {
        ArrayList<Flag> flags = new ArrayList<>();

        // Process varargs, if any
        for (int i = 0; i < varargs.length; i++) {
            if (varargs[i].compareTo("--cli") == 0) {
                flags.add(Flag.CLI_MODE);
            } else if (varargs[i].compareTo("--no-load-saves") == 0) {
                flags.add(Flag.NO_LOAD_SAVES);
            }
        }

        return flags;
    }

    /**
     * Is the entry point of the application.
     * @param args Arguments provided through the console when running the program.
     */
    public static void main(String[] args) {

        Ui.printProgramInfo();
        System.out.println("Initialising system . . .");

        // Process varargs for flags
        ArrayList<Flag> flags = processVarArgs(args);

        //Initialise components
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        // Retrieve saved data (if any)
        if (!flags.contains(Flag.NO_LOAD_SAVES)) {
            duke.storage.loadDataFromFile();
        }

        System.out.println("System is ready!");
        duke.ui.println("\n\n");
        duke.ui.printBufferLine();

        if (flags.contains(Flag.CLI_MODE)) {
            duke.cli();
        } else {
            duke.ui.println("Launching GUI ...");
            TODO: Application.launch(Duke.class, args);
        }
    }

}
