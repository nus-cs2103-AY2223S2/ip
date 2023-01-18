import java.util.Scanner;
public class Duke {
    String lines = "____________________________________________________________\n";
    boolean exit = false;
    String msg;
    String[] tasks = new String[100];
    int num_tasks = 0;
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.welcome_msg());
        Scanner scanner = new Scanner(System.in);
        while(!duke.exit) {
            String inp = scanner.nextLine();
            duke.msg = duke.add_lines("added: " + inp + "\n");
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
        } else if (inp.equals("list")) {
            this.msg = "";
            for(int i = 0; i < num_tasks; i++) {
                this.msg += Integer.toString(i + 1) + ". " + this.tasks[i] + "\n";
            }
            this.msg = this.add_lines(this.msg);
        } else {
            tasks[num_tasks] = inp;
            num_tasks = num_tasks + 1;
        }
    }

    String add_lines(String msg) {
        return this.lines + msg + this.lines;
    }
}
