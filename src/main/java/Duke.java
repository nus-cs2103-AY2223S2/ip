import java.util.Scanner;

import data.TaskFileReaderWriter;
import data.TaskManager;
import ui.Format;
import ui.Response;
import utils.Parser;



/**
 * CLI version of Duke with an UwU personality to help users keep track
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
        TaskFileReaderWriter taskReaderWriter = new TaskFileReaderWriter();


        if (!taskReaderWriter.createTaskFile()) {
            System.out.println("Error creating data file");
            return;
        }

        TaskManager taskManager = taskReaderWriter.loadDataFromFile();

        Parser parser = new Parser(taskManager);
        String input;

        while (true) {

            input = scanner.nextLine();

            if (input.contains("bye")) {

                if (!taskReaderWriter.updateTaskFile(taskManager)) {
                    System.out.println("Error updating data file");
                    return;
                }
                System.out.println(Format.formatResponse(Response.BYE_BYE.toString()));
                break;
            }

            String output = parser.processInput(input);
            System.out.println(Format.formatResponse(output));
        }
        scanner.close();
    }
}
