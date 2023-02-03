import java.util.Scanner;

public class Parser {
    private Scanner sc = new Scanner(System.in);
    private String input;
    private String[] raw;

    public void initialise() {
        System.out.print("User: ");
        this.raw = sc.nextLine().split(" ");
        this.input = raw[0];
    }

    public void newInput() {
        this.raw = sc.nextLine().split(" ");
        this.input = raw[0];
    }

    public boolean isInput(String s) {
        return this.input.equalsIgnoreCase(s);
    }

    public String currInput() {
        return this.input;
    }

    public int getTaskNum() {
        return Integer.parseInt(raw[1]) - 1;
    }

    public String[] getRaw() {
        return this.raw;
    }

    public void closeParser() {
        sc.close();
    }
    
}
