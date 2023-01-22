import data.TaskManager;
import formatters.Format;
import formatters.Response;
import task.Task;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A bot with an UwU personality to help users keep track
 * of tasks such as events, deadlines and to-dos
 * @author Nicholas Lee
 */

public class Duke {

    /**
     * Runs the bot and reads in user input in a loop, parses it and responds accordingly
     * @param args program args
     */
    public static void main(String[] args) {

        System.out.println("Hewwo! I'm UwU_TaskMaster! How c-can I hewp you?!?");

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        Parser parser = new Parser(taskManager);
        String input;

        while (true) {

            input = scanner.nextLine();

            if (input.contains("bye")) {
                System.out.println(Format.formatResponse(Response.BYE_BYE.toString()));
                break;
            }

            String output = parser.processInput(input);
            System.out.println(Format.formatResponse(output));
        }
        scanner.close();
    }
}
