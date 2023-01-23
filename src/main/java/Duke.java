import java.util.Scanner;

public class Duke {

    public static void userInput() {
        data hist = new data();
        System.out.println("Greetings");
        Scanner user = new Scanner(System.in);

        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye!");
                break;
            }

            if (input.equals("list")) {
                hist.printHist();
                continue;
            }

            if (input.contains("unmark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                hist.unmark(pos-1);
                System.out.println("Unmarked: ");
                System.out.println(hist.getHist(pos-1).toString());
                continue;
            }

            if (input.contains("mark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                //error check for pos exceeding size
                hist.mark(pos-1);
                System.out.println("Marked: ");
                System.out.println(hist.getHist(pos-1).toString());
                continue;
            }

            if (input.contains("todo")) {
                Task todo = new ToDo();
                todo.genDscp(input);
                hist.addHist(todo);
                System.out.println(String.format("Now you have %d tasks in the list", hist.getNum()));
                continue;
            }

            if (input.contains("event")) {
                Task event = new Event();
                event.genDscp(input);
                hist.addHist(event);
                System.out.println(String.format("Now you have %d tasks in the list", hist.getNum()));
                continue;
            }

            if (input.contains("deadline")) {
                Task deadline = new Deadline();
                deadline.genDscp(input);
                hist.addHist(deadline);
                System.out.println(String.format("Now you have %d tasks in the list", hist.getNum()));
                continue;
            }
//            Task newTask = new Task(input);
//            hist.addHist(newTask);
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
