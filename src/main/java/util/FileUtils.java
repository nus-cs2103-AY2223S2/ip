package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {
    private static final int READ_LIMIT = 2097152;


    /**
     * Returns the contents in the file in the specified path. Contents are
     * decoded in the default charset.
     * 
     * @param path - the path of the file to read
     * 
     * @return the contents in the file in the specified path
     * 
     * @throws IOException if an I/O error occurs
     */
    public static String readFile(Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            byte[] arr = new byte[READ_LIMIT];
            while (inputStream.read(arr) > 0) {
                builder.append(new String(arr));
            }
        }
        return builder.toString();
    }


    /**
     * Writes the given content to the file in the specified path. Content is
     * encoded in the default charset.
     * 
     * @param path - the path of the file to write to
     * @param content - the contents to write
     * 
     * @throws IOException if an I/O error occurs
     */
    public static void writeFile(Path path, String content) throws IOException {
        try (OutputStream outStream = new BufferedOutputStream(new FileOutputStream(path.toFile()))) {
            outStream.write(content.getBytes());
        }
    }


    /**
     * Creates the directory of the specified path including any parent
     * directories.
     * 
     * @param path - the path of the directory to create
     * 
     * @return {@code true} if the directory exists after execution and
     *  {@code false} otherwise
     */
    public static boolean mkdirs(Path path) {
        if (path.toFile().exists()) {
            return true;
        }
        return path.toFile().mkdirs();
    }


    /**
     * Converts the given String path to a {@code Path}.
     * 
     * @param path - the String path to convert
     * 
     * @return the {@code Path} object of the specified String path
     */
    public static Path getFilePath(String path) {
        return Paths.get(path);
    }
}
