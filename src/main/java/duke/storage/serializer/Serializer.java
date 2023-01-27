package duke.storage.serializer;
import java.util.HashMap;

/**
 * Serializer is the base class that handles serializing of instances.
 */
public class Serializer {
    private static final String GROUP_DELIMITER = String.valueOf((char) 0x1d);
    private static final String RECORD_DELIMITER = String.valueOf((char) 0x1e);

    private HashMap<String, Object> map;

    public Serializer() {
        map = new HashMap<>();
    }

    /**
     * Initialises a Serializer with data extracted from serialized
     *
     * @param serialized The string to be deserialized
     */
    public Serializer(String serialized) {
        this();
        String[] entries = serialized.split(GROUP_DELIMITER);
        for (String entry: entries) {
            String[] keyVal = entry.split(RECORD_DELIMITER);
            add(keyVal[0], keyVal[1]);
        }
    }

    public void add(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key: map.keySet()) {
            sb.append(key);
            sb.append(RECORD_DELIMITER);
            sb.append(map.get(key).toString());
            sb.append(GROUP_DELIMITER);
        }
        return sb.toString();
    }
}
