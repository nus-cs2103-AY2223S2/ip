import java.util.Scanner;

public class KiraBot {

    private static String formatString(String raw) {
        StringBuilder response = new StringBuilder("============= KiraBot ============= \n");
        response.append(raw);
        response.append("=================================== \n");
        return response.toString();
    }

    private static String formatStoringTaskString(Task task, Store db) {
        StringBuilder ret = new StringBuilder("Storing...\n");
        ret.append(task);
        ret.append("\nYou currently have ");
        ret.append(db.getTotal() + " Tasks\n");
        return formatString(ret.toString());
    }

    private static void listenForCommand() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;
        Store database = new Store();

        while (isActive) {
            String[] input = sc.nextLine().split(" ", 2);
            String command = input[0];
            switch (command) {
            case "bye":
                isActive = false;
                break;
            case "list":
                String dataList = database.list();
                System.out.println(formatString(dataList));
                break;
            case "mark":
                int index = Integer.valueOf(input[1]);
                String output = database.mark(index);
                System.out.println(formatString(output));
                break;
            case "unmark":
                index = Integer.valueOf(input[1]);
                output = database.unmark(index);
                System.out.println(formatString(output));
                break;
            case "todo":
                ToDo todo = new ToDo(input[1]);
                database.store(todo);
                System.out.println(formatStoringTaskString(todo, database));
                break;
            case "deadline":
                String[] format = input[1].split(" /by ", 2);
                Deadline deadline = new Deadline(format[0], format[1]);
                database.store(deadline);
                System.out.println(formatStoringTaskString(deadline, database));
                break;
            case "event":
                format = input[1].split(" /");
                Event event = new Event(format[0],
                                    format[1].substring(5), 
                                    format[2].substring(3));
                database.store(event);
                System.out.println(formatStoringTaskString(event, database));
                break;
            default:
                System.out.println(formatString("Sorry, I don't know this command :C\n"));
                break;
            }
        }
        sc.close();
    }
    public static void main(String[] args) {
        String intro = formatString("Hi! I am KiraBot!\nHow may I help you today?\n");
        String outro = formatString("Bye! Have a nice day :)\n");

        System.out.println(intro);
        listenForCommand();
        System.out.println(outro);
    }
}
