package duke;

import duke.task.Task;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    static Stage stage;

    public Ui(Stage stage) {
        this.stage = stage;
    }

    public static String showAddMessage(Task taskNum, int size) {
        String result = "\tGot it. I've added this task:\n";
        result += ("\t\t" + taskNum);
        result += ("\tNow you have " + size + " tasks in the list.");
        return result;
    }

    public static String showDeleteMessage(Task taskNum, int size) {
        String result = "\tNoted. I've removed this task:\n";
        result += ("\t\t" + taskNum);
        result += ("\tNow you have " + size + " tasks in the list.");
        return result;
    }

    public static String showList(ArrayList<Task> tasks) {
        String result = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            result += String.format("\t%d. %s\n", taskNum, tasks.get(i));
        }
        return result;
    }

    public static String showFind(ArrayList<Task> tasks) {
        String result = ("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            result += String.format("\t%d. %s\n", taskNum, tasks.get(i));
        }
        return result;
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public String showError(String msg) {
        return(msg);
    }

    public static String showLoadingError() {
        String result = ("\tCannot load file.\n");
        return result;
    }

    public static String showGreeting() {
        String result = ("Hello! I'm Duke\n\tWhat can I do for you?\n");
        return result;
    }

    public static String showExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> stage.close());
        delay.play();
        String result = ("\tBye. Hope to see you again soon!\n");
        return result;
    }

    public static String showMessage(String msg) {
        return(msg);
    }
}