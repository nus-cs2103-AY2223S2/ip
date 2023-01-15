import java.util.Scanner;

public class Connor {

    private static String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    private static String getTask(String input) {
        return input.substring(input.indexOf(' ') + 1, input.length());

    }

    public static void main(String[] args) {
        String name = "Connor";
        System.out.println("Hello! I'm " + name + ", the android sent by Cyberlife");
        System.out.println("Please type in your command below.");
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String command = getCommand(input);
            try {
                switch (Command.valueOf(command)) {
                    case HI:
                        System.out.println("Hi, I hope that you are having a nice day.");
                        break;

                    case ADD:
                        // TODO: handle errors and invalid commands.
                        String task = getTask(input);
                        list.addTask(task);
                        System.out.println("I have added the task to my memory");
                        break;

                    case LIST:
                        list.printList();
                        break;

                    case BYE:
                        System.out.println("It was a good session Hank, Bye.");
                        sc.close();
                        return;

                }
            } catch (IllegalArgumentException e) {
                System.out.println("My program does not understand your command");
            }
        }


    }
}
