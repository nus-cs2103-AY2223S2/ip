import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ,\n";
        System.out.println("Hello, I'm\n" + logo + "how may I help?");

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("exit")) break;
            System.out.println(input);
        }
    }
}
