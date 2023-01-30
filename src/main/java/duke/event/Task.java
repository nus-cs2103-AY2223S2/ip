package duke.event;
import duke.data.TypeOfTask;
import duke.parser.Parser;
import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected TypeOfTask type;
    protected Parser parser;

    public Task(String description,TypeOfTask type){
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.parser = new Parser();
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return this.description;
    }

    public String getTypeOfTask(){
        switch(this.type){
            case deadline:
                return "D";
            case event:
                return "E";
            case todo:
                return "T";
            default:
                return null;
        }
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmarkAsDone(){
        this.isDone = false;
    }

}
