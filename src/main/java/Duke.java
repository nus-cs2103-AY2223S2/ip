import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String[] inputs = new String[100];

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        int index = -1;
        String lineBreak = "***---***---***---***---***---***---***" + "\n" + "    ";

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(lineBreak + "\n" + "    GoodBye, have a nice day!");
                break;
            } else if (input.equals("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= 100; i++) {
                    if (inputs[i - 1] != null) {
                        sb.append(i + ". " + input + "\n");
                    }
                }

                System.out.println(lineBreak + sb.toString());
            } else {
                index++;
                inputs[index] = input;
                System.out.println(lineBreak + "added: " + input);
            }
        }
    }
}
