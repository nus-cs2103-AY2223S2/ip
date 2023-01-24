import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> tasklst = new ArrayList<>();

    public enum Cmdtype {
        mark,
        unmark,
        delete,
        list,
        todo,
        deadline,
        event,
        bye
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("--------------------------------------------------------------------");
        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?");
        System.out.println("--------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String cmd = input.nextLine();
            try {
                // check if command is valid
                checkCommand(cmd);
                System.out.println("--------------------------------------------------------------------");
                String c = cmd.split(" ")[0];
                Cmdtype cmdtype = Cmdtype.valueOf(c);
                switch (cmdtype) {
                    case list:
                        int counter = 1;
                        for (Task tmp : tasklst) {
                            System.out.println(counter++ + ". " + tmp.toString());
                        }
                        break;
                    case mark:
                        int id1 = Integer.parseInt(cmd.split(" ")[1]);
                        Task t1 = tasklst.get(id1 - 1);
                        t1.mark();
                        System.out.println("Nice! I've marked this task as done:\n" + t1);
                        updateTasklist();
                        break;
                    case unmark:
                        int id2 = Integer.parseInt(cmd.split(" ")[1]);
                        Task t2 = tasklst.get(id2 - 1);
                        t2.unmark();
                        System.out.println("OK, I've marked this task as not done yet:\n" + t2);
                        updateTasklist();
                        break;
                    case todo:
                        String activity1 = cmd.substring(c.length() + 1);
                        Task t3 = new Todo(activity1);
                        tasklst.add(t3);
                        System.out.println("Got it. I've added this task:\n" + t3
                                + "\n Now you have " + tasklst.size() + " tasks in the list.");
                        updateTasklist();
                        break;
                    case deadline:
                        int indexOfBy = cmd.indexOf("/by ");
                        int indexOfDate = indexOfBy + 4; // "/by "
                        String activity2 = cmd.substring(c.length() + 1, indexOfBy - 1);
                        String date = cmd.substring(indexOfDate);
                        Task t4 = new Deadline(activity2, date);
                        tasklst.add(t4);
                        System.out.println("Got it. I've added this task:\n" + t4
                                + "\n Now you have " + tasklst.size() + " tasks in the list.");
                        updateTasklist();
                        break;
                    case event:
                        int indexOfFrom = cmd.indexOf("/from ");
                        int indexOfFromTime = indexOfFrom + 6;
                        int indexOfTo = cmd.indexOf("/to ");
                        int indexOfToTime = indexOfTo + 4;
                        String activity = cmd.substring(c.length() + 1, indexOfFrom - 1);
                        String from = cmd.substring(indexOfFromTime, indexOfTo - 1);
                        String to = cmd.substring(indexOfToTime);
                        Task t5 = new Event(activity, from, to);
                        tasklst.add(t5);
                        System.out.println("Got it. I've added this task:\n" + t5
                                + "\n Now you have " + tasklst.size() + " tasks in the list.");
                        updateTasklist();
                        break;
                    case delete:
                        int id3 = Integer.parseInt(cmd.split(" ")[1]);
                        Task t6 = tasklst.remove(id3 - 1);
                        System.out.println("Noted. I've removed this task:\n" + t6
                                + "\n Now you have " + tasklst.size() + " tasks in the list.");
                        updateTasklist();
                        break;
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("--------------------------------------------------------------------");
                        return;
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("--------------------------------------------------------------------");
        }
    }

    public static boolean checkCommand(String cmd) throws DukeException {
        String cmdtype = cmd.split(" ")[0];
        // check that command list must not have any more description
        if (cmdtype.equals("list") && cmdtype.length() != cmd.length()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but list cannot have a description.");
        } else if (cmdtype.equals("todo") && cmdtype.length() == cmd.length()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (cmdtype.equals("deadline")) {
            try {
                int indexOfBy = cmd.indexOf("/by ");
                int indexOfDate = indexOfBy + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfBy - 1);
                String date = cmd.substring(indexOfDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The format of a deadline: deadline {activity} /by {date}.");
            }
        } else if (cmdtype.equals("event")) {
            try {
                int indexOfFrom = cmd.indexOf("/from ");
                int indexOfFromTime = indexOfFrom + 6;
                int indexOfTo = cmd.indexOf("/to ");
                int indexOfToTime = indexOfTo + 4;
                String activity = cmd.substring(cmdtype.length() + 1, indexOfFrom - 1);
                String from = cmd.substring(indexOfFromTime, indexOfTo - 1);
                String to = cmd.substring(indexOfToTime);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The format of a event: event {activity} /from {datetime} /to {datetime}.");
            }
        }
        return true;
    }

    public static void updateTasklist() {
        String DIRECTORY = "./data";
        String FILENAME = "duke.txt";
        try {
            File directory = new File(DIRECTORY);
            if (!directory.exists()){
                directory.mkdir();
            }
            StringBuilder res = new StringBuilder();
            int counter = 1;
            for (Task tmp : tasklst) {
                res.append(counter++ + ". " + tmp.toString() + "\n");
            }
            FileWriter myWriter = new FileWriter(DIRECTORY + "/" + FILENAME);
            myWriter.write(res.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}