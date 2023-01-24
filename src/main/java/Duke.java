import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import command.ByeCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.DukeException;
import task.Task;


public class Duke {
    private static ArrayList<Task> taskList;

    enum commandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args)  {
        boolean terminate = false;
        taskList = new ArrayList<>();

        String line = "    ----------------------------------------";
        String logo = "____    ____  __    __   __    __  \n"
                    + "\\   \\  /   / |  |  |  | |  |  |  |\n"
                    + " \\   \\/   /  |  |  |  | |  |  |  | \n"
                    + "  \\_    _/   |  |  |  | |  |  |  | \n"
                    + "    |  |     |  `--'  | |  `--'  | \n"
                    + "    |__|      \\______/   \\______/  \n";
        System.out.println("           Hello! I am\n" + logo);
        System.out.println("    What can I do for you?");

        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!terminate) {

            try {
                System.out.println(line);
                String input = br.readLine().trim();

                if (input.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but input cannot be empty");

                } else {}
                    String[] inputs = input.split(" ");
                    commandType command = commandType.valueOf(inputs[0].toUpperCase());
                    switch(command) {
                        case BYE:
                            terminate = true;
                            new ByeCommand().execute();
                            break;

                        case LIST:
                            new ListCommand(taskList).execute();
                            break;

                        case MARK:
                            new MarkCommand(input, taskList).execute();
                            break;

                        case UNMARK:
                            new UnmarkCommand(input, taskList).execute();
                            break;

                        case DELETE:
                            new DeleteCommand(input, taskList).execute();
                            break;

                        case TODO:
                            new TodoCommand(input, taskList).execute();;
                            break;
                            
                        case DEADLINE:
                            new DeadlineCommand(input, taskList).execute();;
                            break;

                        case EVENT:
                            new EventCommand(input, taskList).execute();;
                            break;

                        default :
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }


            } catch (IOException e) {
                System.out.println("    " + e.getMessage());
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
