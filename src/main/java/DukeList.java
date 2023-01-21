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

    public void output() {
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
            } else if (input.length() >=  6 && input.substring(0, 5).equals("mark ") && isNumeric(input.substring(5))) {
                int taskindex = Integer.parseInt(input.substring(5)) - 1;
                if (taskindex < 0 || taskindex > list.size() - 1) {
                    this.list.add(new Task(input));
                    System.out.println("added: " + input);
                } else {
                    this.list.get(taskindex).markAsDone();
                    System.out.println("Solid! I'm marking this task as done:\n" + this.list.get(taskindex).toString());
                }
            } else if (input.length() >=  8 && input.substring(0, 7).equals("unmark ") && isNumeric(input.substring(7))) {
                int taskindex = Integer.parseInt(input.substring(7)) - 1;
                if (taskindex < 0 || taskindex > list.size() - 1) {
                    this.list.add(new Task(input));
                    System.out.println("added: " + input);
                } else {
                    this.list.get(taskindex).markAsNotDone();
                    System.out.println("Aight, marking this as not done:\n" + this.list.get(taskindex).toString());
                }
            } else {
                this.list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
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
