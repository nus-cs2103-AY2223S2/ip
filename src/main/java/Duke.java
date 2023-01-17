import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
