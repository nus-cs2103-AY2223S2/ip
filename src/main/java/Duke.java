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
        TaskList tl = new TaskList();
        String greeting = duke.greetUser();
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(!userInput.equals("bye")) {
            if(!userInput.equals("list")) {
                Task task = new Task(userInput);
                String addConfirmation = tl.addTask(task);
                System.out.println(addConfirmation);
                userInput = sc.nextLine();
            } else {
                String output = tl.printTaskList();
                System.out.println(output);
                userInput = sc.nextLine();
            }
        }

        String farewell = duke.farewellUser();
        System.out.println(farewell);
    }
}
