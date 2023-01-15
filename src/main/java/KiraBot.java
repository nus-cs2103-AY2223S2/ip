import java.util.Scanner;

public class KiraBot {

    private static String formatString(String raw) {
        StringBuilder response = new StringBuilder("============= KiraBot ============= \n");
        response.append(raw);
        response.append("=================================== \n");
        return response.toString();
    }

    private static void listenForCommand() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;
        Store database = new Store();

        while (isActive) {
            String command = sc.nextLine();
            switch (command) {
            case "bye":
                isActive = false;
                break;
            case "list":
                String dataList = database.list();
                System.out.println(formatString(dataList));
                break;
            default:
                if (command.matches("mark *[0-9]")) {
                    int index = Integer.valueOf(command.substring(5));
                    String output = database.mark(index);
                    System.out.println(formatString(output));
                } else if (command.matches("unmark *[0-9]")) {
                    int index = Integer.valueOf(command.substring(7));
                    String output = database.unmark(index);
                    System.out.println(formatString(output));
                } else {
                    database.store(command);
                    System.out.println(formatString("Stored: " + command + "\n"));
                }
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
