import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class MyDuke {
    private static ArrayList<Task> allTasks = new ArrayList<Task>();
    private static int taskCount = 0;
    private static String reply = "|     ";

    public void init() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo
                            + "\nWhat's on your mind today?\n");
    }

    public void quit() {
        // To add: Deleting all tasks...
        System.out.println( "Quitting MyDuke...\n" 
                            + "See you soon!");
    }

    public void exec(String[] tokens) throws InvalidCommandException {
        try {
            switch (tokens[0]) {
                case "list":
                    showAll();
                    break;
                case "todo":
                    addTodo(tokens);
                    showCount();
                    break;
                case "deadline":
                    addDeadline(tokens);
                    showCount();
                    break;
                case "event":
                    addEvent(tokens);
                    showCount();
                    break;
                case "mark":
                    toggle(tokens);
                    break;
                case "unmark":
                    toggle(tokens);
                    break;
                case "delete":
                    delete(tokens);
                    break;
                default:
                    throw new InvalidCommandException("Invalid Command! Try: " +
                                                    "[list, todo, deadline, event, mark/unmark]");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.errorMessage);
        }
    }

    private void showCount() {
        String isare;
        String s;
        if (taskCount > 1) {
            isare = " are: ";
            s = " tasks";
        } else {
            isare = " is: ";
            s = " task";
        }
        System.out.println("\nThere" + isare + Integer.toString(taskCount) 
                            + s + " in the list.\n");
    }

    private void addTask(Task task) {
        allTasks.add(task);
        taskCount++;
    }

    private void showAll() {
        System.out.println("All Tasks:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = "   "  + Integer.toString(i+1)+ ": "
                                + allTasks.get(i).toString();
            System.out.println(showString);     
        }
        System.out.println();
    }

    private void toggle(String[] tokens) {
        int taskIndex = 0;

        try {
            // Can we handle batch mark/unmark?
            if (tokens.length == 1 || tokens.length > 2) {
                throw new InvalidCommandException(
                    "ERROR: Please mark/unmark tasks with task number! Find the task number with 'list'.");
            }
            taskIndex = Integer.parseInt(tokens[1]);
            if (taskIndex <= 0 || taskIndex > taskCount) {
                throw new InvalidCommandException(
                    "ERROR: No such task. View all tasks with 'list'");
            }
        // NaN input from user to mark/unmark task
        } catch (NumberFormatException e) {
            System.out.println(
                "ERROR: Please mark/unmark tasks with task number! Find the task number with 'list'.");
            return;
        } catch (InvalidCommandException e) {
            System.out.println(e.errorMessage);
            return;
        }

        Task task = allTasks.get(taskIndex-1);
        if (!task.isDone() && tokens[0].equals("mark")) {
            task.toggleDoneOrNot();
            System.out.println("Successfully completed:\n"
                                + " " + task.toString());
        } else if (task.isDone() && tokens[0].equals("unmark")) {
            task.toggleDoneOrNot();
            System.out.println("Unmark Task:\n"
                                + " " + task.toString());
        } else if (!task.isDone() && tokens[0].equals("unmark")) {
            System.out.println("Task not marked yet: " + task.toString());
        } else if (task.isDone() && tokens[0].equals("mark")) {
            System.out.println("Task already done: " + task.toString());
        }
    }

    private void addTodo(String[] tokens) {
        // if input is only "todo"
        try {
            if (tokens.length == 1) {
                // raise invalid command
                throw new InvalidCommandException(
                    "ERROR: Missing task name. Add todo task with: todo {name}.");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.errorMessage);
            return;
        }

        String t = "";
        for (String s : tokens) {
            if (!s.equals("todo")) {
                t += " " + s;
            }
        }

        ToDo todo = new ToDo(t);
        addTask(todo);
        System.out.println("Successfully added:\n" + todo.toString());
    }

    private void addDeadline(String[] tokens) {
        List<String> t = Arrays.asList(tokens);
        int byIndex = t.indexOf("/by");

        try {
            if (byIndex == -1) {
                throw new InvalidCommandException(
                    "ERROR: Set a deadline with: deadline {task name} /by {deadline}");
            }
            if (byIndex + 1 == t.size()) {
                throw new InvalidCommandException(
                    "ERROR: Please specify a date/time.");
            }
            if (byIndex == 1) {
                throw new InvalidCommandException(
                    "ERROR: Missing task name.");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.errorMessage);
            return;
        }

        String desc = String.join(" ",t.subList(1, byIndex));
        String byString = String.join(" ", t.subList(byIndex+1, t.size()));
        Deadline d = new Deadline(desc, byString);
        addTask(d);
        System.out.println("Successfully added:\n" + d.toString());           
    }

    private void addEvent(String[] tokens) {
        List<String> t = Arrays.asList(tokens);
        int fromIndex = t.indexOf("/from"); int toIndex = t.indexOf("/to");

        try {
            if (fromIndex == -1 || toIndex == -1) {
                // raise invalid command
                throw new InvalidCommandException(
                    "ERROR: Missing 'from' and 'to times'. Please specify 'from' and 'to' times.");
            }

            if (fromIndex + 1 == toIndex || toIndex + 1 == t.size()) {
                throw new InvalidCommandException(
                    "ERROR: Please specify both 'from' and 'to' times");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.errorMessage);
            return;
        }

        String desc = String.join(" ",t.subList(1, fromIndex));
        String from = String.join(" ", t.subList(fromIndex+1, toIndex));
        String to = String.join(" ", t.subList(toIndex+1, t.size()));
        Event e = new Event(desc, from, to);
        addTask(e);
        System.out.println("Successfully added:\n" + e.toString());
    }

    private void delete(String[] tokens) {
        int taskIndex = 0;

        try {
            // Can we handle batch delete?
            if (tokens.length == 1 || tokens.length > 2) {
                throw new InvalidCommandException(
                    "ERROR: Missing task to delete. Please specify a task.");
            }

            taskIndex = Integer.parseInt(tokens[1]);
            if (taskIndex <= 0 || taskIndex > taskCount) {
                throw new InvalidCommandException(
                    "ERROR: No such task. View all tasks with 'list'");
            }
        } catch (NumberFormatException e) {
            System.out.println(
                "ERROR: Please mark/unmark tasks with task number! Find the task number with 'list'.");
            return;
        } catch (InvalidCommandException e) {
            System.out.println(e.errorMessage);
            return;
        }

        System.out.println(allTasks.get(taskIndex).toString() + " deleted.");
        allTasks.remove(taskIndex);
        taskCount--;
        showCount();
    }
}
