import java.util.Scanner;
import java.util.ArrayList;
import Exceptions.*;

enum Commands{
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
}

public class Duke {
    // Bot methods
    public static void spawnBot() {
        Format.line();
        System.out.println("HEY! I'm GRUMMY!\nHow can I help you :>");
        Format.line();
    }
    public static void despawnBot() {
        Format.line();
        System.out.println("Goodbye! See you next time :>");
        Format.line();
    }
    public static void printDefaultException() {
            Format.line();
            System.out.println("I'm sorry, I don't understand :<");
            Format.line();
    }
    
    public static void main(String[] args) throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        spawnBot();

        exit:
        while (sc.hasNext()) {
            try {
            String[] input = sc.nextLine().split(" ", 2);
            String command = input[0];
            Commands currCommand = Commands.valueOf(command.toUpperCase());

                switch (currCommand) {
                    // Command for bye
                    case BYE:
                        despawnBot();
                        break exit;
                    // Command for list
                    case LIST: {
                        Tasklist.listTasks(taskList);
                        break;
                    }
                    // Command to mark as done
                    case MARK: {
                        Task.setMark(taskList, input[1]);
                        break;
                    }
                    // Command to unmark
                    case UNMARK: {
                        Task.setUnmark(taskList, input[1]);
                        break;
                    }
                    // Command to remove task
                    case DELETE: {
                        Tasklist.deleteTask(taskList, input[1]);
                        break;
                    }
                    // Create Todo task
                    case TODO: {
                        Todo.runTodo(taskList, input[1]);
                        break;
                    }
                    // Create deadline task
                    case DEADLINE: {
                        Deadline.runDeadline(taskList, input[1]);
                        break;
                    }
                    // Create event task
                    case EVENT: {
                        Event.runEvent(taskList, input[1]);
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                printDefaultException();
            }
        }
    }
}



