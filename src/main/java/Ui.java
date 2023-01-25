import java.util.Scanner;

public class Ui {
    private Scanner r;

    public Ui() {
        this.r = new Scanner(System.in);
    }

    public String greetUser() {
        return "Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n";
    }

    public String readUserInput(){
        
        String input = r.nextLine();
        return input;
    }
}
