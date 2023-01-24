import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.*;
import java.io.File;

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
    public static File fileInit(String parent, String child) {
        File taskFile = new File("./data/duke.txt");
        if (!taskFile.exists()) {
            try {
                File parentFolder = new File(parent);
                parentFolder.mkdirs();
                taskFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        Scanner sc;

        try {
            sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String row = sc.nextLine();

                Task curr;
                Matcher m;
                switch (row.charAt(1)) {
                case 'T':
                    m = Pattern.compile("\\[[TDE]\\]\\[[X ]\\] (.+)").matcher(row);
                    m.find();
                    curr = new Todo(m.group(1));
                    break;
                case 'D':
                    m = Pattern.compile("\\[[TDE]\\]\\[[X ]\\] (.*) \\(by: (.+)\\)").matcher(row);
                    m.find();
                    curr = new Deadline(m.group(1), m.group(2));
                    break;
                case 'E':
                    m = Pattern.compile("\\[[TDE]\\]\\[[X ]\\] (.*) \\(from: (.*) to: (.*)\\)").matcher(row);
                    m.find();
                    curr = new Event(m.group(1), m.group(2), m.group(3));
                    break;
                default:
                    return taskFile;
                }

                if (row.charAt(4) == 'X') {
                    curr.setDone(true);
                }

                tasklist.add(curr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return taskFile;
    }

    public static void fileUpdate(File taskFile) {
        try {
            FileWriter fw = new FileWriter(taskFile);
            for (Task item : tasklist) {
                fw.write(item.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File taskFile = fileInit("./data", "duke.txt");
        Scanner sc = new Scanner(System.in);
        String input;
        Matcher m;
        chatbox("I am Duke.\nHow may I be of service?");

        while (loop) {
            try {
                fileUpdate(taskFile);

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
