import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> todos = new ArrayList<>();

    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        print(greeting);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                System.exit(0);
            } else if (input.equals("list")) {
                if (todos.isEmpty()) {
                    print("None yet.");
                } else {
                    int i = 1;
                    for (String s : todos) {
                        print(i + ". " + s);
                        i++;
                    }
                }
            } else {
                todos.add(input);
                print("added:" + input);
            }
        }
    }
}