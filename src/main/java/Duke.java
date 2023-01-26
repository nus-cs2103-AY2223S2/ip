import java.io.*;
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

    public void inputEvent(String s, String time) {
        Event event = new Event(s, time);
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

    public void saveListToOutput() throws DukeException {
        ArrayList<String> temp = new ArrayList<>();
        try {
            File file = new File("./data/duke.txt");
            file.getParentFile().mkdir();

            FileWriter fw = new FileWriter(file);

            for (int i = 0; i < list.size(); i++) {
                temp.add(list.get(i).sendOutputToFile());
            }
            fw.write(String.join("\n", temp));
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Error when adding file");
        }
    }

    public void outputListToHardDrive() throws DukeException {
        File file = new File("./data/duke.txt");
        file.getParentFile().mkdirs();

        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String[] curr = scanner.nextLine().split(" \\| ");
                    String taskType = curr[0];
                    switch (taskType) {
                        case "E":
                            Event event = new Event(curr[2], curr[3]);
                            if (Integer.parseInt(curr[1]) == 1) {
                                event.markAsDone();
                            } else {
                                event.markAsNotDone();
                            }
                            list.add(event);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(curr[2], curr[3]);
                            if (Integer.parseInt(curr[1]) == 1) {
                                deadline.markAsDone();
                            } else {
                                deadline.markAsNotDone();
                            }
                            list.add(deadline);
                            break;

                        case "T":
                            Todo todo = new Todo(curr[2]);
                            if (Integer.parseInt(curr[1]) == 1) {
                                todo.markAsDone();
                            } else {
                                todo.markAsNotDone();
                            }
                            list.add(todo);
                            break;
                        default:
                            throw new DukeException("Error: Wrong task");
                    }
                }

            }
        } catch (Exception ex) {
            throw new DukeException("Exception has occurred");
        }

    }

    public void runApp() {
        try {
            outputListToHardDrive();
        } catch (DukeException e) {
            printMessage(e.getMessage());
        }
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
                    String[] deadlineConstructor = input.replace("deadline ", "").split(" /by ");
                    inputDeadline(deadlineConstructor[0], deadlineConstructor[1]);
                } else if (addTodoCheck(input)) { // check if input type is todo
                    todoInputChecker(input);
                    inputTodo(input.replace("todo ", ""));
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                saveListToOutput();
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
        goodbyeMessage();
    }
}





