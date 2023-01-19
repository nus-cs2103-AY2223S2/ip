import java.util.*;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        List<String> lst = new ArrayList<>();

        while (true) {
            String a = sc.nextLine().toLowerCase();
            if (Objects.equals(a, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (Objects.equals(a, "list")) {
                for (int i = 1; i <= lst.size(); i ++){
                    System.out.println(i + ". " + lst.get(i - 1));
                }
            } else {
                lst.add(a);
                System.out.println("added: " + a);
            }

        }
    }
}
