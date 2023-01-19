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

        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int index = 0;
        while (true) {
            String data = scanner.nextLine();
            if (data.equals("bye")) {
                System.out.println("Goodbye! It's been a pleasure talking to you!");
                break;
            } else if (data.equals("list")) {
                for (int i = 0 ; i < index ; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else {
                list[index] = data;
                index++;
                System.out.println("Added: " + data);
            }
        }
    }
}
