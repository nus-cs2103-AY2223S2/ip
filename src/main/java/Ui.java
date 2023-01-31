import java.util.Scanner;

public class Ui {
    private String userPrompt;
    private String systemPrompt;
    private Scanner sc;

    public Ui(String userPrompt, String systemPrompt) {
        this.userPrompt = userPrompt;
        this.systemPrompt = systemPrompt;
        this.sc = new Scanner(System.in);
    }

    public void prettyPrint(String output) {
        System.out.println(userPrompt + " " + output);
    }

    public void systemPrint(String output) {
        System.out.println(systemPrompt + " " + output);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
