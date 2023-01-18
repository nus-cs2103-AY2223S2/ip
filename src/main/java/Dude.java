import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dude {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadStorage();
        getIntro();

        while(true) {
            String command = sc.nextLine();
            if (!command.equals("")) {
                try {
                    getCommands(command);
                } catch (DudeInvalidCommandException e) {
                    showError("What command is that even sia?");
                } catch (DudeMissingCommandException e) {
                    showError("Eh you forget to put your details for the task ah?");
                }
                if (command.equals("bye")) {
                    break;
                }
            }
        }
    }

    public static void getIntro() {
        String logo = "  _____           __     \n" +
                " |  __ \\ __ __    | | ___  \n" +
                " | |  | | | | |/ _` |/ _ \\\n" +
                " | |__| | |_| | (_| |  __/\n" +
                " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        System.out.println(" _______________________________________________________________________");
        System.out.println("\tYo! I'm dude");
        System.out.println("\tWhat you want me do for you?");
        System.out.println(" _______________________________________________________________________\n");
    }

    public static void loadStorage() {
        try {
            taskList = Storage.loadData();
            for (Task i : taskList) {
                System.out.println(i);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveStorage(ArrayList<Task> taskList) {
        Storage.saveData(taskList);
    }

    public static void getCommands(String command) throws DudeInvalidCommandException, DudeMissingCommandException {
        String [] cmd = command.split(" ", 2);
        switch(cmd[0]) {
            case "list":
                System.out.println(" _______________________________________________________________________");
                getList();
                System.out.println(" _______________________________________________________________________\n");
                break;
            case "bye":
                System.out.println(" _______________________________________________________________________");
                System.out.println("\tCiaos! See you next time.");
                System.out.println(" _______________________________________________________________________\n");
                break;
            case "mark":
                if (cmd.length < 2) throw new DudeMissingCommandException();
                System.out.println(" _______________________________________________________________________");
                markTask(Integer.parseInt(cmd[1]) - 1);
                System.out.println(" _______________________________________________________________________\n");
                break;
            case "unmark":
                if (cmd.length < 2) throw new DudeMissingCommandException();
                System.out.println(" _______________________________________________________________________");
                unmarkTask(Integer.parseInt(cmd[1]) - 1);
                System.out.println(" _______________________________________________________________________\n");
                break;
            case "todo":
                if (cmd.length < 2) throw new DudeMissingCommandException();
                addTask(Type.TODO, cmd[1]);
                break;
            case "deadline":
                if (cmd.length < 2) throw new DudeMissingCommandException();
                addTask(Type.DEADLINE, cmd[1]);
                break;
            case "event":
                if (cmd.length < 2) throw new DudeMissingCommandException();
                addTask(Type.EVENT, cmd[1]);
                break;
            case "delete":
                if (cmd.length < 2) throw new DudeMissingCommandException();
                System.out.println(" _______________________________________________________________________");
                deleteTask(Integer.parseInt(cmd[1]) - 1);
                System.out.println(" _______________________________________________________________________\n");
                break;
            default:
                throw new DudeInvalidCommandException();
        }
    }

    public static void addTask(Type type, String description) throws DudeMissingCommandException {
        Task task = null;
        String[] format;
        switch(type){
            case TODO:
                task = new Todo(description);
                break;
            case DEADLINE:
                format = description.split(" /by ");
                if (format.length < 2) throw new DudeMissingCommandException();
                task = new Deadline(format[0], format[1]);
                break;
            case EVENT:
                format = description.split(" /from ");
                if (format.length < 2) throw new DudeMissingCommandException();
                String[] details = format[1].split(" /to ");
                if (details.length < 2) throw new DudeMissingCommandException();
                task = new Event(format[0], details[0], details[1]);
                break;
        }
        taskList.add(task);
        Task.addTaskCount();
        saveStorage(taskList);
        System.out.println(" _______________________________________________________________________");
        System.out.println("\tGot it. I've added this task already:");
        System.out.println("\t" + task);
        System.out.println("\tNow got " + Task.count + " tasks in your list liao.");
        System.out.println(" _______________________________________________________________________\n");
    }

    public static void getList() {
        if (Task.count != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < Task.count; i++) {
                System.out.println("\t" + (i + 1) + "." + taskList.get(i).toString());
            }
        } else {
            System.out.println("\tEh... You currently got no task leh.");
        }
    }

    public static void markTask(int task) {
        if (Task.count > task && Task.count != 0) {
            Task currentTask = taskList.get(task);
            currentTask.mark();
            saveStorage(taskList);
            System.out.println("\tSwee! I've marked this task as done loh:");
            System.out.println("\t" + currentTask);
        } else {
            System.out.println("\tUhh... Where got this task for me to mark?");
        }
    }

    public static void unmarkTask(int task) {
        if (Task.count > task && Task.count != 0) {
            Task currentTask = taskList.get(task);
            currentTask.unmark();
            saveStorage(taskList);
            System.out.println("\tOkay liar, I've marked this task as undone liao:");
            System.out.println("\t" + currentTask);
        } else {
            System.out.println("\tUhh... Where got this task for me to unmark?");
        }
    }

    public static void showError(String error) {
        System.out.println(" _______________________________________________________________________");
        System.out.println("\tERROR: " + error);
        System.out.println(" _______________________________________________________________________\n");
    }

    public static void deleteTask(int task) {
        if (Task.count > task && Task.count != 0) {
            Task currentTask = taskList.get(task);
            taskList.remove(task);
            Task.removeTaskCount();
            saveStorage(taskList);
            System.out.println("\tOkay can. I've removed this task already:");
            System.out.println("\t" + currentTask);
            System.out.println("\tNow only left with " + Task.count + " tasks in your list liao.");
        } else {
            System.out.println("\tUhh... Where got this task for me to delete?");
        }
    }

}
