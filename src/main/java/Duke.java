import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> todo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            }
            if (str.equals("list")) {
                recite(todo);
            }
            else if (str.length() > 4 && (str.substring(0, 5).equals("mark "))) {
                int n = 0;
                Task t = null;
                if (str.length() == 6) {
                    String num = String.valueOf(str.charAt(5));
                    n = Integer.parseInt(num) - 1;
                    t = todo.get(n);
                    t.markAsDone();
                } else {
                    String num = str.substring(5, 7);
                    n = Integer.parseInt(num) - 1;
                    t = todo.get(n);
                    t.markAsDone();
                }
                System.out.println("Nice! I've marked this task as done:\n"
                                    + "  [" + t.getStatusIcon() + "] " + t.description);

            }
            else if (str.length() > 6 && (str.substring(0, 7).equals("unmark "))) {
                int n = 0;
                Task t = null;
                if (str.length() == 8) {
                    String num = String.valueOf(str.charAt(7));
                    n = Integer.parseInt(num) - 1;
                    t = todo.get(n);
                    t.unMark();
                } else {
                    String num = str.substring(7, 9);
                    n = Integer.parseInt(num) - 1;
                    t = todo.get(n);
                    t.unMark();
                }
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + "  [" + t.getStatusIcon() + "] " + t.description);

            }
            else {
                Task t = new Task(str);
                todo.add(t);
                System.out.println("added: " + str);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
    static void recite(ArrayList<Task> l) {
        for (int i = 1; i <= l.size(); i++) {
            Task t = l.get(i-1);
            System.out.println("" + i + ".[" + t.getStatusIcon() + "] " + t.description);
        }

    }
}
