import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class Duke {

    public enum Keyword {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private String word;
        Keyword(String word) {
            this.word = word;
        }
        @Override
        public String toString() {
            return this.word;
        }
    }
    public static final String dashes = "--------------------------------------";

    public static final String fileDir = "./data/";

    public static final String fileName = "duke.txt";
    public static final String filePath = fileDir + fileName;

    public String formatString(String input) {
        return dashes + "\n" + input + "\n" + dashes + "\n";
    }

    public void echo() {
        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(this.formatString(line));
        }
    }

    public String formatList(List userTasks) {
        String formattedList = "";
        for (Object t : userTasks) {
            int pos = userTasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + "\n";
        }
        return formattedList.trim();
    }

    public String[] deadlineSplitter(String message) throws DukeException  {
        String[] resultArr = new String[2];
        Pattern p = Pattern.compile("/by");
        String temp[] = p.split(message);
        if (temp.length < 2) {
            throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
        }
        String description = temp[0].trim();
        String by = temp[1].trim();
        resultArr[0] = description;
        resultArr[1] = by;
        return resultArr;
    }

    public String[] eventSplitter(String message) throws DukeException {
        String[] resultArr = new String[3];
        Pattern p1 = Pattern.compile("/from");
        String temp1[] = p1.split(message);
        if (temp1.length < 2) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String description = temp1[0].trim();
        Pattern p2 = Pattern.compile("/to");
        String[] temp2 = p2.split(temp1[1]);
        if (temp2.length < 2) {
            throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
        }
        String from = temp2[0].trim();
        String to = temp2[1].trim();
        resultArr[0] = description;
        resultArr[1] = from;
        resultArr[2] = to;
        return resultArr;
    }

    public File makeFile() {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    //Solution below adapted from https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
    public void deleteLine(String line) {
        File ogFile = new File(filePath);
        File tempFile = new File(fileDir + "temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ogFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String rmLine = line;
            String currLine;
            boolean done = false;
            boolean linesLeft = true;
            while (linesLeft) {
                currLine = reader.readLine();
                if (currLine == null) {
                    linesLeft = false;
                } else {
                    String trimmedLine = currLine.trim();
                    if (trimmedLine.equals(rmLine) && !done) {
                        done = true;
                        continue;
                    }
                    writer.write(currLine + "\n");
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(ogFile);
    }

    public void modifyLine(String line, String newLine) {
        File ogFile = new File(filePath);
        File tempFile = new File(fileDir + "temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(ogFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String rmLine = line;
            String currLine;
            boolean done = false;
            boolean linesLeft = true;
            while (linesLeft) {
                currLine = reader.readLine();
                if (currLine == null) {
                    linesLeft = false;
                } else {
                    String trimmedLine = currLine.trim();
                    if (trimmedLine.equals(rmLine) && !done) {
                        done = true;
                        writer.write(newLine + "\n");
                    } else {
                        writer.write(currLine + "\n");
                    }
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(ogFile);
    }

    public void appendToFile(String text){
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(text + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        File f = new File(filePath);
        ArrayList<Task> userTasks = new ArrayList<>();
        if (f.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));

                String currLine;
                boolean linesLeft = true;
                while (linesLeft) {
                    currLine = reader.readLine();
                    if (currLine == null) {
                        linesLeft = false;
                    } else {
                        String[] fields = currLine.split(Pattern.quote(" | "));
                        if (fields[0].equals("T")) {
                            Task t = new ToDo(fields[2]);
                            if (fields[1].equals("1")) {
                                t.markAsDone();
                            }
                            userTasks.add(t);
                        } else if (fields[0].equals("D")) {
                            Task t = new Deadline(fields[2], fields[3]);
                            if (fields[1].equals("1")) {
                                t.markAsDone();
                            }
                            userTasks.add(t);
                        } else if (fields[0].equals("E")) {
                            Task t = new Event(fields[2], fields[3], fields[4]);
                            if (fields[1].equals("1")) {
                                t.markAsDone();
                            }
                            userTasks.add(t);
                        }
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return userTasks;
        } else {
            return userTasks;
        }
    }

    public void addMarkList(){
        //Make sure the duke.txt file exists
        makeFile();

        ArrayList<Task> userTasks = loadFromFile();

        System.out.println(this.formatString("Hello! I'm Gerty\nWhat can I do for you?"));

        boolean doLoop = true;

        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine();
            String arr[] = line.split(" ", 2);
            String keyword = arr[0].toLowerCase();
            try {
                if (keyword.equals(Keyword.BYE.toString())) {
                    System.out.println(this.formatString("Bye. Hope to see you again soon!"));
                    break;
                } else if (keyword.equals(Keyword.LIST.toString())) {
                    System.out.println(this.formatString("Here are the tasks in your list:\n" + this.formatList(userTasks)));
                } else if (keyword.equals(Keyword.MARK.toString())) {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    String prevText = t.toText();
                    t.markAsDone();
                    String newText = t.toText();
                    modifyLine(prevText, newText);
                    System.out.println(this.formatString("Nice! I've marked this task as done:\n" + t));
                } else if (keyword.equals(Keyword.UNMARK.toString())) {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.get(index);
                    String prevText = t.toText();
                    t.unmarkAsDone();
                    String newText = t.toText();
                    modifyLine(prevText, newText);
                    System.out.println(this.formatString("OK, I've marked this task as not done yet:\n" + t));
                } else if (keyword.equals(Keyword.DELETE.toString())) {
                    String i = arr[1];
                    int index = Integer.parseInt(i) - 1;
                    Task t = userTasks.remove(index);
                    deleteLine(t.toText());
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Noted. I've removed this task:\n" + t + "\n" + numTasks));
                } else if (keyword.equals(Keyword.TODO.toString())) {
                    //Only keyword todo present, no description
                    if (arr.length == 1) {
                        throw new DukeException("Invalid format for ToDo.\nUsage: todo <task>\n");
                    }
                    String info = arr[1];
                    Task t = new ToDo(info);
                    userTasks.add(t);
                    appendToFile(t.toText());
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                } else if (keyword.equals(Keyword.DEADLINE.toString())) {
                    if (arr.length == 1) {
                        throw new DukeException("Invalid format for Deadline.\nUsage: deadline <task> /by <date/time>\n");
                    }
                    String info = arr[1];
                    String[] params = deadlineSplitter(info);
                    Task t = new Deadline(params[0], params[1]);
                    userTasks.add(t);
                    appendToFile(t.toText());
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                } else if (keyword.equals(Keyword.EVENT.toString())) {
                    if (arr.length == 1) {
                        throw new DukeException("Invalid format for Event.\nUsage: <task> /from <date/time> /to <date/time>\n");
                    }
                    String info = arr[1];
                    String[] params = eventSplitter(info);
                    Task t = new Event(params[0], params[1], params[2]);
                    userTasks.add(t);
                    appendToFile(t.toText());
                    String numTasks = "Now you have " + userTasks.size() + " tasks in the list.";
                    System.out.println(this.formatString("Got it. I've added this task:\n" + t + "\n" + numTasks));
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke gerty = new Duke();
        gerty.addMarkList();
    }
}
