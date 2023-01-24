import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello there! I am 4RTHUR";
    private static final String INIT_ERR_MSG = "Failed to init!";

    private final IFormatter responseFormatter;
    private final Scanner scanner;

    public Ui() {
       this.responseFormatter = new ResponseFormatter();
       scanner = new Scanner(System.in);
    }

    public boolean hasUserInput() {
        return scanner.hasNext();
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void displayGreeting() {
        System.out.println(responseFormatter.format("\n" + LOGO));
        System.out.println(responseFormatter.format(GREETING));
    }

    public void displayInitError() {
        System.out.println(responseFormatter.format(INIT_ERR_MSG));
    }

    public void displayResponse(String response) {
       System.out.println(responseFormatter.format(response));
    }
}
