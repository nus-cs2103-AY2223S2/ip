import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();

        System.out.println(duke.greeting());

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (true) {
            if (userInput.equals("list")) {
                System.out.println(duke.separate(duke.print_curr_tasks()));
                userInput = sc.nextLine();
            } else if (userInput.equals("bye")) {
                break;
            } else {
                Task task_given = new Task(userInput);
                duke.addTask(task_given);
                System.out.println(duke.separate(duke.msg_of_add(task_given)));
                userInput = sc.nextLine();
            }
        }
        System.out.println(duke.separate(duke.ending()));
    }
}
