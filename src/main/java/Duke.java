import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class implements a personal assistant chatbot
 * that helps a person keep track of various things.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        // Initialize components
        Feedback fb = new Feedback();
        Scanner scn = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(100);

        fb.greet(LOGO);
        String input = scn.nextLine();
        String cmd = input.split(" ")[0];

        while (!cmd.equals("bye")) {
            int index;
            Task task;

            switch (cmd) {
                case "list":
                    fb.listTask(tasks);
                    break;

                case "mark":
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    task = tasks.get(index);
                    task.markDone();
                    fb.markTask(task);
                    break;

                case "unmark":
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    task = tasks.get(index);
                    task.unmarkedDone();
                    fb.unmarkedTask(task);
                    break;

                default:
                    tasks.add(new Task(input));
                    fb.addTask(input);
            }
            input = scn.nextLine();
            cmd = input.split(" ")[0];
        }
        fb.exit();
        scn.close();
    }
}
