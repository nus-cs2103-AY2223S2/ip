package duke;

import javafx.application.Application;

/**
 * Launcher class to launch the Main object.
 */
public class Launcher {

    /**
     * Default main method
     * @param args arguments to be passed in.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Application.launch(Main.class, args);
    }
}
