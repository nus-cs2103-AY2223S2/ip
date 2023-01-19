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
            String[] inputArr = input.split(" ");
            String firstWord = inputArr[0];
            int index = 0;
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
                        System.out.println(formatOutput("Got it, I've marked this task as not done yet:\n\t\t" + currentTask.toString()));
                    }
                    break;
                default:
                    Task t = new Task(input);
                    taskList.add(t);
                    System.out.println(formatOutput("Got it, added: " + input));
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
}
