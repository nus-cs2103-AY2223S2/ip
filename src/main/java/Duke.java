import task.TaskList;
import java.util.Scanner;

public class Duke {
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static void checkMark(TaskList taskList, Command command) {
        String str = command.str;
        String arr[] = str.split("\\s+");
        if (arr.length == 2 && isNumber(arr[1]) && (arr[0].equals("mark") || arr[0].equals("unmark"))) {
            // check if task exists
            int taskNum = Integer.parseInt(arr[1]);
            if (taskList.doesTaskExist(taskNum)) {
                // mark or unmark task
                if (arr[0].equals("mark")) {
                    command.markCommand(taskList, taskList.getTask(taskNum - 1));
                } else if (arr[0].equals("unmark")){
                    command.unmarkCommand(taskList, taskList.getTask(taskNum - 1));
                }
            } else {
                System.out.println("Huh... the task does not exist.");
            }
        } else {
            System.out.println("I can't quite understand you :-/");
        }
    }

    public static void checkCommand(TaskList taskList, Command command) {
        String str = command.str;
        String arr[] = str.split("\\s+");
        if (arr.length == 1 && !str.equals("mark") && !str.equals("unmark")
                && !str.equals("mark ") && !str.equals("unmark ")) {
            System.out.println("I can't quite understand you :-/");
        } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
            checkMark(taskList, command);
        } else {
            if (arr[0].equals("todo")) {
                String desc = str.split(" ", 2)[1];
                command.todoCommand(taskList, desc);
            } else if (arr[0].equals("deadline")) {
                if (command.countSlash() != 1) {
                    System.out.println("Please specify the deadline.");
                } else {
                    String segments[] = str.split("/");
                    String deadline = segments[segments.length - 1];
                    String date = deadline.split(" ", 2)[1];
                    String subSegments[] = segments[0].split(" ", 2);
                    String desc = subSegments[1];
                    command.deadlineCommand(taskList, desc, date);
                }
            } else if (arr[0].equals("event")) {
                if (command.countSlash() != 2) {
                    System.out.println("Please specify both the start and end times/dates.");
                } else {
                    String segments[] = str.split("/", 3);
                    String start = segments[segments.length - 2].split(" ", 2)[1];
                    String end = segments[segments.length - 1].split(" ", 2)[1];
                    String subSegments[] = segments[0].split(" ", 2);
                    String desc = subSegments[1];
                    command.eventCommand(taskList, start, end, desc);
                }
            } else {
                System.out.println("I can't quite understand you :-/");
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
        Command command = new Command(sc.nextLine());
        TaskList taskList = new TaskList();

        while (!command.str.equals("bye")) {
            if (command.str.equals("list")) {
                command.listCommand(taskList);
            } else if (command.str.equals("blah")) {
                command.blahCommand();
            } else {
                checkCommand(taskList, command);
            }
            command.nextCommand();
            command = new Command(sc.nextLine());
        }
        command.byeCommand();
    }
}
