import java.util.*;
public class Duke {

    ArrayList<Task> list;

    Duke() {
        this.list = new ArrayList<Task>();
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                duke.getList();
            } else {
                Task newTask = new Task(command);
                duke.list.add(newTask);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void getList() {
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            System.out.println(String.format("%d. %s", i+1, task.description));
        }
    }
}




