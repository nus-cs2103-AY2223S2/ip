import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            }
            if (str.equals("list")) {
                recite(list);
            }

            switch (getSwitch(str)) {
                case 0:
                    int n;
                    Task t;
                    String num;
                    num = str.substring(5);
                    n = Integer.parseInt(num) - 1;
                    t = list.get(n);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + "  [" + t.getStatusIcon() + "] " + t.description);
                    break;
                case 1:
                    int n_1;
                    Task t_1;
                    String num_1;
                    num_1 = str.substring(7, 9);
                    n_1 = Integer.parseInt(num_1) - 1;
                    t_1 = list.get(n_1);
                    t_1.unMark();
                    System.out.println("OK, I've marked this task as not done yet:\n"
                            + "  [" + t_1.getStatusIcon() + "] " + t_1.description);
                    break;
                case 2:
                    Task a = new Todo(str.substring(5));
                    list.add(a);
                    System.out.println("Got it. I've added this task:\n  "
                                        + a.toString() + "\nNow you have " + list.size()
                                        + " tasks in the list.");
                    break;
                case 3:
                    int ind = str.indexOf("/by");
                    Task b = new Deadline(str.substring(9, ind), str.substring(ind+3));
                    list.add(b);
                    System.out.println("Got it. I've added this task:\n  "
                            + b.toString() + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;
                case 4:
                    int index_1 = str.indexOf("/from");
                    int index_2 = str.indexOf("/to");
                    Task c = new Event(str.substring(6, index_1), str.substring(index_1+5, index_2),
                                            str.substring(index_2 + 3));
                    list.add(c);
                    System.out.println("Got it. I've added this task:\n  "
                            + c.toString() + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    static void recite(ArrayList<Task> l) {
        for (int i = 1; i <= l.size(); i++) {
            Task t = l.get(i-1);
            System.out.println("" + i + ". " + t.toString());
        }

    }
    static int getSwitch(String str) {
        if (str.startsWith("mark ")) return 0;
        if (str.startsWith("unmark ")) return 1;
        if (str.startsWith("todo ")) return 2;
        if (str.startsWith("deadline ")) return 3;
        if (str.startsWith("event ")) return 4;
        return 5;
    }
}
