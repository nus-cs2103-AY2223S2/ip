import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Duke {
    private static int counter;
    private static HashMap<Integer, Task> taskList;

    public static void main(String[] args)  {
        boolean terminate = false;
        counter = 0;
        taskList = new HashMap<>();

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (!terminate) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();

                if (!command.trim().isEmpty()) {
                    String[] inputs = command.split(" ");

                    // Once detect "bye", print and terminate
                    if (command.equals("bye")) {
                        terminate = true;
                        System.out.println("Bye. Hope to see you again soon!");

                    // If command is "list", prints the taskList
                    } else if (command.equals("list")) {
                        printList();

                    // if the first token is "mark" and second token is within limit
                    } else if (inputs[0].equals("mark") && inputs.length == 2) {
                        Integer task_no = Integer.parseInt(inputs[1]);
                        if (task_no <= inputs.length) {
                            mark(task_no);
                        } else {
                            System.out.println("Invalid task number!");
                        }

                    // if the first token is "unmark" and second token is within limit
                    } else if (inputs[0].equals("unmark") && inputs.length == 2) {
                        Integer task_no = Integer.parseInt(inputs[1]);
                        if (task_no <= inputs.length) {
                            unmark(task_no);
                        } else {
                            System.out.println("Invalid task number!");
                        }

                    } else if (inputs[0].equals("todo")) {
                        addToDo(command);

                    } else if (inputs[0].equals("deadline")) {
                        addDeadLine(command);

                    } else if (inputs[0].equals("event")) {
                        addEvent(command);

                    // Else handles the commands
                    } else {
                        addToDo(command);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
     * print_list iterates through the taskList and prints
     * out the status of each individual tasks
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list: ");

        for (Integer key: taskList.keySet()) {
            Task toDo = taskList.get(key);

            System.out.println(key + "." + toDo);

        }

    }

    /*
     * mark takes in an Integer key
     * and marks the corresponding task as completed
     */
    public static void mark(Integer key) {
        taskList.get(key).markCompleted();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(key));

    }

    /*
     * mark takes in an Integer key
     * and marks the corresponding task as not completed
     */
    public static void unmark(Integer key) {
        taskList.get(key).markUncompleted();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(key));
    }

    public static void addToDo(String command) {
        ToDo toDo = new ToDo(getTaskName("todo", command));
        taskList.put(++counter, toDo);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + toDo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void addDeadLine(String command) {
        String taskName = getTaskName("deadline", command);
        String endDate = getEndDate("deadline", command);

        Deadline deadline = new Deadline(taskName, endDate);
        taskList.put(++counter, deadline);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void addEvent(String command) {
        String taskName = getTaskName("event", command);
        String startDate = getStartDate(command);
        String endDate = getEndDate("event", command);

        Event event = new Event(taskName, startDate, endDate);
        taskList.put(++counter, event);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static String getTaskName(String type, String command) {
        if (type.equals("todo")) {
            int firstSpace = command.indexOf(" ");
            return command.substring(firstSpace + 1);
        } else if (type.equals("deadline")) {
            String startWord = "deadline ";
            String endWord = " /by";
            int startIndex = command.indexOf(startWord) + startWord.length();
            int endIndex = command.indexOf(endWord);
            return command.substring(startIndex, endIndex);
        } else {
            String startWord = "event ";
            String endWord = " /from";
            int startIndex = command.indexOf(startWord) + startWord.length();
            int endIndex = command.indexOf(endWord);
            return command.substring(startIndex, endIndex);
        }
    }

    public static String getStartDate(String command) {
        String startWord = "/from ";
        String endWord = " /to";
        int startIndex = command.indexOf(startWord) + startWord.length();
        int endIndex = command.indexOf(endWord);
        return command.substring(startIndex, endIndex);
    }

    public static String getEndDate(String type, String command) {
        if (type.equals("deadline")) {
            String keyword = "/by";
            int startIndex = command.indexOf(keyword) + keyword.length() + 1;
            return command.substring(startIndex);
        // Event
        } else {
            String keyword = "/to";
            int startIndex = command.indexOf(keyword) + keyword.length() + 1;
            return command.substring(startIndex);
        }
    }


}
