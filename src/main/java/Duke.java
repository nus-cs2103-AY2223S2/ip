/**
 * Main entry point of the program.
 */
public class Duke {
    public static void main(String[] args) {
        // Create an instance of our main application
        // We need to create a new class since the static main
        // method cannot access instance attributes, so we cannot
        // store program state.
        MainApplication app = new MainApplication();
        app.start();
    }
}
