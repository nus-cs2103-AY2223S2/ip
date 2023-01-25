import java.util.*;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Happie \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList taskList = new ArrayList<String>();


        while(!input.equals("bye")) {

            if (input.equals("list")) {
                for (int i = 1; i < taskList.size() + 1; i++) {
                    System.out.println(i + ". " + taskList.get(i - 1));
                }
            } else {
                taskList.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
