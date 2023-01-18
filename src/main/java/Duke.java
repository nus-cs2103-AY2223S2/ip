import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        chat();
    }

    private static void chat() {
        int count = 0;
        Task[] data = new Task[100];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String echo = sc.nextLine();
            String[] command = echo.split(" ");

            if (command[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (command[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + "." + data[i].getStatusIcon() + " " + data[i].description);
                }
                continue;
            }
            if (command[0].equals("mark") || command[0].equals("unmark")) {
                String action = command[0];
                int index = Integer.parseInt(command[1]) - 1;
                if (action.equals("mark")) {
                    data[index].markAsDone(); 
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(data[index].getStatusIcon() + " " + data[index].description);
                } else {
                    data[index].unmarkTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(data[index].getStatusIcon() + " " + data[index].description);
                }
                continue;
            }
            System.out.println("added: " + echo);
            data[count] = new Task(echo);
            count++;
        }
        sc.close();
    }
}
