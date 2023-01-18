import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MyDuke {
    private ArrayList<Task> allTasks = new ArrayList<Task>();
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

    public void exec(String[] tokens) {

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
                toggle(tokens[1]);
                break;
            case "unmark":
                toggle(tokens[1]);
                break;
            default:
                System.out.println("invalid command");
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

    private void toggle(String token) {
        int taskIndex = Integer.parseInt(token);
        // What if token is not an integer?
        Task task = allTasks.get(taskIndex-1);
        if (!task.isDone()) {
            task.toggleDoneOrNot();
            System.out.println("Successfully completed:\n"
                                + " " + task.toString());
        } else {
            task.toggleDoneOrNot();
            System.out.println("Unmark Task:\n"
                                + " " + task.toString());
        }
    }

    private void addTodo(String[] tokens) {
        // if input is only "todo"
        if (tokens.length == 1) {
            // raise invalid command
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

        if (byIndex == -1) {
            // raise invalid command
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

        if (fromIndex == -1 || toIndex == -1) {
            // raise invalid command
        }

        String desc = String.join(" ",t.subList(1, fromIndex));
        String from = String.join(" ", t.subList(fromIndex+1, toIndex));
        String to = String.join(" ", t.subList(toIndex+1, t.size()));
        Event e = new Event(desc, from, to);
        addTask(e);
        System.out.println("Successfully added:\n" + e.toString());
    }
}
