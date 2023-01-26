package duke;

import java.util.List;
import java.util.Scanner;

public class UIText {
    private static final String GREET = "Hello! I am Kevin, an interactive chatbot. What can I do for you?" + "\n";
    private static final String EXIT = "Bye! Hope to talk to you again!";
    private static final String SEPARATOR = "____________________________________________________________";
    private Scanner sc;
    public UIText() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public String greet() {
        return GREET;
    }
    public String exit() {
        sc.close();
        return EXIT;
    }

    public String separate() {
        return SEPARATOR + "\n";
    }


    public static String printFind(List<Task> wordList) {
        String heading = "Here are the matching tasks in your list: " + "\n";
        String list = "";
        if (wordList.size() == 0) {
            return "No tasks found. ";
        } else {
            int indexCounter = 1;
            for (Task task : wordList) {
                list += indexCounter + ". " + task.toString() + "\n";
                indexCounter++;
            }
        }
        return heading + list;
    }
}
