import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        boolean flag = true;
        List<Task> ls = new ArrayList<>();

        while (flag) {
            Scanner sc = new Scanner(System.in);
            String inp = sc.nextLine();
            switch (inp) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i<ls.size();i++) {
                        System.out.print(i+1+".");
                        ls.get(i).printStatus();
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = false;
                    break;
                default:
                    //if input match mark/unmark x == we mark/unmark the line
                    if (inp.matches("mark \\d+")) {
                        String[] s = inp.split(" ");
                        int index = Integer.parseInt(s[1]);
                        Task t = ls.get(index-1);
                        t.status = true;
                        System.out.println("Nice! I've marked this task as done:");
                        t.printStatus();
                    } else if (inp.matches("unmark \\d+")) {
                        String[] s = inp.split(" ");
                        int index = Integer.parseInt(s[1]);
                        Task t = ls.get(index-1);
                        t.status = false;
                        System.out.println("OK, I've marked this task as not done yet:");
                        t.printStatus();
                    } else {
                        ls.add(new Task(false, inp));
                        System.out.println("added: "+inp);
                    }
            }
        }
    }
}
