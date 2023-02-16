package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import util.Either;

/**
 * Manages storing of objects.
 *
 * @param <T> Object to be stored.
 * @see Serializable
 */
public class Storage<T extends Serializable> {
    private static final String DIR = "./data";

    private final Class<T> type;
    private final String filepath;

    private Storage(Class<T> type, String filepath) {
        this.type = type;
        this.filepath = filepath;

        // Checks if directory exists and if not, creates it.
        File directory = new File(DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

    /**
     * @param <T>      Type of object to store.
     * @param type     class object for type parameter.
     * @param filename filename for storage file.
     * @return Storage object.
     */
    public static <T extends Serializable> Storage<T> of(Class<T> type, String filename) {
        String filepath = DIR + '/' + filename;
        return new Storage<>(type, filepath);
    }

    /**
     * @return Either the object or a StorageError.
     * @see Either
     * @see StorageError
     */
    public Either<T, StorageError> load() {
        ObjectInputStream inputStream;

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

    /**
     * Saves object into the file.
     *
     * @param object Object to be saved.
     * @return Either a success message or a StorageError.
     * @see Either
     * @see StorageError
     */
    public Either<String, StorageError> save(T object) {
        ObjectOutputStream outputStream;

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

    /**
     * Errors that could occur during storage
     */
    public enum StorageError {
        FILE_NOT_FOUND,
        IO_ERROR,
        CAST_ERROR
    }
}
