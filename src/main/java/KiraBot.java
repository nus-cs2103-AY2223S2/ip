import java.util.Scanner;

public class KiraBot {

    private static String formatString(String raw) {
        StringBuilder response = new StringBuilder("============= KiraBot ============= \n");
        response.append(raw);
        response.append("=================================== \n");
        return response.toString();
    }
    public static void main(String[] args) {
        String intro = formatString("Hi! I am KiraBot!\nHow may I help you today?\n");
        String outro = formatString("Bye! Have a nice day :)\n");
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        Store database = new Store();

        System.out.println(intro);
        while (active) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    active = false;
                    break;
                case "list":
                    String dataList = database.list();
                    System.out.println(formatString(dataList));
                    break;
                default:
                    database.store(command);
                    System.out.println(formatString("Stored: " + command + "\n"));
            }
        }

        System.out.println(outro);
        sc.close();
    }
}
