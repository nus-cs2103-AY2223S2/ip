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

            if (input.contains("mark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                hist.mark(pos-1);
                System.out.println("Marked: ");
                System.out.println(hist.getHist(pos-1).toString());
                continue;
            }

            if (input.contains("unmark")) {
                char query = input.charAt(input.length() - 1);
                int pos = Character.getNumericValue(query);
                hist.unmark(pos-1);
                System.out.println("unmarked: ");
                System.out.println(hist.getHist(pos-1).toString());
                continue;
            }
            hist.addHist(input);
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
