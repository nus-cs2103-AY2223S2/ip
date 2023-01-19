import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Scanner sc = new Scanner(System.in);
        ToDoList ls = new ToDoList();

        Duke.welcomeMsg();
        Duke.input(sc, ls);
        Duke.endMsg();
    }

    private static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "What can the Duke help you with today?\n" + divider);
    }

    private static void endMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "Goodbye, feel free to call the Duke again whenever you need.\n" + divider);
    }

    private static void input(Scanner sc, ToDoList ls) {
        while (true) {
            try {
                String input[] = sc.nextLine().split(" ", 2);
                String command = input[0];
                String sub;
                String subinput[];

                switch (command) {
                    case "bye":
                        return;
                    case "list":
                        System.out.println(ls);
                        break;
                    case "mark":
                        sub = input[1];
                        ls.markTask(Integer.parseInt(sub));
                        break;
                    case "unmark":
                        sub = input[1];
                        ls.unmarkTask(Integer.parseInt(sub));
                        break;
                    case "todo":
                        sub = input[1];
                        ls.add(new ToDoTask(sub));
                        break;
                    case "deadline":
                        sub = input[1];
                        subinput = sub.split(" /by ");
                        ls.add(new DeadlineTask(subinput[0], subinput[1]));
                        break;
                    case "event":
                        sub = input[1];
                        subinput = sub.split(" /from ");
                        String duration[] = subinput[1].split(" /to ");
                        ls.add(new EventTask(subinput[0], duration[0], duration[1]));
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
