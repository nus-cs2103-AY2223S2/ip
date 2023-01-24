import java.util.Scanner;

public class Parser {
    private final Scanner sc;

    Parser() {
        this.sc = new Scanner(System.in);
    }

    String[] parseUserInput() {
        String input = sc.nextLine();
        return input.split(" ");
    }
}
