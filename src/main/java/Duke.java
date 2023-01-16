import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        List<Task> lst = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Zhizhou's Chatbot");
        System.out.println("What can I do for you?");
        String input;
        while (!(input = userInput.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________");
                System.out.println("  Here are the tasks in your list:\n");
                for (int i = 0; i < lst.size(); i++) {
                    Task currTask = lst.get(i);
                    int taskIndex = i+1;
                    if (currTask.isDone()) {
                        System.out.println("   " + taskIndex + ".[X] " + currTask.getDescription());
                    } else {
                        System.out.println("   " + taskIndex + ".[ ] " + currTask.getDescription());
                    }
                }
                System.out.println("____________________");

            } else if (input.length() > 4 && input.substring(0,4).equals("mark")) {
                int indexToMark = Integer.parseInt(input.substring(5)) - 1; // mark 2
                if (indexToMark < lst.size()) {
                    Task toMark = lst.get(indexToMark);
                    toMark.markAsDone();
                    customMessage("   Nice! I've marked this task as done:\n" +
                            "      [X] " + toMark.getDescription());
                } else {
                    customMessage("Invalid, there is no such task");
                }
            }else if (input.length() > 6 && input.substring(0,6).equals("unmark")) {
                int indexToUnmark = Integer.parseInt(input.substring(7)) - 1; // unmark 2
                if (indexToUnmark < lst.size()) {
                    Task toUnmark = lst.get(indexToUnmark);
                    toUnmark.markAsUndone();
                    customMessage("   OK, I've marked this task as not done yet:\n" +
                            "      [ ] " + toUnmark.getDescription());
                } else {
                    customMessage("Invalid, there is no such task");
                }
            } else {
                Task newTask = new Task(input);
                lst.add(newTask);
                customMessage("   added: " + input);
            }
        }
        customMessage("   Bye. Hope to see you again soon!");
    }
    private static void customMessage(String message) {
        System.out.println("____________________");
        System.out.println(message);
        System.out.println("____________________");
    }
}
