import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static Scanner sc;
    private static List<Task> list;

    public static void main(String[] args) {
        System.out.println("Greetings, I am Nibbalim!\n" + "Your wish is my command!\n");
        sc = new Scanner(System.in);
        list = new ArrayList<>();
        try {
            Duke.output();
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.println("Kay thanks bye!");
    }

    public static void output() throws DukeException {
        while (true) {
            String input = sc.nextLine();
            if ("bye".equals(input)) {
                break;
            } else if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + list.get(i).toString());
                }
            } else if (isMark(input, list.size())) {
                int taskindex = Integer.parseInt(input.substring(5)) - 1;
                list.get(taskindex).markAsDone();
                System.out.println("Solid! I'm marking this task as done:\n" + list.get(taskindex).toString());
            } else if (isUnMark(input, list.size())) {
                int taskindex = Integer.parseInt(input.substring(7)) - 1;
                list.get(taskindex).markAsNotDone();
                System.out.println("Aight, marking this as not done:\n" + list.get(taskindex).toString());
            } else if (isDelete(input, list.size())) {
                int taskindex = Integer.parseInt(input.substring(7)) - 1;
                String removed = list.get(taskindex).toString();
                list.remove(taskindex);
                System.out.println("Swee! One less task to go! Removing...\n" + removed);
            } else { //Task Creation
                Task task = null;
                if (isToDo(input)) {
                    task = new ToDo(input);
                    list.add(task);
                } else if (isDeadline(input)) {
                    int index = input.indexOf(" /by ");
                    task = new Deadline(input.substring(0, index), input.substring(index + 5));
                    list.add(task);
                } else if (isEvent(input)) {
                    int fromdex = input.indexOf(" /from ");
                    int todex = input.indexOf(" /to ");
                    task = new Event(input.substring(0, fromdex), input.substring(fromdex + 7, todex), input.substring(todex + 5));
                    list.add(task);
                } else {
                    throw new DukeException("What are you saying?\n" + "Please input a task with either todo, deadline or event prefixed!");
                }
                System.out.println("Roger. This task has been added:\n" + "  " + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in your list.");
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
