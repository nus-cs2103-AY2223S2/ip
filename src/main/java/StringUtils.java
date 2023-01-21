import java.util.Arrays;

public class StringUtils {
    public StringUtils() {

    }
    public static String joinString(String[] array, int start, int end) {
        String string = "";
        for (int i = start; i < end; i++) {
            string += array[i] + " ";
        }
        return string + array[end];
    }

    public static String[] removeWhiteSpace(String[] array) {
        return Arrays.stream(array).filter(x -> !x.equals(" ") && !x.equals("")).toArray(String[]::new);
    }

    public static int searchString(String[] array, String string) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(string)) {
                return i;
            }
        }
        return -1;
    }
}
