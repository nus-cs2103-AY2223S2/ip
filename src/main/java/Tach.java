import containers.FileContainer;
import handlers.*;
import services.Dispatcher;
import services.SpeakerRegistry;
import services.TaskList;
import speakers.StdOut;
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
    private static final Dispatcher dispatcher = new Dispatcher();
    private static final TaskList ts = new TaskList(FileContainer.ofLocation("./data/tasks.txt", true));
    private static final SpeakerRegistry sp = new SpeakerRegistry();
    private static Boolean shouldContinue = true;

    private static void hello() {
        sp.broadcast("Yoooo from\n" + logo);
    }

    private static void initSpeaker() {
        sp.registerSpeaker(new StdOut());
    }

    private static void initDispatcher() {
        dispatcher.setSpeakerRegistry(sp);
        dispatcher.setDefaultHandler(new JThrowException());
        dispatcher.registerCommand(new JAddTask(ts));
        dispatcher.registerCommand(new JShowTaskList(ts));
        dispatcher.registerCommand(new JMarkTask(ts));
        dispatcher.registerCommand(new JDeleteTask(ts));
        dispatcher.registerError(new ETodoEmptyDescription());
        dispatcher.setExitHandler(new JBye());
        dispatcher.setToExit(() -> shouldContinue = false);
    }

    private static void takeInput() {
        sp.broadcast(Prompt.beforeInput());
        dispatcher.handle(scanner.nextLine());
        sp.broadcast(Prompt.afterInput());
    }

    public static void main(String[] args) {
        initSpeaker();
        initDispatcher();
        hello();

        while (shouldContinue) {
            takeInput();
        }
    }
}
