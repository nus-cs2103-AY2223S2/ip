import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner sc;
    private static ArrayList<Task> TaskList;
    private static String filename;

    public static void main(String[] args) {
        initializeStorage("list_store_data.txt");
        TaskList = new ArrayList<>();
        readData();
        System.out.println("Greetings, I am Nibbalim!\n" + "I am a task organizer!\n");
        sc = new Scanner(System.in);
        try {
            Duke.output();
        } catch (DukeException e) {
            System.out.println(e);
        }
        writeData();
        System.out.println("Kay thanks bye!");
    }

    public static void writeData() {
        try {
            File data = new File(filename);
            FileWriter writer = new FileWriter(data);
            while (!TaskList.isEmpty()) {
                Task task = TaskList.remove(0);
                String isDone = "";
                String writeTask = "";

                if(task.isDone()) {
                    isDone = "1";
                } else {
                    isDone = "0";
                }

                if (task instanceof ToDo) {
                    writeTask = "T " + isDone + " " + task.getDescription() + "\n";
                } else if (task instanceof Deadline) {
                    writeTask += "D " + isDone + " " + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n";
                } else if (task instanceof Event) {
                    writeTask += "E " + isDone + " " + task.getDescription() + " | " + ((Event) task).getFrom() + " | "
                        + ((Event) task).getTo() + "\n";
                }
                writer.write(writeTask);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public static void readData() {
        try {
            File data = new File(filename);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String Line = sc.nextLine();
                char Type = Line.charAt(0);
                int isDone = Integer.parseInt(Character.toString(Line.charAt(2)));
                Task task = null;
                if (Type == 'T') { // T 1 read book
                    String description = Line.substring(4);
                    task = new ToDo(description);
                    if (isDone == 1) {
                        task.markAsDone();
                    }
                    TaskList.add(task);
                } else if (Type == 'D') { // D 1 read book | June 12 4pm
                    int dividerIndex = Line.indexOf('|');
                    String description = Line.substring(4, dividerIndex - 1);
                    String by = Line.substring(dividerIndex + 2);
                    task = new Deadline(description, by);
                    if (isDone == 1) {
                        task.markAsDone();
                    }
                    TaskList.add(task);
                } else if (Type == 'E') { // E 1 read book | June 12 4pm | June 13 4pm
                    int firstDividerIndex = Line.indexOf('|');
                    int lastDividerIndex = Line.lastIndexOf('|');
                    String description = Line.substring(4, firstDividerIndex - 1);
                    String from = Line.substring(firstDividerIndex + 2, lastDividerIndex - 1);
                    String to = Line.substring(lastDividerIndex + 2);
                    task = new Event(description, from, to);
                    if (isDone == 1) {
                        task.markAsDone();
                    }
                    TaskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void initializeStorage(String FilenameInput) {
        filename = FilenameInput;
        try {
            File data = new File(filename);
            data.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void output() throws DukeException {
        while (true) {
            String input = sc.nextLine();
            if ("bye".equals(input)) {
                break;
            } else if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < TaskList.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + TaskList.get(i).toString());
                }
            } else if (isMark(input, TaskList.size())) {
                int taskindex = Integer.parseInt(input.substring(5)) - 1;
                TaskList.get(taskindex).markAsDone();
                System.out.println("Solid! I'm marking this task as done:\n" + TaskList.get(taskindex).toString());
            } else if (isUnMark(input, TaskList.size())) {
                int taskindex = Integer.parseInt(input.substring(7)) - 1;
                TaskList.get(taskindex).markAsNotDone();
                System.out.println("Aight, marking this as not done:\n" + TaskList.get(taskindex).toString());
            } else if (isDelete(input, TaskList.size())) {
                int taskindex = Integer.parseInt(input.substring(7)) - 1;
                String removed = TaskList.get(taskindex).toString();
                TaskList.remove(taskindex);
                System.out.println("Swee! One less task to go! Removing...\n" + removed);
            } else { //Task Creation
                Task task = null;
                if (isToDo(input)) {
                    task = new ToDo(input);
                    TaskList.add(task);
                } else if (isDeadline(input)) {
                    int index = input.indexOf(" /by ");
                    task = new Deadline(input.substring(0, index), input.substring(index + 5));
                    TaskList.add(task);
                } else if (isEvent(input)) {
                    int fromdex = input.indexOf(" /from ");
                    int todex = input.indexOf(" /to ");
                    task = new Event(input.substring(0, fromdex), input.substring(fromdex + 7, todex), input.substring(todex + 5));
                    TaskList.add(task);
                } else {
                    throw new DukeException("Please input a task with either todo, deadline or event prefixed!");
                }
                System.out.println("Roger. This task has been added:\n" + "  " + task);
                System.out.println("Now you have " + TaskList.size() + " tasks in your list.");
            }
        }
    }

    public static boolean isMark(String input, int listSize) {
        if (input.length() >=  6 && input.startsWith("mark ") && isNumeric(input.substring(5))) {
            int taskindex = Integer.parseInt(input.substring(5)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    public static boolean isUnMark(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("unmark ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    public static boolean isDelete(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("delete ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    public static boolean isToDo(String input) throws DukeException {
        if (input.length() >= 4 && input.startsWith("todo")) {
            if (input.equals("todo") || input.substring(4).isBlank()) {
                throw new DukeException("TODO needs a description!");
            } else return input.startsWith("todo ");
        }
        return false;
    }

    public static boolean isDeadline(String input) throws DukeException {
        if (input.length() >= 8 && input.startsWith("deadline")) {
            if (input.equals("deadline") || input.substring(8).isBlank() || input.equals("deadline /by") || input.equals("deadline /by ")) {
                throw new DukeException("DEADLINE needs a description!");
            } else return input.contains(" /by ");
        }
        return false;
    }

    public static boolean isEvent(String input) throws DukeException {

        if (input.length() >= 5 && input.startsWith("event")) {
            if (input.equals("event") || input.substring(5).isBlank()) {
                throw new DukeException("EVENT needs a description!");
            }
                int fromdex = input.indexOf(" /from ");
                int todex = input.indexOf(" /to ");
                if (fromdex + 7 > todex) {
                    throw new DukeException("What are you saying?");
                }
            return true;
        }
        return false;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
