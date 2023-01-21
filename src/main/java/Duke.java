import java.util.*;
public class Duke {

    public static ArrayList<Task> list;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<Task>();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                getList();
            } else if (command.length() >= 6 && isMark(command) ) {
                int index = Integer.parseInt(String.valueOf(command.charAt(command.length()-1)));
                Task task = list.get(index-1);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("%s [%s] %s", "  ", task.getStatusIcon(), task.description));
            } else if (command.length() >= 8 && isUnmark(command)) {
                int index = Integer.parseInt(String.valueOf(command.charAt(command.length()-1)));
                Task task = list.get(index-1);
                task.unmark();
                System.out.println("OK,, I've marked this task as not done yet");
                System.out.println(String.format("%s [%s] %s", "  ", task.getStatusIcon(), task.description));
            } else {
                Task newTask = new Task(command);
                list.add(newTask);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void getList() {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println(String.format("%d. [%s] %s", i+1, task.getStatusIcon(), task.description));
        }
    }

    public static boolean isMark(String command) {
        return command.substring(0,4).equals("mark");
    }

    public static boolean isUnmark(String command) {
        return command.substring(0,6).equals("unmark");
    }
}






