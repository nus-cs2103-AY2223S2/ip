import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //I'm going to start abstracting functions otherwise I'll puke blood

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();

            while (!nextLine.equals("bye")) {
                try {
                if (nextLine.startsWith("todo")) {
                    String[] splitArray = nextLine.split(" ");
                    if (splitArray.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String taskName = "";
                    for (int i = 1; i < splitArray.length; i++) {
                        taskName += splitArray[i];
                    }

                    addToTasks(nextLine, new Todo(taskName), tasks);
                    nextLine = sc.nextLine();

                } else if (nextLine.startsWith("deadline")) {
                    String[] splitArray = nextLine.split("/");
                    String[] splitTask = splitArray[0].split(" ");
                    String taskName = "";
                    for (int i = 1; i < splitTask.length; i++) {
                        taskName += splitTask[i];
                    }
                    String by = splitArray[1];
                    addToTasks(taskName, new Deadline(taskName, by), tasks);
                    nextLine = sc.nextLine();

                } else if (nextLine.startsWith("event")) {
                    String[] splitArray = nextLine.split("/");
                    String from = splitArray[1];
                    String to = splitArray[2];
                    String[] splitTask = splitArray[0].split(" ");
                    String taskName = "";
                    for (int i = 1; i < splitTask.length; i++) {
                        taskName += splitTask[i];
                    }
                    addToTasks(taskName, new Event(taskName, from, to), tasks);
                    nextLine = sc.nextLine();

                } else if (nextLine.startsWith("mark")) {
                    String theSplitPart = nextLine.split(" ")[1];
                    int taskNumber = Integer.parseInt(theSplitPart);
                    markTasks(taskNumber, tasks);
                    nextLine = sc.nextLine();

                } else if (nextLine.startsWith("unmark")) {
                    String theSplitPart = nextLine.split(" ")[1];
                    int taskNumber = Integer.parseInt(theSplitPart);
                    unmarkTasks(taskNumber, tasks);
                    nextLine = sc.nextLine();

                } else if (nextLine.equals("list")) {
                    printTaskList(tasks);
                    nextLine = sc.nextLine();

                } else if (nextLine.startsWith("delete")) {
                    String theSplitPart = nextLine.split(" ")[1];
                    int taskNumber = Integer.parseInt(theSplitPart);
                    delete(tasks, taskNumber);
                    nextLine = sc.nextLine();

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                }
                catch (DukeException e) {
                    System.out.println(e);
                    nextLine = sc.nextLine();
                }
            }

        exit();
    }

    static void delete(ArrayList<Task> tasks, int taskNumber) { //abstraction for delete
        Task taskToRemove = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        printDashedLines();
        System.out.println("\t  Noted. I've removed this task:");
        System.out.println(String.format("\t\t%s", taskToRemove.toString()));
        returnTasksLeftString(tasks);
        printDashedLines();
    }
    static String returnTasksLeftString(ArrayList<Task> tasks) { // abstraction for number of tasks left in list
        int taskSize = tasks.size();
        return String.format("\t  Now you have %d tasks in your list", taskSize);
    }
    static void printDashedLines() { //abstraction just for dashed line printing
        System.out.println("\t____________________________________________________________");
    }

    static void echo(String whatToEcho) { //for level 1
        System.out.println(String.format("\t\t%s", whatToEcho));
    }

    static void greet() { //to start off with a beautiful totoro
        String hello = " ╱▔▔▔▔▔▔▔▔▔▔▔▔▔╲\n" +
                " ▏     ┏┓┏┳━┳┓┏┓┏━━┓      ▕\n" +
                " ▏     ┃┗┛┃┏┛┃┃┃┃┏┓┃      ▕\n" +
                " ▏     ┃┏┓┃┗┓┃┃┃┃┗┛┃      ▕\n" +
                " ▏     ┃┃┃┃┏┛┃┃┃┃┛┗┃      ▕\n" +
                " ▏     ┃┃┃┃┗┓┗┫┗┫╰╯┃      ▕\n" +
                " ▏     ┗┛┗┻━┻━┻━┻━━┛      ▕\n" +
                " ╲▂▂▂▂▂▂▂▂▂▂▂▂▂▂╲\n";
        String logo =
                " ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣶⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⠃⠀⠹⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⡿⠛⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⡟⠀⠀⠀⢹⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⠏⠀⠀⠈⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⡿⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣇⠀⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡆⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⢀⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⢻⣿⣿⣦⡀⠀⠀⣿⣤⠤⠴⠶⠶⠒⠒⠒⠒⠒⣤⣼⣿⣿⠇⠀⣶⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣽⣿⣿⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠀⣤⣿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⠃ ⢀⠠⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀  ⠀⣀⣀⣀⠀⠀⠐⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠤⣤⣀⣀⣀⡀⠀⠀⣠⣿⣿⣿⣿⠊⠀⠰⢿⠿⠀⢢⠀⠀⠀⠠⠤⠤⣄⣀⣀⠀⠄⠀⢠⠋⢿⣿⠆⠈⢢⠀⠘⣧⡀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠉⠉⠙⠛⣿⣿⣿⣿⣿⠏⠢⣀⠀⠀⣀⡠⠊⠀⠀⠀⠀⠀⠉⠛⠛⠉⠁⠀⠀⠀⠸⣄⠀⠀⠀⢀⠜⠀⣠⡼⢷⠒⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠉⠙⠛⠒⠒⠒⠲⠶⣶⣿⣿⣿⣿⣯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀    ⠀⠀⠀⠠⠤⠼⢷⡤⠤⠤⠤⠤⠤⠤⠤⠤⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⢀⣀⣀⣿⣿⣿⣿⣿⡃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠒⠺⢷⡶⠶⠦⣤⣤⣄⣀⠀⠀⠀\n" +
                        "⠀⠒⠛⠛⠉⠉⢉⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣀⣀⣀⡀⠀⠀⠀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣄⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⣀⣤⣶⠶⠖⠛⠋⠀⠀⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠙⠓⠲⠦⢤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢦⠀⠀⠀⠀⠀\n" +
                        "⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣯⡤⠖⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠳⢦⣄⡀⠀⠀⠀⠀⠀  ⠀⠀⠀⠳⣄⠀⠀⠀\n" +
                        "⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠀⢀⣀⣠⣤⣀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⡀⠀⠈⠛⢦⣄⠀⠀⠀⠀⠀⠀⠀⠈⢳⡄⠀\n" +
                        "⣾⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⢠⣶⣿⠿⠿⠿⠿⢷⠀⠀⠀⠀⠾⠿⠿⠛⠉⠉⠙⠛⠿⣷⠀⠀⠀⣠⣶⡿⠿⠟⠿⣿⣷⡀⠀⠀⠙⢷⣄⠀⠀⡄⠀⠀⠀⠀⢻⡄\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠈⠁⠀⠁⠀⠀⠀⠀⠀⠁";
        printDashedLines();
        System.out.println("\t\tHola from Tohtoro!\n" + hello + logo);
        printDashedLines();
    }

    static void exit() { //abstraction for exiting the code when "bye" is read
        printDashedLines();
        System.out.println("\t\tBye. Hope to see you soon!");
        printDashedLines();
        System.exit(0);
    }

    static void markTasks(int theTaskNumberToMark, ArrayList<Task> tasks) { //abstraction to mark tasks
        if (theTaskNumberToMark > tasks.size()) {
            printDashedLines();
            System.out.println("\t\tThere is not enough tasks to mark this :O");
            printDashedLines();
        } else {
            Task currentTaskToMark = tasks.get(theTaskNumberToMark - 1);
            currentTaskToMark.markAsDone();
        }
    }

    static void unmarkTasks(int theTaskNumberToUnmark, ArrayList<Task> tasks) { //abstraction to unmark tasks
        if (theTaskNumberToUnmark > tasks.size()) {
            printDashedLines();
            System.out.println("\t\tThere is not enough tasks to mark this :O");
            printDashedLines();
        } else {
            Task currentTaskToMark = tasks.get(theTaskNumberToUnmark - 1);
            currentTaskToMark.markAsUndone();
        }
    }

    static void addToTasks(String taskName, Task newTask, ArrayList<Task> tasks) { //abstraction to add tasks
        tasks.add(newTask);
        printDashedLines();
        System.out.println("\t  Got it. I've added this task:");
        System.out.println("\t\tAdded: " + taskName);
        System.out.println(returnTasksLeftString(tasks));
        printDashedLines();
    }

    static void printTaskList(ArrayList<Task> tasks) { //abstraction for when list is called
        printDashedLines();
        System.out.println("\t  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task taskinTasks = tasks.get(i);
            System.out.println(String.format("\t\t%s. %s", i + 1, taskinTasks));
        }
        printDashedLines();
    }
}

