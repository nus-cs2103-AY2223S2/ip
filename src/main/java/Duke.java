import java.io.*;
import java.util.*;

public class Duke {

    static ArrayList<Task> storedText = new ArrayList<Task>();
    static Ui DukeUi;
    static Parser DukeParser;
    static Storage DukeStorage;
    static TaskList DukeTaskList;

    Duke() throws IOException, DukeExceptions {
        DukeUi = new Ui();
        DukeParser = new Parser();
        DukeStorage = new Storage("data", "dukedata.txt");

        try {
            DukeTaskList = new TaskList(DukeStorage.loadTask());
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void run() throws IOException, DukeExceptions {
        String input = DukeUi.gettingUserInput();

        while (!input.equals("bye")) {
            DukeParser.readInput(input, DukeTaskList);
            input = DukeUi.gettingUserInput();
        }
    }

    public static void main(String[] args) throws IOException, DukeExceptions, StringIndexOutOfBoundsException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Duke initDuke = new Duke();
        initDuke.run();
        DukeStorage.storeTask(DukeTaskList.listOfTasks);
        System.out.println("Bye! Hope to see you again soon!");
    }

}
