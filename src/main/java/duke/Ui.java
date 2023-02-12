package duke;
import java.util.List;
public class Ui {
    public Ui () {
        //UI does not need to store anything 
    } 
    public void showLoadingError() {
        System.out.println("error loading file");
    }
    public void line(int l) {
        System.out.print('\n');
        for (int i = 0; i < l + 15; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }
    
    public void showList(TaskList taskList) {
        taskList.showList();
    }
}
