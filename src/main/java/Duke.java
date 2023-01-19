import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList;

    public static void main(String[] args)  {
        boolean terminate = false;
        taskList = new ArrayList<>();

        String logo = "____    ____  __    __   __    __  \n"
                    + "\\   \\  /   / |  |  |  | |  |  |  |\n"
                    + " \\   \\/   /  |  |  |  | |  |  |  | \n"
                    + "  \\_    _/   |  |  |  | |  |  |  | \n"
                    + "    |  |     |  `--'  | |  `--'  | \n"
                    + "    |__|      \\______/   \\______/  \n";
        System.out.println("           Hello! I am\n" + logo);
        System.out.println("    What can I do for you?");


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!terminate) {

            try {
                
                String command = br.readLine().trim();

                if (!command.isEmpty()) {
                    String[] inputs = command.split(" ");

                    // Once detect "bye", print and terminate
                    if (command.equals("bye")) {
                        terminate = true;
                        System.out.println("    Bye. Hope to see you again soon!");

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

                    } else if (inputs[0].equals("delete")) {
                        deleteTask(command);

                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }

            } catch (IOException e) {
                System.out.println("    " + e.getMessage());
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            }
        }
    }

    /*
     * print_list iterates through the taskList and prints
     * out the status of each individual tasks
     */
    public static void printList() {
        System.out.println("    Here are the tasks in your list: ");

        for (int i = 0; i < taskList.size(); i++) {
            Task toDo = taskList.get(i);

            System.out.println("    " + (i+1) + "." + toDo);

        }
    }

    /*
     * Mark takes in a String command
     * and handles the command
     * and marks the corresponding task as completed
     */
    public static void mark(String command) throws DukeException {
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(ind).markCompleted();

            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + taskList.get(ind));

        } else {
            throw new DukeException("    Correct command: mark <valid task index>");
        }
    }

    /*
     * Unmark takes in a String command
     * and handles the command
     * before unmarking the corresponding task as not completed
     */
    public static void unmark(String command) throws DukeException {
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            taskList.get(ind).markUncompleted();

            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + taskList.get(ind));

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
        taskList.add(toDo);

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + toDo);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    /*
     * adds deadline base on the string command
     * deadline requires taskName and EndDate
     */
    public static void addDeadLine(String command) throws DukeException{
        String taskName = getTaskName("deadline", command);
        String endDate = getEndDate("deadline", command);

        Deadline deadline = new Deadline(taskName, endDate);
        taskList.add(deadline);

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + deadline);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
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
        taskList.add(event);

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + event);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
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

    /*
     * delete task by removing the Task at the corresponding index
     * throws exception for wrong syntax and invalid task number
     */
    public static void deleteTask(String command) throws DukeException {
        String[] inputs = command.split(" ");
        if (inputs.length == 2) {
            int ind = Integer.parseInt(inputs[1]) - 1;
            if (ind >= taskList.size() || ind < 0) throw new DukeException("☹ OOPS!!! Invalid task number :(");

            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + taskList.get(ind));

            taskList.remove(ind);

        } else {
            throw new DukeException("    Correct command: delete <valid task index>");
        }
    }
}
