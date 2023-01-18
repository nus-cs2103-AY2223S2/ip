import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                break;
            } else {
                System.out.println(input + "\n");
            }
        }

    }


}
