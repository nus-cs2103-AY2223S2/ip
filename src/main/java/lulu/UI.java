package lulu;

import java.util.Scanner;

public class UI {
    private static final String LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    public void showGreetText() {
        System.out.println(LINE);
        System.out.println("Hello! I am lulu (=◕ᆽ◕ฺ=)");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showExitText() {
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon! (=◉ᆽ◉=)");
        System.out.println(LINE);
    }

    public void showAddText(String taskDescription, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskDescription);
        System.out.println("Now you have " + size + " tasks in the list. ฅ(=චᆽච=ฅ)");
        System.out.println(LINE);
    }

    public void showDeleteText(String taskDescription, int size) {
        System.out.println(LINE);
        System.out.println("Noted! I've removed this task:");
        System.out.println("  " + taskDescription);
        System.out.println("Now you have " + size + " tasks in the list. ฅ(=චᆽච=ฅ)");
        System.out.println(LINE);
    }

    public void listText() {
        System.out.println("ฅ(=චᆽච=ฅ) Here are the tasks in your list: ");
    }

    public void listMatchText() {
        System.out.println("ฅ(=චᆽච=ฅ) Here are the matching tasks in your list: ");
    }

    public void showMarkText(String taskMark) {
        System.out.println(LINE);
        System.out.println("(₌♥ᆽ♥₌) Nice! I've marked this task as done:");
        System.out.println(" " + taskMark);
        System.out.println(LINE);
    }

    public void showUnmarkText(String taskUnmark) {
        System.out.println(LINE);
        System.out.println("(₌ ᵕ̣̣̣̣̣ ᆽ ᵕ̣̣̣̣̣₌) OK, I've marked this task as not done yet:");
        System.out.println(" " + taskUnmark);
        System.out.println(LINE);
    }

    public void showLoad() {
        System.out.println("loading...");
    }

    public void showLoadComplete() {
        System.out.println("loading complete.");
    }

    public void showOutOfBounds() {
        System.out.println("(=ಠᆽಠ=) That is out of bounds!");
    }
}
