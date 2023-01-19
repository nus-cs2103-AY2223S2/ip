import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static int exit = 0;

    public static class Task {
        int done;
        String msg;
        public Task(String msg) {
            this.msg = msg;
            this.done = 0;
        }
    }
    static List<Task> todo = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        while (exit != 1) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();

            if (info.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                exit = 1;
            }
            else if (info.equals("list")) {
                showList();
            } else {
                if (info.contains(" ")) {
                    String action = info.split(" ", 2)[0];
                    switch (action) {
                        case "mark":
                            int n = Integer.parseInt(info.split(" ", 2)[1]) - 1;
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[X] "+todo.get(n).msg);
                            todo.get(n).done = 1;
                            break;
                        case "unmark":
                            int num = Integer.parseInt(info.split(" ", 2)[1]) - 1;
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[ ] "+todo.get(num).msg);
                            todo.get(num).done = 0;
                            break;
                        default:
                            System.out.println("added: " + info);
                            Task th = new Task(info);
                            todo.add(th);
                    }
                } else {
                    System.out.println("added: " + info);
                    Task th = new Task(info);
                    todo.add(th);
                }
            }
        }
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        int size = todo.size();
        for (int i=0; i<size; i++) {
            Task th = todo.get(i);
            if (th.done==0) {
                System.out.println( (i+1) + ".[ ] " + th.msg);
            } else {
                System.out.println( (i+1) + ".[X] " + th.msg);
            }
        }
    }
}
