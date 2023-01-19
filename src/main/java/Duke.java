import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        // List<String> arr = new ArrayList<String>();
        List<Task> arr = new ArrayList<>();

        while (sc.hasNextLine()) {
            String ip = sc.nextLine();
            if (ip.equalsIgnoreCase("bye")) {
                break;
            } else if (ip.equalsIgnoreCase("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.printf("%d.%s\n", i + 1, arr.get(i).toString());
                }
                System.out.println();
            }
            // level 1
            // System.out.println(ip + "\n");

            else if (ip.startsWith("mark") || ip.startsWith("unmark") ) {
                String[] parts = ip.split(" ");
                if (parts.length != 2) {
                    System.out.println("invalid\n");
                    continue;
                }
                int index = Integer.parseInt(parts[1]) - 1;
                if (parts[0].equalsIgnoreCase("mark")) {
                    System.out.println("Nice! I've marked this task as done:");
                    arr.get(index).setStatus(parts[0]);
                } else if (parts[0].equalsIgnoreCase("unmark")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    arr.get(index).setStatus(parts[0]);
                }
                System.out.printf(arr.get(index).toString() + "\n");
            } else if ((ip.startsWith("delete"))) {
                String[] parts = ip.split(" ");
                if (parts.length != 2) {
                    System.out.println("invalid\n");
                    continue;
                }
                int index = Integer.parseInt(parts[1]) - 1;
                if (index < arr.size()) {
                    System.out.println("Noted. I've removed this task:");
                    System.out.printf(arr.get(index).toString() + "\n");
                    arr.remove(index);
                    String len = (arr.size() == 1 ? arr.size() + " task" : arr.size() + " tasks");
                    System.out.println("Now you have " + len + " in the list.");
                }
            } else {
                String[] parts = ip.split(" ");
                Task t = new Task(ip);

                try {
                    if (parts[0].equalsIgnoreCase("todo")) {
                        if (ip.length() == 5) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        t = new Todo(ip.substring(5));
                    } else if (parts[0].equalsIgnoreCase("deadline")) {
                        int index = ip.indexOf("/by");
                        if (index != -1) {
                            t = new Deadline(ip.substring(9, index), ip.substring(index + 4));
                        } else {
                            throw new DukeException("OOPS!!! Can't find a /by time for a deadline.");
                        }
                    } else if ((parts[0].equalsIgnoreCase("event"))) {
                        int indexFrom = ip.indexOf("/from");
                        int indexTo = ip.indexOf("/to");
                        if (indexFrom != -1 && indexTo != -1) {
                            t = new Event(ip.substring(6, indexFrom), ip.substring(indexFrom + 6, indexTo - 1), ip.substring(indexTo + 4));
                        } else {
                            throw new DukeException("OOPS!!! Can't find a /from or /to time for an event.");
                        }
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    arr.add(t);
                    System.out.println("added: " + t + "\n");
                    String len = (arr.size() == 1 ? arr.size() + " task" : arr.size() + " tasks");
                    System.out.println("Now you have " + len + " in the list.");
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
