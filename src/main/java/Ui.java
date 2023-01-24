import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
        print("I am Duke.\nHow may I be of service?");
    }

    private void frame() {
        System.out.println("\t____________________________________________________________");
    }
    private void inner(String text) {
        for (String subStr : text.split("\n", -1)) {
            System.out.println("\t" + subStr);
        }
    }
    public void print(String text) {
        frame();
        inner(text);
        frame();
    }
    public void print(String[] texts) {
        frame();
            for (String subStr : texts) {
                inner(subStr);
            }
        frame();
    }

    public String nextLine() {
        return sc.nextLine();
    }

}
