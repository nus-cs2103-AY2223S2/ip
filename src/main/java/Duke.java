import java.util.*;
import java.util.regex.*;

public class Duke {
    public static int MAXCHAR = 60;
    public static LinkedList<Task> tasklist = new LinkedList<Task>();
    public static boolean loop = true;

    public static void chatboxFrame() {
        System.out.println("\t____________________________________________________________");
    }

    public static void chatbox(String text) {
        chatbox(text, true);
    }

    public static void chatbox(String text, boolean frame) {
        // Frame determines if chatboxFrame is run
        if (frame) {
            chatboxFrame();
        }

        for (String substr : text.split("\n", -1)) {
            while (substr.length() > MAXCHAR) {
                System.out.println("\t" + substr.substring(0, MAXCHAR));
                substr = substr.substring(MAXCHAR, substr.length());
            }
            System.out.println("\t" + substr);
        }

        if (frame) {
            chatboxFrame();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Matcher m;
        chatbox("I am Duke.\nHow may I be of service?");

        while (loop) {
            try {
                input = sc.nextLine();

                m = Pattern.compile("^bye$").matcher(input);
                if (m.find()) {
                    chatbox("Goodbye. Shutting down.");
                    break;
                }

                m = Pattern.compile("^list$").matcher(input);
                if (m.find()) {
                    chatboxFrame();
                    chatbox("Here are the tasks in your list:", false);
                    int n = 1 + (int) Math.floor(Math.log10(tasklist.size()));
                    int i = 0;
                    for (Task item : tasklist) {
                        i++;
                        chatbox(
                            String.format("%" + n + "d", i).replace(' ', '0')
                            + ". "
                            + item.toString(),
                            false);
                    }
                    chatboxFrame();
                    continue;
                }

                m = Pattern.compile("^mark (.+)").matcher(input);
                if (m.find()) {
                    Task curr = tasklist.get(Integer.parseInt(m.group(1)) - 1);
                    curr.setDone(true);

                    chatbox("Nice, I've marked this task as done:\n"
                        + curr.toString());
                    continue;
                }

                m = Pattern.compile("^unmark (.+)").matcher(input);
                if (m.find()) {
                    Task curr = tasklist.get(Integer.parseInt(m.group(1)) - 1);
                    curr.setDone(false);

                    chatbox("OK, I've marked this task as not done yet:\n"
                        + curr.toString());
                    continue;
                }

                m = Pattern.compile("^delete (.+)").matcher(input);
                if (m.find()) {
                    Task curr = tasklist.remove(Integer.parseInt(m.group(1)) - 1);

                    chatbox("Noted. I've removed this task:\n"
                        + curr.toString() +
                        String.format("\nNow you have %d tasks in the list.",
                            tasklist.size())
                        );
                    continue;
                }


                if (Pattern.compile("^todo").matcher(input).find()) {
                    m = Pattern.compile("^todo (.+)").matcher(input);
                    if (!m.find()) {
                        throw new InvalidFormatException();
                    }
                    tasklist.add(new Todo(m.group(1)));
                    chatbox("Got it. I've added this task:\n" +
                        tasklist.get(tasklist.size() - 1).toString() +
                        String.format("\nNow you have %d tasks in the list.",
                            tasklist.size())
                        );
                    continue;
                }

                if (Pattern.compile("^deadline ").matcher(input).find()) {
                    m = Pattern.compile("^deadline (.+) /by (.+)").matcher(input);
                    if (!m.find()) {
                        throw new InvalidFormatException();
                    }
                    tasklist.add(new Deadline(m.group(1), m.group(2)));
                    chatbox("Got it. I've added this task:\n" +
                        tasklist.get(tasklist.size() - 1).toString() +
                        String.format("\nNow you have %d tasks in the list.",
                            tasklist.size())
                        );
                    continue;
                }

                if (Pattern.compile("^event ").matcher(input).find()) {
                    m = Pattern.compile("^event (.+) /from (.+) /to (.+)").matcher(input);
                    if (!m.find()) {
                        throw new InvalidFormatException();
                    }
                    tasklist.add(new Event(m.group(1), m.group(2), m.group(3)));
                    chatbox("Got it. I've added this task:\n" +
                        tasklist.get(tasklist.size() - 1).toString() +
                        String.format("\nNow you have %d tasks in the list.",
                            tasklist.size())
                        );
                    continue;
                }

                throw new UnrecognisedCommandException();

            } catch (InvalidFormatException e) {
                chatbox("I recognise that keyword, but the format is wrong.");
            } catch (UnrecognisedCommandException e) {
                chatbox("Your entire input was not understood. Please try again.");
            }
        }

     }
}
