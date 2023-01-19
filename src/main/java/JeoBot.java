import java.util.HashMap;
import java.util.Scanner;

/**
 * Main Bot class which the user may run the program from
 */
public class JeoBot {
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    /**
     * Greets the user when the bot is first run
     */
    public static void greeting() {
        String name = "JeoBot";
        String divider = "-----------------------";
        System.out.println(divider + "\n" + "Greetings from " + name + "!\n" + "How may I help you?\n" + divider);
    }

    /**
     * Executes commands which the user inputs accordingly
     */
    public static void echo() {
        String divider = "________________________________________________________________________________________";
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
                Command command = Command.valueOf(hm.get("command").toUpperCase());
                switch (command) {
                    case BYE:
                        hasInput = false;
                        System.out.println("Thank you for using JeoBot. Hope to see you again soon!");
                        break;
                    case LIST:
                        st.showTasks();
                        break;
                    case MARK:
                        int index = Integer.parseInt(hm.get("index"));
                        st.markTask(index);
                        break;
                    case UNMARK:
                        index = Integer.parseInt(hm.get("index"));
                        st.unmarkTask(index);
                        break;
                    case DELETE:
                        index = Integer.parseInt(hm.get("index"));
                        st.deleteTask(index);
                        break;
                    case TODO:
                        String desc = hm.get("description");
                        if (desc.isEmpty()) {
                            throw new JeoException("Please enter a task description.");
                        }
                        Task task = new ToDo(desc);
                        st.addTask(task);
                        break;
                    case DEADLINE:
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
                    case EVENT:
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
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Error] Sorry, I don't understand what you're saying :(");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[Error] Task number cannot be negative, zero, or exceed the total number of tasks.");
            } catch (JeoException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(divider);
        }
        sc.close();
    }

    /**
     * Accepts a string that represents the trimmed raw string input to be parsed as command and action
     * @param s Trimmed raw string input of current line
     * @return a hashmap linking each essential aspect of the input to its corresponding substring
     */
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
            String[] arr = parseSubstringTasks(s, "deadline");
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
            String[] arr = parseSubstringTasks(s, "event1");
            String desc = arr[0];
            s = arr[1];
            arr = parseSubstringTasks(s, "event2");
            String from = arr[0];
            String to = arr[1];
            hm.put("description", desc);
            hm.put("from", from);
            hm.put("to", to);
        } else if (s.toLowerCase().startsWith("mark")) {
            hm.put("command", "mark");
            int i = parseSubStringActions(s, 4);
            hm.put("index", Integer.toString(i-1));
        } else if (s.toLowerCase().startsWith("unmark")) {
            hm.put("command", "unmark");
            int i = parseSubStringActions(s, 6);
            hm.put("index", Integer.toString(i-1));
        } else if (s.toLowerCase().startsWith("delete")) {
            hm.put("command", "delete");
            int i = parseSubStringActions(s, 6);
            hm.put("index", Integer.toString(i-1));
        }
        else {
            hm.put("command", "");
        }
        return hm;
    }

    /**
     * Accepts a string that represents the trimmed raw string input and an integer that represents
     * the first index after the command, returning the task number that follows (for actions)
     * @param s Trimmed raw string input
     * @param j First index after the command substring
     * @return an integer representing the task number
     */
    public static int parseSubStringActions(String s, int j) throws JeoException {
        s = s.substring(j).trim();
        if (s.isEmpty()) {
            throw new JeoException("Please enter a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new JeoException("Task number needs to be an integer value.");
        }
        return i;
    }

    /**
     * Accepts 2 strings, one representing a substring after the command and the other representing
     * one of 3 cases to be parsed, for deadline and event, returning a list of 2 parsed substring values
     * @param s Substring after the command
     * @param c Takes in "deadline", "event1" or "event2" which parses different segments of deadline and event
     * @return a list of 2 strings which have been parsed
     */
    public static String[] parseSubstringTasks(String s, String c) {
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
