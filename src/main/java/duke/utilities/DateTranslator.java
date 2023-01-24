package duke.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTranslator {
    String raw_input;
    LocalDateTime time;
    public String output;

    public DateTranslator(String raw_input) {
        this.raw_input = raw_input;
        convert();
    }

    public static boolean is_date(String raw_input) {
        String[] token1 = raw_input.split("/");
        String[] token2 = token1[1].split("-");
        //first date  format
        return token1.length > 2 || token2.length == 3;
    }

    void convert() {
        //example input = return book /by 2/12/2019 1800
        String[] tokens;
        tokens = raw_input.split("/");
        // 2/12/2019 1800 format
        if (tokens.length > 2) {
            //return book |by 2|12| 2019 1800
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            int day = Integer.parseInt(tokens[1].split(" ")[1]); //remove by
            int month = Integer.parseInt(tokens[2]);
            String[] year_time = tokens[3].split(" ");
            int year = Integer.parseInt(year_time[0]);
            int time1;
            int hour = 0;
            int min = 0;
            if (year_time.length > 1) {
                time1 = Integer.parseInt(year_time[1]);
                hour = time1 / 100;
                min = time1 % 100;
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

            }
            time = LocalDateTime.of(year, month, day, hour, min);
            output = time.format(formatter);
            System.out.println(output);
        } else {
            //2019-12-02 1800 format
            String[] Date_time = tokens[1].split(" ");
            String[] date = Date_time[1].split("-");
            int year;
            int month;
            int day;
            int timing;
            int hour = 0;
            int min = 0;
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
            if (Date_time.length > 2) {
                timing = Integer.parseInt(Date_time[2]);
                hour = timing / 100;
                min = timing % 100;
            }
            time = LocalDateTime.of(year, month, day, hour, min);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
            output = time.format(formatter);
        }
    }


}
