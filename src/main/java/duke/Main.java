package duke;

import java.util.ArrayList;

import javafx.application.Application;

/**
 * Main entry point of the program.
 */
public class Main {

    enum Flag {
        NO_GUI,
        NO_LOAD_SAVES,
    }

    /**
     * Uses varargs given via program to set flags to determine the behaviour of the application.
     *
     * @param varargs Variable number of arguments provided by the program.
     * @return List of known flags detected.
     */
    private static ArrayList<Flag> setEnvironment(String[] varargs) {
        ArrayList<Flag> flags = new ArrayList<>();

        // Process varargs, if any
        for (int i = 0; i < varargs.length; i++) {
            if (varargs[i].compareTo("--no-gui") == 0) {
                flags.add(Flag.NO_GUI);
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
        ArrayList<Flag> envFlags = setEnvironment(args);
        Duke.setFlags(envFlags);

        System.out.println("System is ready!");

        if (envFlags.contains(Flag.NO_GUI)) {
            Duke duke = new Duke();
            duke.ui.println("\n\n");
            duke.ui.printBufferLine();
            duke.startAsCli();
        } else {
            Application.launch(Duke.class, args);
        }
    }

}
