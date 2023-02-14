package duke.commands.enums;

/**
 * Represents if the AddEventCommand parsing logic is within the task, start date or end date.
 */
public enum AddEventParserLogic {
    inTask("T"),
    inStartDate("S"),
    inEndDate("E");

    private final String identifier;

    AddEventParserLogic(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
