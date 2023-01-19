import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static String horizontalLine = "************************";
    private static Task[] taskArr = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(horizontalLine);
        System.out.println("HELLO! I'M DUKE!");
        System.out.println("HOW CAN I HELP?");
        System.out.println(horizontalLine);
        readInput();
    }

    private static void readInput() {
        String input = scanner.nextLine();

        if (input.equals("bye")) {
            showBye();
        } else if (input.equals("list")) {
            showList();
        } else if (input.startsWith("mark")) {
            showMark(input);
        } else if (input.startsWith("unmark")) {
            showUnmark(input);
        } else if (input.startsWith("todo")) {
            addTodo(input);
        } else if (input.startsWith("deadline")) {
            addDeadline(input);
        } else if (input.startsWith("event")) {
            addEvent(input);
        }
    }

    private static void addTodo(String input) {
        // Get todo name
        String todo = input.substring(5);

        Task newTodo = new Task(todo);
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[T][ ] " + newTodo);
        taskArr[count] = newTodo;
        count++;
        showCount();
        System.out.println(horizontalLine);
        readInput();
    }

    private static void addDeadline(String input) {
        // Get by String
        String inputInfo = input.substring(9);
        String[] inputs = inputInfo.split(" /by ");
        String taskName = inputs[0];
        String by = inputs[1];

        Deadline newDeadline = new Deadline(taskName, by);
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[D][ ] " + newDeadline);
        taskArr[count] = newDeadline;
        count++;
        showCount();
        System.out.println(horizontalLine);
        readInput();
    }

    private static void addEvent(String input) {
        // Get from and to Strings
        String inputInfo = input.substring(6);
        String[] inputs1 = inputInfo.split(" /from ");
        String taskName = inputs1[0];
        String[] inputs2 = inputs1[1].split(" /to ");
        String from = inputs2[0];
        String to = inputs2[1];

        Event newEvent = new Event(taskName, from, to);
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[E][ ] " + newEvent);
        taskArr[count] = newEvent;
        count++;
        showCount();
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showMark(String input) {
        int index = Integer.parseInt(input.substring(5, 6));
        System.out.println(horizontalLine);
        System.out.println("I'VE MARKED THIS TASK AS DONE: ");
        System.out.println("[X] " + taskArr[index-1]);
        taskArr[index-1].setDone(true);
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showUnmark(String input) {
        int index = Integer.parseInt(input.substring(7, 8));
        System.out.println(horizontalLine);
        System.out.println("I'VE MARKED THIS TASK AS UNDONE: ");
        System.out.println("[ ] " + taskArr[index-1]);
        taskArr[index-1].setDone(false);
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showList() {
        System.out.println(horizontalLine);
        System.out.println("HERE ARE YOUR TASKS!");
        for (int i = 0; i < count; i++) {
            System.out.println("" + (i+1) + ". " +
                    "[" + taskArr[i].getIcon() + "]" +
                    "[" + taskArr[i].getStatusIcon() + "] " +
                    taskArr[i]);
        }
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showCount() {
        String plural = "";
        if (count > 1) {
            plural = "S";
        }
        System.out.println("NOW YOU HAVE " + count + " TASK" + plural + " IN THE LIST!");
    }

    private static void showBye() {
        System.out.println(horizontalLine);
        System.out.println("BYE!");
        System.out.println(horizontalLine);
    }

}
