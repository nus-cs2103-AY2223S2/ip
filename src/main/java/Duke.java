import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List ls = new ArrayList<>();

        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");
        String type = s.nextLine();

        while (true) {
            if (type.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(type.equals("list")) {
                for(int i=0; i < ls.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + ls.get(i));
                }
                type = s.nextLine();
            } else {
                System.out.println("added: " + type);
                ls.add(type);
                type = s.nextLine();
            }
        }
    }
}//class
