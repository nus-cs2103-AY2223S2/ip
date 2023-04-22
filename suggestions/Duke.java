import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static final int MAX_TASK = 100;
    public static void printWelcomeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        Task.printHorizontalLine();
    }
    public static void printBlahMessage() {
        Task.printHorizontalLine();
        System.out.println("     blah");
        Task.printHorizontalLine();
    }
    public static void printByeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Task.printHorizontalLine();
    }
    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];
        request:
        while(true){
            String line = in.nextLine();
            String[] parsedInput = line.split(" ");
            switch (parsedInput[0]) {
            case "list":
                Task.printTaskList(tasks);
                break;
            case "mark":
                int markId = Integer.parseInt(parsedInput[1]) - 1;
                tasks[markId].setDone();
                tasks[markId].printMark();
                markTask(tasks, parsedInput);
                break;
            case "unmark":
                int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
                tasks[unmarkId].setNotDone();
                tasks[unmarkId].printUnmark();
                unmarkTask(tasks, parsedInput);
                break;
            case "todo":
                String todoDescription = line.replaceFirst("todo ", "");
                tasks[Todo.getNumberOfTasks()] = new Todo(todoDescription);
                int todoId = Todo.getNumberOfTasks() - 1;
                tasks[todoId].printNewTask();
                addTodo(tasks, line);
                break;
            case "deadline":
                String[] DescriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
                String deadlineDescription = DescriptionBy[0];
                String by = DescriptionBy[1];
                tasks[Deadline.getNumberOfTasks()] = new Deadline(deadlineDescription, by);
                int deadlineId = Task.getNumberOfTasks() - 1;
                tasks[deadlineId].printNewTask();
                addDeadline(tasks, line);
                break;
            case "event":
                String[] DescriptionAt = line.replaceFirst("event ", "").split(" /at ");
                String eventDescription = DescriptionAt[0];
                String at = DescriptionAt[1];
                tasks[Event.getNumberOfTasks()] = new Event(eventDescription, at);
                int eventId = Task.getNumberOfTasks() - 1;
                tasks[eventId].printNewTask();
                addEvent(tasks, line);
                break;
            case "blah":
                printBlahMessage();
@@ -76,11 +59,52 @@ public static void main(String[] args) {
                printByeMessage();
                break request;
            default:
                tasks[Task.getNumberOfTasks()] = new Task(line);
                int taskId = Task.getNumberOfTasks() - 1;
                tasks[taskId].printNewTask();
                addTask(tasks, line);
                break;
            }
        }
    }

    private static void addTask(Task[] tasks, String line) {
        tasks[Task.getNumberOfTasks()] = new Task(line);
        int taskId = Task.getNumberOfTasks() - 1;
        tasks[taskId].printNewTask();
    }

    private static void addEvent(Task[] tasks, String line) {
        String[] DescriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = DescriptionAt[0];
        String at = DescriptionAt[1];
        tasks[Event.getNumberOfTasks()] = new Event(eventDescription, at);
        int eventId = Task.getNumberOfTasks() - 1;
        tasks[eventId].printNewTask();
    }

    private static void addDeadline(Task[] tasks, String line) {
        String[] DescriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = DescriptionBy[0];
        String by = DescriptionBy[1];
        tasks[Deadline.getNumberOfTasks()] = new Deadline(deadlineDescription, by);
        int deadlineId = Task.getNumberOfTasks() - 1;
        tasks[deadlineId].printNewTask();
    }

    private static void addTodo(Task[] tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        tasks[Todo.getNumberOfTasks()] = new Todo(todoDescription);
        int todoId = Todo.getNumberOfTasks() - 1;
        tasks[todoId].printNewTask();
    }

    private static void unmarkTask(Task[] tasks, String[] parsedInput) {
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[unmarkId].setNotDone();
        tasks[unmarkId].printUnmark();
    }

    private static void markTask(Task[] tasks, String[] parsedInput) {
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[markId].setDone();
        tasks[markId].printMark();
    }
}
