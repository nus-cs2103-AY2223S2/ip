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
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
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

    public boolean addEventCheck(String s) {
        return s.startsWith("event ");
    }

    public void todoInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    }

    public boolean addDeadlineCheck(String s) {
        return s.startsWith("deadline ");
    }

    public boolean addTodoCheck(String s) {
        return s.startsWith("todo");
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

    public void deleteTaskFromList(int i) {
        Task toDelete = list.get(i - 1);
        list.remove(i - 1);
        deletedTaskMessage(toDelete);
    }

    public void inputEvent(String s, String timeFrom, String timeTo) {
        Event event = new Event(s, timeFrom, timeTo);
        list.add(event);
        addedTaskMessage(event);
    }

    public void inputDeadline(String s, String d) {
        Deadline deadline = new Deadline(s, d);
        list.add(deadline);
        addedTaskMessage(deadline);

    }

    public void inputTodo(String s) {
        Todo todo = new Todo(s);
        list.add(todo);
        addedTaskMessage(todo);
    }

    public void addedTaskMessage(Task t) {
        printLongLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        printLongLine();
    }

    public void deletedTaskMessage(Task t) {
        printLongLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + list.size() + " tasks in the list");
        printLongLine();
    }

    public void markTaskAsDone(int taskNumber) {
        list.get(taskNumber - 1).markAsDone();
        printMessage("Nice! I've marked this task as done:\n\t" + list.get(taskNumber - 1));
    }

    public void markTaskAsNotDone(int taskNumber) {
        list.get(taskNumber - 1).markAsNotDone();
        printMessage("OK, I've marked this task as not done yet:\n\t" + list.get(taskNumber - 1));
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
            try {
                if (input.equals("bye")) {
                    enteredBye = true;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark ")) {
                    markTaskAsDone(Integer.parseInt(input.split(" ")[1]));
                } else if (input.startsWith("unmark ")) {
                    markTaskAsNotDone(Integer.parseInt(input.split(" ")[1]));
                } else if (input.startsWith("delete ")) {
                    deleteTaskFromList(Integer.parseInt(input.split(" ")[1]));
                } else if (addEventCheck(input)) { // check if input type is event
                    String[] eventConstructor = input.replace("event ", "").split("/");
                    String timeFromModified = eventConstructor[1].replace("from ", "");
                    String timeToModified = eventConstructor[2].replace("to ", "");
                    inputEvent(eventConstructor[0], timeFromModified, timeToModified);
                } else if (addDeadlineCheck(input)) { // check if input type is deadline
                    String[] deadlineConstructor = input.replace("deadline ", "").split(" /by ");
                    inputDeadline(deadlineConstructor[0], deadlineConstructor[1]);
                } else if (addTodoCheck(input)) { // check if input type is todo
                    todoInputChecker(input);
                    inputTodo(input.replace("todo ", ""));
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
        goodbyeMessage();
    }
}





