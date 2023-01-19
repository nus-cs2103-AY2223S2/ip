import task.Task;
import task.TaskList;
import java.util.Scanner;

public class Duke {

    /**
     * Returns true if a String is numerical; return false otherwise.
     *
     */
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the number of '/' characters in the command.
     *
     * @return The number of '/' characters in the command
     */
    public static int countSlash(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '/') {
                count ++;
            }
        }
        return count;
    }

    /**
     * Executes the mark command by marking the task as done.
     *
     * @param taskList The tasklist that the task is in
     * @param t The task to be marked as done
     * @throws DukeException
     */
    public static void markCommand(TaskList taskList, Task t) throws DukeException {
        if (t.isMarked()) {
            throw new DukeException("This task has already been marked as done.");
        } else {
            t.mark();
            System.out.println("Great job on completing this task! I've marked it as done:");
            System.out.println(t);
        }
        if (taskList.isAllCompleted()) {
            System.out.println("Yay! You have completed all your tasks :-)");
        }
    }

    /**
     * Executes the unmark command by unmarking the task.
     *
     * @param taskList The tasklist that the task is in
     * @param t The task to be marked as undone
     * @throws DukeException
     */
    public static void unmarkCommand(TaskList taskList, Task t) throws DukeException {
        if (!t.isMarked()) {
            throw new DukeException("Oops! This task has not been marked as done before.");
        } else {
            t.unMark();
            System.out.println("Alright, I've marked this task as not done yet:");
            System.out.println(t);
        }
    }

    /**
     * Executes the list command by listing the tasks in the tasklist.
     *
     * @param taskList The tasklist to be listed out
     */
    public static void listCommand(TaskList taskList) {
        if (taskList.getNumTasks() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
    }

    /**
     * Executes the todo command.
     *
     * @param tasklist The tasklist to add the todo
     * @param desc The title of the todo
     */
    public static void todoCommand(TaskList tasklist, String desc) {
        System.out.println("Got it, I've added this task:");
        tasklist.addTodo(desc);
    }

    /**
     * Executes the deadline command.
     *
     * @param taskList The tasklist to add the deadline
     * @param desc The title of the deadline
     * @param date The time/date of the deadline
     */
    public static void deadlineCommand(TaskList taskList, String desc, String date) {
        System.out.println("Got it, I've added this task:");
        taskList.addDeadline(date, desc);
    }

    /**
     * Executes the event command.
     *
     * @param taskList The tasklist to add the event
     * @param start The start time/date of the event
     * @param end The end time/date of the event
     * @param desc The title of the event
     */
    public static void eventCommand(TaskList taskList, String start, String end, String desc) {
        System.out.println("Got it, I've added this task:");
        taskList.addEvent(start, end, desc);
    }

    /**
     * Executes the delete command.
     *
     * @param taskList The tasklist to delete the task from
     * @param taskNum The task number of the task to be deleted
     */
    public static void deleteCommand(TaskList taskList, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        taskList.deleteTask(taskNum);
    }

    /**
     * Executes the next command.
     */
    public static void nextCommand() {
        System.out.println("What else can I do for you?");
    }

    /**
     * Executes the bye command.
     */
    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon! :-p");
    }

    /**
     * Checks if the command is in the right format in order to execute the mark command.
     *
     */
    public static void checkMark(TaskList taskList, String command) throws DukeException {
        String arr[] = command.split("\\s+");
        if (arr.length == 2 && isNumber(arr[1]) && (arr[0].equals("mark") || arr[0].equals("unmark"))) {
            // check if task exists
            int taskNum = Integer.parseInt(arr[1]);
            if (taskList.doesTaskExist(taskNum)) {
                // mark or unmark task
                if (arr[0].equals("mark")) {
                    markCommand(taskList, taskList.getTask(taskNum - 1));
                } else if (arr[0].equals("unmark")){
                    unmarkCommand(taskList, taskList.getTask(taskNum - 1));
                }
            } else {
                throw new DukeException("Huh... the task does not exist.");
            }
        } else {
            throw new DukeException("Hmm... I can't quite understand you :-/");
        }
    }

    /**
     * Checks if the command is in the right format to execute a delete command.
     * @param taskList
     * @param command
     * @throws DukeException
     */
    public static void checkDelete(TaskList taskList, String command) throws DukeException {
        String arr[] = command.split("\\s+");
        if (arr.length == 2 && arr[0].equals("delete")) {
            if (isNumber(arr[1])) {
                if (taskList.doesTaskExist(Integer.parseInt(arr[1]))) {
                    deleteCommand(taskList, Integer.parseInt(arr[1]));
                } else {
                    throw new DukeException("Huh... the task does not exist.");
                }
            } else {
                throw new DukeException("Oops! You need to specify the task number for me to delete it.");
            }
        } else {
            throw new DukeException("Hmm... I can't quite understand you :-/");
        }
    }

    /**
     * Checks the command in order to execute the corresponding command correctly.
     */
    public static void checkCommand(TaskList taskList, String command) throws DukeException {
        String arr[] = command.split("\\s+");
        if (arr.length == 1) {
            if (arr[0].equals("todo") || arr[0].equals("event") || arr[0].equals("deadline")) {
                String e = String.format("Oops! The description of a %s cannot be empty.", arr[0]);
                throw new DukeException(e);
            } else if (command.equals("mark") || command.equals("unmark")
                    || command.equals("mark ") || command.equals("unmark ") || command.equals("delete") || command.equals("delete ")) {
                String e = String.format("Oops! You need to specify the task number for me to %s it.", command);
                throw new DukeException(e);
            } else {
                throw new DukeException("Hmm... I can't quite understand you :-/");
            }
        } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
            checkMark(taskList, command);
        } else {
            if (arr[0].equals("todo")) {
                String desc = command.split(" ", 2)[1];
                todoCommand(taskList, desc);
            } else if (arr[0].equals("deadline")) {
                if (countSlash(command) != 1) {
                    throw new DukeException("Please specify the deadline.");
                } else {
                    String segments[] = command.split("/");
                    String deadline = segments[segments.length - 1];
                    String date = deadline.split(" ", 2)[1];
                    String subSegments[] = segments[0].split(" ", 2);
                    String desc = subSegments[1];
                    deadlineCommand(taskList, desc, date);
                }
            } else if (arr[0].equals("event")) {
                if (countSlash(command) != 2) {
                    throw new DukeException("Please specify both the start and end times/dates.");
                } else {
                    String segments[] = command.split("/", 3);
                    String start = segments[segments.length - 2].split(" ", 2)[1];
                    String end = segments[segments.length - 1].split(" ", 2)[1];
                    String subSegments[] = segments[0].split(" ", 2);
                    String desc = subSegments[1];
                    eventCommand(taskList, start, end, desc);
                }
            } else if (arr[0].equals("delete")) {
                checkDelete(taskList, command);
            } else {
                throw new DukeException("Hmm... I can't quite understand you :-/");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        TaskList taskList = new TaskList();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand(taskList);
            } else {
                try {
                    checkCommand(taskList, command);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            nextCommand();
            command = sc.nextLine();
        }
        byeCommand();
    }
}
