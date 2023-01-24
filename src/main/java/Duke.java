import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            String textInput = scan.nextLine();
            if (textInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scan.close();
                return;
            }
            System.out.println(textInput);
        }
    }
}
