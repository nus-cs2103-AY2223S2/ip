import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");


        Storage database = new Storage("./ip/data", "duke.txt");
        TaskList tasklist = new TaskList(database.load());

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            tasklist.parser(userInput);
            database.save(tasklist);
            userInput = myObj.nextLine();
        }
        database.save(tasklist);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
