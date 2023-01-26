import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Duke {

    private static final String indentation = "     ";
    private static final String newLine = "    ____________________________________________________________";
    private static ArrayList<Task> arrOfTask;
    public enum Action {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        BYE
    }

    public static void getTaskForToday() {
        for (Task t: arrOfTask) {
            if (t.getDate().equals(LocalDate.now())) {
                System.out.println(t);
            }
        }
    }
    public static LocalDate convertStringToDate(String date) throws DateTimeException {
        String[] arr = date.split("-");
        if (arr.length < 3) {
            throw new DateTimeException("wrong date format");
        }
        return LocalDate.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
    }

    public static void exit() {
        System.out.println(indentation + "Bye. Hope to see you again soon!");
    }

    public static void greet() {
        System.out.println(newLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(newLine);
    }

    public static void addTask(Task t) {
        arrOfTask.add(t);
        System.out.println(indentation + "Got it. I've added this task:");
        System.out.println(indentation + t);
        totalItems();
    }

    public static void list() {
        System.out.println(indentation + "Here are the tasks in your list:");
        for (int i = 0; i < arrOfTask.size(); i++) {
            System.out.println(indentation + (i + 1) + "." + arrOfTask.get(i));
        }
    }

    public static void checkValidIndex(int index) throws InvalidIndexException{
        if (index > Task.getTotalNumOfTask()) {
            throw new InvalidIndexException("Index too large");
        } else if (index <= 0) {
            throw new InvalidIndexException("Index too small");
        }
    }


    public static void taskDone(int index) {
        checkValidIndex(index);
        Task t = arrOfTask.get(index - 1);
        t.taskDone();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + t);
    }

    public static void taskNotDone(int index) {
        checkValidIndex(index);
        Task t = arrOfTask.get(index - 1);
        t.taskNotDone();
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + t);
    }

    public static void deleteTask(int index) {
        checkValidIndex(index);
        Task t = arrOfTask.get(index - 1);
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + t);
        Task.decreaseNumOfTask();
        totalItems();
    }

    public static void totalItems() {
        System.out.println(indentation + "Now you have " + Task.getTotalNumOfTask() + " tasks in the list.");
    }

    public static void openFile() {
        try {
            File f = new File("DukeEventPlanner.txt");
            f.createNewFile();
            Scanner s = new Scanner(f);
            arrOfTask = new ArrayList<>();
            while (s.hasNext()) {
                String[] arr = s.nextLine().split("\\|");
                Task t;
                if (arr[0].equals("T")) {
                    t = new Todo(arr[2]);
                } else if (arr[0].equals("D")) {
                    t = new Deadline(arr[2], LocalDate.parse(arr[3]));
                } else {
                    t = new Event(arr[2], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
                }
                if (arr[1].equals("1")) {
                    t.taskDone();
                }
                arrOfTask.add(t);
            }
        } catch (IOException e) {
        System.out.println(indentation + e);
        }
    }

    public static void writeFile() {
        try {
            FileWriter fw = new FileWriter("DukeEventPlanner.txt");
            for (Task t : arrOfTask) {
                fw.write(t.toText() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(indentation + "something went wrong");
        }
    }

    public static Action getAction(String command) {
        if (command.equals("bye")) {
            return Action.BYE;
        } else if (command.equals("list")) {
            return Action.LIST;
        } else if (command.startsWith("mark")) {
            return Action.MARK;
        } else if (command.startsWith("unmark")) {
            return Action.UNMARK;
        } else if (command.startsWith("delete")) {
            return Action.DELETE;
        } else if (command.startsWith("todo")) {
            return Action.TODO;
        } else if (command.startsWith("deadline")) {
            return Action.DEADLINE;
        } else if (command.startsWith("event")) {
            return Action.EVENT;
        } else {
            throw new InvalidCommandException("Incorrect command");
        }
    }

    public static void sortActions(Action action, String command) {
        switch (action) {
            case LIST:
                list();
                break;
            case MARK:
                if (command.length() <= 5) {
                    throw new EmptyDescriptionException("The description of a task cannot be empty");
                }
                taskDone(Integer.parseInt(command.substring(5)));
                break;
            case UNMARK:
                if (command.length() <= 7) {
                    throw new EmptyDescriptionException("The description of a task cannot be empty");
                }
                taskNotDone(Integer.parseInt(command.substring(7)));
                break;
            case DELETE:
                if (command.length() <= 7) {
                    throw new EmptyDescriptionException("The description of a task cannot be empty");
                }
                deleteTask(Integer.parseInt(command.substring(7)));
                break;
            case TODO:
                if (command.length() <= 5) {
                    throw new EmptyDescriptionException("The description of a task cannot be empty");
                }
                addTask(new Todo(command.substring(5)));
                break;
            case DEADLINE:
                if (command.length() <= 9) {
                    throw new EmptyDescriptionException("The description of a task cannot be empty");
                }
                String[] str1 = command.substring(9).split("/");
                if (str1.length < 2) {
                    throw new EmptyDescriptionException("The date of a task cannot be empty");
                }
                addTask(new Deadline(str1[0], convertStringToDate(str1[1].substring(3, 13))));
                break;
            case EVENT:
                if (command.length() <= 6) {
                    throw new EmptyDescriptionException("The description of a task cannot be empty");
                }
                String[] str2 = command.substring(6).split("/");
                if (str2.length < 3) {
                    throw new EmptyDescriptionException("The date of a task cannot be empty");
                }
                addTask(new Event(str2[0], convertStringToDate(str2[1].substring(5, 15)), convertStringToDate(str2[2].substring(3, 13))));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        openFile();
        getTaskForToday();
        while (true) {
            try {
                String command = sc.nextLine();
                System.out.println(newLine);
                Action action = getAction(command);
                if (action == Action.BYE) {
                    exit();
                    writeFile();
                    break;
                }
                sortActions(action, command);
            } catch (InvalidCommandException | InvalidIndexException | EmptyDescriptionException e ) {
                System.out.println(indentation + e);
            } catch (DateTimeException e) {
                System.out.println(indentation + "☹ OOPS!!! Please enter date in yyyy-mm-dd format");
            } finally {
                System.out.println(newLine);
            }
        }
    }
}
