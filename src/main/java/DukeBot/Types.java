package DukeBot;

public enum Types {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String identifier;

    Types(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
