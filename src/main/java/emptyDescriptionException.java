public class emptyDescriptionException extends  DukeException{

    public String toString() {
        return  String.format("%s The description of a todo cannot be empty.", super.toString());
    }
}
