import java.util.Scanner;

public class KiraBot {

    private static void printFormatString(String raw) {
        StringBuilder response = new StringBuilder("============= KiraBot ============= \n");
        response.append(raw);
        response.append("=================================== \n");
        System.out.println(response.toString());
    }

    private static void printTaskFormatString(Task task, Store db) {
        StringBuilder ret = new StringBuilder("Storing...\n");
        ret.append(task);
        ret.append("\nYou currently have ");
        ret.append(db.getTotal() + " Tasks\n");
        printFormatString(ret.toString());
    }

    private static void listenForCommand() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;
        Store database = new Store();

        while (isActive) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                String command = input[0];
                switch (command) {
                case "bye":
                    isActive = false;
                    break;
                case "list":
                    String dataList = database.list();
                    printFormatString(dataList);
                    break;
                case "mark":
                    int index = Integer.valueOf(input[1]);
                    String output = database.mark(index);
                    printFormatString(output);
                    break;
                case "unmark":
                    index = Integer.valueOf(input[1]);
                    output = database.unmark(index);
                    printFormatString(output);
                    break;
                case "todo":
                    ToDo todo = new ToDo(input[1]);
                    database.store(todo);
                    printTaskFormatString(todo, database);
                    break;
                case "deadline":
                    String[] format = input[1].split(" /by ", 2);
                    Deadline deadline = new Deadline(format[0], format[1]);
                    database.store(deadline);
                    printTaskFormatString(deadline, database);
                    break;
                case "event":
                    format = input[1].split(" /");
                    Event event = new Event(format[0],
                                        format[1].substring(5), 
                                        format[2].substring(3));
                    database.store(event);
                    printTaskFormatString(event, database);
                    break;
                default:
                    printFormatString("Sorry, I don't know this command :C\n");
                    break;
                }
            } catch (KiraException e) {
                printFormatString("There seems to be an error...\n" + e.getMessage());
            }
        }
        sc.close();
    }
    public static void main(String[] args) {
        printFormatString("Hi! I am KiraBot!\nHow may I help you today?\n");
        listenForCommand();
        printFormatString("Bye! Have a nice day :)\n");
    }
}
