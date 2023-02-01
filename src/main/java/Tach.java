import javafx.application.Application;
import services.Dispatcher;
import gui.JavaFxGuiOut;
import speakers.StdOut;
import types.ISpeaker;
import utilities.DispatcherProvider;
import utilities.Prompt;

import java.util.Scanner;

public class Tach {
    private static final String logo = "___________             .__     \n"
            + "\\__    ___/____    ____ |  |__  \n"
            + "  |    |  \\__  \\ _/ ___\\|  |  \\ \n"
            + "  |    |   / __ \\\\  \\___|   Y  \\\n"
            + "  |____|  (____  /\\___  >___|  /\n"
            + "               \\/     \\/     \\/ \n";
    private static final Scanner scanner = new Scanner(System.in);
    private static final ISpeaker speaker = new StdOut();
    private static final Dispatcher dispatcher = DispatcherProvider.getDefaultDispatcher(() -> System.exit(0), speaker);

    private static void hello() {
        dispatcher.broadcast("Yoooo from\n" + logo);
    }

    private static void takeInput() {
        dispatcher.broadcast(Prompt.beforeInput());
        dispatcher.handle(scanner.nextLine());
        dispatcher.broadcast(Prompt.afterInput());
    }

    public static void main(String[] args) {
        hello();
//
//        while (shouldContinue) {
//            takeInput();
//        }

        Application.launch(JavaFxGuiOut.class, args);
    }
}
