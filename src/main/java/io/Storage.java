package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import util.Either;

public class Storage<T extends Serializable> {
    private final static String DIR = "./data"; 

    private final Class<T> type;
    private final String filepath;

    public static enum StorageError { 
        FILE_NOT_FOUND,
        IO_ERROR,
        CAST_ERROR
    }

    private Storage(Class<T> type,  String filepath) {
        this.type = type;
        this.filepath = filepath;
    }

    public static <T extends Serializable> Storage<T> of(Class<T> type, String filename) {
        String filepath = DIR + '/' + filename;
        return new Storage<>(type, filepath);
    }

    public Either<T, StorageError> load() {
        ObjectInputStream inputStream = null;
        
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filepath));
            try {
                Object output = inputStream.readObject();
                inputStream.close();
                if (type.isInstance(output)) {
                    return Either.left(type.cast(output));
                }
                return Either.right(StorageError.CAST_ERROR);
            } catch (ClassNotFoundException ex) {
                inputStream.close();
                return Either.right(StorageError.IO_ERROR);
            }
        } catch (FileNotFoundException ex) {
            return Either.right(StorageError.FILE_NOT_FOUND);
        } catch (IOException ex) {
            return Either.right(StorageError.IO_ERROR);
        }
    }

    public Either<String, StorageError> save(T object) {
        ObjectOutputStream outputStream = null;
        
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(this.filepath));
            outputStream.writeObject(object);
            outputStream.close();
            return Either.left("File saved successfully.");
        } catch (FileNotFoundException ex) {
            return Either.right(StorageError.FILE_NOT_FOUND); 
        } catch (IOException ex) {
            return Either.right(StorageError.IO_ERROR);
        }
    }
}
