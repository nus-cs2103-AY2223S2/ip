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
                String command = br.readLine().trim();

                if (!command.isEmpty()) {
                    String[] inputs = command.split(" ");

                    // Once detect "bye", print and terminate
                    if (command.equals("bye")) {
                        terminate = true;
                        System.out.println("Bye. Hope to see you again soon!");

                    } else if (command.equals("list")) {
                        printList();

                    } else if (inputs[0].equals("mark")) {
                        mark(command);

                    } else if (inputs[0].equals("unmark")) {
                        unmark(command);

                    } else if (inputs[0].equals("todo")) {
                        addToDo(command);

                    } else if (inputs[0].equals("deadline")) {
                        addDeadLine(command);

                    } else if (inputs[0].equals("event")) {
                        addEvent(command);

                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (DukeException e) {
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
    public static void mark(String command) throws DukeException {
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            Integer key = Integer.parseInt(inputs[1]);
            if (key > taskList.size()) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(key).markCompleted();

            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList.get(key));

        } else {
            throw new DukeException("Correct command: mark <valid task index>");
        }
    }

    /*
     * mark takes in an Integer key
     * and marks the corresponding task as not completed
     */
    public static void unmark(String command) throws DukeException {
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            Integer key = Integer.parseInt(inputs[1]);
            if (key > taskList.size()) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(key).markUncompleted();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + taskList.get(key));

        } else {
            throw new DukeException("Correct command: unmark <valid task index>");
        }
    }

    /*
     * adds todo base on the string command
     * todo only requires taskName
     */
    public static void addToDo(String command) throws DukeException{
        ToDo toDo = new ToDo(getTaskName("todo", command));
        taskList.put(++counter, toDo);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + toDo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /*
     * adds deadline base on the string command
     * deadline requires taskName and EndDate
     */
    public static void addDeadLine(String command) throws DukeException{
        String taskName = getTaskName("deadline", command);
        String endDate = getEndDate("deadline", command);

        Deadline deadline = new Deadline(taskName, endDate);
        taskList.put(++counter, deadline);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /*
     * adds event base on the string command
     * event requires taskName, StartDate and EndDate
     */
    public static void addEvent(String command) throws DukeException {
        String taskName = getTaskName("event", command);
        String startDate = getStartDate(command);
        String endDate = getEndDate("event", command);

        Event event = new Event(taskName, startDate, endDate);
        taskList.put(++counter, event);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + event);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /*
     * getTaskName checks the command line for the correct taskName syntax
     * throws exceptions for missing name or wrong keyword
     */
    public static String getTaskName(String type, String command) throws DukeException {
        if (type.equals("todo")) {
            int firstSpace = command.indexOf(" ");
            if (firstSpace == -1) {
                throw new DukeException("☹ OOPS!!! Missing Task Name.");
            } else {
                return command.substring(firstSpace + 1);
            }
        } else {
            String startWord = type.equals("deadline") ? "deadline " : "event ";
            String endWord = type.equals("deadline") ? " /by" : " /from";

            int startIndex = command.indexOf(startWord) + startWord.length();
            if (startIndex < startWord.length()) throw new DukeException("☹ OOPS!!! Missing Task Name.");
            int endIndex = command.indexOf(endWord);
            if (endIndex == -1) throw new DukeException("☹ OOPS!!! Missing" + endWord + " keyword.");
            return command.substring(startIndex, endIndex);
        }
    }

    /*
     * getStartDate checks the command line for the correct startDate syntax
     * throws exceptions for missing date or keyword
     */
    public static String getStartDate(String command) throws DukeException {
        String startWord = "/from";
        String endWord = " /to";
        int startIndex = command.indexOf(startWord) + startWord.length() + 1;
        if (startIndex > command.length()) throw new DukeException("☹ OOPS!!! Missing Start Date.");
        int endIndex = command.indexOf(endWord);
        if (endIndex == -1) throw new DukeException("☹ OOPS!!! Missing" + endWord + " keyword.");
        return command.substring(startIndex, endIndex);
    }

    /*
     * getEndDate checks the command line for the correct endDate syntax
     * throws exceptions for missing date or keyword
     */
    public static String getEndDate(String type, String command) throws DukeException {
        String keyword = type.equals("deadline") ? "/by" : "/to";
        int startIndex = command.indexOf(keyword) + keyword.length() + 1;
        if (startIndex > command.length()) throw new DukeException("☹ OOPS!!! Missing End Date.");
        return command.substring(startIndex);
    }

}
