import java.util.Scanner;

public class Duke {

    public static void userInput() {
        Data data = new Data();
        System.out.println("Greetings");
        Scanner user = new Scanner(System.in);

        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye!");
                break;
            }

            if (input.equals("list")) {
                data.printData();
                continue;
            }

            if (input.contains("unmark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                data.unmark(pos-1);
                System.out.println("Unmarked:");
                System.out.println(data.getEntry(pos-1).toString());
                continue;
            }

            if (input.contains("mark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                data.mark(pos-1);
                System.out.println("Marked:");
                System.out.println(data.getEntry(pos-1).toString());
                continue;
            }

            if (input.contains("delete")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                Task del = data.getEntry(pos-1);
                data.removeEntry(pos-1);
                System.out.println("Deleted:");
                System.out.println(del.toString());
                continue;
            }

            if (input.contains("todo ")) {
                Task todo = new ToDo();
                try {
                    todo.genDscp(input);
                } catch (DukeExceptions e){
                    System.out.println(e.getMessage());
                    continue;
                }
                data.addEntry(todo);
                System.out.println(String.format("Now you have %d tasks in the list", data.getNum()));
                continue;
            }

            if (input.contains("event ")) {
                Task event = new Event();
                try {
                    event.genDscp(input);
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                data.addEntry(event);
                System.out.println(String.format("Now you have %d tasks in the list", data.getNum()));
                continue;
            }

            if (input.contains("deadline ")) {
                Task deadline = new Deadline();
                try {
                    deadline.genDscp(input);
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                data.addEntry(deadline);
                System.out.println(String.format("Now you have %d tasks in the list", data.getNum()));
                continue;
            }
            System.out.println("I do not understand your instructions...");
        }
        return;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        userInput();
    }
}
