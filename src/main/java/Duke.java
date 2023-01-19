import java.util.*;

public class Duke {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        System.out.println("Hello! I'm Somebody\n" + "What can I do for you?");
        Scanner scn = new Scanner(System.in);
        while (true) {

            String input = scn.nextLine();

            if (Objects.equals(input.toLowerCase(), "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scn.close();
                return;
            } else if (Objects.equals(input.toLowerCase(), "list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);

            }

        }
    }
}
