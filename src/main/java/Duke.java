import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String LINE = "----------------------------------------------";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(LINE+"\n");
        while (true){
            String command = scanner.nextLine();
            String[] splittedCmd = command.split(" ");
            if (command.equals("bye")){
                System.out.println(LINE);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(LINE+"\n");
                break;
            }
            else if (command.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < list.size(); i++){
                    Task task = list.get(i);
                    System.out.println("\t" + (i + 1) + ". " + task.toString());
                }
                System.out.println(LINE+"\n");
            }
            else if (splittedCmd[0].equals("mark")) {
                int idx = Integer.parseInt(splittedCmd[1]) - 1;
                System.out.println(LINE);
                System.out.println("\tOK, I've marked this task as not done yet:");
                Task task = list.get(idx);
                task.mark();
                System.out.println("\t" + task.toString());
                System.out.println(LINE+"\n");
                list.set(idx, task);
            }
            else if (splittedCmd[0].equals("unmark")) {
                int idx = Integer.parseInt(splittedCmd[1]) - 1;
                System.out.println(LINE);
                System.out.println("\tNice! I've unmarked this task as done:");
                Task task = list.get(idx);
                task.unmark();
                System.out.println("\t" + task.toString());
                System.out.println(LINE+"\n");
                list.set(idx, task);
            }
            else {
                System.out.println(LINE);
                System.out.println("\t" + "added: " + command);
                System.out.println(LINE + "\n");
                Task task = new Task(command);
                list.add(task);
            }
        }
    }
}
