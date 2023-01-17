import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text = "Hello! I'm Duke \n What can I do for you";
        String horiLine = "___________________________";
        String terminate = "bye";
        Scanner s = new Scanner(System.in);
        while (!text.equals(terminate)) {
            System.out.println(horiLine);
            System.out.println(text);
            System.out.println(horiLine);
            text = s.nextLine();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
