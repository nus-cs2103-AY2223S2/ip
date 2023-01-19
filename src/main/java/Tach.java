import handlers.*;
import services.Dispatcher;
import utilities.Prompt;
import services.TaskStorage;

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
    private static final TaskStorage ts = new TaskStorage();
    private static Boolean shouldContinue = true;

    public static void hello() {
        System.out.println("Yoooo from\n" + logo);
    }

    private static void initDispatcher() {
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
        Prompt.beforeInput();
        dispatcher.handle(scanner.nextLine());
        Prompt.afterInput();
    }

    public static void main(String[] args) {
        initDispatcher();
        hello();

        while (shouldContinue) {
            takeInput();
        }
    }
}
