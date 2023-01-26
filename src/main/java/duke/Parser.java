package duke;

import duke.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String[] separate(String line) throws MissingTimeException, MissingNameException {
        try {
            StringBuilder sb = new StringBuilder();

            int i;
            for (i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '/') {
                    break;
                }
                sb.append(line.charAt(i));
            }

            String name = sb.toString();
            if (name.isBlank()) {
                throw new MissingNameException();
            }
            String theRest = line.substring(i + 1);
            return new String[] {name, theRest};
        } catch (IndexOutOfBoundsException err) {
            throw new MissingTimeException();
        }
    }

    public static String getDeadline(String line) throws MissingTimeException, InvalidSyntaxException {
        try {
            String toReturn = line.substring(3);
            if (toReturn.isBlank()) {
                throw new MissingTimeException();
            }
            if (!line.startsWith("by ")) {
                throw new InvalidSyntaxException();
            }
            return toReturn;
        } catch (IndexOutOfBoundsException err) {
            throw new MissingTimeException();
        }
    }

    public static int getTaskNumber(String str, int from) throws MissingArgumentsException, InvalidTaskNumberException {
        int numOfTask;
        try {
            numOfTask = Integer.parseInt(str.substring(from));
        } catch (NullPointerException | IndexOutOfBoundsException err) {
            throw new MissingArgumentsException();
        }

        // Handle when the index is out of bound -> create a separate function
        if (numOfTask <= 0 || numOfTask > TaskList.getList().size()) {
            throw new InvalidTaskNumberException();
        }
        return numOfTask;
    }






    public static String[] getDuration(String line) throws MissingArgumentsException, MissingTimeException, InvalidSyntaxException {
        try {
            String[] arr = line.split("/");
            if (!arr[0].startsWith("from ")) {
                throw new InvalidSyntaxException();
            }
            arr[0] = arr[0].substring(5);
            if (!arr[1].startsWith("to ")) {
                throw new InvalidSyntaxException();
            }
            arr[1] = arr[1].substring(3);
            if (arr[0].isBlank() || arr[1].isBlank()) {
                throw new MissingTimeException();
            }
            return arr;
        } catch (IndexOutOfBoundsException err) {
            throw new MissingArgumentsException();
        }
    }


    public static String getName(String str, int from) throws MissingNameException, MissingArgumentsException {
        try {
            String name = str.substring(from);

            if (name.isBlank()) {
                throw new MissingNameException();
            }

            return name;
        }  catch (IndexOutOfBoundsException err) {
            throw new MissingArgumentsException();
        }
    }

    public static String getString(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
        return d.format(formatter);
    }

    public static LocalDate getDate(String str) {
        return LocalDate.parse(str);
    }

    public static String getDateMMM(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
        return getString(LocalDate.parse(str, formatter));
    }


}
