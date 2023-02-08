<<<<<<< HEAD
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
=======
import java.io.*;
>>>>>>> branch-Level-8
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    static final Pattern DEADLINE_PATTERN = Pattern.compile("(.+)/by (.+)");
    static final Pattern EVENT_PATTERN = Pattern.compile("(.+)/from (.+) /to (.+)");

    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            readFile();
            printASCII();

            String command;
            do {
                command = sc.next();
                String body = sc.nextLine();

                switch (command) {
                    case ("throwerr"):
                        throw new RuntimeException();
                    case ("bye"):
                        break;
                    case ("list"):
                        if(tasks.size() == 0) {
                            System.out.println("You dont have any tracked tasks");
                        } else {
                            System.out.println("Your current tracked tasks: ");
                            for (int i = 0; i < tasks.size(); i++) {
                                Task curr = tasks.get(i);
                                System.out.println((i + 1) + "." + curr);
                            }
                        }
                        break;
                    case ("unmark"):
                    case ("mark"):
                        body = body.substring(1);
                        int index = Integer.parseInt(body) - 1;
                        tasks.get(index).toggleDone();
                        System.out.println("Toggled state:\n [" + tasks.get(index).getDoness() + "] " + tasks.get(index).getDesc());
                        break;
                    case ("delete"):
                        try {
                            body = body.substring(1);
                            int i = Integer.parseInt(body) - 1;
                            Task temp = tasks.get(i);
                            tasks.remove(temp);
                            printDelete(temp);
                            break;
                        } catch (Exception e) {
                            System.out.println("ERROR: input a number to delete or item does not exist");
                        }
                        break;
                    case ("todo"):
                        try {
                            body = body.substring(1);
                            ToDo curr = new ToDo(body, false);
                            tasks.add(curr);
                            printNotif(curr);
                        } catch (Exception e) {
                            System.out.println("Please input something TO DO????!!");
                        }
                        break;
                    case ("deadline"):
                        body = body.substring(1);
                        Matcher dlMatcher = DEADLINE_PATTERN.matcher(body);
                        if(dlMatcher.matches()) {
                            String desc = dlMatcher.group(1);
                            String deadlineDay = dlMatcher.group(2);
                            LocalDateTime deadlineDayParsed = LocalDateTime.parse(deadlineDay, DATE_TIME_FORMATTER);

                            Deadline dl = new Deadline(desc, false, deadlineDayParsed);
                            tasks.add(dl);
                            printNotif(dl);
                        }
                        break;
                    case ("event"):
                        body = body.substring(1);
                        Matcher eMatcher = EVENT_PATTERN.matcher(body);
                        if(eMatcher.matches()) {
                            String desc = eMatcher.group(1);
                            String from = eMatcher.group(2);
                            String to = eMatcher.group(3);

                            LocalDateTime fromParsed = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
                            LocalDateTime toParsed = LocalDateTime.parse(to, DATE_TIME_FORMATTER);

                            Event dl = new Event(desc, false, fromParsed, toParsed);
                            tasks.add(dl);
                            printNotif(dl);
                        }
                        break;
                    default:
                        System.out.println("I DONT KNOW WHAT YOU WANT");
                        break;
                }
            } while (!command.equals("bye"));

        } catch (Exception e) {
            System.out.println("Encountered exception: " + e + "\nExiting program");
        } finally {
            System.out.println("Saving your task list...");
            writeFile();
            System.out.println("Goodbye!");
        }
    }

    private static void printDelete(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("SENDING TASK TO THE VOID (DELETING)");
        System.out.println("\t" + task);
        System.out.println("You currently have " + tasks.size() + " tracked tasks");
        System.out.println("____________________________________________________________");
    }

    private static void printNotif(Task curr) {
        System.out.println("____________________________________________________________");
        System.out.println("Me add your task to list: ");
        System.out.println("\t" + curr);
        System.out.println("You currently have " + tasks.size() + " tracked tasks");
        System.out.println("____________________________________________________________");
    }

    static void printASCII(){
        String line = " ---------------------------------------------------------";
        String logo = "\t\t\t\t            | |       \n" +
                "\t\t\t\t _ __  _   _| | _____ \n" +
                "\t\t\t\t| '_ \\| | | | |/ / _ \\\n" +
                "\t\t\t\t| |_) | |_| |   <  __/\n" +
                "\t\t\t\t| .__/ \\__,_|_|\\_\\___|\n" +
                "\t\t\t\t| |                   \n" +
                "\t\t\t\t|_|                  ";
        System.out.println(line + "\n" + logo + "\n" + line);
        System.out.println("Welcome to PUKE, the worst program in existence");
        System.out.println("Input a command");
    }

    static void createFile() throws IOException {
        String filePath = "data\\save.txt";
        File f = new File(filePath);
        File d = new File("data");

        if (!d.exists()) {
            System.out.println("Data directory does not exist\nCreating new data directory...");
            if(d.mkdir()) {
                System.out.println("Successfully created new data directory");
            }
        }

        if(!f.isFile()) {
            System.out.println("Save file does not exist!\nCreating new save file...");
            if(f.createNewFile()) {
                System.out.println("Successfully created new file at " + filePath);
            }
        }
    }

    static void readFile() throws IOException {
        if(!new File("data\\save.txt").isFile()) {
            createFile();
        }

        // creating the buffered reader to read the file
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("data\\save.txt")));

        try {
            String curr;
            while ((curr = br.readLine()) != null) {
                boolean done;

                String[] currArr = curr.split("\\|");
                switch(currArr[0]) {
                    case ("T"):
                        done = currArr[1].equals("1");
                        tasks.add(new ToDo(currArr[2], done));
                        break;
                    case ("D"):
                        done = currArr[1].equals("1");
                        tasks.add(new Deadline(currArr[2], done, currArr[3]));
                        break;
                    case ("E"):
                        done = currArr[1].equals("1");
                        tasks.add(new Event(currArr[2], done, currArr[3], currArr[4]));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred reading save file: " + e);
        }

    }

    static void writeFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data\\save.txt"))) {
            for (Task t : tasks) {
                int done;
                if (t instanceof ToDo) {
                    done = t.getDone() ? 1 : 0;
                    bw.write("T|" + done + "|" + t.getDesc());
                } else if (t instanceof Deadline) {
                    done = t.getDone() ? 1 : 0;
                    bw.write("D|" + done + "|" + t.getDesc() + "|" + ((Deadline) t).getDeadlineDay());
                } else if (t instanceof Event) {
                    done = t.getDone() ? 1 : 0;
                    bw.write("E|" + done + "|" + t.getDesc() + "|" +
                            ((Event) t).getFrom() + "|" + ((Event) t).getTo());
                }
                bw.write("\n");
            }
        } catch (Exception e) {
            System.out.println("Error occurred writing to save file" + e);
        }
    }
}
