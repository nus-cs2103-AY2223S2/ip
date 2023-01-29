import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Chiwa, your personal chatbot (◔◡◔✿)");
        System.out.println("What can I do for you today?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showMessageWithoutNewline(String msg) {
        System.out.print(msg);
    }

}
