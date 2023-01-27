package jeo.parser;

import java.util.HashMap;
import jeo.exception.JeoException;

public class Parser {

    /**
     * Accepts a string that represents the trimmed raw string input to be parsed as command and action
     * @param s Trimmed raw string input of current line
     * @return a hashmap linking each essential aspect of the input to its corresponding substring
     */
    public static HashMap<String, String> parseString(String s) throws JeoException {
        HashMap<String, String> hm = new HashMap<>();
        String str = s.trim();
        if (str.contains("\\")) {
            throw new JeoException("Backslash character \"\\\" not allowed");
        }
        if (str.equalsIgnoreCase("bye")) {
            hm.put("command", "bye");
        } else if (str.equalsIgnoreCase("list")) {
            hm.put("command", "list");
        } else if (str.toLowerCase().startsWith("todo")) {
            hm.put("command", "todo");
            str = str.substring(4).trim();
            hm.put("description", str);
            if (str.isEmpty()) {
                throw new JeoException("Please enter a task description.");
            }
        } else if (str.toLowerCase().startsWith("deadline")) {
            hm.put("command", "deadline");
            str = str.substring(8).trim();
            if (!str.toLowerCase().contains("/by")) {
                throw new JeoException(str.isEmpty()
                        ? "Please enter a task description."
                        : "Please follow the format: deadline <description> /by <yyyy-MM-dd HH:mm>");
            }
            String[] arr = parseSubstringTasks(str, "deadline");
            String desc = arr[0];
            String by = arr[1];
            if (desc.isEmpty()) {
                throw new JeoException("Please enter a task description.");
            }
            if (by.isEmpty()) {
                throw new JeoException("Please enter a date-time in the format " +
                        "\"yyyy-MM-dd HH:mm\" after \"/by\".");
            }
            hm.put("description", desc);
            hm.put("by", by);
        } else if (str.toLowerCase().startsWith("event")) {
            hm.put("command", "event");
            str = str.substring(5).trim();
            if (!str.toLowerCase().contains("/from") || !str.toLowerCase().contains("/to")) {
                throw new JeoException(str.isEmpty()
                        ? "Please enter a task description."
                        : "Please follow the format: event <description> " +
                        "/from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>");
            }
            String[] arr = parseSubstringTasks(str, "event1");
            String desc = arr[0];
            str = arr[1];
            arr = parseSubstringTasks(str, "event2");
            String from = arr[0];
            String to = arr[1];
            if (desc.isEmpty()) {
                throw new JeoException("Please enter a task description.");
            }
            if (from.isEmpty() && to.isEmpty()) {
                throw new JeoException("Please enter a date-time in the format " +
                        "\"yyyy-MM-dd HH:mm\" after \"/from\" and \"/to\".");
            }
            if (from.isEmpty()) {
                throw new JeoException("Please enter a date-time in the format " +
                        "\"yyyy-MM-dd HH:mm\" after \"/from\".");
            }
            if (to.isEmpty()) {
                throw new JeoException("Please enter a date-time in the format " +
                        "\"yyyy-MM-dd HH:mm\" after \"/to\".");
            }
            hm.put("description", desc);
            hm.put("from", from);
            hm.put("to", to);
        } else if (str.toLowerCase().startsWith("mark")) {
            hm.put("command", "mark");
            int i = parseSubStringActions(str, 4);
            hm.put("index", Integer.toString(i-1));
        } else if (str.toLowerCase().startsWith("unmark")) {
            hm.put("command", "unmark");
            int i = parseSubStringActions(str, 6);
            hm.put("index", Integer.toString(i-1));
        } else if (str.toLowerCase().startsWith("delete")) {
            hm.put("command", "delete");
            int i = parseSubStringActions(str, 6);
            hm.put("index", Integer.toString(i-1));
        } else if (str.toLowerCase().startsWith("due")) {
            hm.put("command", "due");
            String by = str.substring(3).trim();
            if (by.isEmpty()) {
                throw new JeoException("Please enter a date in the format: \"yyyy-MM-dd\".");
            }
            hm.put("by", by);
        } else if (str.toLowerCase().startsWith("find")) {
            hm.put("command", "find");
            String key = str.substring(4).trim();
            if (key.isEmpty()) {
                throw new JeoException("Please enter a keyword.");
            }
            hm.put("key", key);
        } else {
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
}
