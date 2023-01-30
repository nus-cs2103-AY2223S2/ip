import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CommandManager {

    public static void run(String input, String[] command, TaskList tasks) {
        try {
            if (command[0].equals("list")) {
                tasks.listTasks();
            } else if (command[0].equals("mark")) {
                tasks.mark(command);
            } else if (command[0].equals("unmark")) {
                tasks.unmark(command);
            } else if (command[0].equals("todo")) {
                tasks.addTask(input);
            } else if (command[0].equals("deadline")) {
                tasks.addDeadline(input);
            } else if (command[0].equals("event")) {
                tasks.addEvent(input);
            } else if (command[0].equals("delete")) {
                tasks.deleteTask(command);
            } else {
                System.out.println("Invalid command wake up brother");
            }
        } catch (InvalidTaskDescriptionException e) {
            System.out.println(e.getMessage());
        }


    }
}

