import gui.JavaFxGuiOut;
import javafx.application.Application;
import services.Dispatcher;
import speakers.StdOut;
import types.ISpeaker;
import utilities.DispatcherProvider;

/**
 * The main class.
 */
public class Tach {
    private static final String logo = "___________             .__     \n"
            + "\\__    ___/____    ____ |  |__  \n"
            + "  |    |  \\__  \\ _/ ___\\|  |  \\ \n"
            + "  |    |   / __ \\\\  \\___|   Y  \\\n"
            + "  |____|  (____  /\\___  >___|  /\n"
            + "               \\/     \\/     \\/ \n";
    private static final ISpeaker speaker = new StdOut();
    private static final Dispatcher dispatcher = DispatcherProvider.getDefaultDispatcher(() -> System.exit(0), speaker);

    private static void hello() {
        dispatcher.broadcast("Yoooo from\n" + logo);
    }

    /**
     * The main method to launch the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        hello();

        Application.launch(JavaFxGuiOut.class, args);
    }
}
