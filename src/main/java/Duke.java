import java.util.Scanner;
public class Duke {
    static protected Task[] tasks = new Task[100];
    static protected int taskCnt = 0;
    public static void addTask(Task newTask) {
        tasks[taskCnt] = newTask;
        taskCnt++;
        prettyPrint(String.format(
                "Got it. I've added this task:\n\t\t%s\n\tNow you have %d task%s in the list.",
                newTask,
                taskCnt,
                taskCnt > 1 ? "s" : ""
        ));
    }
    public static void listTasks() {
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
        prettyPrint(listOutput.toString());
    }

    public static void parseInput(String inputStr) throws BadCommandException {
        try {
            if (inputStr.equals("list")) {
                listTasks();
                return;
            }
            String[] inputSplit = inputStr.split(" ", 2);
            if (inputSplit.length < 2) {
                throw new BadCommandException("There are insufficient parameters!");
            }
            String command = inputSplit[0].trim();
            String params = inputSplit[1];
            if (command.equals("mark")) {
                int idx = Integer.parseInt(params) - 1;
                if (idx >= taskCnt || idx < 0) {
                    throw new BadCommandException("Index given is out of bounds!");
                }
                tasks[idx].markAsDone();
                prettyPrint(String.format(
                        "Nice! I've marked this task as done:\n\t\t%s",
                        tasks[idx]
                ));
            } else if (command.equals("unmark")) {
                int idx = Integer.parseInt(params) - 1;
                if (idx >= taskCnt || idx < 0) {
                    throw new BadCommandException("Index given is out of bounds!");
                }
                tasks[idx].unmarkAsDone();
                prettyPrint(String.format(
                        "OK, I've marked this task as not done yet:\n\t\t%s",
                        tasks[idx]
                ));
            } else if (command.equals("todo")) {
                addTask(new Todo(params.trim()));
            } else if (command.equals("deadline")) {
                String[] paramsSplit = params.split("/by", 2);
                if (paramsSplit.length < 2) {
                    throw new BadCommandException("There are insufficient parameters!");
                }
                addTask(new Deadline(paramsSplit[0].trim(), paramsSplit[1].trim()));
            } else if (command.equals("event")) {
                String[] paramsSplit = params.split("/from|/to", 3);
                if (paramsSplit.length < 3) {
                    throw new BadCommandException("There are insufficient parameters!");
                }
                addTask(new Event(paramsSplit[0].trim(), paramsSplit[1].trim(), paramsSplit[2].trim()));
            } else {
                throw new BadCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            prettyPrint(e.getMessage());
        }
    }
    public static void prettyPrint(String out) {
        final String divider = "____________________________________________________________";
        System.out.println(String.format("\t%s\n\t%s\n\t%s", divider, out, divider));
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        prettyPrint("Hello! I'm Duke\n\tWhat can I do for you?");
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            try {
                parseInput(inputStr);
            } catch (BadCommandException e) {
                prettyPrint(e.getMessage());
            }
            inputStr = inputScanner.nextLine().trim();
        }
        prettyPrint("Bye. Hope to see you again soon!");;
    }
}
