package app.task;

import java.util.HashMap;
import java.util.Map;

public enum TaskTypes {
    inputToTask(new HashMap<String, Type>()),
    symbolToTask(new HashMap<String, Type>());
    static{
        TaskTypes.inputToTask.getValue().put("todo", TaskTypes.Type.TODO);
        TaskTypes.inputToTask.getValue().put("deadline", TaskTypes.Type.DEADLINE);
        TaskTypes.inputToTask.getValue().put("event", TaskTypes.Type.EVENT);

        TaskTypes.symbolToTask.getValue().put("T", TaskTypes.Type.TODO);
        TaskTypes.symbolToTask.getValue().put("D", TaskTypes.Type.DEADLINE);
        TaskTypes.symbolToTask.getValue().put("E", TaskTypes.Type.EVENT);
    }

    private Map<String, Type> value;

    public Map<String, Type> getValue() {
        return value;
    }

    TaskTypes(Map<String, Type> value) {
        this.value = value;
    }

    public enum Type {TODO, DEADLINE, EVENT}
}