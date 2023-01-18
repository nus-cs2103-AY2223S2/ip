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
            String inp = scanner.next();
            int idx = -1;
            String desc = "";
            if (inp.equals("mark") || inp.equals("unmark")) idx = scanner.nextInt();
            else desc = scanner.nextLine();
            String by = "";
            if (inp.equals("deadline")) {
                String[] temp = desc.split(" /by ");
                desc = temp[0];
                by = temp[1];
            }
            duke.check_msg(inp, idx, desc, by);
            System.out.println(duke.msg);
        }
    }
    String welcome_msg() {
        return this.add_lines("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    void check_msg(String inp, int idx, String desc, String by) {
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
        } else if (inp.equals("todo")){
            Todo cur = new Todo(desc);
            tasks[num_tasks] = cur;
            num_tasks = num_tasks + 1;
            this.msg = "Got it. I've added this task:\n" + cur + "\n";
            this.msg += "Now you have " + this.num_tasks + " tasks in the list.\n";
            this.msg = this.add_lines(this.msg);
        } else if (inp.equals("deadline")) {
            Deadline cur = new Deadline(desc, by);
            tasks[num_tasks] = cur;
            num_tasks = num_tasks + 1;
            this.msg = "Got it. I've added this task:\n" + cur + "\n";
            this.msg += "Now you have " + this.num_tasks + " tasks in the list.\n";
            this.msg = this.add_lines(this.msg);
        } else {
            this.msg = "Invalid input.\n";
            this.msg = this.add_lines(this.msg);
        }
    }

    String add_lines(String msg) {
        return this.lines + msg + this.lines;
    }
}
