import java.util.Scanner;
public class Duke {
    String lines = "____________________________________________________________\n";
    boolean exit = false;
    String msg;
    Task[] tasks = new Task[100];
    int num_tasks = 0;
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.welcome_msg());
        Scanner scanner = new Scanner(System.in);
        while(!duke.exit) {
            String inp = scanner.nextLine();
            int idx = -1;
            if ((inp.length() >= 4 && inp.substring(0, 4).equals("mark")) || (inp.length() >= 6 && inp.substring(0, 6).equals("unmark"))) {
                idx = Integer.parseInt(String.valueOf(inp.charAt(inp.length() - 1)));
                System.out.println(idx);
                inp = inp.substring(0, 4).equals("mark") ? inp.substring(0, 4) : inp.substring(0, 6);
            }
            duke.check_msg(inp, idx);
            System.out.println(duke.msg);
        }
    }
    String welcome_msg() {
        return this.add_lines("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    void check_msg(String inp, int idx) {
        if (inp.equals("bye")) {
            this.exit = true;
            this.msg = this.add_lines("Bye. Hope to see you again soon!\n");
        } else if (inp.equals("list")) {
            this.msg = "Here are the tasks in your list:\n";
            for(int i = 0; i < num_tasks; i++) {
                Task cur = this.tasks[i];
                this.msg += Integer.toString(i + 1) + ". " + cur + "\n";
            }
            this.msg = this.add_lines(this.msg);
        } else if (inp.equals("mark") || inp.equals("unmark")){
            Task cur = this.tasks[idx - 1];
            if (inp.equals("mark")) {
                this.msg = "Nice! I've marked this task as done:\n";
                cur.mark();
            } else {
                this.msg = "OK, I've marked this task as not done yet:\n";
                cur.unmark();
            }
            this.msg += cur + "\n";
            this.msg = this.add_lines(this.msg);
        } else {
            Task cur = new Task(inp);
            tasks[num_tasks] = cur;
            num_tasks = num_tasks + 1;
            this.msg = this.add_lines("added: " + inp + "\n");
        }
    }

    String add_lines(String msg) {
        return this.lines + msg + this.lines;
    }
}
