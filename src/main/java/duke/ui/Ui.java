package duke.ui;
import java.util.Scanner;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Task;

/**
 * User Interface Class for handling user's interactions
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Ui {

    private Scanner scanner;

    /**
     * Default constructor
     */
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message with customized logo
     */
    public void displayWelcome() {
        String personal_logo = "                      - \n"
                             + "    /                (_) \n"
                             + "   /  \\   ___ ___ _ __ _ _____ __ ___ \n"
                             + "  / /  \\ / __/ _ \\ '__| |_  / '_ ` _ \\ \n"
                             + " / ____ \\ (_|  __/ |  | |/ /| | | | | | \n"
                             + "/_/    \\_\\___\\___|_|  |_/___|_| |_| |_| \n";

        System.out.println("Hi there! I am \n" + personal_logo);
        this.showLine();
        System.out.println("What can I do for you?");
        this.showLine();
    }

    /**
     * Closes the scanner instance
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Shows the long repeated lines for UI
     */
    public void showLine() {
        System.out.println("*-".repeat(100));
    }

    /**
     * Displays the result of the commands given by the user
     * @param type Type of task
     * @param task Task instance
     * @param taskList List of tasks
     */
    public void displayResult(TypeOfTask type,Task task, TaskList taskList) {
        if(type == TypeOfTask.bye) {
            System.out.println("Bye. Hope to see you again soon!");
            //this.showLine();
        } else if(type == TypeOfTask.list){
            // for listing of task
            System.out.println("Here are the tasks in your list:");
        } else if(type == TypeOfTask.mark){
            // for marking tasks
            this.showLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());
            //this.showLine();
        } else if(type == TypeOfTask.unmark){
            // for unmarking tasks
            this.showLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.toString());
            //this.showLine();
        } else if(type == TypeOfTask.todo){
            // for todo tasks
            this.showLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println(String.format("Now you have %d tasks in the list",taskList.getSize()));
            //this.showLine();
        } else if(type == TypeOfTask.deadline){
            // for deadline
            this.showLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println(String.format("Now you have %d tasks in the list",taskList.getSize()));
            //this.showLine();
        } else if (type == TypeOfTask.event) {
            // for event
            this.showLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println(String.format("Now you have %d tasks in the list",taskList.getSize()));
            //this.showLine();
        } else if(type == TypeOfTask.delete) {
            System.out.println(String.format("Now you have %d tasks in the list",taskList.getSize()));
            //System.out.println("*-".repeat(100))
        } else if(type == TypeOfTask.find) {
            System.out.println("Here are the matching tasks in your list");
        } else {
            System.out.println("Oh no! You forgot to type in something useful :p");
            System.out.println("What can I do for you?");
        }

    }

    /**
     * Reads the command from the user's inputs
     * @return the user's inputs
     * @throws Exception when user does not type anything and immediately enters an empty response or command.
     */
    public String readCommand() throws Exception {
        // this is what I want
        return this.scanner.nextLine();
    }
}
