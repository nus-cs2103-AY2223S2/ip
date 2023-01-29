import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Sunday {
    private static State state;
    public static void main(String[] args) {
        try {
            State.GREET.execute("create/load data file");
        } catch (SundayException e) {
            Printer.printException(e);
        }
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
}
