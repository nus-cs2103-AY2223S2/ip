import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static String horizontalLine = "************************";
    private static ArrayList<Task> taskArr = new ArrayList<>();
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

        try {
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
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                throw new DukeException("I DON'T UNDERSTAND THAT!");
            }
        } catch (DukeException e) {
            showUnknown(e.getMessage());
        }

    }

    private static void showUnknown(String errorMsg) {
        System.out.println(horizontalLine);
        System.out.println(":( SORRY! " + errorMsg);
        System.out.println(horizontalLine);
        readInput();
    }

    private static void addTodo(String input) throws DukeException {
        // Error handling
        if (input.length() <= 5) {
            throw new DukeException("TODO NEEDS A DESCRIPTION!");
        }

        // Get to-do name
        String todo = input.substring(5);

        // Error handling
        if (todo.length() == 0) {
            throw new DukeException("TODO DESCRIPTION CANNOT BE EMPTY!");
        }

        Task newTodo = new Task(todo);
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[T][ ] " + newTodo);
        taskArr.add(newTodo);
        count++;
        showCount();
        System.out.println(horizontalLine);
        readInput();
    }

    private static void addDeadline(String input) throws DukeException {
        // Error handling
        if (input.length() <= 9) {
            throw new DukeException("DEADLINE NEEDS A DESCRIPTION!");
        }

        // Get by String
        String inputInfo = input.substring(9);
        String[] inputs = inputInfo.split(" /by ");
        String taskName = inputs[0];
        String by = inputs[1];

        // Error handling
        if (taskName.length() == 0 || by.length() == 0) {
            throw new DukeException("DEADLINE DESCRIPTION AND BY DATE CANNOT BE EMPTY!");
        }

        Deadline newDeadline = new Deadline(taskName, by);
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[D][ ] " + newDeadline);
        taskArr.add(newDeadline);
        count++;
        showCount();
        System.out.println(horizontalLine);
        readInput();
    }

    private static void addEvent(String input) throws DukeException {
        // Error handling
        if (input.length() <= 6) {
            throw new DukeException("EVENT NEEDS A DESCRIPTION!");
        }

        // Get from and to Strings
        String inputInfo = input.substring(6);
        String[] inputs1 = inputInfo.split(" /from ");
        String taskName = inputs1[0];
        String[] inputs2 = inputs1[1].split(" /to ");
        String from = inputs2[0];
        String to = inputs2[1];

        // Error handling
        if (taskName.length() == 0 || from.length() == 0 || to.length() == 0) {
            throw new DukeException("EVENT DESCRIPTION AND FROM/TO DATE CANNOT BE EMPTY!");
        }

        Event newEvent = new Event(taskName, from, to);
        System.out.println(horizontalLine);
        System.out.println("OK. I'VE ADDED THIS TASK:");
        System.out.println("[E][ ] " + newEvent);
        taskArr.add(newEvent);
        count++;
        showCount();
        System.out.println(horizontalLine);
        readInput();
    }

    private static void deleteTask(String input) throws DukeException {
        // Error handling
        if (input.length() <= 7) {
            throw new DukeException("DELETE NEEDS A TASK NUMBER!");
        }

        // Get delete task number
        int index = Integer.parseInt(input.substring(7));

        System.out.println(horizontalLine);
        System.out.println("OK! I'VE DELETED THIS TASK: ");
        System.out.println("" + (index) + ". " +
                "[" + taskArr.get(index-1).getIcon() + "]" +
                "[" + taskArr.get(index-1).getStatusIcon() + "] " +
                taskArr.get(index-1));
        taskArr.remove(index-1);
        count--;
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showMark(String input) {
        int index = Integer.parseInt(input.substring(5));
        System.out.println(horizontalLine);
        System.out.println("I'VE MARKED THIS TASK AS DONE: ");
        System.out.println("[X] " + taskArr.get(index-1));
        taskArr.get(index-1).setDone(true);
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showUnmark(String input) {
        int index = Integer.parseInt(input.substring(7));
        System.out.println(horizontalLine);
        System.out.println("I'VE MARKED THIS TASK AS UNDONE: ");
        System.out.println("[ ] " + taskArr.get(index-1));
        taskArr.get(index-1).setDone(false);
        System.out.println(horizontalLine);
        readInput();
    }

    private static void showList() {
        System.out.println(horizontalLine);
        System.out.println("HERE ARE YOUR TASKS!");
        for (int i = 0; i < count; i++) {
            System.out.println("" + (i+1) + ". " +
                    "[" + taskArr.get(i).getIcon() + "]" +
                    "[" + taskArr.get(i).getStatusIcon() + "] " +
                    taskArr.get(i));
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
