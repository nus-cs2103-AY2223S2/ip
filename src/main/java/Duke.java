import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String EXIT_COMMAND = "bye";
        String LIST_COMMAND = "list";

        List<String> current_list = new ArrayList<>();

        Scanner user_input = new Scanner(System.in);

        System.out.println("Hello! I'm Anton's Bot");
        System.out.println("What can I do for you?");

        while (true){
            String current_input = user_input.nextLine();
            if (current_input.equals(EXIT_COMMAND)) {
                System.out.println("        Bye. Hope to see you again soon!");
                break;
            } else if (current_input.equals(LIST_COMMAND)) {
                for (int i = 0; i < current_list.size(); i++) {
                    System.out.println(String.format("        %d. %s", i + 1, current_list.get(i)));
                }
            } else {
                System.out.println("        added: " + current_input);
                current_list.add(current_input);
            }
        }
        user_input.close();
    }
}
