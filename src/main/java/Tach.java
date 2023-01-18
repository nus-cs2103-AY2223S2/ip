import handlers.HAddTask;
import handlers.HShowTaskList;
import services.CommandHelper;
import services.Parser;
import services.Prompt;
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
    private static final Parser parser = new Parser();
    private static final TaskStorage ts = new TaskStorage();
    private static Boolean shouldContinue = true;

    public static void hello() {
        System.out.println("Yoooo from\n" + logo);
    }

    private static void initParser() {
        parser.setDefaultHandler(new HAddTask(ts));
        parser.registerCommand(new HShowTaskList(ts));
        parser.setExitHandler(CommandHelper.getClass("handlers.HBye"));
        parser.setToExit(() -> shouldContinue = false);
    }

    private static void takeInput() {
        Prompt.beforeInput();
        parser.handle(scanner.nextLine());
        Prompt.afterInput();
    }

    public static void main(String[] args) {
        initParser();
        hello();

        while (shouldContinue) {
            takeInput();
        }
    }
}
