import java.util.Scanner;

public class Ui {
    private Scanner r;

    public Ui() {
        this.r = new Scanner(System.in);
    }

    public void greetUser() {
        System.out.println("Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n");
    }

    public String readCommand(){
        String input = r.nextLine();
        return input;
    }

    public boolean replyUser(String s) {
        if (s == "bye") {
            sayBye();
            return true;
        }
        System.out.println(s);
        return false;
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showLine() {
        System.out.println("__________________________________");
    }

    public void sayBye() {
        System.out.println("Bye I'm gonna go eat cookies. Hope to see you again soon!");
    }
}
