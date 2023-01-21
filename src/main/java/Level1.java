import java.util.Scanner;

public class Level1 {
    private Scanner sc;

    public Level1(Scanner sc) {
        this.sc = sc;
    }

    public void output() {
        while (true) {
            String input = sc.nextLine();
            if ("bye".equals(input)) {
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
