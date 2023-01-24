package aqua.logic;

import java.util.HashMap;
import java.util.Optional;


public class ArgumentMap {
    public static final String TAG_MAIN_INPUT = "MAIN_INPUT";

    private final HashMap<String, String> inputMap;


    public ArgumentMap(HashMap<String, String> inputMap) {
        this.inputMap = new HashMap<>(inputMap);
    }


    public Optional<String> getMainInput() {
        return get(TAG_MAIN_INPUT);
    }


    public Optional<String> get(String key) {
        return Optional.ofNullable(inputMap.get(key));
    }
}
