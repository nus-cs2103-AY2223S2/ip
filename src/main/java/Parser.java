public class Parser {
    public enum CmdType{
        bye,
        list,
        mark,
        unmark,
        delete,
        todo,
        deadline,
        event,
    }
    public Parser(){

    }
    public boolean isByeCommand(String userInput){
        return userInput.replaceAll("\\s","").equals(CmdType.bye.name());
    }

    public boolean isListCommand(String userInput){
        return userInput.replaceAll("\\s", "").equals(CmdType.list.name());
    }

    public boolean isMarkCommand(String userInput){
        return (userInput.split(" ")[0]).equals(CmdType.mark.name());
    }

    public boolean isUnmarkCommand(String userInput){
        return (userInput.split(" ")[0]).equals(CmdType.unmark.name());
    }

    public boolean isDeleteCommand(String userInput){
        return userInput.split(" ")[0].equals(CmdType.delete.name());
    }

    public boolean isTodoCommand(String userInput){
        return userInput.split(" ")[0].equals(CmdType.todo.name());
    }

    public boolean isDeadlineCommand(String userInput){
        return userInput.split(" ")[0].equals(CmdType.deadline.name());
    }

    public boolean isEventCommand(String userInput){
        return userInput.split(" ")[0].equals(CmdType.event.name());
    }
}
