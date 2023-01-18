import java.util.Scanner;
public class Duke {
    public static String formatOutput(String out) {
        final String divider = "____________________________________________________________";
        return String.format("\t%s\n\t%s\n\t%s", divider, out, divider);
    }
    public static void main(String[] args) {
        String[] store = new String[100];
        int storeCnt = 0;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(formatOutput("Hello! I'm Duke\n\tWhat can I do for you?"));
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            if (inputStr.equals("list")) {
                StringBuilder listOutput = new StringBuilder();
                for (int i = 0; i < storeCnt; i++) {
                    if (i > 0) {
                        listOutput.append('\t');
                    }
                    listOutput.append(String.format("%d. %s", i + 1, store[i]));
                    if (i < storeCnt - 1) {
                        listOutput.append("\n");
                    }
                }
                System.out.println(formatOutput(listOutput.toString()));
            } else {
                store[storeCnt] = inputStr;
                storeCnt++;
                System.out.println(formatOutput("added: " + inputStr));
            }
            inputStr = inputScanner.nextLine().trim();
        }
        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
    }
}
