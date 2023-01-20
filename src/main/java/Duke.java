import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Task t = new Task();

        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");


        while (true) {
            String type = s.nextLine();
            String[] tokens = type.split(" ");
            if (tokens[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (tokens[0].equals("list")) {
                t.showList();
            } else if(tokens[0].equals("mark")) {
                    int i = Integer.parseInt(tokens[1]);
                    t.markTask(i);
            } else if(tokens[0].equals("unmark")) {
                    int i = Integer.parseInt(tokens[1]);
                    t.unmarkedTask(i);
            } else {
                t.addTask(type);
            }

            }
        }
}//class

class Task {
    String s;
    List<String> task = new ArrayList<>();
    List<String> marked = new ArrayList<>();


    public void showList() {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < task.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + marked.get(i) + " " + task.get(i));
        }
    }

    public void markTask(int i) {
        int index = i-1;
        marked.set(index, "[X]");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(marked.get(index) + " " + task.get(index));
    }

    public void unmarkedTask(int i) {
        int index = i-1;
        marked.set(index, "[ ]");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(marked.get(index) + " " + task.get(index));
    }

    public void addTask(String s) {
        task.add(s);
        marked.add("[ ]");
        System.out.println("added: " + s);
    }
}

