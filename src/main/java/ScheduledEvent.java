class ScheduledEvent extends Task {
    String dateBegin;
    String dateEnd;
    public ScheduledEvent(String dateBegin, String dateEnd, String action) {
        super("E", action);
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    @Override
    String getAdditionalInfo() {
        return " (FROM: " + dateBegin + " TO: " + dateEnd + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " (FROM: " + dateBegin + " TO: " + dateEnd + ")";
    }
}