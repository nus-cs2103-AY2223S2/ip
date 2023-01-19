import java.util.Scanner;

public class JeoBot {
    public static void greeting() {
        String name = "JeoBot";
        String divider = "-----------------------";
        System.out.println(divider + "\n" + "Greetings from " + name + "!\n" + "How may I help you?\n" + divider);
    }

    public static void echo() {
        String divider = "________________________________________________________________";
        boolean hasInput = true;
        Storage st = new Storage();
        while (hasInput) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            s = s.trim();
            String command = s.replaceAll("\\s", "").toLowerCase();
            // Check if first word of command is "mark" or "unmark", and if an integer follows after
            if (command.startsWith("unmark") && command.substring(6).matches("-?\\d+")) {
                command = "unmark";
            } else if (command.startsWith("mark") && command.substring(4).matches("-?\\d+")) {
                command = "mark";
            } else if (command.startsWith("todo") && !command.substring(4).equals("")) {
                command = "todo";
            } else if (command.startsWith("deadline") && command.contains("/by")) {
                command = "deadline";
            } else if (command.startsWith("event") && command.contains("/from") && command.contains("/to")) {
                command = "event";
            }
            System.out.println(divider);
            switch (command) {
                case "bye":
                    hasInput = false;
                    System.out.println("Thank you for using JeoBot. Hope to see you again soon!");
                    break;
                case "list":
                    st.showTasks();
                    break;
                case "mark":
                    s = s.replaceAll("\\s", "");
                    // Check if input command given is just "mark" without an integer (no task number)
                    if (s.substring(4).equals("")) {
                        System.out.println("Please indicate task number!");
                        break;
                    }
                    int index = Integer.parseInt(s.substring(4)) - 1;
                    // Check if integer falls within the list of task numbers
                    if (index >= 0 && index < st.getNumberOfTasks()) {
                        st.markTask(index);
                    } else {
                        System.out.println("Invalid task number given!");
                    }
                    break;
                case "unmark":
                    s = s.replaceAll("\\s", "");
                    // Check if input command given is just "unmark" without an integer (no task number)
                    if (s.substring(6).equals("")) {
                        System.out.println("Please indicate task number!");
                        break;
                    }
                    index = Integer.parseInt(s.substring(6)) - 1;
                    // Check if integer falls within the list of task numbers
                    if (index >= 0 && index < st.getNumberOfTasks()) {
                        st.unmarkTask(index);
                    } else {
                        System.out.println("Invalid task number given!");
                    }
                    break;
                case "todo":
                    if (s.replaceAll("\\s", "").toLowerCase().equals("todo")) {
                        System.out.println("Invalid command given!");
                        break;
                    }
                    Task task = new ToDo(s.substring(4).trim());
                    st.addTask(task);
                    break;
                case "deadline":
                    if (s.replaceAll("\\s", "").toLowerCase().equals("deadline")) {
                        System.out.println("Invalid command given!");
                        break;
                    }
                    s = s.substring(8).trim();
                    System.out.println(s);
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while (i < s.length()) {
                        if (s.charAt(i) != '/') {
                            sb.append(s.charAt(i));
                        } else {
                            if (s.substring(i, i+3).equals("/by")) {
                                s = s.substring(i+3).trim();
                                break;
                            }
                        }
                        i++;
                    }
                    String desc = sb.toString().trim();
                    String by = s;
                    task = new Deadline(desc, by);
                    st.addTask(task);
                    break;
                case "event":
                    if (s.replaceAll("\\s", "").toLowerCase().equals("event")) {
                        System.out.println("Invalid command given!");
                        break;
                    }
                    s = s.substring(5).trim();
                    sb = new StringBuilder();
                    i = 0;
                    while (i < s.length()) {
                        if (s.charAt(i) != '/') {
                            sb.append(s.charAt(i));
                        } else {
                            if (s.substring(i, i+5).equals("/from")) {
                                s = s.substring(i+5).trim();
                                break;
                            }
                        }
                        i++;
                    }
                    desc = sb.toString().trim();
                    sb = new StringBuilder();
                    i = 0;
                    while (i < s.length()) {
                        if (s.charAt(i) != '/') {
                            sb.append(s.charAt(i));
                        } else {
                            if (s.substring(i, i+3).equals("/to")) {
                                s = s.substring(i+3).trim();
                                break;
                            }
                        }
                        i++;
                    }
                    String from = sb.toString().trim();
                    String to = s;
                    task = new Event(desc, from, to);
                    st.addTask(task);
                    break;
                default:
                    System.out.println("Invalid command given!");
            }
            System.out.println(divider);
        }
    }

    public static void main(String[] args) {
        greeting();
        echo();
    }
}
