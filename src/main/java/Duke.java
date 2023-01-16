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
            String[] split = userLine.split(" ");
            String command = split[0];
            switch (command) {
                case "bye":
                    System.out.println("Bye, hope to see you again.");
                    break;
                case "list":
                    printAllTasks(itemCount, list);
                    break;
                case "mark": {
                    int number = Integer.parseInt(split[1]) - 1;
                    list[number].setIsDone(true);
                    System.out.println("done");
                    printAllTasks(itemCount, list);
                    break;
                }
                case "unmark": {
                    int number = Integer.parseInt(split[1]) - 1;
                    list[number].setIsDone(false);
                    System.out.println("done");
                    printAllTasks(itemCount, list);
                    break;
                }
                case "todo": {
                    try {
                        list[itemCount] = new ToDo(split[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Description cannot be empty for todo");
                    }
                    itemCount++;
                    System.out.println("Added: " + split[1]);
                    break;
                }
                case "deadline": {
                    split = userLine.split(" (/by)|(deadline)");
                    if (split.length < 3) {
                        System.out.println("Invalid format");
                        break;
                    }
                    list[itemCount] = new Deadline(split[1].trim(), split[2]);
                    itemCount++;
                    System.out.println("Added: " + split[1]);
                    break;
                }
                case "event": {
                    split = userLine.split(" (/from)|(/to)|(event)");
                    if (split.length < 4) {
                        System.out.println("Invalid format");
                        break;
                    }
                    list[itemCount] = new Event(split[1].trim(), split[2], split[3]);
                    itemCount++;
                    System.out.println("Added: " + split[1].trim());
                    break;
                }
                default:
                    System.out.println("Command not found");
            }
        }
    }
}
