import java.util.HashMap;
import java.util.Scanner;

public class JeoBot {
    public static void greeting() {
        String name = "JeoBot";
        String divider = "-----------------------";
        System.out.println(divider + "\n" + "Greetings from " + name + "!\n" + "How may I help you?\n" + divider);
    }

    public static void echo() {
        String divider = "________________________________________________________________________________";
        boolean hasInput = true;
        Scanner sc = new Scanner(System.in);
        Storage st = new Storage();
        while (hasInput) {
            String s = sc.nextLine();
            s = s.trim();
            // Parse
            try {
                System.out.println(divider);
                HashMap<String, String> hm = parseString(s);
                String command = hm.get("command");
                switch (command) {
                    case "bye":
                        hasInput = false;
                        System.out.println("Thank you for using JeoBot. Hope to see you again soon!");
                        break;
                    case "list":
                        st.showTasks();
                        break;
                    case "mark":
                        if (hm.get("index").isEmpty()) {
                            throw new JeoException("Please enter a task number to be marked.");
                        }
                        int index = Integer.parseInt(hm.get("index")) - 1;
                        if (index < 0 || index >= st.getNumberOfTasks()) {
                            throw new JeoException("Task number cannot be negative, zero, or exceed the total number of tasks.");
                        }
                        st.markTask(index);
                        break;
                    case "unmark":
                        if (hm.get("index").isEmpty()) {
                            throw new JeoException("Please enter a task number to be unmarked.");
                        }
                        index = Integer.parseInt(hm.get("index")) - 1;
                        if (index < 0 || index >= st.getNumberOfTasks()) {
                            throw new JeoException("Task number cannot be negative, zero, or exceed the total number of tasks.");
                        }
                        st.unmarkTask(index);
                        break;
                    case "todo":
                        String desc = hm.get("description");
                        if (desc.isEmpty()) {
                            throw new JeoException("Please enter a task description.");
                        }
                        Task task = new ToDo(desc);
                        st.addTask(task);
                        break;
                    case "deadline":
                        desc = hm.get("description");
                        String by = hm.get("by");
                        if (desc.isEmpty()) {
                            throw new JeoException("Please enter a task description.");
                        }
                        if (by.isEmpty()) {
                            throw new JeoException("Please enter a date/time after \"/by\".");
                        }
                        task = new Deadline(desc, by);
                        st.addTask(task);
                        break;
                    case "event":
                        desc = hm.get("description");
                        String from = hm.get("from");
                        String to = hm.get("to");
                        if (desc.isEmpty()) {
                            throw new JeoException("Please enter a task description.");
                        }
                        if (from.isEmpty() && to.isEmpty()) {
                            throw new JeoException("Please enter a date/time after \"/from\" and \"/to\".");
                        }
                        if (from.isEmpty()) {
                            throw new JeoException("Please enter a date/time after \"/from\".");
                        }
                        if (to.isEmpty()) {
                            throw new JeoException("Please enter a date/time after \"/to\".");
                        }
                        task = new Event(desc, from, to);
                        st.addTask(task);
                        break;
                    default:
                        System.out.println("Sorry, I don't understand what you're saying :(");
                }
            } catch (JeoException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(divider);
        }
        sc.close();
    }

    // Throw empty description, by, from, to, index in echo()
    // Also for index, handle if it's within valid number of tasks
    public static HashMap<String, String> parseString(String s) throws JeoException {
        HashMap<String, String> hm = new HashMap<>();
        if (s.equalsIgnoreCase("bye")) {
            hm.put("command", "bye");
        } else if (s.equalsIgnoreCase("list")) {
            hm.put("command", "list");
        } else if (s.toLowerCase().startsWith("todo")) {
            hm.put("command", "todo");
            s = s.substring(4).trim();
            hm.put("description", s);
        } else if (s.toLowerCase().startsWith("deadline")) {
            hm.put("command", "deadline");
            s = s.substring(8).trim();
            if (!s.toLowerCase().contains("/by")) {
                throw new JeoException(s.isEmpty()
                        ? "Please enter a task description."
                        : "Please follow the format: deadline <description> /by <date/time>");
            }
            String[] arr = parseSubstring(s, "deadline");
            String desc = arr[0];
            String by = arr[1];
            hm.put("description", desc);
            hm.put("by", by);
        } else if (s.toLowerCase().startsWith("event")) {
            hm.put("command", "event");
            s = s.substring(5).trim();
            if (!s.toLowerCase().contains("/from") || !s.toLowerCase().contains("/to")) {
                throw new JeoException(s.isEmpty()
                        ? "Please enter a task description."
                        : "Please follow the format: event <description> /from <date/time> /to <date/time>");
            }
            String[] arr = parseSubstring(s, "event1");
            String desc = arr[0];
            s = arr[1];
            arr = parseSubstring(s, "event2");
            String from = arr[0];
            String to = arr[1];
            hm.put("description", desc);
            hm.put("from", from);
            hm.put("to", to);
        } else if (s.toLowerCase().startsWith("mark")) {
            hm.put("command", "mark");
            s = s.substring(4).trim();
            int i;
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new JeoException("Task number needs to be an integer value to be marked.");
            }
            hm.put("index", Integer.toString(i));
        } else if (s.toLowerCase().startsWith("unmark")) {
            hm.put("command", "unmark");
            s = s.substring(6).trim();
            int i;
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new JeoException("Task number needs to be an integer value to be unmarked.");
            }
            hm.put("index", Integer.toString(i));
        } else {
            hm.put("command", "");
        }
        return hm;
    }

    public static String[] parseSubstring(String s, String c) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String first = "";
        String second  = "";
        while (i < s.length()) {
            if (s.charAt(i) != '/') {
                sb.append(s.charAt(i));
            } else {
                String temp1 = s.substring(i + 3).trim();
                if (c.equals("deadline") && s.startsWith("/by", i)) {
                    first = sb.toString().trim();
                    second = temp1;
                } else if (c.equals("event1") && s.startsWith("/from", i)) {
                    first = sb.toString().trim();
                    second = s.substring(i + 5).trim();
                } else if ((c.equals("event1") || c.equals("event2")) && s.startsWith("/to", i)) {
                    first = sb.toString().trim();
                    second = temp1;
                } else if (c.equals("event2") && s.startsWith("/from", i)) {
                    first = s.substring(i + 5).trim();
                    second = sb.toString().trim();
                }
                break;
            }
            i++;
        }
        return new String[]{first, second};
    }

    public static void main(String[] args) {
        greeting();
        echo();
    }
}
