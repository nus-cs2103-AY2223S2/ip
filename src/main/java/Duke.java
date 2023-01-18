import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        ArrayList<String> stringInputs = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < stringInputs.size(); i++) {
                    System.out.println((i + 1) + ". " + stringInputs.get(i));
                }
            } else {
                stringInputs.add(input);
                System.out.println(input + "\n");
            }
        }
    }
}
