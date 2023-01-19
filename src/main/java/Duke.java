import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println(formatOutput("Hey there! I'm Sirius\n\t What can I do for you today? :D"));

        String input = sc.nextLine();
        while (!isBye(input)) {
            String[] inputArr = input.split(" ", 2);
            String firstWord = inputArr[0];
            int index = 0;
            String taskInput = "";
            try {
                switch (firstWord) {
                    case "list":
                        StringBuilder sb = new StringBuilder();
                        sb.append("Here are the tasks in your list:\n\t ");
                        for (int i = 0; i < taskList.size(); i++) {
                            int count = i + 1;
                            String res = count + "." + taskList.get(i).toString();
                            if (i != taskList.size() - 1) {
                                res += "\n\t ";
                            }
                            sb.append(res);
                        }
                        System.out.println(formatOutput(sb.toString()));
                        break;
                    case "mark":
                        index = Integer.parseInt(inputArr[1]) - 1;
                        if (index >= taskList.size()) {
                            System.out.println("Oopsies.. Seems like that task does not exist :(");
                        } else {
                            Task currentTask = taskList.get(index);
                            currentTask.markAsDone();
                            System.out.println(formatOutput("Great :D I knew you could do it! I've marked this task as done:\n\t\t" + currentTask.toString()));
                        }
                        break;
                    case "unmark":
                        index = Integer.parseInt(inputArr[1]) - 1;
                        if (index >= taskList.size()) {
                            System.out.println("Oopsies.. Seems like that task does not exist :(");
                        } else {
                            Task currentTask = taskList.get(index);
                            currentTask.markAsNotDone();
                            System.out.println(formatOutput("Ok, I've marked this task as not done yet:\n\t\t" + currentTask.toString()));
                        }
                        break;
                    case "delete":
                        index = Integer.parseInt(inputArr[1]) - 1;
                        Task toDelete = taskList.get(index);
                        taskList.remove(index);
                        System.out.println(formatOutput("Ok. I've removed this task:\n\t\t" + toDelete.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    case "todo":
                        if (isDescriptionEmpty(inputArr)) {
                            throw new DukeException(formatOutput("Hey now.. The description of a todo cannot be empty. >:("));
                        }
                        taskInput = inputArr[1];
                        Todo todo = new Todo(taskInput);
                        taskList.add(todo);
                        System.out.println(formatOutput("Got it. I've added this task:\n\t\t" + todo.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    case "deadline":
                        if (isDescriptionEmpty(inputArr)) {
                            throw new DukeException(formatOutput("Hey now.. The description of a deadline cannot be empty. >:("));
                        }
                        taskInput = inputArr[1];
                        String[] dArr = taskInput.split("/by", 2);
                        String deadlineDesc = dArr[0].trim();
                        String by = dArr[1].trim();
                        Deadline deadline = new Deadline(deadlineDesc, by);
                        taskList.add(deadline);
                        System.out.println(formatOutput("Got it. I've added this task:\n\t\t" + deadline.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    case "event":
                        if (isDescriptionEmpty(inputArr)) {
                            throw new DukeException(formatOutput("Hey now.. The description of an event cannot be empty. >:("));
                        }
                        taskInput = inputArr[1];
                        String[] eArr = taskInput.split("/");
                        String eventDesc = eArr[0].trim();
                        String from = eArr[1].trim().substring(5);
                        String to = eArr[2].trim().substring(3);
                        Event event = new Event(eventDesc, from, to);
                        taskList.add(event);
                        System.out.println(formatOutput("Got it. I've added this task:\n\t\t" + event.toString() + "\n\t Now you have " + taskList.size() + " tasks in the list."));
                        break;
                    default:
                        throw new DukeException(formatOutput("Huh? What do you mean? :o"));
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }

        System.out.println(formatOutput("Well, I'm off! Hope to see you again soon :)"));
    }

    public static String formatOutput(String input) {
        String line = "\t____________________________________________________________\n";
        return line + "\t " + input + "\n" + line;
    }

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }

    public static boolean isDescriptionEmpty(String[] arr) {
        return arr.length == 1 || arr[1].trim().isEmpty();
    }
}
