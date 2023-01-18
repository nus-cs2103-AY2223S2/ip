import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Scanner sc = new Scanner(System.in);
        ToDoList ls = new ToDoList();

        //welcome message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.welcomeMsg();
        Duke.input(sc, ls);
        Duke.endMsg();
    }

    private static void welcomeMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "What can the Duke help you with today?\n" + divider);
    }

    private static void endMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "Goodbye, feel free to call the Duke again whenever you need.\n" + divider);
    }

    private static void input(Scanner sc, ToDoList ls) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) { //close input reading
                break;
            }
            if (input.equals("list")) { //list out todoList
                System.out.println(ls);
                continue;
            }

            //tick & untick operations
            if (input.startsWith("mark")) {
                String ind = input.substring(5);
                ls.markTask(Integer.parseInt(ind));
                continue;
            }
            if (input.startsWith("unmark")) {
                String ind = input.substring(7);
                ls.unmarkTask(Integer.parseInt(ind));
                continue;
            }

            //task addition operations
            if (input.startsWith("todo")) {
                String sub = input.substring(5);
                ls.add(new ToDoTask(sub));
                continue;
            }
            if (input.startsWith("deadline")) {
                String inputArr[] = input.split(" /");
                ls.add(new DeadlineTask(inputArr[0].substring(9), inputArr[1].substring(3)));
                continue;
            }
            if (input.startsWith("event")) {
                String inputArr[] = input.split(" /");
                ls.add(new EventTask(inputArr[0].substring(6),
                        inputArr[1].substring(5),
                        inputArr[2].substring(3)));
                continue;
            }
            ls.add(new Task(input));
            System.out.println(divider + "added: " + input + "\n" + divider);
        }
    }
}
