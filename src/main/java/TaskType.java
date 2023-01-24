import java.util.HashMap;
import java.util.Map;

public class TaskType {
    public enum Type { TODO, DEADLINE, EVENT }
    public static final Map<String, Type> inputToTask = new HashMap<>();
    public static final Map<String, Type> symbolToTask = new HashMap<>();

    static{
        inputToTask.put("todo", Type.TODO);
        inputToTask.put("deadline", Type.DEADLINE);
        inputToTask.put("event", Type.EVENT);

        symbolToTask.put("T", Type.TODO);
        symbolToTask.put("D", Type.DEADLINE);
        symbolToTask.put("E", Type.EVENT);
    }
}
