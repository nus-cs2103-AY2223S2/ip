package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the conversion of time from user input.
 */
public class DateTime {
    
    /**
     * Returns dateTime from user given input.
     * 
     * @param words user input that has been put into an array of strings.
     * @param index the index before the date.
     * @return return LocalDateTime derived from user input.
     */
    public static LocalDateTime getDateTime(String[] words, int index) {
        LocalDateTime dateTime;

        if (index != -1 && words.length >= index+2) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                dateTime = LocalDateTime.parse(words[index + 1] + " " + words[index + 2], formatter);
                return dateTime;
            } catch (DateTimeParseException e) {
                return null;
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
