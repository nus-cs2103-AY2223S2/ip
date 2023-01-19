import java.util.List;
import java.util.Scanner;

public class Reply {
    protected String[] command;

    public Reply(String[] command) {
        this.command = command;
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void list(List<Task> tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    public void mark(List<Task> tasks) {
        String action = command[0];
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner sc = new Scanner(command[1]);
            if (!sc.hasNextInt()) {
                sc.close();
                throw new DukeException(null, null);
            }
            int index = sc.nextInt() - 1;
            if (tasks.get(index) == null) {
                sc.close();
                throw new DukeException(null, null);
            }

            if (action.equals("mark")) {
                tasks.get(index).markAsDone(); 
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(index).unmarkTask();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.get(index).toString());
            sc.close();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! A valid number has to follow the mark or unmark command.");
        }
    }

    public void todo(List<Task> tasks, int count) {
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                sb.append(command[i]);
                if (i + 1 != command.length) {
                    sb.append(" ");
                }
            }
            tasks.add(new Todo(sb.toString()));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(count).toString());
            System.out.printf("Now you have %d tasks in the list.\n", count + 1);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description cannot be empty!");
        }
    }

    public void event(List<Task> tasks, int count) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            boolean fr = false;
            boolean t = false;
            for (int i = 1; i < command.length; i++) {
                if (fr) {
                    if (command[i].equals("/to")) {
                        t = true;
                        fr = false;
                        from.setLength(from.length()- 1);
                        continue;
                    }
                    from.append(command[i]);
                    from.append(" ");
                } else if (t) {
                    to.append(command[i]);
                    if (i + 1 != command.length) {
                        to.append(" ");
                    }
                } else {
                    if (command[i].equals("/from")) {
                        fr = true;
                        sb.setLength(sb.length() - 1);
                        continue;
                    }
                    sb.append(command[i]);
                    sb.append(" ");
                }
            }
            if (from.length() == 0 || to.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Event(sb.toString(), from.toString(), to.toString()));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(count).toString());
            System.out.printf("Now you have %d tasks in the list.\n", count + 1);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The timing was not specified!");
        }
    }

    public void deadline(List<Task> tasks, int count) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder by = new StringBuilder();
            boolean b = false;
            for (int i = 1; i < command.length; i++) {
                if (b) {
                    by.append(command[i]);
                    if (i + 1 != command.length) {
                        by.append(" ");
                    }
                } else {
                    if (command[i].equals("/by")) {
                        b = true;
                        sb.setLength(sb.length() - 1);
                        continue;
                    }
                    sb.append(command[i]);
                    sb.append(" ");
                }
            }
            if (by.length() == 0) {
                throw new DukeException(null, null);
            }
            tasks.add(new Deadline(sb.toString(), by.toString()));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(count).toString());
            System.out.printf("Now you have %d tasks in the list.\n", count + 1);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The timing was not specified!");
        }
    }

    public void delete(List<Task> tasks, int count) {
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            Scanner sc = new Scanner(command[1]);
            if (!sc.hasNextInt()) {
                sc.close();
                throw new DukeException(null, null);
            }
            int index = sc.nextInt() - 1;
            if (tasks.get(index) == null) {
                sc.close();
                throw new DukeException(null, null);
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index).toString());
            System.out.printf("Now you have %d tasks in the list.\n", count - 1);
            tasks.remove(index);
            sc.close();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! A valid number has to follow the delete command.");
        }
    }

    public void def() {
        try {
            throw new DukeException(null, null);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
