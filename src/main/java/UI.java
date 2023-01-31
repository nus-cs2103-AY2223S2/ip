import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI(){
        Scanner scanner = new Scanner(System.in);
        this.scanner = scanner;
    }

    public void showWelcome(){
        String start_message = "Hello! I'm Duke\n" +  "What can I do for you?";
    }


    public void showLine() {
        System.out.println("_________________________________");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public String readCommand(){
        String input = scanner.nextLine();
        return input;
    }




}
