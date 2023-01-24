import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        talk("Meow I'm Toto! What can I do for you?");

        File theDir = new File("src/main/data");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        try {
            createFile("src/main/data/duke.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Task> arrayList;
        try {
            arrayList = writeToList("src/main/data/duke.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (true) {
                try {
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (answer.equals("bye")) {
                    System.out.println("Byebye CATch you later!");
                    try {
                        writeToFile("src/main/data/duke.txt", arrayList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                if (answer.equals("list")) {
                    list(arrayList);
                    continue;
                }

                if (answer.startsWith("mark ")) {
                    mark(answer, arrayList);
                    continue;
                }
                if (answer.startsWith("unmark ")) {
                    unmark(answer, arrayList);
                    continue;
                }
                if (answer.startsWith("delete ")) {
                    delete(answer, arrayList);
                    continue;
                }
                if (answer.startsWith("todo ")) {
                    addTodo(answer, arrayList);
                    continue;
                }
                if (answer.startsWith("deadline ")) {
                    addDeadline(answer, arrayList);
                    continue;
                }
                if (answer.startsWith("event ")) {
                    addEvent(answer, arrayList);
                    continue;
                }
                throw new DukeException("I don't know that one!");
                }

                catch (DukeException e) {
                    System.out.println(e.toString());
                }

            }


        }


    public static void talk(String s) {
        System.out.println(s);
    }
    private static void createFile(String filePath) throws IOException {
        File file = new File(filePath);

        file.createNewFile();
    }
    private static ArrayList<Task> writeToList(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> arrayList = new ArrayList<>();
        while (s.hasNext()) {
            String[] parts = s.nextLine().split("-");
            if (parts[0].equals("T")) {
                Task t = new Todo(parts[2]);
                if (parts[1].equals("1")) t.markAsDone();
                arrayList.add(t);

            }
            if (parts[0].equals("D")) {
                Task t = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) t.markAsDone();
                arrayList.add(t);
            }
            if (parts[0].equals("E")) {
                Task t = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) t.markAsDone();
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    private static void writeToFile(String filePath, ArrayList<Task> arrayList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < arrayList.size(); i++) {

            Task t = arrayList.get(i);
            if (t instanceof Todo) {
                fw.write("T-" + t.getStatusIconBinary() + "-" + t.getDescription());
            }
            if (t instanceof Deadline) {
                fw.write("D-" + t.getStatusIconBinary() + "-" + t.getDescription() + "-" + ((Deadline) t).getBy());
            }
            if (t instanceof Event) {
                fw.write("E-" + t.getStatusIconBinary() + "-" + t.getDescription() + "-" + ((Event) t).getFrom() + "-" + ((Event) t).getTo());
            }

            fw.write(System.lineSeparator());

        }
        fw.close();
    }
    public static void mark(String answer, ArrayList<Task> arrayList) throws DukeException {
        if (answer.substring(5, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Mark cannot be empty");
        }
        int index = Integer.valueOf(answer.substring(5, answer.length()));
        if (index <= 0 || index > arrayList.size()) {
            throw new DukeInvalidIndexException("That index is out of bounds");
        }
        arrayList.get(index - 1).markAsDone();
        System.out.println("I've marked this task as done: " + arrayList.get(index - 1));
    }

    public static void unmark(String answer, ArrayList<Task> arrayList) throws DukeException {
        if (answer.substring(7, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Unmark cannot be empty");
        }
        int index = Integer.valueOf(answer.substring(7, answer.length()));

        if (index <= 0 || index > arrayList.size()) {
            throw new DukeInvalidIndexException("That index is out of bounds");
        }
        arrayList.get(index - 1).markAsUndone();
        System.out.println("I've marked this task as not done yet: " + arrayList.get(index - 1 ));
    }

    public static void delete(String answer, ArrayList<Task> arrayList) throws DukeException {
        if (answer.substring(7, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Delete cannot be empty");
        }
        int index = Integer.valueOf(answer.substring(7, answer.length()));
        if (index <= 0 || index > arrayList.size()) {
            throw new DukeInvalidIndexException("That index is out of bounds");
        }
        System.out.println("I've deleted this task: " + arrayList.get(index - 1 ));
        arrayList.remove(index - 1);
    }
    public static void addTodo(String answer, ArrayList<Task> arrayList) throws DukeInvalidArgumentException {
        if (answer.substring(5, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Todo cannot be empty");
        }

        Task t = new Todo(answer.substring(5, answer.length()));
        System.out.println("Meow! Just added: \n" + t);
        arrayList.add(t);
    }

    public static void addDeadline(String answer, ArrayList<Task> arrayList) throws DukeInvalidArgumentException {
        if (answer.substring(9, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Deadline cannot be empty");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("By when?");
        String by = scanner.nextLine();
        if (by.isEmpty()) {
            throw new DukeInvalidArgumentException("By is empty, please input task again");
        }
        Task t = new Deadline(answer.substring(9, answer.length()), by);
        System.out.println("Meow! Just added: \n" + t);
        arrayList.add(t);
    }

    public static void addEvent(String answer, ArrayList<Task> arrayList) throws DukeInvalidArgumentException{
        if (answer.substring(6, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Event cannot be empty");
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("From?");
        String from = scanner.nextLine();
        if (from.isEmpty()) {
            throw new DukeInvalidArgumentException("From is empty, please input task again");
        }
        System.out.println("To?");
        String to = scanner.nextLine();
        if (to.isEmpty()) {
            throw new DukeInvalidArgumentException("To is empty, please input task again");
        }
        Task t = new Event(answer.substring(6, answer.length()), from, to);
        System.out.println("Meow! Just added: \n" + t);
        arrayList.add(t);
    }

    public static void list(ArrayList<Task> arrayList) {
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println(i + ". " + arrayList.get(i - 1));
        }
    }


}

