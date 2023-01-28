package duke;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    String readLine() {
        return sc.nextLine();
    }

    void printWelcomeMessage() {
        System.out.println("Hi, I'm Nero and I am an automated chat bot"
                + "\n" + "What would you like to do?");
    }

    void printGoodbyeMessage() {
        System.out.println("Goodbye! Hope to see you again XOXO");
    }

    void printTasksMessage() {
        System.out.println("Here are all your tasks!");
    }

    void printMarkedTaskMessage(String string) {
        System.out.println("Good job on completing this task!" + "\n" + string);
    }

    void printUnmarkedTaskMessage(String string) {
        System.out.println("Remember to complete this task!!" + "\n" + string);
    }

    void printAddedTasks(String string, int size) {
        System.out.println(String.format("Got it! I've added this task to the list!"
                + "\n" + "%s" + "\n" + "Now you have %d tasks in the list!"
                + "\n", string, size));
    }

    void printDeletedTasks(String string, int size) {
        System.out.println(String.format("Alright, let me remove this task..."
                + "\n" + "%s" + "\n" + "Now you have %d tasks in the list!"
                + "\n", string, size));
    }

    void printWrongInput() {
        System.out.println("Wrong input!!");
    }

    void printFileNotFound() {
        System.out.println("File not found :(((");
    }

}
