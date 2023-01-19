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
                if(userInput.startsWith("mark")) {
                    String[] strArr = userInput.split(" ", 2);
                    int taskNumber = Integer.parseInt(strArr[1]);
                    if (taskNumber < 1 || taskNumber > tl.items) {
                        System.out.println("No such task detected!");
                        userInput = sc.nextLine();
                    } else {
                        Task taskToMark = tl.list.get(taskNumber - 1);
                        String markMessage = taskToMark.markAsDone();
                        System.out.println(markMessage);
                        userInput = sc.nextLine();
                    }
                } else if (userInput.startsWith("unmark")) {
                    String[] strArr = userInput.split(" ", 2);
                    int taskNumber = Integer.parseInt(strArr[1]);
                    if (taskNumber < 1 || taskNumber > tl.items) {
                        System.out.println("No such task detected!");
                        userInput = sc.nextLine();
                    } else {
                        Task taskToUnmark = tl.list.get(taskNumber - 1);
                        String unmarkMessage = taskToUnmark.markAsUndone();
                        System.out.println(unmarkMessage);
                        userInput = sc.nextLine();
                    }
                } else {
                    Task task = new Task(userInput);
                    String addConfirmation = tl.addTask(task);
                    System.out.println(addConfirmation);
                    userInput = sc.nextLine();
                }
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
