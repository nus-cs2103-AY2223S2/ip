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

        Functions fn = new Functions(ls);

        int index;
        Task t;
        String[] temp;
        String taskDes;

        while (flag) {
            try {
                Scanner sc = new Scanner(System.in);
                String inp = sc.nextLine();
                String[] s = inp.split(" ");

                //could use enums here to check user input before going into switch case

                switch (s[0]) {
                    case "list":
                        fn.list(inp);
                        break;
                    case "bye":
                        flag = fn.bye();
                        break;
                    case "mark":
                        fn.mark(inp);
                        break;
                    case "unmark":
                        fn.unmark(inp);
                        break;
                    case "delete":
                        fn.delete(inp);
                        break;
                    case "todo":
                        fn.todo(inp);
                        break;
                    case "deadline":
                        fn.deadline(inp);
                        break;
                    case "event":
                        fn.events(inp);
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
