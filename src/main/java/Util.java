import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Class that stores all helper functions statically.
 */
public final class Util {
    /**
     * @deprecated Class that stores all resource values statically. Should not be initialized.
     */
    Util() {
    }
    public static String parenthesize(char c){
        return "["+c+']';
    }
    public static final Function<String,UserTask> todoNew= TodoTask::new;
}
