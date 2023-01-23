import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private static final Path STORAGE_LOCATION = Paths.get(".", "data", "storage.txt");
    public static void main(String[] args) throws Exception {
        String LOGO = "       _             _    _    _                _                \n" +
                "  ___ | |__    __ _ | |_ | |_ (_) _ __    __ _ | |_  ___   _ __  \n" +
                " / __|| '_ \\  / _` || __|| __|| || '_ \\  / _` || __|/ _ \\ | '_ \\ \n" +
                "| (__ | | | || (_| || |_ | |_ | || | | || (_| || |_| (_) || | | |\n" +
                " \\___||_| |_| \\__,_| \\__| \\__||_||_| |_| \\__, | \\__|\\___/ |_| |_|\n" +
                "                                         |___/                   ";
        System.out.println("Hello from\n" + LOGO);
        ChatBot chatBot = new ChatBot(STORAGE_LOCATION);

        chatBot.reply("This is Chattington the Chatter, I'll chat with you.\n" +
                "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                chatBot.close();
                break;
            } else {
                try {
                    chatBot.processInput(input);
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }
}
