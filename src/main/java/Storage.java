import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a storage that load previous to do list and save current to do list.
 */

public class Storage {
    private Path path;
    private boolean doesDirectoryExist;

    public Storage() {
        this.path = Paths.get(".", "src", "main", "ToDoListCS2103.txt");
        this.doesDirectoryExist = java.nio.file.Files.exists(path);
    }

    public TodoList load() {
        try {
            File previousToDoList = new File(path.toString());
            if (doesDirectoryExist) {
                FileInputStream fis = new FileInputStream(previousToDoList);
                ObjectInputStream ois = new ObjectInputStream(fis);
                TodoList savedTodoList = (TodoList) ois.readObject();
                ois.close();
                fis.close();
                return savedTodoList;
            } else {
                previousToDoList.createNewFile();
                return new TodoList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new TodoList();
    }

    public void save(TodoList todoList) {
        try{
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(todoList);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
