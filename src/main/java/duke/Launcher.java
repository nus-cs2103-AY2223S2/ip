package duke;

/**
 * Handles launching the app.
 */
public class Launcher {
    /**
     * Determines which version of the app to run, CLI or GUI, and runs it.
     * <p>
     * By default, the app runs using a GUI. However, if the "--cli" argument is provided, then the app will run via a
     * CLI.
     * </p>
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        if (shouldLaunchCli(args)) {
            CliDuke.launch();
        } else {
            GuiDuke.launch(args);
        }
    }

    private static boolean shouldLaunchCli(String[] args) {
        assert args != null;

        return args.length == 1 && args[0].equals("--cli");
    }
}
