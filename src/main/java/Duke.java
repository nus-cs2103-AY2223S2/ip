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
                String input[] = Duke.commandHandler(sc.nextLine(), " ", 2, 1);
                String command = input[0];
                String sub;

                switch (command) {
                    case "bye":
                        return;
                    case "list":
                        System.out.println(ls);
                        break;
                    case "mark":
                        sub = input[1];
                        ls.markTask(Integer.parseInt(sub)); //numbersformatexception to be handled
                        break;
                    case "unmark":
                        sub = input[1];
                        ls.unmarkTask(Integer.parseInt(sub)); //numbersformatexception to be handled
                        break;
                    case "delete":
                        sub = input[1];
                        ls.delete(Integer.parseInt(sub)); //numbersformatexception to be handled
                        break;
                    case "todo":
                    case "event":
                    case "deadline":
                        Duke.taskCommandHandler(input, ls);
                        break;
                    default:
                        throw new DukeException("The Duke does not understand your words!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String[] commandHandler(String input, String regex,int limit, int minSize) throws DukeException {
        String sub[] = input.split(regex, limit);
        if (sub.length < minSize) {
            throw new DukeException("Not enough details are given!\nThe Duke expects more information!");
        }
        return sub;
    }

    private static void taskCommandHandler(String[] input, ToDoList ls) throws DukeException {
        String command = input[0];
        if (input.length < 2) {
            throw new DukeException("Not enough details are given!\n The Duke expects more information!");
        }
        if (command.equals("todo")) {
            ls.add(new ToDoTask(input[1]));
            return;
        }
        if (command.equals("deadline")) {
            String[] sub = Duke.commandHandler(input[1], " /by ", 2, 2);
            ls.add(new DeadlineTask(sub[0], sub[1]));
            return;
        }
        if (command.equals("event")) {
            String[] sub = Duke.commandHandler(input[1], " /from ", 2, 2);
            String[] subDuration = Duke.commandHandler(sub[1], " /to ", 2, 2);
            ls.add(new EventTask(sub[0], subDuration[0], subDuration[1]));
        }
    }

}
