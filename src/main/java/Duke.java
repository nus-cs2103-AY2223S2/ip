import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello there! I am 4RTHUR";

    public static void main(String[] args) {
        System.out.println(GREETING + "\n" + LOGO);

        // Response Loop
        Scanner scanner = new Scanner(System.in);
        Bot bot =  new Bot();

        boolean running =  true;
        while (running) {
            String input = scanner.next();
            BotResult result = bot.process(input);

            System.out.println(result.response);

            running = result.resultStatus != BotResult.BotStatus.Exit;
        }
    }
}
