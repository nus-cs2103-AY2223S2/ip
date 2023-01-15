import java.util.Scanner;

public class KiraBot {

    private static String formatString(String raw) {
        StringBuilder response = new StringBuilder("============= KiraBot ============= \n");
        response.append(raw);
        response.append("\n");
        response.append("=================================== \n");
        return response.toString();
    }
    public static void main(String[] args) {
        String intro = formatString("Hi! I am KiraBot!\nHow may I help you today?");
        String outro = formatString("Bye! Have a nice day :)");
        Scanner sc = new Scanner(System.in);

        System.out.println(intro);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(formatString(command));
        }

        System.out.println(outro);
        sc.close();
    }
}
