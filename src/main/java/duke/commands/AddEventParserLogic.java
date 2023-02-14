package duke.commands;

public class inStartDateOrEndDate {
    inTask("T"),
    inStartDate("D"),
    inEndDate("E");

    private final String identifier;

    inStartDateOrEndDate(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
