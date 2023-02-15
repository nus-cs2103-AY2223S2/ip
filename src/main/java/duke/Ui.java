package duke;

//CHECKSTYLE:OFF
import duke.exceptions.*;
//CHECKSTYLE:ON

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/** A UI class that deals with interactions with the user */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n" + LOGO;
    private static final String LIST = "Duke presents tasks in your list: ";
    private static final String BYE = "Duke says bye bye ~~";
    private static final String MATCHING = "Duke finds these items containing your keyword: ";

    private BufferedReader bf;

    public Ui() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getLine() {
        try {
            return bf.readLine();
        } catch (IOException err) {
            System.out.println("Error in analyzing inputs");
            return "";
        }
    }

    public void close() throws IOException {
        bf.close();
    }

    public String greet() {
        System.out.println(GREETING);
        return "Hello from Duke! Nice to see you";
    }

    public String goodBye() {
        lineUI();
        System.out.println(BYE);
        lineUI();

        return BYE;
    }

    public String listCommand() {
        ArrayList<Task> tasks = TaskList.getList();
        return printList(tasks, "tasks");
    }

    public String listNotesCommand() {
        ArrayList<Note> notes = NoteList.getList();
        return printList(notes, "notes");
    }

     static String todoCommand(String str, Storage storage) throws IOException {
        Task toDo;
        try {
            toDo = new ToDo(Parser.getName(str, "todo ".length()));
        } catch (MissingArgumentsException | MissingNameException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        TaskList.addTask(toDo);
        storage.loadTasks();
        String res = "Got it. I've added this task:\n" + toDo + "\n"
                 + "Now you have " + TaskList.getList().size() + " tasks in the list.";
        printWithLines(res);
        return res;
    }

     static String eventCommand(String str, Storage storage) throws IOException {
        try {
            String[] nameAndTheRest;
            try {
                nameAndTheRest = Parser.separate(str.substring("event ".length()));
            } catch (IndexOutOfBoundsException err) {
                throw new MissingArgumentsException();
            }

            String[] dur = Parser.getDuration(nameAndTheRest[1]);
            Task toAdd = new Event(nameAndTheRest[0], dur[0], dur[1]);
            TaskList.addTask(toAdd);
            storage.loadTasks();
            String res = "Got it. I've added this task:\n" + toAdd + "\n"
                    + "Now you have " + TaskList.getList().size() + " tasks in the list.";
            printWithLines(res);
            return res;
        } catch (MissingArgumentsException | MissingNameException | MissingTimeException | InvalidSyntaxException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

    }

     static String deadlineCommand(String str, Storage storage) throws IOException {
        try {
            String[] nameAndTheRest;
            try {
                nameAndTheRest = Parser.separate(str.substring("deadline ".length()));
            } catch (IndexOutOfBoundsException err) {
                throw new MissingArgumentsException();
            }

            Task toAdd = new Deadline(nameAndTheRest[0], Parser.getDeadline(nameAndTheRest[1]));
            TaskList.addTask(toAdd);
            storage.loadTasks();
            String res = "Got it. I've added this task:\n" + toAdd + "\n"
                    + "Now you have " + TaskList.getList().size() + " tasks in the list.";
            printWithLines(res);
            return res;
        } catch (MissingArgumentsException | MissingTimeException | MissingNameException | InvalidSyntaxException |
                 InvalidDateFormatException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }
    }

     static String deleteCommand(String str, Storage storage) throws IOException {
        int num;
        try {
            num = Parser.getNumber(str, "delete ".length());
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        Task removed = TaskList.remove(num - 1);
        storage.loadTasks();
        String res = "Noted. I've removed this task:\n" + removed + "\n"
                    + "Now you have " + TaskList.getList().size() + " tasks in the list.";
        printWithLines(res);
        return res;

    }

     String markCommand(String str, Storage storage) throws IOException {
        int index;
        try {
            index = Parser.getNumber(str, "mark ".length()) - 1;
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        String name = TaskList.markDone(index);
        storage.loadTasks();
        String res = "Nice! Duke has marked this task as done:\n" + "[X] " + name;
        printWithLines(res);
        return res;
    }

     String unmarkCommand(String str, Storage storage) throws IOException {
        int index;
        try {
            index = Parser.getNumber(str, "unmark ".length()) - 1;
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        String name = TaskList.markUndone(index);
        storage.loadTasks();
        String res = "OK, Duke has marked this task as not done yet:\n" +  "[O] " + name;
        printWithLines(res);
        return res;
    }

    String findCommand(String str) {
        String keyword;
        try {
            keyword = Parser.getName(str, "find ".length());
        } catch (MissingNameException | MissingArgumentsException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        ArrayList<Task> matchedTasks = TaskList.findMatch(keyword);
        String res = MATCHING + "\n" + printList(matchedTasks, "matching tasks");
        printWithLines(res);
        return res;
    }

    String noteCommand(String str, Storage storage) throws IOException{
        String note;
        try {
            note = Parser.getName(str, "note ".length());
        } catch (MissingNameException | MissingArgumentsException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        Note toAdd = new Note(note);
        NoteList.addNote(toAdd);
        storage.loadNotes();
        String res = "Got it. I've added this note:\n" + toAdd
                + "\n" + "Now you have " + NoteList.getList().size() + " notes in the list.";
        printWithLines(res);
        return res;
    }

    String deleteNoteCommand(String str, Storage storage) throws IOException {
        int numOfNote;
        try {
            numOfNote = Parser.getNumber(str, "n-delete ".length());
        } catch (MissingArgumentsException | InvalidTaskNumberException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }

        Note removed = NoteList.remove(numOfNote - 1);
        storage.loadNotes();
        String res = "Noted. I've removed this note:\n" + removed + "\n"
                + "Now you have " + NoteList.getList().size() + " notes in the list.";
        printWithLines(res);
        return res;
    }

    String printList(ArrayList<? extends Object> arr, String type) {
        if (arr.size() == 0) {
            System.out.println("Duke sees no " + type + " in your list");
            return "Duke sees no " + type + " in your list";
        }

        String res = "";
        int count = 1;
        while (count <= arr.size()) {
            Object item = arr.get(count - 1);
            res += count + ". " + item.toString() + "\n";
            System.out.println(count + ". " + item);
            count++;
        }
        return res;
    }

    private static void printWithLines(String toPrint) {
        lineUI();
        System.out.println(toPrint);
        lineUI();
    }

     static void lineUI() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
