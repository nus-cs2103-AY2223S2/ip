import java.util.Scanner;

public class Ui {
    Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    public void readInput() {
        System.out.println("Greetings");
        Scanner user = new Scanner(System.in);
        String responseMsg = "";
        while (true) {
            String input = user.nextLine();
            responseMsg = this.parser.parse(input);
            System.out.println(responseMsg);
            if (responseMsg.equals("Bye!")) {
                break;
            }
        }
    }
}
