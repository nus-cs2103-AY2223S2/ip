package lulu;

import java.util.Scanner;
import java.lang.StringBuilder;

public class UI {
    private static final String LINE = "______________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    public String showContainer(String... content) {
        StringBuilder container = new StringBuilder();
        container.append(LINE);
        container.append('\n');
        for (String s : content) {
            container.append(s);
            container.append('\n');
        }
        container.append(LINE);
        return container.toString();
    }

    public void showGreetText() {
        System.out.println(LINE);
        System.out.println("Hello! I am lulu (=◕ᆽ◕ฺ=)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public String showExitText() {
        return ("Bye! Hope to see you again soon!");
    }

    public String showAddText(String taskDescription, int size) {
        StringBuilder text = new StringBuilder();
        text.append("Got it. I've added this task:");
        text.append('\n');
        text.append("  " + taskDescription);
        text.append('\n');
        text.append("Now you have " + size + " tasks in the list.");
        return text.toString();
    }

    public String showDeleteText(String taskDescription, int size) {
        StringBuilder text = new StringBuilder();
        text.append("Noted! I've removed this task:");
        text.append('\n');
        text.append("  " + taskDescription);
        text.append('\n');
        text.append("Now you have " + size + " tasks in the list.");
        return text.toString();
    }

    public String listText() {
        return (" Here are the tasks in your list: ");
    }

    public String listMatchText() {
        return ("Here are the matching tasks in your list: ");
    }

    public String showMarkText(String taskMark) {
        StringBuilder text = new StringBuilder();
        text.append("Nice! I've marked this task as done:");
        text.append('\n');
        text.append(" " + taskMark);
        return text.toString();
    }

    public String showUnmarkText(String taskUnmark) {
        StringBuilder text = new StringBuilder();
        text.append("OK, I've marked this task as not done yet:");
        text.append('\n');
        text.append(" " + taskUnmark);
        return text.toString();
    }

    public String showLoad() {
        return ("loading...");
    }

    public String showLoadComplete() {
        return ("loading complete.");
    }

    public String showOutOfBounds() {
        return ("(=ಠᆽಠ=) That is out of bounds!");
    }
}
