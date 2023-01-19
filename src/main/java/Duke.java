import java.util.*;
public class Duke {
    protected enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        List myList = new List();
        System.out.println("Hi! I am Duke. How may I help you today?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            CommandEnum cmd;
            try {
                cmd = CommandEnum.valueOf(inputArr[0].toUpperCase());
            }
            catch (IllegalArgumentException i){
                System.out.println("OPPS! I'm sorry, there is no such command.\nPlease try again.\n");
                continue;
            }
            switch(cmd) {
                case BYE:
                    System.out.println("Bye! Hope to see you again soon!\n");
                    return;

                case LIST:
                    myList.print();
                    break;

                case MARK:
                    try {
                        myList.mark(Integer.parseInt(inputArr[1]) - 1);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case UNMARK:
                    try {
                        myList.unmark(Integer.parseInt(inputArr[1]) - 1);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case DELETE:
                    try {
                        myList.delete(Integer.parseInt(inputArr[1]) - 1);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;

                case TODO: case DEADLINE: case EVENT:
                    try {
                        myList.add(input);
                    }
                    catch (DukeException e) {
                        System.out.println(e);
                    }
            }
        }
    }
}
