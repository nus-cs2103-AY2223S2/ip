package duncan;

import java.io.Serializable;
import java.util.ArrayList;

public class Ui implements Serializable{

    private ArrayList<String> statementList = new ArrayList<>();

    public Ui() {
    }

    public void addStatement(String input) {
        this.statementList.add(input) ;
    }

    public ArrayList<String> getStatements() {
        return statementList;
    }

    public void clearStatements() {
        statementList = new ArrayList<>();
    }

}
