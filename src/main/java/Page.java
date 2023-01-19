import java.util.Scanner;

public class Page {

    private Scanner scan;

    public Page() {
        scan = new Scanner(System.in);
    }

    private void echo() {
        String input = scan.nextLine();
        if (input.equals("bye")) {
            System.out.println("Farewell, my liege.");
        } else {
            System.out.println(input);
            echo();
        }
    }

    public static void main(String[] args) {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.";
        System.out.println(welcome);
        Page page = new Page();
        page.echo();
    }
}

