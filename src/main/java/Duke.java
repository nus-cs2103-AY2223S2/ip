import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String LINE = "----------------------------------------------";
    public static void main(String[] args) throws EmptyTaskException, InvalidRequestException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(LINE+"\n");
        while (true){
            String command = scanner.nextLine();
            String[] splittedCmd = command.split(" ", 2);
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
                try {
                    System.out.println(LINE);
                    int idx = Integer.parseInt(splittedCmd[1]) - 1;
                    Task task = list.get(idx);
                    System.out.println("\tOK, I've marked this task as done:");
                    task.mark();
                    System.out.println("\t" + task.toString());
                    System.out.println(LINE + "\n");
                    list.set(idx, task);
                }catch (Exception e){
                    System.out.println("\tOOPS!!! Please input the index.\n" + LINE + "\n");
                }
            }
            else if (splittedCmd[0].equals("unmark")) {
                try {
                    System.out.println(LINE);
                    int idx = Integer.parseInt(splittedCmd[1]) - 1;
                    Task task = list.get(idx);
                    System.out.println("\tNice! I've unmarked this task as not done yet:");
                    task.unmark();
                    System.out.println("\t" + task.toString());
                    System.out.println(LINE + "\n");
                    list.set(idx, task);
                } catch (Exception e) {
                    System.out.println("\tOOPS!!! Please input the index.\n" + LINE + "\n");
                }
            }
            else if (splittedCmd[0].equals("todo")){
                try {
                    System.out.println(LINE);
                    ToDos todos = new ToDos(splittedCmd[1]);
                    list.add(todos);
                    System.out.println("\t" + "Got it. I've added this task:");
                    System.out.println("\t" + todos.toString());
                    System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                    System.out.println(LINE + "\n");
                }catch(Exception e){
                    System.out.println("\tOOPS!!! The description of a todo cannot be empty.\n" + LINE + "\n");
                }
            }
            else if (splittedCmd[0].equals("deadline")){
                try {
                    System.out.println(LINE);
                    String[] request = splittedCmd[1].split("/", 2);
                    String task = request[0];
                    String date = request[1];
                    Deadlines deadlines = new Deadlines(task, date);
                    list.add(deadlines);
                    System.out.println("\t" + deadlines.toString());
                    System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                    System.out.println(LINE + "\n");
                }catch(Exception e) {
                    System.out.println("\tOOPS!!! Make sure insert all required input.\n" + LINE + "\n");
                }
            }
            else if (splittedCmd[0].equals("event")){
                try {
                    System.out.println(LINE);
                    String[] request = splittedCmd[1].split("/", 3);
                    String task = request[0];
                    String from = request[1];
                    String to = request[2];
                    Events event = new Events(task, from, to);
                    list.add(event);
                    System.out.println("\t" + event.toString());
                    System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                    System.out.println(LINE + "\n");
                }catch(Exception e) {
                    System.out.println("\tOOPS!!! Make sure insert all required input.\n" + LINE + "\n");
                }
            }
            else {
                System.out.println(LINE);
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means\n" + LINE + "\n");
            }
        }
    }
}
