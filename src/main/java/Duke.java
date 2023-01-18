import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        String LOGO = "       _             _    _    _                _                \n" +
                "  ___ | |__    __ _ | |_ | |_ (_) _ __    __ _ | |_  ___   _ __  \n" +
                " / __|| '_ \\  / _` || __|| __|| || '_ \\  / _` || __|/ _ \\ | '_ \\ \n" +
                "| (__ | | | || (_| || |_ | |_ | || | | || (_| || |_| (_) || | | |\n" +
                " \\___||_| |_| \\__,_| \\__| \\__||_||_| |_| \\__, | \\__|\\___/ |_| |_|\n" +
                "                                         |___/                   ";

        System.out.println("Hello from\n" + LOGO);

        ChatBot chatBot = new ChatBot();

        chatBot.reply("This is Chattington the Chatter, I'll chat with you.\n" +
                "What can I do for you?\n");

        while(true) {
            Scanner sc = new Scanner(System.in);
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
