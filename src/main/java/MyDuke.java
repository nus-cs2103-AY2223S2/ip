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
                System.out.println("deadline not implemented yet");
                break;
            case "event":
                System.out.println("event not implemented yet");
                break;
            case "mark":
                toggle(tokens[1]);
                break;
            case "unmark":
                toggle(tokens[1]);
                break;
            default:
                String taskDesc = String.join(" ", tokens);
                Task newTask = new Task(taskDesc);
                allTasks.add(newTask); taskCount++;
                System.out.println("Successfully added:  "
                                    + newTask.toString());
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

    
}
