import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        ArrayList<Task> userTasks = new ArrayList<>();
        while (!userInput.equals("bye"))
        {
            Pattern mark = Pattern.compile("mark [0-9]+");
            Pattern unmark = Pattern.compile("unmark [0-9]+");
            Pattern delete = Pattern.compile("delete [0-9]+");
            Matcher matchMark = mark.matcher(userInput);
            Matcher matchUnmark = unmark.matcher(userInput);
            Matcher matchDelete = delete.matcher(userInput);

            if (userInput.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= userTasks.size(); i++)
                {
                    System.out.println(i + "." + userTasks.get(i - 1));
                }
            }
            else if (matchMark.matches())
            {
                System.out.println("Nice! I've marked this task as done:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                userTasks.get(idx - 1).markTask();
                System.out.println("    " + idx + "." + userTasks.get(idx - 1));
            }
            else if (matchUnmark.matches())
            {
                System.out.println("OK, I've marked this task as not done yet:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                userTasks.get(idx - 1).unmarkTask();
                System.out.println("    " + idx + "." + userTasks.get(idx - 1));
            }
            else if (matchDelete.matches())
            {
                System.out.println("Noted. I've removed this task:");
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                System.out.println("    " + idx + "." + userTasks.get(idx - 1));
                userTasks.remove(idx - 1);
                System.out.println("Now you have " + userTasks.size() + " tasks in the list.");
            }
            else
            {
                Task userTask;
                String[] inputs = userInput.split(" ");
                String taskType = inputs[0];
                switch (taskType) {
                    case "todo": {

                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String taskName = userInput.split(" ", 2)[1];
                        userTask = new ToDo(taskName);
                        break;
                    }
                    case "deadline": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] taskNameAndDeadline = userInput.split(" ", 2)[1].split(" /by ");
                        if (taskNameAndDeadline.length < 2) {
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                        }
                        String taskName = taskNameAndDeadline[0];
                        String deadline = taskNameAndDeadline[1];
                        userTask = new Deadline(taskName, deadline);
                        break;
                    }
                    case "event": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] taskNameAndDate = userInput.split(" ", 2)[1].split(" /from ");
                        if (taskNameAndDate.length < 2) {
                            throw new DukeException("☹ OOPS!!! The from date of an event cannot be empty.");
                        }
                        String taskName = taskNameAndDate[0];
                        String[] toAndFrom = taskNameAndDate[1].split(" /to ");
                        if (toAndFrom.length < 2) {
                            throw new DukeException("☹ OOPS!!! The to date of an event cannot be empty.");
                        }
                        String from = toAndFrom[0];
                        String to = toAndFrom[1];
                        userTask = new Event(taskName, from, to);
                        break;
                    }
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                userTasks.add(userTask);
                System.out.println("Got it. I've added this task: \n    " + userTask + "\nNow you have " + userTasks.size() + " tasks in the list.");
            }
            userInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
