package duke.exception;
import duke.data.TypeOfTask;

public class DukeException extends Exception {

    /*
        Error Codes ranges from 0 to n, where n will be specified further in the future
     */
    String message;
    TypeOfTask errorTask;
    int errorCode;

    //default constructor
    public DukeException(TypeOfTask task, int errorCode){
        super();
        this.errorCode = errorCode;
        this.errorTask = task;
        switch(task){
            case todo: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case deadline: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case event: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    case 1:
                        this.message = "Please check your inputs again for the days and time given";
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case list: {
                break;
            }
            case mark: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    case 1:
                        this.message = "Please check if your inputs are valid.";
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case unmark: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    case 1:
                        this.message = "Please check if your inputs are valid.";
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case delete: {
                switch(errorCode) {
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    case 1:
                        this.message = "You entered wrongly. Please try again!";
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case parser: {
                switch(errorCode) {
                    case 0:
                        this.message = "format of date given is not recognized";
                        break;
                    case 1:
                        this.message = "format of time given is not recognized";
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            case storage: {
                switch(errorCode) {
                    case 0: 
                        this.message = "Unable to retrieve data from file given. Please check if the file path is correct";
                        break;
                    case 1:
                        this.message = "Unable to save data to disk. Something went wrong";
                        break;
                    default:
                        this.message = "Something went wrong here";
                        break;
                }
                break;
            }
            default:
                this.message = "Oops! I don't understand what it means!";
                break;
        }
    }

    // for actions/commands that does not belong in the enum types
    public DukeException(){
        super();
        this.message = "Oops! I don't understand what it means!";
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public TypeOfTask getErrorTask() {
        return this.errorTask;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
