import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void deadlineInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
        }
    }

    public void deleteInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to delete.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to delete.");
            }
        }
    }

    public boolean addDeadlineCheck(String s) {
        return s.startsWith("deadline");
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

    public void inputEvent(String s, String time) {
        Event event = new Event(s, time);
        list.add(event);
        addedTaskMessage(event);
    }

    public void inputDeadline(String s, LocalDate d) {
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

    public void addDeadlineFormatted(String input) {
        String[] constructor = input.replace("deadline ", "").split(" /by ");
        LocalDate temp = LocalDate.parse(constructor[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        inputDeadline(constructor[0], temp);
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
                    deleteInputChecker(input);
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if ( taskNumber > list.size() || taskNumber <= 0) {
                        throw new DukeException("OOPS!!! Invalid task number! Try again.");
                    }
                    deleteTaskFromList(Integer.parseInt(input.split(" ")[1]));
                } else if (addEventCheck(input)) { // check if input type is event
                    String[] eventConstructor = input.replace("event ", "").split("/at ");
                    String timeModified = eventConstructor[1].replace("from ", "");

                    inputEvent(eventConstructor[0], timeModified);
                } else if (addDeadlineCheck(input)) { // check if input type is deadline
                    deadlineInputChecker(input);
                    addDeadlineFormatted(input);
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





