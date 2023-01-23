import java.util.Scanner;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, TODAY
}

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
                Command command;
                try {
                    command = Command.valueOf(input[0].toUpperCase());
                    switch (command) {
                    case BYE:
                        isActive = false;
                        break;
                    case LIST:
                        String dataList = database.list();
                        printFormatString(dataList);
                        break;
                    case MARK:
                        int index = Integer.valueOf(input[1]);
                        String output = database.mark(index);
                        printFormatString(output);
                        break;
                    case UNMARK:
                        index = Integer.valueOf(input[1]);
                        output = database.unmark(index);
                        printFormatString(output);
                        break;
                    case TODO:
                        ToDo todo = new ToDo(input[1]);
                        database.store(todo);
                        printTaskFormatString(todo, database);
                        break;
                    case DEADLINE:
                        String[] format = input[1].split(" /by ", 2);
                        Deadline deadline = new Deadline(format[0], format[1]);
                        database.store(deadline);
                        printTaskFormatString(deadline, database);
                        break;
                    case EVENT:
                        format = input[1].split(" /");
                        Event event = new Event(format[0],
                                            format[1].substring(5), 
                                            format[2].substring(3));
                        database.store(event);
                        printTaskFormatString(event, database);
                        break;
                    case DELETE:
                        index = Integer.valueOf(input[1]);
                        output = database.delete(index);
                        printFormatString(output);
                        break;
                    case TODAY:
                        output = database.findToday();
                        printFormatString(output);
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    throw new KiraException("Sorry, I don't know this command :C\n");
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
