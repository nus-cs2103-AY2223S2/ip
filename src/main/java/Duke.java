import java.util.Scanner;

public class Duke {
    public String greetUser() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public String farewellUser() {
        return "Bye. Hope to see you again soon!";
    }

    public void echoUser(String userInput) {
        System.out.println(userInput);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String greeting = duke.greetUser();
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        while(!userInput.equals("bye")) {
            duke.echoUser(userInput);
            userInput = sc.next();
        }

        String farewell = duke.farewellUser();
        System.out.println(farewell);
    }
}
