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
                continue;
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
                    Task a;
                    try {
                        a = new Todo(str.substring(5));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    list.add(a);
                    System.out.println("Got it. I've added this task:\n  "
                                        + a.toString() + "\nNow you have " + list.size()
                                        + " tasks in the list.");
                    break;
                case 3:
                    int ind = str.indexOf("/by");
                    Task b = null;
                    try {
                        b = new Deadline(str.substring(9, ind), str.substring(ind+3));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    list.add(b);
                    System.out.println("Got it. I've added this task:\n  "
                            + b.toString() + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;
                case 4:
                    int index_1 = str.indexOf("/from");
                    int index_2 = str.indexOf("/to");
                    Task c = null;
                    try {
                        c = new Event(str.substring(6, index_1), str.substring(index_1+5, index_2),
                                                str.substring(index_2 + 3));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    list.add(c);
                    System.out.println("Got it. I've added this task:\n  "
                            + c.toString() + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;
                case 5:
                    int nD;
                    Task tD;
                    String numD;
                    numD = str.substring(7);
                    nD = Integer.parseInt(numD) - 1;
                    tD = list.get(nD);
                    list.remove(nD);
                    System.out.println("Noted. I've removed this task:\n  "
                            + tD.toString()
                            + "\nNow you have " + list.size()
                            + " tasks in the list.");
                    break;

                case 6:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

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
        if (str.startsWith("delete ")) return 5;
        return 6;
    }
}
