import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> todoList = new ArrayList<>();

        System.out.println(formatOutput("Hey there! I'm Sirius\n\t What can I do for you today? :D"));

        String input = sc.nextLine();
        while (!isBye(input)) {
            if (isList(input)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < todoList.size(); i++) {
                    int count = i + 1;
                    String res = count + ". " + todoList.get(i);
                    if(i != todoList.size() - 1) {
                        res += "\n\t ";
                    }
                    sb.append(res);
                }
                System.out.println(formatOutput(sb.toString()));
            } else {
                todoList.add(input);
                System.out.println(formatOutput("added: " + input));
            }
            input = sc.nextLine();
        }

        System.out.println(formatOutput("Well, I'm off! Hope to see you again soon :)"));
    }

    public static String formatOutput(String input) {
        String line = "\t____________________________________________________________\n";
        return line + "\t " + input + "\n" + line;
    }

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }
}
