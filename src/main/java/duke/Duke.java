package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * The Duke program implements an application that takes in an input and places it in a list.
 * If the words are not of any keywords, an error message will be printed.
 * @author yanlinglim
 *
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * This is a constructor for Duke such that we will create a file.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.handleLoad();
        } catch (IOException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * This is the main method to run our file
     * @param args
     * @throws DukeException
     * @throws IOException
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("text-ui-test/saved-tasks.txt").run();
    }

    /**
     * Checks for any input, and will either add the action into a list or print out an error.
     * @throws IOException
     */
    public void run() throws IOException {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.nextLine();
        int count = 0;


        while (!reply.startsWith("bye")) {
            if (reply.startsWith("deadline")) {
                reply = reply.replaceAll("deadline", "");
                String[] replies = Parser.splitforDeadline(reply);
                handleInvalidArgs checked = new handleInvalidArgs(replies);
                checked.checkForDeadline(checked.replies);
                Deadline deadline = new Deadline(replies[0],replies[1]);
                taskList.add(deadline);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(deadline);


            }else if (reply.startsWith("todo")) {
                reply = reply.replaceAll("todo", "");
                handleInvalidArgs checked = new handleInvalidArgs(reply);
                checked.checkForToDo(checked.reply);
                ToDo todo = new ToDo(reply);
                taskList.add(todo);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(todo);

            } else if (reply.startsWith("event")) {
                reply = reply.replaceAll("event", "");
                String[] replies = Parser.splitForEvent(reply);
                for (int i = 0; i< replies.length; i++){
                    System.out.println(replies[i]);
                }
                String[] datecheck =  replies[1].split("/");
                datecheck[0] = datecheck[0].replaceAll("from ", "");
                datecheck[1] = datecheck[1].replaceAll("to ","");
                replies[1] =   datecheck[0] + "/" + datecheck[1];
                handleInvalidArgs checked = new handleInvalidArgs(replies);
                checked.checkForEvent(checked.replies);
                Event event = new Event(replies[0],replies[1]);
                taskList.add(event);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(event);
            }
            else if (reply.startsWith("unmark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                taskList.get(value).unmark();
            } else if (reply.startsWith("mark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                taskList.get(value).mark();
            }
            else if (reply.startsWith("list")) {
                ui.showList();
            } else if (reply.startsWith("delete")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                Task deleted = taskList.get(value);
                Task.actions -= 1;
                taskList.remove(deleted);
                System.out.println("Noted. I've removed this task: \n" + deleted);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
            } else {
                handleInvalidArgs checked = new handleInvalidArgs(reply);
                checked.checkForRandomWords(checked.reply);
            }
            storage.saveTasks();
            reply = myObj.nextLine();
        }
        System.out.println("Bye, Hope to see you again soon!");
    }

}

