import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("ฅʕ•ᴥ•ʔฅ :: Hiii! I'm duke, what can I help you with?");
        //echo();
        add();
    }
    public static void echo() {
        Scanner input = new Scanner(System.in);
        String cmd;
        while (true) {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("ʕっ￫ᴥ￩ʔっ :: Buhbyeee, hope to see you again soon!");
                return;
            } else {
                System.out.println(cmd);
            }
        }
    }

    public static void add() throws DukeException {
        Scanner input = new Scanner(System.in);
        String cmd;
        ArrayList<Task> list = new ArrayList<Task>(100);
        Integer count = 0;
        Integer num;
        while (true) {
            System.out.println("•──────────────────♛─────────────────•");
            try {
                cmd = input.nextLine();
                System.out.println("•──────────────────♛─────────────────•");
                if (cmd.equals("bye")) {
                    System.out.println("ʕっ￫ᴥ￩ʔっ :: Buhbyeee, hope to see you again soon!");
                    return;
                } else if (cmd.equals("list")) {
                    System.out.println("ʕ•ᴥ•ʔっ :: Here are the task(s) in your list:");
                    for (int i = 1; i <= count; i++) {
                        System.out.println(i + "." + list.get(i-1).toString());
                    }
                } else if (cmd.startsWith("mark")) {
                    num = Integer.valueOf(cmd.substring(5));
                    if (count < num) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
                    }
                    list.get(num - 1).setDone();
                    System.out.println("ʕ•̀ω•́ʔ✧ :: Nice! I've marked this task as done:\n  " + list.get(num - 1).toString());
                } else if (cmd.startsWith("unmark")) {
                    num = Integer.valueOf(cmd.substring(7));
                    if (count < num) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
                    }
                    list.get(num - 1).setUndone();
                    System.out.println("ʕ•̀ω•́ʔ✧ :: OK, I've marked this task as not done yet:\n  " + list.get(num - 1).toString());
                } else if (cmd.startsWith("todo") || cmd.startsWith("deadline") || cmd.startsWith("event")) {
                    boolean valid = true;
                    if (cmd.startsWith("todo")) {
                        if (cmd.substring(4).equals("")) {
                            valid = false;
                            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The description of a todo cannot be empty!");
                        } else {
                            list.add(new ToDo(cmd.substring(5)));
                        }
                    } else if (cmd.startsWith("deadline")) {
                        int ind = cmd.indexOf("/by");
                        if (ind == -1) {
                            valid = false;
                            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The deadline is missing!");
                        }
                        if (ind == 9) {
                            valid = false;
                            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The description of a deadline cannot be empty!");
                        }
                        list.add(new Deadline(cmd.substring(9, ind - 1), cmd.substring(ind + 4)));
                    } else {
                        int start = cmd.indexOf("/from");
                        int end = cmd.indexOf("/to");
                        if (start == -1 || end == -1) {
                            valid = false;
                            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The event duration is missing!");
                        }
                        if (start == 6) {
                            valid = false;
                            throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The description of an event cannot be empty!");
                        }
                        list.add(new Event(cmd.substring(6, start - 1), cmd.substring(start + 6, end - 1), cmd.substring(end + 4)));
                    }
                    if (valid) {
                        System.out.println("ฅʕ'ᴥ'ʔฅ :: Got it. I've added this task:");
                        System.out.println("  " + list.get(count).toString());
                        count++;
                        System.out.println("ʕ•ᴥ•ʔっ :: Now you have " + count + " task(s) in the list.");
                    }
                } else if (cmd.startsWith("delete")) {
                    if (count == 0) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The list is empty!");
                    }
                    num = Integer.valueOf(cmd.substring(7));
                    if (count < num) {
                        throw new DukeException("ʕ ﾟ ● ﾟʔ :: ☹ OOPS!!! The task does not exist!");
                    }
                    System.out.println("ฅʕ'ᴥ'ʔฅ :: Noted. I've removed this task :");
                    System.out.println("  " + list.remove(num - 1).toString());
                    count --;
                    System.out.println("ʕ•ᴥ•ʔっ :: Now you have " + count + " task(s) in the list.");
                } else {
                    throw new DukeException("╮ʕ˚ᴥ˚ʔ╭ :: ☹ OOPS!!! I'm sorry, but I don't know what that means!");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
