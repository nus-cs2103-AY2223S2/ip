import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Tasks tasks = new Tasks();

        //opening storage file, or create one if it doesn't exist
        dataLoader(tasks);

        Scanner myScan = new Scanner(System.in);
        String task = myScan.nextLine();

        while (!(task.equalsIgnoreCase("bye"))) {
            divider();

            if (task.equalsIgnoreCase("list")) {
                tasks.printList();
            }
            else if (compareString(task, "todo")) {
                String desc = task.split(" ", 2)[1];
                tasks.addToList(new Todo(desc), false);

            } else if (compareString(task, "deadline")) {
                String temp = task.split(" ", 2)[1]; //task and deadline remain
                String[] details = temp.split("/by ");
                tasks.addToList(new Deadline(details[0], details[1]), false);

            } else if (compareString(task, "event")) {
                String temp = task.split(" ", 2)[1]; //task, from and to remain
                String[] details = temp.split("/from ", 2);
                String[] time = details[1].split("/to ");
                tasks.addToList(new Event(details[0], time[0], time[1]), false);

            } else if (compareString(task, "mark")) {
                try {
                    int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                    tasks.markTaskDone(taskNum, false);
                } catch (Exception e) {
                    System.out.println("Come on now, try again. I asked for an number.");
                }

            }
            else if (compareString(task, "unmark")) {
                try {
                    int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                    tasks.markTaskUndone(taskNum);
                } catch (Exception e) {
                    System.out.println("Come on now, try again. I asked for an number.");
                }
            }

            else if (compareString(task, "delete")) {
                int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                tasks.deleteTask(taskNum);
            }

            else if (task.isEmpty()) {
                System.out.println("Don't appreciate the silence :(");
            }

            else {
                System.out.println("Bzzt... My bad, didn't catch what you said, did you mess up your spelling? 0_o");
            }

            divider();
            task = myScan.nextLine();
            updateData(tasks);
        }
        myScan.close();
        exit();
    }

    public static boolean compareString(String task, String comparator) {
        return task.split(" ")[0].equalsIgnoreCase(comparator);
    }

    public static void greet() {
        System.out.println("Hi there!\nWhat can I do for you on this fine day :)?");
        divider();
    }

    public static void exit() {
        System.out.println("YAY Thank GOD! BYEEEEE~");
    }

    public static void divider() {
        System.out.println("-".repeat(50));
    }

    public static void dataLoader(Tasks tasks) {
        try {
            File data = new File("./DukeData.txt");
            //create file if file does not exist
            if (data.createNewFile()) {
                System.out.println("Hey hey~ Welcome new user~ :)");
            } else {
                Scanner s = new Scanner(data);
                while (s.hasNext()) {
                    //format of a line: Type|status|description ...
                    String currLine = s.nextLine();
                    String[] details = currLine.split("\\|");
                    String type = details[0];
                    String status = details[1];
                    String desc = details[2];

                    switch (type) {
                        case "T":
                            Todo t = new Todo(desc);
                            //task is done
                            if (status.equalsIgnoreCase("X")) {
                                t.markTaskDone(true);
                            }
                            tasks.addToList(t, true);
                            break;

                        case "D":
                            String by = details[3];
                            Deadline d = new Deadline(desc, by);
                            if (status.equalsIgnoreCase("X")) {
                                d.markTaskDone(true);
                            }
                            tasks.addToList(d, true);
                            break;

                        case "E":
                            String from = details[3];
                            String to = details[4];
                            Event e = new Event(desc, from, to);
                            if (status.equalsIgnoreCase("X")) {
                                e.markTaskDone(true);
                            }
                            tasks.addToList(e, true);
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value: " + type);
                    }
                }
                s.close();
            }
        } catch (IOException e) {
            System.out.println("Data file error.");
        }
    }

    public static void updateData(Tasks tasks) {
        try {
            FileWriter fw = new FileWriter("./DukeData.txt");
            String data = tasks.formatForFile();
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}