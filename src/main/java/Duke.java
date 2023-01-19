import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
//    private ArrayList<String> list;
    private ArrayList<Task> list;

    public Duke() {
        this.list = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.runApp();
    }

    public void greetingMessage() {
        printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public void goodbyeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printMessage(String s) {
        printLongLine();
        System.out.println("\t" + s);
        printLongLine();
    }

    public void echoMessage(String s) {
        printMessage(s);
    }

    public void printLongLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void inputToTaskList(String s) {
        list.add(new Task(s));
        printMessage("added: " + s);
    }

    public void markTaskAsDone(int taskNumber) {
        list.get(taskNumber - 1).markAsDone();
        printMessage("Nice! I've marked this task as done: \n\t" + list.get(taskNumber - 1));
    }

    public void markTaskAsNotDone(int taskNumber) {
        list.get(taskNumber - 1).markAsNotDone();
        printMessage("OK, I've marked this task as not done yet: \n\t" + list.get(taskNumber - 1));
    }

    public void printList() {
        printLongLine();
        for (int i = 0; i < list.size(); i++) {
            int number = i + 1;
            System.out.printf("\t%d. %s\n", number, list.get(i));
        }
        printLongLine();
    }

    public void runApp() {
        greetingMessage();
        boolean enteredBye = false;
        while (!enteredBye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                enteredBye = true;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark ")) {
                markTaskAsDone(Integer.parseInt(input.split(" ")[1]));
            } else if (input.startsWith("unmark ")) {
                markTaskAsNotDone(Integer.parseInt(input.split(" ")[1]));
            } else {
                inputToTaskList(input);
            }
        }
        goodbyeMessage();
    }
}





