package aqua.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


/** A utility class for file reading and writing functions. */
public class FileUtils {
    // magic number adapted from https://stackoverflow.com/a/35544601
    private static final int READ_LIMIT = 2097152;


    /**
     * Returns the contents in the file in the specified path. Contents are
     * decoded in the default charset.
     *
     * @param path - the path of the file to read
     * @return the contents in the file in the specified path
     * @throws IOException if an I/O error occurs
     */
    public static String readFile(Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (InputStream inputStream = getInputStream(path)) {
            byte[] arr = new byte[READ_LIMIT];
            while (inputStream.read(arr) > 0) {
                builder.append(new String(arr));
            }
        }
        return builder.toString();
    }


    /**
     * Returns the input stream of the file in the given path.
     *
     * @return the input stream of the file in the given path.
     * @throws IOException if an I/O error occurs.
     */
    public static BufferedInputStream getInputStream(Path path) throws IOException {
        return new BufferedInputStream(new FileInputStream(path.toFile()));
    }


    /**
     * Writes the given content to the file in the specified path. Content is
     * encoded in the default charset.
     *
     * @param path - the path of the file to write to
     * @param content - the contents to write
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
     * @return {@code true} if the directory exists after execution and
     *      {@code false} otherwise
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
     * @return the {@code Path} object of the specified String path
     */
    public static Path getFilePath(String path) {
        return Paths.get(path);
    }


    /**
     * Returns the URL of the specified String path of the file in the resource
     * folder.
     *
     * @param pathString - the String path to the file.
     * @return the URL of the specified String path of the file in the resource
     *      folder.
     * @throws FileNotFoundException if the file cannot be found.
     * @throws NullPointerException if {@code pathString} is {@code null}.
     */
    public static URL getResourceUrl(String pathString) throws FileNotFoundException {
        return Optional.ofNullable(FileUtils.class.getResource(pathString))
                .orElseThrow(() -> new FileNotFoundException(
                        String.format("Resource file %s not found", pathString)));
    }
}
