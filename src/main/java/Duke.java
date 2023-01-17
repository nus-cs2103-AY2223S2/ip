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

        Scanner input = new Scanner(System.in);

        while (doLoop) {
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
                    System.out.println(this.formatString("Here are the tasks in your list:\n" + this.formatList(userTasks)));
                    break;
                }
                case ("mark"): {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    t.markAsDone();
                    System.out.println(this.formatString("Nice! I've marked this task as done:\n" + t));
                    break;
                }
                case ("unmark"): {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    t.unmarkAsDone();
                    System.out.println(this.formatString("OK, I've marked this task as not done yet:\n" + t));
                    break;
                }
                case ("todo"): {
                    String info = arr[1];
                    Task t = new ToDo(info);
                    userTasks.add(t);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                    break;
                }
                case ("deadline"): {
                    String info = arr[1];
                    String temp[] = info.split("/", 2);
                    String description = temp[0].trim();
                    String by = temp[1].split(" ", 2)[1].trim();
                    Task t = new Deadline(description, by);
                    userTasks.add(t);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                    break;
                }
                case ("event"): {
                    String info = arr[1];
                    String temp[] = info.split("/", 3);
                    String description = temp[0].trim();
                    String from = temp[1].split(" ", 2)[1].trim();
                    String to = temp[2].split(" ", 2)[1].trim();
                    Task t = new Event(description, from, to);
                    userTasks.add(t);
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                    break;
                }
                default: {
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
