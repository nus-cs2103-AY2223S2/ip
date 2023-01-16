package DukeBot;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class DukeBot {

    ArrayList<String> list;
    String frame = "    ____________________________________________________________\n";
    Scanner scanner;
    boolean isActive;


    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        this.isActive = true;
        this.list = new ArrayList<>();
    }

    public void activate() {
        System.out.println(this.frame +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                this.frame);
        while (this.isActive) {
            String line = this.scanner.nextLine();
            System.out.println(this.text(line));
        }
    }

    public String text(String line) {

        if (Objects.equals(line, "bye")) {
           return this.bye();
        } else if (Objects.equals(line, "list")) {
            return this.list();
        }
        else {
            return this.addItem(line);
        }

    }

    private String list() {
        StringBuilder res = new StringBuilder(this.frame);
        for (int i = 0; i < this.list.size(); i++) {
            res.append("     ").append(i + 1).append(". ").append(this.list.get(i)).append("\n");
        }
        return res.append(this.frame).toString();
    }

    public String addItem(String line) {
        this.list.add(line);
        return this.frame +
                "     added: " + line + "\n" +
                this.frame;
    }

    public String bye() {
        this.isActive = false;
        return this.frame +
                "     Bye. Hope to see you again soon!\n" +
                this.frame;
    }





}
