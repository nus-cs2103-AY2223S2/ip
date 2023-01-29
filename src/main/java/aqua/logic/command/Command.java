package aqua.logic.command;


/** Enumeration of commands. */
public enum Command {
    LIST(new ListCommand()),
    MARK(new MarkTaskCommand(true)),
    UNMARK(new MarkTaskCommand(false)),
    TODO(new AddToDoCommand()),
    DEADLINE(new AddDeadlineCommand()),
    EVENT(new AddEventCommand()),
    DELETE(new DeleteCommand()),
    FIND(new FilterCommand()),
    BYE(new ByeCommand());


    private final ServiceProvider serviceProvider;


    Command(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }


    /**
     * Returns the ServiceProvider of the command.
     *
     * @return the ServiceProvider of the command.
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
}
