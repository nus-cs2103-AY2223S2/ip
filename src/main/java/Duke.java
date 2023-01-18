import java.util.Scanner;
public class Duke {
    static protected Task[] tasks = new Task[100];
    static protected int taskCnt = 0;
    public static void addTask(Task newTask) {
        tasks[taskCnt] = newTask;
        taskCnt++;
        System.out.println(formatOutput(String.format(
                "Got it. I've added this task:\n\t\t%s\n\tNow you have %d task%s in the list.",
                newTask,
                taskCnt,
                taskCnt > 1 ? "s" : ""
        )));
    }
    public static String formatOutput(String out) {
        final String divider = "____________________________________________________________";
        return String.format("\t%s\n\t%s\n\t%s", divider, out, divider);
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(formatOutput("Hello! I'm Duke\n\tWhat can I do for you?"));
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            if (inputStr.equals("list")) {
                StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 0; i < taskCnt; i++) {
                    listOutput.append(String.format(
                            "\t%d. %s",
                            i + 1,
                            tasks[i]
                    ));
                    if (i < taskCnt - 1) {
                        listOutput.append("\n");
                    }
                }
                System.out.println(formatOutput(listOutput.toString()));
            } else if (inputStr.startsWith("mark ")) {
                int idx = Integer.parseInt(inputStr.substring(5)) - 1;
                tasks[idx].markAsDone();
                System.out.println(formatOutput(String.format(
                        "Nice! I've marked this task as done:\n\t\t%s",
                        tasks[idx]
                )));
            } else if (inputStr.startsWith("unmark ")) {
                int idx = Integer.parseInt(inputStr.substring(7)) - 1;
                tasks[idx].unmarkAsDone();
                System.out.println(formatOutput(String.format(
                        "OK, I've marked this task as not done yet:\n\t\t%s",
                        tasks[idx]
                )));
            } else if (inputStr.startsWith("todo ")) {
                String desc = inputStr.substring(5);
                addTask(new Todo(desc));
            } else if (inputStr.startsWith("deadline ")) {
                String[] splitArr = inputStr.substring(9).split("/by");
                addTask(new Deadline(splitArr[0].trim(), splitArr[1].trim()));
            } else if (inputStr.startsWith("event ")) {
                String[] splitArr = inputStr.substring(6).split("/from|/to");
                addTask(new Event(splitArr[0].trim(), splitArr[1].trim(), splitArr[2].trim()));
            } else {
                System.out.println(formatOutput("Unrecognised command."));
            }
            inputStr = inputScanner.nextLine().trim();
        }
        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
    }
}
