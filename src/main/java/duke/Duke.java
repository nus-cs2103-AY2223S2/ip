package duke;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.image.Image;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    private TaskList searchList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));




    public Duke() {
        ui = new Ui();
        storage = new Storage("text-ui-test/saved-tasks.txt");
        try {
            taskList = new TaskList();
            storage.handleLoad();
        } catch (IOException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }
    /**
     * This is a constructor for Duke such that we will create a file.
     * @param filePath
     */
    public Duke(String filePath) throws IOException {
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
     * Checks for any input, and will either add the action into a list or print out an error.
     * @throws IOException
     * @return
     */
    /*public static void main(String[] args) throws IOException {
        new Duke("text-ui-test/saved-tasks.txt").run();
    }*/
    /*public void run() throws IOException {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.nextLine();
        int count = 0;


        while (!reply.startsWith("bye")) {
            if (reply.startsWith("deadline")) {
                reply = reply.replaceAll("deadline", "");
                String[] replies = Parser.splitForDeadline(reply);
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
                ToDo toDo = new ToDo(reply);
                taskList.add(toDo);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(toDo);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(toDo);

            } else if (reply.startsWith("event")) {
                reply = reply.replaceAll("event ", "");
                String[] replies = Parser.splitForEvent(reply);
                String[] dateCheck =  replies[1].split("/");
                dateCheck[0] = dateCheck[0].replaceAll("from ", "");
                dateCheck[1] = dateCheck[1].replaceAll("to ","");
                replies[1] =   dateCheck[0] + "/" + dateCheck[1];
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
                Task.tasks.get(value).unmark();
            } else if (reply.startsWith("mark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                Task.tasks.get(value).mark();
            }
            else if (reply.startsWith("list")) {
                ui.showList();
            } else if (reply.startsWith("delete")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                Task deleted = Task.tasks.get(value);
                Task.actions -= 1;
                Task.tasks.remove(deleted);
                System.out.println("Noted. I've removed this task: \n" + deleted);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
            } else if (reply.startsWith("find")){
                int counter = 0;
                String desc  = reply.replaceAll("find ", "");
                System.out.println(desc);
                TaskList.find(desc);
                TaskList.printFind();

            } else {
                handleInvalidArgs checked = new handleInvalidArgs(reply);
                checked.checkForRandomWords(checked.reply);
            }
            storage.saveTasks();
            reply = myObj.nextLine();
        }
        System.out.println("Bye, Hope to see you again soon!");

    }*/


    /*private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/

    /**
     *
     * @param input String input from users
     * @return String representation based on the action
     * @throws IOException
     */
    String getResponse(String input) throws IOException {
        int count = 0;

            if (input.startsWith("deadline")) {
                input = input.replaceAll("deadline ", "");
                String[] replies = Parser.splitForDeadline(input);
                InvalidArgsHandler checked = new InvalidArgsHandler(replies);
                checked.checkForDeadline(checked.replies);
                Deadline deadline = new Deadline(replies[0],replies[1]);
                taskList.add(deadline);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(deadline);
                storage.saveTasks();
                return ui.addTask(deadline);

            }else if (input.startsWith("todo")) {
                input = input.replaceAll("todo", "");
                InvalidArgsHandler checked = new InvalidArgsHandler(input);
                checked.checkForToDo(checked.reply);
                ToDo toDo = new ToDo(input);
                taskList.add(toDo);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(toDo);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(toDo);
                storage.saveTasks();
                return ui.addTask(toDo);

            } else if (input.startsWith("event")) {
                input = input.replaceAll("event", "");
                String[] replies = Parser.splitForEvent(input);
                String[] dateCheck =  replies[1].split("/");
                dateCheck[0] = dateCheck[0].replaceAll("from ", "");
                dateCheck[1] = dateCheck[1].replaceAll("to ","");
                replies[1] =   dateCheck[0] + "/" + dateCheck[1];
                InvalidArgsHandler checked = new InvalidArgsHandler(replies);
                checked.checkForEvent(checked.replies);
                Event event = new Event(replies[0],replies[1]);
                taskList.add(event);
                count += 1;
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                Task.tasks.add(event);
                storage.saveTasks();
                return ui.addTask(event);
            }
            else if (input.startsWith("unmark")) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task.tasks.get(value).unmark();
                storage.saveTasks();
                return ui.stringMark(Task.tasks.get(value));
            } else if (input.startsWith("mark")) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task.tasks.get(value).mark();
                storage.saveTasks();
                return ui.stringMark(Task.tasks.get(value));
            }
            else if (input.startsWith("list")) {
                return ui.showList();
            } else if (input.startsWith("delete")) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                Task deleted = Task.tasks.get(value);
                Task.actions -= 1;
                Task.tasks.remove(deleted);
                storage.saveTasks();
                System.out.println("Noted. I've removed this task: \n" + deleted);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
                return ui.stringDelete(deleted);
            } else if (input.startsWith("find")) {
                int counter = 0;
                String desc = input.replaceAll("find ", "");
                System.out.println(desc);
                TaskList.find(desc);
                return TaskList.printFind();
            } else if (input.startsWith("bye")){
                System.out.println("Bye, Hope to see you again soon!");
                return "Bye, Hope to see you again soon!";

            } else {
                InvalidArgsHandler checked = new InvalidArgsHandler(input);
                checked.checkForRandomWords(checked.reply);

            }
            storage.saveTasks();
        return "Duke heard: " + input;
    }


}

