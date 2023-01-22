import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DukeList {
    private Scanner sc;
    private List<Task> list;

    public DukeList(Scanner sc) {
        this.sc = sc;
        this.list = new ArrayList<>();
    }

    public void output() throws DukeException {
        while (true) {
            String input = sc.nextLine();
            if ("bye".equals(input)) {
                break;
            } else if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < this.list.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + this.list.get(i).toString());
                }
            } else if (isMark(input, list.size())) {
                int taskindex = Integer.parseInt(input.substring(5)) - 1;
                this.list.get(taskindex).markAsDone();
                System.out.println("Solid! I'm marking this task as done:\n" + this.list.get(taskindex).toString());
            } else if (isUnMark(input, list.size())) {
                int taskindex = Integer.parseInt(input.substring(7)) - 1;
                this.list.get(taskindex).markAsNotDone();
                System.out.println("Aight, marking this as not done:\n" + this.list.get(taskindex).toString());
            } else { //Task Creation
                Task task = null;
                if (isToDo(input)) {
                    task = new ToDo(input);
                    this.list.add(task);
                } else if (isDeadline(input)) {
                    int index = input.indexOf(" /by ");
                    task = new Deadline(input.substring(0, index), input.substring(index + 5));
                    this.list.add(task);
                } else if (isEvent(input)) {
                    int fromdex = input.indexOf(" /from ");
                    int todex = input.indexOf(" /to ");
                    task = new Event(input.substring(0, fromdex), input.substring(fromdex + 7, todex), input.substring(todex + 5));
                    this.list.add(task);
                } else {
                    throw new DukeException("What are you saying");
                }
                System.out.println("Roger. This task has been added:\n" + "  " + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in your list.");
            }
        }
    }

    public static boolean isMark(String input, int listSize) {
        if (input.length() >=  6 && input.substring(0, 5).equals("mark ") && isNumeric(input.substring(5))) {
            int taskindex = Integer.parseInt(input.substring(5)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
            return false;
    }

    public static boolean isUnMark(String input, int listSize) {
        if (input.length() >=  8 && input.substring(0, 7).equals("unmark ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
            return false;
    }

    public static boolean isToDo(String input) throws DukeException {
        if (input.length() >= 5 && input.substring(0, 5).equals("todo ")) {
            if (input.length() == 5) {
                throw new DukeException("TODO needs a description!");
            }
            return true;
        }
        return false;
    }

    public static boolean isDeadline(String input) throws DukeException {
        if (input.length() >= 9 && input.substring(0, 9).equals("deadline ") && input.contains(" /by ")) {
            if (input.length() == 9) {
                throw new DukeException("DEADLINE needs a description!");
            }
            return true;
        }
        return false;
    }

    public static boolean isEvent(String input) throws DukeException {

        if (input.length() >= 6 && input.substring(0, 6).equals("event ") &&
                input.contains(" /from ") && input.contains(" /to ")) {
            int fromdex = input.indexOf(" /from ");
            int todex = input.indexOf(" /to ");
            if (fromdex + 7 > todex) {
                throw new DukeException("What are you saying?");
            }
            if (input.length() == 6) {
                throw new DukeException("EVENT needs a description!");
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
