import java.util.Scanner;
public class Duke {
    String lines = "____________________________________________________________\n";
    boolean exit = false;
    String msg;
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.welcome_msg());
        Scanner scanner = new Scanner(System.in);
        while(!duke.exit) {
            String inp = scanner.nextLine();
            duke.msg = duke.add_lines(inp + "\n");
            duke.check_msg(inp);
            System.out.println(duke.msg);
        }
    }
    String welcome_msg() {
        return this.add_lines("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    void check_msg(String inp) {
        if (inp.equals("bye")) {
            this.exit = true;
            this.msg = this.add_lines("Bye. Hope to see you again soon!\n");
        }
    }

    String add_lines(String msg) {
        return this.lines + msg + this.lines;
    }
}
