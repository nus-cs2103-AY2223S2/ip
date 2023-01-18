import java.util.Scanner;
public class Duke {
    public static String formatOutput(String out) {
        final String divider = "____________________________________________________________";
        return String.format("\t%s\n\t%s\n\t%s", divider, out, divider);
    }
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCnt = 0;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(formatOutput("Hello! I'm Duke\n\tWhat can I do for you?"));
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            if (inputStr.equals("list")) {
                StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 0; i < taskCnt; i++) {
                    listOutput.append(String.format(
                            "\t[%s] %d. %s",
                            tasks[i].getStatusIcon(),
                            i + 1,
                            tasks[i].getDescription()
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
                        "Nice! I've marked this task as done:\n\t\t[%s] %s",
                        tasks[idx].getStatusIcon(),
                        tasks[idx].getDescription()
                )));
            } else if (inputStr.startsWith("unmark ")) {
                int idx = Integer.parseInt(inputStr.substring(7)) - 1;
                tasks[idx].unmarkAsDone();
                System.out.println(formatOutput(String.format(
                        "OK, I've marked this task as not done yet:\n\t\t[%s] %s",
                        tasks[idx].getStatusIcon(),
                        tasks[idx].getDescription()
                )));
            } else {
                tasks[taskCnt] = new Task(inputStr);
                taskCnt++;
                System.out.println(formatOutput("added: " + inputStr));
            }
            inputStr = inputScanner.nextLine().trim();
        }
        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
    }
}
