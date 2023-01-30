package duke;

/**
 * Handles launching the app.
 */
public class Launcher {
    /**
     * Main method of the app. By default, the app runs using a graphical user interface. However, if the "--cli"
     * argument is provided, then the app will run using a command-line interface.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        if (shouldLaunchCli(args)) {
            DukeCli.run();
        } else {
            DukeGui.run(args);
        }
    }

    private static boolean shouldLaunchCli(String[] args) {
        return args.length == 1 && args[0].equals("--cli");
    }
}
