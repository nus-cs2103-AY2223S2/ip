import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean DukeContinues = true;
        ArrayList<Task> taskList = new ArrayList<>();
        String filePath = "data/duke.txt";
        File file = new File(filePath);
        while (DukeContinues) {
            String s = sc.nextLine();
            String command = "";
            String restOfString = "";
            boolean taskListChanged = false;
            if (s.contains(" ")) {
                command = s.substring(0, s.indexOf(" "));
                restOfString = s.substring(s.indexOf(" ") + 1);
            } else {
                command = s;
            }
            try {
                switch (command) {
                    case "bye" -> {
                        Duke.bye();
                        DukeContinues = false;
                    }
                    case "list" -> Duke.list(taskList);
                    case "mark" -> {
                        Duke.mark(taskList, restOfString);
                        taskListChanged = true;
                    }
                    case "unmark" -> {
                        Duke.unmark(taskList, restOfString);
                        taskListChanged = true;
                    }
                    case "todo" -> {
                        Duke.addTodo(taskList, restOfString);
                        taskListChanged = true;
                    }
                    case "deadline" -> {
                        Duke.addDeadline(taskList, restOfString);
                        taskListChanged = true;
                    }
                    case "event" -> {
                        Duke.addEvent(taskList, restOfString);
                        taskListChanged = true;
                    }
                    case "delete" -> {
                        Duke.delete(taskList, restOfString);
                        taskListChanged = true;
                    }
                    default -> throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if (taskListChanged) {
                    file.createNewFile();
                    FileWriter fw = new FileWriter(filePath);
                    for (Task t : taskList) {
                        fw.write(t.toString());
                        fw.write("\n");
                    }
                    fw.close();
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon! :^)");
    }

    private static void list(ArrayList<Task> taskList) {
        int taskCount = taskList.size();
        if (taskCount == 0) {
            System.out.println("You don't have any tasks now!");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    private static void mark(ArrayList<Task> taskList, String restOfString) throws DukeException {
        try {
            int i = Integer.parseInt(restOfString);
            taskList.get(i - 1).markUnmark(true);
        } catch (NumberFormatException err) {
            throw new DukeException("☹ OOPS!!! " + restOfString + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("☹ OOPS!!! There are less than " + restOfString + " tasks.");
        }
    }

    private static void unmark(ArrayList<Task> taskList, String restOfString) throws DukeException {
        try {
            int i = Integer.parseInt(restOfString);
            taskList.get(i - 1).markUnmark(false);
        } catch (NumberFormatException err) {
            throw new DukeException("☹ OOPS!!! " + restOfString + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("☹ OOPS!!! There are less than " + restOfString + " tasks.");
        }
    }

    private static void addTodo(ArrayList<Task> taskList, String restOfString) throws DukeException {
        if (restOfString.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            ToDo t = ToDo.addToDo(restOfString);
            taskList.add(t);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    private static void addDeadline(ArrayList<Task> taskList, String restOfString) throws DukeException {
        if (restOfString.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            Deadline t = Deadline.addDeadline(restOfString);
            taskList.add(t);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    private static void addEvent(ArrayList<Task> taskList, String restOfString) throws DukeException {
        if (restOfString.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        } else {
            Event t = Event.addEvent(restOfString);
            taskList.add(t);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    private static void delete(ArrayList<Task> taskList, String restOfString) throws DukeException {
        try {
            int i = Integer.parseInt(restOfString);
            Task removedTask = taskList.remove(i-1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException err) {
            throw new DukeException("☹ OOPS!!! " + restOfString + " is not a valid integer for indexing the task list.");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("☹ OOPS!!! There are less than " + restOfString + " tasks.");
        }
    }

}
