import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeStuff {
    private static final String[] DFORMS = {"y-M-d", "d/M/y", "d MMM y", "MMM d y"};
    private static final String[] TFORMS = {" Hmm", " H:m",
            " ha", " h a", " h.ma", " h.m a", " h:ma", " h:m a"};
    
    private static final DateTimeFormatter UI_FORM = DateTimeFormatter.ofPattern("dd/MM/yy, HHmm");
    
    static LocalDateTime parseDT(String dtStr) throws DateTimeParseException {
        String str = dtStr.trim();
        
        for (String df : DFORMS) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(df)).atStartOfDay();
            } catch (DateTimeParseException e1) {
                for (String tf : TFORMS) {
                    try {
                        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(df + tf));
                    } catch (DateTimeParseException e2) {}
                }
            }
        }
        
        return LocalDateTime.parse(str);
    }
    
    static String text(LocalDateTime dt) {
        return dt.format(UI_FORM);
    }
}