import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class Save {
    public static ArrayList<Task> loadSave() {
        File file = new File("./save");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream("./save");
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                @SuppressWarnings("unchecked")
                ArrayList<Task> obj = (ArrayList<Task>) ois.readObject();
                return obj;
                            
            } catch (IOException e) {
                System.out.println("Wrong format object");  
            } catch (ClassNotFoundException e) {
                System.out.println("object can't be loaded");  
            }
            return new ArrayList<Task>();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("cant create new file");
            }
            return new ArrayList<Task>();
        }         
    }
    public static void makeSave(ArrayList<Task> tasks) {
        try (FileOutputStream fos = new FileOutputStream("./save");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(tasks); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}