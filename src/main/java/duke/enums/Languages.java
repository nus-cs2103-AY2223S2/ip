package duke.enums;

import java.util.Arrays;

/**
 * Language strings
 */
public enum Languages {
    ENG(),
    PIRATE(),
    SHAKESPEARE(),
    LOLCAT();

    /**
     * List out the available languages in one string
     *
     * @return String of available enums
     */
    public static String listEnumStrings() {
        String[] languages = Arrays.stream(Languages.values())
                .map(Enum::name)
                .toArray(String[]::new);
        String returnString = "";
        for (String lang : languages) {
            returnString += lang + " ";
        }
        return returnString.trim();
    }
}
