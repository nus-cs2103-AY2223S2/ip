package aqua.logic;

import java.util.HashMap;
import java.util.Optional;


public class ArgumentMap {
    public static final String MAIN_INPUT_KEY = "MAIN_INPUT";

    private final HashMap<String, String> inputMap;


    public ArgumentMap(HashMap<String, String> inputMap) {
        this.inputMap = new HashMap<>(inputMap);
    }


    public Optional<String> getMainInput() {
        return get(MAIN_INPUT_KEY);
    }


    public Optional<String> get(String key) {
        return Optional.ofNullable(inputMap.get(key));
    }
}
