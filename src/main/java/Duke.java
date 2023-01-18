import java.util.*;
//TODO: COMMIT CHANGES
public class Duke {
    private static boolean isRunning = true;
    private static List<Task> tasks = new ArrayList<Task>();

    private static void endDuke() {
        isRunning = false;
        System.out.println("Bye! Hope to see you again soon!");
    }
    private static void showTasks() {
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(String.format("%d.%s", i, tasks.get(i - 1).printTask()));
            }
        } else {
            System.out.println("You currently have no items in your to-do list!");
        }
    }
    private static void markTask(String command) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            tasks.get(taskIndex).setDone(true);
            System.out.println("Okay! I've marked this task as done!");
            System.out.println(tasks.get(taskIndex).printTask());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Which task do you want to mark as done?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You don't have that many tasks!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }
    private static void unmarkTask(String command) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            tasks.get(taskIndex).setDone(false);
            System.out.println("Okay! I've marked this task as not done yet!");
            System.out.println(tasks.get(taskIndex).printTask());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Which task do you want to mark as undone?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You don't have that many tasks!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }
    private static void addTodo(String command) {
        try {
            command = command.substring("todo ".length());
            Task newTask = new Todo(command);
            tasks.add(newTask);
            System.out.println(String.format("Added: %s", newTask.printTask()));
        } catch (IllegalArgumentException e) {
            System.out.println("Oops! The description of todo cannot be empty!");
        }
    }
    private static void addDeadline(String command) {
        try {
            String delimiter = "/by ";
            String task = command.substring("deadline ".length(), command.indexOf(delimiter) - 1);
            String deadline = command.substring(command.indexOf(delimiter) + delimiter.length());
            Task newTask = new Deadline(task, deadline);
            tasks.add(newTask);
            System.out.println(String.format("Added: %s", newTask.printTask()));
        } /*catch (IllegalArgumentException e) {
            System.out.println("Oops! What task do you want to set a deadline for?");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Oops! Please use /by to specify the deadline for the task.");
        } */ catch (Exception e) {
            System.out.println("Sorry, that command is invalid. Specify a deadline task with \n >> deadline [description] /by [time]");
        }
    }
    private static void addEvent(String command) {
        try {
            String startDelimiter = "/from ";
            String endDelimiter = "/to ";
            String task = command.substring("event ".length(), command.indexOf(startDelimiter) - 1);
            String startTime = command.substring(
                    command.indexOf(startDelimiter) + startDelimiter.length(),
                    command.indexOf(endDelimiter) - 1);
            String endTime = command.substring(
                    command.indexOf(endDelimiter) + endDelimiter.length());
            Task newTask = new Event(task, startTime, endTime);
            tasks.add(newTask);
            System.out.println(String.format("Added: %s", newTask.printTask()));
        } catch (Exception e) {
            System.out.println("Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]");
        }
    }
    private static void noMatch() {
        System.out.println("Sorry, I didn't understand that, please ask again.");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        String separator = "____________________________________________________________";
        String duke = "Duke: ";
        String you = "You: ";
        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println(duke);
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);

        while (isRunning) {
            System.out.println(you);
            String command = sc.nextLine();
            System.out.println(separator);
            System.out.println(duke);

            if (command.equals("bye")) {
                endDuke();
            } else if (command.equals("list")) {
                showTasks();
            } else if (command.startsWith("mark ")) {
                markTask(command);
            } else if (command.startsWith("unmark ")) {
                unmarkTask(command);
            } else if (command.startsWith("todo ")) {
                addTodo(command);
            } else if (command.startsWith("deadline ")) {
                addDeadline(command);
            } else if (command.startsWith("event ")) {
                addEvent(command);
            } else {
                noMatch();
            }

            System.out.println(separator);
        }
    }
}
