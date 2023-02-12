package duke;
import java.io.*;
import java.util.*;
public class Duke {

    private static ArrayList<Task> list;
    public Duke() {
        this.list = new ArrayList<>();
    }
    private static void line(int l) {
        System.out.print('\n');
        for (int i = 0; i < l + 15; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }
    private static File load() throws DukeException {
        File save = new File("./duke.txt");
        try {
            if (save.createNewFile()) {
                System.out.println("R U NEW?? I MADE NEW SAVE FILE 4 U");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(save));
                String line = reader.readLine();
                String[] parmArr = line.split("\\|");
                List<String> parm = Arrays.asList(parmArr);
                while (line != null) {
                    parmArr = line.split("\\|");
                    parm = Arrays.asList(parmArr);
                    Command command = Command.fromTag(parm.get(0));
                    boolean isMark = parm.get(1) == "1";
                    String description = parm.get(2);
                    switch (command) {
                    case TODO:
                        addList(new Todo(description, isMark));
                        break;
                    case DEADLINE:
                        String date = parm.get(3);
                        addList(new Deadline(description, date, isMark));
                        break;
                    case EVENT:
                        String from = parm.get(3);
                        String to = parm.get(4);
                        addList(new Event(description, from, to, isMark));
                        break;
                    default:
                        break;
                    }

                    line = reader.readLine();
                }
                reader.close();
            }
        } catch (Exception e) {
            throw new DukeException(e);
        }
        return save;
    }
    private static void save(File save) throws DukeException, IOException {
        save.delete();
        File saver = new File("./duke.txt");
        FileWriter mySaveWriter = new FileWriter(saver, false);
        try {
            for (Task i: list) {
                String tag = i.getTag();
                String mark = i.getStatusIcon();
                if (mark == "X") {
                    mark = "1";
                } else {
                    mark = "0";
                }
                String description = i.getDescription();
                String date = i.getDate();
                mySaveWriter.write(tag + "|" + mark + "|" + description + "|" + date + "\n");
            }
            mySaveWriter.flush();
            mySaveWriter.close();
        } catch (Exception e) {
            throw new DukeException(e);
        }
    }
    private static void showList() {
        int j = 0;
        for (Task i: list) {
            j++;
            System.out.println(String.valueOf(j) + ". " + i);
        }
    }
    private static void markTask(int i, boolean b) {
        int index = i - 1;
        list.get(index).markTask(b);
        System.out.println("Marked/Unmarked the task, task is in the state:\n");
        System.out.print("  " + list.get(index));
    }
    private static void addList(Task task) {
        list.add(task);
        System.out.println("added: " + task.getDescription());
        System.out.print("You have: " + list.size() + " task(s)\n");
    }
    private static void deleteTask(int i) {
        int index = i - 1;
        System.out.println("removed: " + list.get(index).toString());
        System.out.print("You have: " + (list.size() - 1) + " task(s)\n");
        list.remove(index);

    }
    private static void parseIn(List<String> parm) throws DukeException {
        int byIndex;
        int fromIndex;
        String description;
        List<String> l;
        String deadline;
        Command command = Command.fromString(parm.get(0));
        switch(command) {
        case LIST:
            showList();
            break;
        case TODO:
            l = parm.subList(1, parm.size());
            if (parm.size() == 1) {
                throw new DukeException("TASK MUST HAS DESCRIPSHUN MEOW");
            }
            description = String.join(" ", l);
            addList(new Todo(description));
            break;
        case DEADLINE:
            try {
                //find the /by keyword
                byIndex = parm.indexOf("/by");
                l = parm.subList(1, byIndex);
                if (byIndex == 1) {
                    throw new DukeException("TASK MUST HAS DESCRIPSHUN MEOW");
                }
                description = String.join(" ", l);
                l = parm.subList(byIndex + 1, parm.size());
                deadline = String.join(" ", l);
                addList(new Deadline(description, deadline));
            } catch (IllegalArgumentException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
            break;
        case EVENT:
            try {
                fromIndex = parm.indexOf("/from");
                byIndex = parm.indexOf("/to");
                l = parm.subList(1, fromIndex);
                description = String.join(" ", l);
                List<String> f = parm.subList(fromIndex + 1, byIndex);
                String fDescription = String.join(" ", f);

                List<String> t = parm.subList(byIndex + 1, parm.size());
                String tDescription = String.join(" ", l);
                tDescription = String.join(" ", t);
                addList(new Event(description, fDescription, tDescription));
            } catch (IllegalArgumentException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
            break;
        case MARK:
            try {
                markTask(Integer.parseInt(parm.get(1)), true);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(e);
            } catch (NumberFormatException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
            break;

        case UNMARK:
            try {
                markTask(Integer.parseInt(parm.get(1)), false);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(e);
            } catch (NumberFormatException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
            break;
        case DELETE:
            try {
                deleteTask(Integer.parseInt(parm.get(1)));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(e);
            } catch (NumberFormatException e) {
                throw new DukeException(e);
            } catch (Exception e) {
                throw new DukeException(e);
            }
            break;
        case SOMETHINGELSE:
            throw new DukeException();
        default:
            throw new DukeException();
        }
    }
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
    //CHECKSTYLE.OFF: checkStyleTest 
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    //CHECKSTYLE.ON: checkStyleTest
        System.out.println("Hello from\n" + logo);
        System.out.println("I will remember things now");
        File save = load();
        while (true) {
            System.out.print('\n');
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if (in.equals("bye")) {
                System.out.println("No don't go!!");
                break;
            }
            line(in.length());
            String[] parmArr = in.split("\\s+");
            List<String> parm = Arrays.asList(parmArr);
            try {
                parseIn(parm);
                save(save);
            } catch (DukeException e) {
                return;
            }
            line(in.length());
        }
    }
}
