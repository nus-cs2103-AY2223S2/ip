import java.util.Arrays;
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
        TaskList ls = new TaskList();

        int index;
        Task t;
        String[] temp;
        String taskDes;

        while (flag) {
            try {
                Scanner sc = new Scanner(System.in);
                String inp = sc.nextLine();
                String[] s = inp.split(" ");

                switch (s[0]) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < ls.count(); i++) {
                            System.out.print(i + 1 + ".");
                            ls.getTask(i).printStatus();
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        flag = false;
                        break;
                    case "mark":
                        if (s.length<2) {
                            throw new DukeException("I don't know which task to mark...");
                        }
                        index = Integer.parseInt(s[1]);
                        t = ls.getTask(index - 1);
                        t.status = true;
                        System.out.println("Nice! I've marked this task as done:");
                        t.printStatus();
                        break;
                    case "unmark":
                        if (s.length<2) {
                            throw new DukeException("I don't know which task to mark...");
                        }
                        index = Integer.parseInt(s[1]);
                        t = ls.getTask(index - 1);
                        t.status = false;
                        System.out.println("OK, I've marked this task as not done yet:");
                        t.printStatus();
                        break;
                    case "todo":
                        if (s.length<2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        temp = Arrays.copyOfRange(s, 1, s.length);
                        taskDes = String.join(" ", temp);
                        ToDos td = new ToDos(false, taskDes);
                        ls.addTask(td);
                        break;
                    case "deadline":
                        if (s.length<2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        temp = Arrays.copyOfRange(s, 1, s.length);
                        taskDes = String.join(" ", temp);
                        Deadlines dl = new Deadlines(false, taskDes);
                        ls.addTask(dl);
                        break;
                    case "event":
                        if (s.length<2) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        temp = Arrays.copyOfRange(s, 1, s.length);
                        taskDes = String.join(" ", temp);
                        Events ev = new Events(false, taskDes);
                        ls.addTask(ev);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
