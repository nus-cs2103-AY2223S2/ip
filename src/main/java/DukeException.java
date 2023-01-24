import java.util.Arrays;

public class DukeException extends Exception {

    // local variables
    /*
        Error Codes ranges from 0 to n, where n will be specified further in the future

        0: description after task given is empty
        1: Wrong userInput
     */
    String message;

    //default constructor
    public DukeException(TypeOfTask task, int errorCode){
        super();
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
                    default:
                        this.message = "Something went wrong here";
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
                    default:
                        this.message = "Something went wrong here";
                }
                break;
            }
            case unmark: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    default:
                        this.message = "Something went wrong here";
                }
                break;
            }
            case delete: {
                switch(errorCode){
                    case 0:
                        this.message = String.format("Oops! The description of %s cannot be empty",task.toString());
                        break;
                    case 1:
                        this.message = "You entered wrongly. Please try again!";
                    default:
                        this.message = "Something went wrong here";
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

    @Override
    public String getMessage(){
        return this.message;
    }

}
