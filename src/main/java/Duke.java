import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, Duke here. How can I help you?");
        Scanner sc = new Scanner(System.in);
        TaskList taskList = Storage.load();
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
                System.out.println(taskList);
                break;
            case "mark": {
                int number = Integer.parseInt(split[1]) - 1;
                taskList.mark(number);
                System.out.println(taskList);
                break;
            }
            case "unmark": {
                int number = Integer.parseInt(split[1]) - 1;
                taskList.unmark(number);
                System.out.println(taskList);
                break;
            }
            case "todo": {
                taskList.addTodo(split[1]);
                break;
            }
            case "deadline": {
                split = userLine.split(" ");
                if (split.length < 4) {
                    System.out.println("Invalid format");
                    break;
                }
                taskList.addDeadline(split[1], split[3]);
                break;
            }
            case "event": {
                split = userLine.split(" ");
                if (split.length < 6) {
                    System.out.println("Invalid format");
                    break;
                }
                taskList.addEvent(split[1], split[3], split[5]);
                break;
            }
            case "delete": {
                if (split.length != 2) {
                    System.out.println("Invalid format");
                }
                try {
                    int itemIndex = Integer.parseInt(split[1]) - 1;
                    System.out.printf("Removing item %d\n", itemIndex + 1);
                    taskList.delete(itemIndex);
                    System.out.printf("Removal successful. New list: \n%s", taskList);
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
            Storage.store(taskList);
        }
    }
}
