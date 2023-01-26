import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DukeCommands {
    private static final String SPACER = "____________________" + "______________________";

    private static final String DATA_PATH = "data.txt";

    public static void printMsg(String msg) {
        System.out.println(SPACER);
        System.out.println(msg);
        System.out.println(SPACER);
    }

    public static void printError(DukeException e) {
        System.out.println(SPACER);
        System.out.println(e.getMessage());
        System.out.println("Please try again!");
        System.out.println(SPACER);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        String msgHeader = "Current data in the list are:";

        System.out.println(SPACER);
        System.out.println(msgHeader);
        for (int i = 0; i < tasks.size(); i++) {
            String output = String.format("%d. %s", i + 1, tasks.get(i));
            System.out.println(output);
        }
        System.out.println(SPACER);
    }

    public static int isValidIndex(String indexStr, ArrayList<Task> tasks) throws DukeException {
        Pattern p = Pattern.compile("^[0-9]+$");
        boolean isNumber = p.matcher(indexStr).matches();

        if (!isNumber) {
            throw new DukeException("Index provided is not an integer.");
        }

        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bounds of tasks list.");
        }

        return index;
    }

    public static void execMarkTask(String indexStr, ArrayList<Task> tasks) throws DukeException {
        int index = isValidIndex(indexStr, tasks);

        tasks.get(index).markTask();

        String msgHeader = "I've marked this task as done:";

        System.out.println(SPACER);
        System.out.println(msgHeader);
        System.out.println(tasks.get(index));
        System.out.println(SPACER);
    }

    public static void execUnmarkTask(String indexStr, ArrayList<Task> tasks) throws DukeException {
        int index = isValidIndex(indexStr, tasks);

        tasks.get(index).unmarkTask();

        String msgHeader = "I've unmarked this task as not done:";

        System.out.println(SPACER);
        System.out.println(msgHeader);
        System.out.println(tasks.get(index));
        System.out.println(SPACER);
    }

    public static void printTask(Task task, int size) {
        String msgHeader = "I've added this task into the list:";
        String msgFooter = String.format("Now you have a total of %s tasks in the list", size);

        System.out.println(SPACER);
        System.out.println(msgHeader);
        System.out.println(task);
        System.out.println(msgFooter);
        System.out.println(SPACER);
    }

    public static void execTodo(String description, ArrayList<Task> tasks) throws DukeException {
        Task todo = new Todo(description);
        tasks.add(todo);
        printTask(todo, tasks.size());
    }

    public static void execDeadline(String data, ArrayList<Task> tasks) throws DukeException {
        String[] splitData = data.split(" /by ", 2);
        if (splitData.length < 2) {
            throw new DukeException("Deadline command format error. Missing /by");
        }

        Task deadline = new Deadline(splitData[0], splitData[1]);
        tasks.add(deadline);
        printTask(deadline, tasks.size());
    }

    public static void execEvent(String data, ArrayList<Task> tasks) throws DukeException {
        String[] splitData1 = data.split(" /from ", 2);
        if (splitData1.length < 2) {
            throw new DukeException("Event command format error. Missing /from");
        }

        String[] splitData2 = splitData1[1].split(" /to ", 2);
        if (splitData2.length < 2) {
            throw new DukeException("Event command format error. Missing /to");
        }

        Task event = new Event(splitData1[0], splitData2[0], splitData2[1]);
        tasks.add(event);
        printTask(event, tasks.size());
    }

    public static void execDeleteTask(String indexStr, ArrayList<Task> tasks) throws DukeException {
        int index = isValidIndex(indexStr, tasks);

        Task task = tasks.get(index);
        tasks.remove(index);

        String msgHeader = "I've deleted this task from the list:";
        String msgFooter = String.format("Now you have a total of %s tasks in the list", tasks.size());

        System.out.println(SPACER);
        System.out.println(msgHeader);
        System.out.println(task);
        System.out.println(msgFooter);
        System.out.println(SPACER);
    }

    public static ArrayList<Task> getData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(DATA_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = fromData(data);
                if (!task.isEmpty()) {
                    tasks.add(task);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            File file = new File(DATA_PATH);
        } catch (Exception e) {
            printError(new DukeException("Unexpected error when getting data"));
        }
        return tasks;
    }

    public static void updateData(List<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter(DATA_PATH);
            String data = toData(tasks);
            myWriter.write(data);
            myWriter.close();
            printMsg("Current data have all been saved.");
        } catch (IOException e) {
            System.out.println(e);
            printError(new DukeException("Error has occurred when saving data."));
        }
    }

    private static String toData(List<Task> tasks) {
        String output = "";
        for (Task task : tasks) {
            output += task.toData();
            output += "\n";
        }
        return output;
    }

    private static Task fromData(String data) {
        String[] typeSplit = data.split(" \\| ", 2);
        switch (typeSplit[0]) {
        case "Todo":
            return Todo.fromData(typeSplit[1]);
        case "Deadline":
            return Deadline.fromData(typeSplit[1]);
        case "Event":
            return Event.fromData(typeSplit[1]);
        default:
            printError(new DukeException("Unknown entry in data file"));
            break;
        }
        return Task.EMPTY_TASK;
    }
}
