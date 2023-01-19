import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FULL_LINE = "_______________________________________________\n";
    private static ArrayList<String> inputList = new ArrayList<>();
    public static void main(String[] args) {
        String welcomeString = "Hello I'm Duke! Type anything and I'll echo it.";
        String byeString = "Bye. Hope to see you again soon!";

        printFormattedOutput(welcomeString);

        String input = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                printFormattedOutput(byeString);
                break;
            } else if (input.equals("list")) {
                String output = "";
                for (int i = 0; i < inputList.size(); ++i) {
                    output += (i + 1) + ". " + inputList.get(i) + "\n";
                }
                printFormattedOutput(output);
            } else {
                inputList.add(input);
                String output = "Added: " + input;
                printFormattedOutput(output);
            }
        }
    }

    public static void printFormattedOutput(String output) {
        output = FULL_LINE + output + "\n" + FULL_LINE;
        System.out.println(output);
    }
}