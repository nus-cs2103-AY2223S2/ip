import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Duke duke = new Duke();

        Scanner s = new Scanner(System.in);
        while (duke.isActive()) {
            String input = s.nextLine();
            duke.parseInput(input);
        }
    }
}
