import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Task[] inputs = new Task[100];
        int curr = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String currInput = scanner.nextLine();
        System.out.println("____________________________________________________________");

        String[] splitStr = currInput.split(" ", 2);
        String type = splitStr[0];

        while(!currInput.equals("bye")) {
            boolean isMarkTask = type.equals("mark") || type.equals("unmark");
            boolean isSuppTask = type.equals("todo") || type.equals("deadline") || type.equals("event");

            try {
                checkIfValid(type);
            } catch (DukeException e) {
                System.out.println(e);
            }

            if(currInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < curr; i++) {
                    System.out.println((i + 1) + "." + inputs[i]);
                }
            } else if(isMarkTask) {
                    Task taskToMark = inputs[Integer.parseInt(splitStr[1]) - 1];
                    mark(type, taskToMark);
                    System.out.println(taskToMark);
            } else if(isSuppTask) {
                try {
                    addSuppTask(type, inputs, curr, splitStr);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(inputs[curr++]);
                    System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
                } catch(DukeException e) {
                    System.out.println(e);
                }
            }
            System.out.println("____________________________________________________________\n");
            currInput = scanner.nextLine();
            splitStr = currInput.split(" ", 2);
            type = splitStr[0];
            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    public static void mark(String type, Task taskToMark) {
        if(type.equals("mark")) {
            taskToMark.setIsDone(true);
            System.out.println("Nice! I've marked this task as done:");
        } else {
            taskToMark.setIsDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    public static void addSuppTask(String type, Task[] inputs, int curr, String[] splitstr) throws DukeException {
        if(splitstr.length == 1) {
            throw new DukeException(String.format("The description of a %s cannot be empty.", splitstr[0]));
        }

        String task = splitstr[1];
        if(type.equals("todo")) {
            inputs[curr] = new Todo(task);
        } else if(type.equals("deadline")) {
            String[] arr = task.split("/", 2);
            String desc = arr[0].trim();
            String by = arr[1].substring(3);
            inputs[curr] = new Deadline(desc, by);
        } else {
            String[] arr = task.split("/", 3);
            String desc = arr[0].trim();
            String from = arr[1].substring(5).trim();
            String to = arr[2].substring(3);
            inputs[curr] = new Event(desc, from, to);
        }
    }

    public static void checkIfValid(String currType) throws DukeException {
        String[] validTypes = new String[]{"list", "mark", "unmark", "todo", "deadline", "event"};
        boolean valid = false;

        for(String type : validTypes) {
            if(currType.equals(type)) {
                valid = true;
            }
        }

        if(!valid) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
