package cbot.time;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeStuff {
    private static final String[] D_FORMS = {"y-M-d", "d/M/y", "d MMM y", "MMM d y"};
    private static final String[] T_FORMS = {" Hmm", " H:m",
            " ha", " h a", " h.ma", " h.m a", " h:ma", " h:m a"};
    
    private static final DateTimeFormatter UI_FORM = DateTimeFormatter.ofPattern("dd/MM/yy, HHmm");
    
    public static LocalDateTime parseDT(String dtStr) throws DateTimeParseException {
        String str = dtStr.trim();
        
        for (String df : D_FORMS) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(df)).atStartOfDay();
            } catch (DateTimeParseException e) {
                for (String tf : T_FORMS) {
                    try {
                        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(df + tf));
                    } catch (DateTimeParseException ignore) { }
                }
            }
        }
        
        return LocalDateTime.parse(str);
    }
    
    public static String text(LocalDateTime dt) {
        return dt.format(UI_FORM);
    }
}