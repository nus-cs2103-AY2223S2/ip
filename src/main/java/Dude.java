import java.util.Scanner;

public class Dude {
    private static final Task[] todoList = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        getIntro();

        while(true) {
            String command = sc.nextLine();
            if (!command.equals("")) {
                getCommands(command);
                if (command.equals("bye")) {
                    break;
                }
            }
        }
    }

    public static void getIntro() {
        String logo = "  _____          __     \n" +
                " |  __ \\ __ __   | |  ___  \n" +
                " | |  | | | | |/ _` |/ _ \\\n" +
                " | |__| | |_| | (_| |  __/\n" +
                " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        System.out.println(" ____________________________________________________________");
        System.out.println("\tYo! I'm dude");
        System.out.println("\tWhat you want me do for you?");
        System.out.println(" ____________________________________________________________\n");
    }

    public static void getCommands(String command) {
        String [] cmd = command.split(" ", 2);
        switch(cmd[0]) {
            case "list":
                System.out.println(" ____________________________________________________________");
                getList();
                System.out.println(" ____________________________________________________________\n");
                break;
            case "blah":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tblah");
                System.out.println(" ____________________________________________________________\n");
                break;
            case "bye":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tCiaos! See you next time.");
                System.out.println(" ____________________________________________________________\n");
                break;
            case "mark":
                System.out.println(" ____________________________________________________________");
                markTask(Integer.parseInt(cmd[1]) - 1);
                System.out.println(" ____________________________________________________________\n");
                break;
            case "unmark":
                System.out.println(" ____________________________________________________________");
                unmarkTask(Integer.parseInt(cmd[1]) - 1);
                System.out.println(" ____________________________________________________________\n");
                break;
            case "todo":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tGot it. I've added this task:");
                addTask("todo", cmd[1]);
                System.out.println(" ____________________________________________________________\n");
                break;
            case "deadline":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tGot it. I've added this task:");
                addTask("deadline", cmd[1]);
                System.out.println(" ____________________________________________________________\n");
                break;
            case "event":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tGot it. I've added this task:");
                addTask("event", cmd[1]);
                System.out.println(" ____________________________________________________________\n");
                break;
        }
    }

    public static void addTask(String type, String description) {
        Task task = new Task("");
        String[] format;
        switch(type){
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                format = description.split(" /by ");
                task = new Deadline(format[0], format[1]);
                break;
            case "event":
                format = description.split(" /from ");
                String[] details = format[1].split("/to ");
                task = new Event(format[0], details[0], details[1]);
                break;
        }
        todoList[taskCount] = task;
        taskCount++;
        System.out.println("\t" + task);
        System.out.println("\tNow got " + taskCount + " tasks in your list liao.");

    }

    public static void getList() {
        if (taskCount != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + (i + 1) + "." + todoList[i].toString());
            }
        } else {
            System.out.println("\tEh... You currently got no task leh.");
        }
    }

    public static void markTask(int task) {
        if (taskCount > task && taskCount != 0) {
            System.out.println("\tSwee! I've marked this task as done loh:");
            Task currentTask = todoList[task];
            currentTask.mark();
            System.out.println("\t" + currentTask);
        } else {
            System.out.println("\tUhh... Where got this task for me to mark?");
        }
    }

    public static void unmarkTask(int task) {
        if (taskCount > task && taskCount != 0) {
            System.out.println("\tOkay liar, I've marked this task as undone liao:");
            Task currentTask = todoList[task];
            currentTask.unmark();
            System.out.println("\t" + currentTask);
        } else {
            System.out.println("\tUhh... Where got this task for me to unmark?");
        }
    }

}
