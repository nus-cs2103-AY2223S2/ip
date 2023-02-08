package tasks;
import duke.tasks.Todos;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void test() {
        Todos todo = new Todos("Description here");
        System.out.println(todo);
        todo.mark();
        System.out.println(todo);

    }


}
