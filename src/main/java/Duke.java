import java.util.Scanner;

public class Duke {
    private static void printAllTasks(int itemCount, Task[] list) {
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%d: %s%n", i + 1, list[i]);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello, Duke here. How can I help you?");
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int itemCount = 0;
        String userLine = "";
        while (!userLine.equals("bye")) {
            userLine = sc.nextLine();
            String command = userLine.split(" ")[0];
            switch (command) {
                case "bye":
                    System.out.println("Bye, hope to see you again.");
                    break;
                case "list":
                    printAllTasks(itemCount, list);
                    break;
                case "mark": {
                    int number = Integer.parseInt(userLine.split(" ")[1]) - 1;
                    list[number].setIsDone(true);
                    System.out.println("done");
                    printAllTasks(itemCount, list);
                    break;
                }
                case "unmark": {
                    int number = Integer.parseInt(userLine.split(" ")[1]) - 1;
                    list[number].setIsDone(false);
                    System.out.println("done");
                    printAllTasks(itemCount, list);
                    break;
                }
                case "todo": {
                    list[itemCount] = new ToDo(userLine);
                    itemCount++;
                    System.out.println("Added: " + userLine);
                    break;
                }
            }
        }
    }
}
