import java.util.Scanner;
public class Ui {
    static String ERROR_HORIZONTAL = "X".repeat(40);
    static String HORIZONTAL = "+=".repeat(20);
    static String INDENT = "> ";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void indent() {
        /**
         * Indents the next line.
         */
        System.out.println(Ui.INDENT);
    }

    public void showLine() {
        /**
         * Prints a horizontal line.
         * @returns void
         */
        System.out.println(Ui.HORIZONTAL);
    }

    public void sayHi() {
        /**
         * Greets the user.
         */
        System.out.println("Hallo Hallo niece and nephew! My name is Uncle Roger.");
        System.out.println("What you want?");
        this.showLine();
    }

    public void sayBye() {
        System.out.println("Bye Bye. Leave good review please! PLEAASEEE!");
        this.showLine();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(Ui.ERROR_HORIZONTAL);
        System.out.println(errorMessage);
        System.out.println(Ui.ERROR_HORIZONTAL);
    }
}
