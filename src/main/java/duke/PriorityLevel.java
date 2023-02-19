package duke;

public enum PriorityLevel {
    LOW( "L"),
    MEDIUM("M"),
    HIGH("H");

    private final String letter;
    PriorityLevel(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return this.letter;
    }
}
