package duke.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Date translator.
 */
public class DateTranslator {
    /**
     * The Hundred.
     */
    static final int HUNDRED = 100;
    /**
     * The Formatesize.
     */
    static final int FORMATESIZE = 3;
    /**
     * The Raw input.
     */
    private final String rawInput;
    /**
     * The Output.
     */
    private String output;

    /**
     * Instantiates a new Date translator.
     *
     * @param rawInput the raw input
     */
    public DateTranslator(String rawInput) {
        this.rawInput = rawInput;
        convert();
    }

    /**
     * Is date boolean.
     *
     * @param rawInput the raw input
     * @return the boolean
     */
    public static boolean isDate(String rawInput) {
        String[] token1 = rawInput.split("/");
        String[] token2 = token1[1].split("-");
        //first date format
        return token1.length > 2 || token2.length == FORMATESIZE;
    }


    private void convert() {
        //example input = return book /by 2/12/2019 1800
        String[] tokens;
        tokens = rawInput.split("/");
        // 2/12/2019 1800 format
        LocalDateTime time;
        if (tokens.length > 2) {
            //return book |by 2|12| 2019 1800
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int day = Integer.parseInt(tokens[1].split(" ")[1]); //remove by
            int month = Integer.parseInt(tokens[2]);
            String[] yearTime = tokens[FORMATESIZE].split(" ");
            int year = Integer.parseInt(yearTime[0]);
            int time1;
            int hour = 0;
            int min = 0;
            if (yearTime.length > 1) {
                time1 = Integer.parseInt(yearTime[1]);
                hour = time1 / HUNDRED;
                min = time1 % HUNDRED;
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

            }
            time = LocalDateTime.of(year, month, day, hour, min);
            output = time.format(formatter);
        } else {
            //2019-12-02 1800 format
            String[] dateTime = tokens[1].split(" ");
            String[] date = dateTime[1].split("-");
            int year;
            int month;
            int day;
            int timing;
            int hour = 0;
            int min = 0;
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
            if (dateTime.length > 2) {
                timing = Integer.parseInt(dateTime[2]);
                hour = timing / HUNDRED;
                min = timing % HUNDRED;
            }
            time = LocalDateTime.of(year, month, day, hour, min);
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
            output = time.format(formatter);
        }
    }

    /**
     * Gets output.
     *
     * @return the output
     */
    public String getOutput() {
        return output;
    }

}
