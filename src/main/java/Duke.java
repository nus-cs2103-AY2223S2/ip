
import java.util.Scanner;

public class Duke {

    public static Task[] li = new Task[100];

    public static int countSlash(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '/') {
                count ++;
            }
        }
        return count;
    }

    public static void listCommand() {
        if (Task.getCount() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getCount(); i++) {
                System.out.println(i+1+ "." + li[i]);
            }
        }
        System.out.println("What else can I do for you?");
    }

    public static void blahCommand() {
        System.out.println("blah");
        System.out.println("What else can I do for you?");
    }

    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon! :-p");
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static void checkMark(String str) {
        String arr[] = str.split("\\s+");
        if (arr.length == 2 && isNumber(arr[1]) && (arr[0].equals("mark") || arr[0].equals("unmark"))) {
            // check if task exists
            int taskNum = Integer.parseInt(arr[1]);
            if (taskNum <= Task.getCount() && taskNum != 0) {
                // mark or unmark task
                if (arr[0].equals("mark")) {
                    Task t = li[Integer.parseInt(arr[1]) - 1];
                    t.mark();
                    if (Integer.parseInt(arr[1]) == Task.getCount()) {
                        System.out.println("Yay! You have completed all your tasks :-)");
                    }
                } else if (arr[0].equals("unmark")){
                    Task t = li[Integer.parseInt(arr[1])-1];
                    t.unMark();
                }
            } else {
                System.out.println("Huh.. the task does not exist.");
            }
        } else {
            System.out.println("I can't quite understand you... :-/");
        }
    }

    public static void addTodo(String str) {
        String desc = str.split(" ", 2)[1];
        int i = Task.getCount();
        li[i] = new Todo(desc);
    }

    public static void addDeadline(String str) {
        if (countSlash(str) != 1) {
            System.out.println("Please specify the date/time.");
        } else {
            String segments[] = str.split("/");
            String deadline = segments[segments.length-1];
            String date = deadline.split(" ", 2)[1];
            String subSegments[] = segments[0].split(" ", 2);
            String desc = subSegments[1];

            int i = Task.getCount();
            li[i] = new Deadline(date, desc);
        }
    }

    public static void addEvent(String str) {
        if (countSlash(str) != 2) {
            System.out.println("Please specify both the start and end times/dates.");
        } else {
            String segments[] = str.split("/", 3);
            String start = segments[segments.length -2].split(" ", 2)[1];
            String end = segments[segments.length-1].split(" ", 2)[1];
            String subSegments[] = segments[0].split(" ", 2);
            String desc = subSegments[1];

            int i = Task.getCount();
            li[i] = new Event(start, end, desc);
        }
    }

    public static void checkCommand(String str) {
        String arr[] = str.split("\\s+");
        if (arr.length == 1 && !str.equals("mark") && !str.equals("unmark")
                && !str.equals("mark ") && !str.equals("unmark ")) {
            System.out.println("I can't quite understand you... :-/");
        } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
            checkMark(str);
        } else {
            if (arr[0].equals("todo")) {
                addTodo(str);
            } else if (arr[0].equals("deadline")) {
                addDeadline(str);
            } else if (arr[0].equals("event")) {
                addEvent(str);
            } else {
                System.out.println("I can't quite understand you... :-/");
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

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand();
                command = sc.nextLine();
            } else if (command.equals("blah")) {
                blahCommand();
                command = sc.nextLine();
            } else {
                checkCommand(command);
                System.out.println("What else can I do for you?");
                command = sc.nextLine();
            }
        }
        byeCommand();
    }
}
