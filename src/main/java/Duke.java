import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void printAllTasks(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d: %s\n", i + 1, list.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello, Duke here. How can I help you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
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
                printAllTasks(list);
                break;
            case "mark": {
                int number = Integer.parseInt(split[1]) - 1;
                try {
                    list.get(number).setIsDone(true);
                    System.out.println("done");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Item does not exist");
                }
                printAllTasks(list);
                break;
            }
            case "unmark": {
                int number = Integer.parseInt(split[1]) - 1;
                try {
                    list.get(number).setIsDone(false);
                    System.out.println("done");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Item does not exist");
                }
                printAllTasks(list);
                break;
            }
            case "todo": {
                try {
                    list.add(new ToDo(split[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Description cannot be empty for todo");
                }
                System.out.println("Added: " + split[1]);
                break;
            }
            case "deadline": {
                split = userLine.split(" (/by)|(deadline)");
                if (split.length < 3) {
                    System.out.println("Invalid format");
                    break;
                }
                list.add(new Deadline(split[1].trim(), split[2]));
                System.out.println("Added: " + split[1]);
                break;
            }
            case "event": {
                split = userLine.split(" (/from)|(/to)|(event)");
                if (split.length < 4) {
                    System.out.println("Invalid format");
                    break;
                }
                list.add(new Event(split[1].trim(), split[2], split[3]));
                System.out.println("Added: " + split[1].trim());
                break;
            }
            case "delete": {
                if (split.length != 2) {
                    System.out.println("Invalid format");
                }
                try {
                    int itemIndex = Integer.parseInt(split[1]) - 1;
                    System.out.printf("Removing item %d: %s\n", itemIndex + 1, list.get(itemIndex));
                    list.remove(itemIndex);
                    System.out.println("Removal successful. New list:");
                    printAllTasks(list);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf("Error: Item %d does not exist", Integer.parseInt(split[1]));
                } catch (NumberFormatException e) {
                    System.out.printf("Error: %s is not a number", split[1]);
                }
                break;
            }
            default:
                System.out.println("Command not found");
            }
        }
    }
}
