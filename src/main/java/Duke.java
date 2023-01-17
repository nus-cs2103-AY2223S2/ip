import java.util.*;
public class Duke {
    public static final String dashes = "--------------------------------------";
    public String formatString(String input) {
        return dashes + "\n" + input + "\n" + dashes + "\n";
    }
    public void echo() {
        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(this.formatString(line));
        }
    }
    public String formatList(List userTasks) {
        String formattedList = "";
        for (Object t : userTasks) {
            int pos = userTasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + "\n";
        }
        return formattedList.trim();
    }
    public void addMarkList() {
        List<Task> userTasks = new ArrayList<>();

        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));

        boolean doLoop = true;

        while (doLoop) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            String arr[] = line.split(" ", 2);
            String keyword = arr[0];

            switch (keyword.toLowerCase()) {
                case ("bye"): {
                    doLoop = false;
                    System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                    break;
                }
                case ("list"): {
                    System.out.println(this.formatString("Here are the tasks in your list: \n" + this.formatList(userTasks)));
                    break;
                }
                case ("mark"): {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    t.markAsDone();
                    System.out.println(this.formatString("Nice! I've marked this task as done: \n" + t));
                    break;
                }
                case ("unmark"): {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    t.unmarkAsDone();
                    System.out.println(this.formatString("OK, I've marked this task as not done yet: \n" + t));
                    break;
                }
                default: {
                    Task t = new Task(line);
                    userTasks.add(t);
                    System.out.println(this.formatString(line));
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Duke gerty = new Duke();
        gerty.addMarkList();
    }
}
