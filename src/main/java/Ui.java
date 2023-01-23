import java.util.Scanner;

public class Ui {
    private Scanner sc;
    String LOGO = "       _             _    _    _                _                \n" +
            "  ___ | |__    __ _ | |_ | |_ (_) _ __    __ _ | |_  ___   _ __  \n" +
            " / __|| '_ \\  / _` || __|| __|| || '_ \\  / _` || __|/ _ \\ | '_ \\ \n" +
            "| (__ | | | || (_| || |_ | |_ | || | | || (_| || |_| (_) || | | |\n" +
            " \\___||_| |_| \\__,_| \\__| \\__||_||_| |_| \\__, | \\__|\\___/ |_| |_|\n" +
            "                                         |___/                   ";


    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome(){
        reply("Hello from\n" + LOGO);
        reply("This is Chattington the Chatter, I'll chat with you.\n" +
                "What can I do for you?\n");
    }


    public void reply(String s) {
        if (!s.endsWith("\n")) {
            s += '\n';
        }
        for (String line : s.split("\n")) {
            System.out.println(indent(line));
        }
    }

    public void showError(String s) {
        reply(s);
    }

    public void showLine() {
        System.out.println("----------------------------------------");
    }

    private static String indent(String s) {
        return "\t" + s;
    }
}
