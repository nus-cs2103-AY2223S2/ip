public class MyDuke {
    private Task[] allTasks = new Task[100];
    private static int taskCount = 0;
    public static String feedback = "|    ";

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
        System.out.println(feedback + "Quitting MyDuke...\n" 
                            + feedback + "See you soon!");
    }

    public void exec(String[] tokens) {

        switch (tokens[0]) {
            case "list":
                showAll();
                break;
            case "todo":
                System.out.println("todo not yet implemented");
                break;
            case "deadline":
                System.out.println("deadline not implemented yet");
                break;
            case "event":
                System.out.println("event not implemented yet");
            default:
                String taskDesc = String.join(" ", tokens);
                Task newTask = new Task(taskDesc);
                allTasks[taskCount] = newTask; taskCount++;
                System.out.println(feedback + "Successfully added:  "
                                    + newTask.getDescription());
        }
    }

    public void showAll() {
        System.out.println("All Tasks:");
        for (Integer i = 0; i < taskCount; i++) {
            String showString = Integer.toString(i+1)+ ": "
                                + allTasks[i].getDescription();
            System.out.println(feedback + showString);     
        }
        System.out.println();
    }
}
