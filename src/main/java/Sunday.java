import java.util.Scanner;
public class Sunday {
    private static State state;
    private static Printer printer = new Printer();
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
                printer.printException(e);
            }
        }
        sc.close();
    }
    private static void greet() {
        printer.printBar();
        printer.printText("Hi! I'm Sunday, pleasure to meet you!");
        printer.printText("How can I help?");
        printer.printBar();
    }
}
