import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    static protected Command read(Scanner sc) {
        String command = sc.next();
        // Useful variables
        int rank;
        String[] message;
        try {
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return new Command(0);
                case "list":

                    return new Command(1);
                case "mark":
                    try {
                        rank = Integer.parseInt(sc.nextLine().trim());
                        return new Command(2, rank - 1);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! mark must have an integer rank");
                    }

                case "unmark":
                    try {
                        rank = Integer.parseInt(sc.nextLine().trim());
                        return new Command(3, rank - 1);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! unmark must have an integer rank");
                    }
                case "todo":
                    message = sc.nextLine().trim().split("/");
                    return new Command(4, message);
                case "deadline":
                    message = sc.nextLine().trim().split("/");
                    return new Command(5, message);
                case "event":
                    message = sc.nextLine().trim().split("/");
                    return new Command(6, message);
                case "delete":
                    try {
                        rank = Integer.parseInt(sc.nextLine().trim());
                        return new Command(7, rank - 1);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! delete must have an integer rank");
                    }

                default:
                    throw new DukeException("Invalid input.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return new Command(-1);
        }
    }

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    protected static MaybeDate parseDate(String s) throws DukeException {
        String temp = s;
        if (s.length() == 10) {
            temp = temp + " 2359";
        }
        try {
            return new MaybeDate(LocalDateTime.parse(temp, inputFormatter));
        } catch (DateTimeParseException e) {
            return new MaybeDate(s);
        }
    }

    public static void main(String[] args) {
        try {
            parseDate("01-12-2019");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
