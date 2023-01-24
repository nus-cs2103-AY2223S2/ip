import java.util.OptionalInt;

public class DukeUtils {

    public static OptionalInt convertStringToInt(String input) {
        try {
            return OptionalInt.of(Integer.parseInt(input));
        } catch (NumberFormatException ex) {
            return OptionalInt.empty();
        }
    }
}
