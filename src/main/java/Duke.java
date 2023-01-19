import java.text.NumberFormat;
import java.util.Scanner;
public class Duke {
    String lines = "____________________________________________________________\n";
    boolean exit = false;
    String msg;
    Task[] tasks = new Task[100];
    int num_tasks = 0;
    public static void main(String[] args) throws DukeException, NumberFormatException {
        Duke duke = new Duke();
        System.out.println(duke.welcome_msg());
        Scanner scanner = new Scanner(System.in);
        while(!duke.exit) {
            String inp = scanner.next();
            int idx = -1;
            String desc = "";
            if (inp.equals("mark") || inp.equals("unmark")) {
                String tmp;
                tmp = scanner.nextLine();
                if (!tmp.equals("")) idx = Integer.parseInt(tmp.trim());
                if (tmp.equals("")) {
                    idx = -1;
                }
            }
            else {
                desc = scanner.nextLine();
            }
            String by = "";
            String from = "";
            if (inp.equals("deadline")) {
                String[] temp = desc.split(" /by ");
                desc = temp[0];
                by = temp[1];
            }
            if (inp.equals("event")) {
                String[] temp = desc.split(" /from |\\ /to ");
                desc = temp[0];
                from = temp[1];
                by = temp[2];
//                System.out.println(temp[0]);
//                System.out.println(temp[1]);
            }
            try {
                duke.check_msg(inp, idx, desc, by, from);
                System.out.println(duke.msg);
            } catch (DukeException e) {
                System.out.println(duke.msg);
            }
        }
    }
    String welcome_msg() {
        return this.add_lines("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    void check_msg(String inp, int idx, String desc, String by, String from) throws DukeException {
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
        } else if ((inp.equals("mark") || inp.equals("unmark"))){
            if (idx == -1) {
                this.msg = this.add_lines(" ☹ OOPS!!! The description of a mark / unmark cannot be empty.\n");
                throw new DukeException("No Description");
            }
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
            if (desc.equals("")) {
                this.msg = this.add_lines(" ☹ OOPS!!! The description of a todo cannot be empty.\n");
                throw new DukeException("No Description");
            }
            Todo cur = new Todo(desc);
            tasks[num_tasks] = cur;
            num_tasks = num_tasks + 1;
            this.msg = "Got it. I've added this task:\n" + cur + "\n";
            this.msg += "Now you have " + this.num_tasks + " tasks in the list.\n";
            this.msg = this.add_lines(this.msg);
        } else if (inp.equals("deadline")) {
            if(desc.equals("") || by.equals("")) {
                this.msg = this.add_lines(" ☹ OOPS!!! The description / deadline of a deadline cannot be empty.\n");
                throw new DukeException("No Description");
                // haven't handle the string split exception
            }
            Deadline cur = new Deadline(desc, by);
            tasks[num_tasks] = cur;
            num_tasks = num_tasks + 1;
            this.msg = "Got it. I've added this task:\n" + cur + "\n";
            this.msg += "Now you have " + this.num_tasks + " tasks in the list.\n";
            this.msg = this.add_lines(this.msg);
        } else if (inp.equals("event")){
            if(desc.equals("") || by.equals("") || from.equals("")) {
                this.msg = this.add_lines(" ☹ OOPS!!! The description / start / end of an event cannot be empty.\n");
                throw new DukeException("No Description");
                // haven't handle the split string exception
            }
            Event cur = new Event(desc, by, from);
            tasks[num_tasks] = cur;
            num_tasks = num_tasks + 1;
            this.msg = "Got it. I've added this task:\n" + cur + "\n";
            this.msg += "Now you have " + this.num_tasks + " tasks in the list.\n";
            this.msg = this.add_lines(this.msg);
        } else {
            this.msg = this.add_lines("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            throw new DukeException("Invalid command");
        }
    }

    String add_lines(String msg) {
        return this.lines + msg + this.lines;
    }
}
