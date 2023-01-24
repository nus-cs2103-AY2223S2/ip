import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        talk("Meow I'm Toto! What can I do for you?");
        ArrayList<Task> arrayList = new ArrayList<>(100);


            while (true) {
                try {
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (answer.equals("bye")) {
                    System.out.println("Byebye CATch you later!");
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
        String byString = scanner.nextLine();
        if (byString.isEmpty()) {
            throw new DukeInvalidArgumentException("By is empty, please input task again");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse(byString, formatter);
            Task t = new Deadline(answer.substring(9, answer.length()), by);
            System.out.println("Meow! Just added: \n" + t);
            arrayList.add(t);
        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format, please input task again");
        }

    }

    public static void addEvent(String answer, ArrayList<Task> arrayList) throws DukeInvalidArgumentException{
        if (answer.substring(6, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Event cannot be empty");
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("From?");
        String fromString = scanner.nextLine();
        if (fromString.isEmpty()) {
            throw new DukeInvalidArgumentException("From is empty, please input task again");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            System.out.println("To?");
            String toString = scanner.nextLine();
            if (toString.isEmpty()) {
                throw new DukeInvalidArgumentException("To is empty, please input task again");
            }

            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            Task t = new Event(answer.substring(6, answer.length()), from, to);
            System.out.println("Meow! Just added: \n" + t);
            arrayList.add(t);
        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format, please input task again");
        }

    }

    public static void list(ArrayList<Task> arrayList) {
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println(i + ". " + arrayList.get(i - 1));
        }
    }


}

