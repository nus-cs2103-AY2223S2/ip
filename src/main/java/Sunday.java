import java.util.Scanner;
public class Sunday {
    private static State state;
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (state != State.BYE) {
            try {
                String command = sc.next();
                String input = sc.nextLine();
                state = State.determine(command);
                state.execute(input);
            } catch (SundayException e) {
                Printer.printException(e);
            }
        }
        sc.close();
    }
    private static void greet() {
        Printer.printBar();
        Printer.printText("Hi! I'm Sunday, pleasure to meet you!");
        Printer.printText("How can I help?");
        Printer.printBar();
    }
}
