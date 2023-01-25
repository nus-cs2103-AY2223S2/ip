import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {
    private static TaskList taskList = new TaskList();
    private static Scanner sc = new Scanner(System.in);
    private static boolean exitApp = false;
    private static Storage storage = new Storage("data.txt");
    private static Parser parser = new Parser();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        init();

        // App loop
        while (!exitApp) {
            update();
        }

        // Exit message
        try {
            storage.save(taskList.toStorageString());
        } catch (IOException e) {
            displayMessage("☹ OOPS!!! Something went wrong when saving your tasks!");
        }
        displayMessage("Bye! Hope to see you again soon!");
    }

    private static void init() {
        try {
            storage.read(taskList);
        } catch (FileNotFoundException e) {
            displayMessage("☹ OOPS!!! Could not find the storage file.");
        }
        displayMessage("Hello! I'm Duke\nWhat can i do for you?");
    }

    private static void update() {
        String input = sc.nextLine();
        if (input.isEmpty()) {
            return;
        }

        exitApp = parser.parseAndExecute(input, taskList);

    }


    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
