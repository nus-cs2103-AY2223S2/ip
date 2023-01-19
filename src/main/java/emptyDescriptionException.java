public class emptyDescriptionException extends  DukeException{
    private String command;
    public emptyDescriptionException(String command) {
        this.command = command;
    }
    @Override
    public String toString() {
        return  String.format("%s The description of a %s cannot be empty.", super.toString(), command );
    }
}
