package kude;

import kude.models.ItemList;

import java.beans.XMLDecoder;
import java.io.*;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ItemList readItems() throws IOException, ClassNotFoundException {
        var file = new File(path);
        if (file.createNewFile()) {
            writeItems(new ItemList());
        }
        var fis = new FileInputStream(file);
        var ois = new ObjectInputStream(fis);
        // restrict to only models
        var filter = ObjectInputFilter.Config.createFilter("kude.models.*;");
        ois.setObjectInputFilter(filter);
        var items = (ItemList)ois.readObject();
        ois.close();
        fis.close();
        return items;
    }

    public void writeItems(ItemList list) throws IOException {
        var file = new File(path);
        file.createNewFile();
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    }
}
