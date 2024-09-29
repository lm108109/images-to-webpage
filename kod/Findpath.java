import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Findpath {

    // ----------------------------------------------------------------
    public static List<Path> listDirectoryPaths(Path path) throws IOException {

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path, 1)) { //stream olvas, ha a fajl mappa, akkor listaba rak + rendezes
            result = walk.filter(Files::isDirectory)
                    .collect(Collectors.toList());
        }

        Collections.sort(result);

        return result;
    }

    // ----------------------------------------------------------------
    public static List<Path> listDirectoryPathsRecursive(Path path) throws IOException {

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isDirectory)
                    .collect(Collectors.toList());
        }

        Collections.sort(result);

        return result;
    }

    // -----------------------------------------------------------------
    public static List<Path> listFilePaths(Path path) throws IOException {

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path, 1)) {
            result = walk
                    .filter(Files::isRegularFile) // is a file
                    .filter(p -> p.getFileName().toString().endsWith(".jpg")
                            || p.getFileName().toString().endsWith(".jpeg")
                            || p.getFileName().toString().endsWith(".gif"))
                    .collect(Collectors.toList());
        }

        Collections.sort(result);

        return result;
    }

    //-------------------------------------------------------------------
    public static List<Path> listFilePathsRecursive(Path path) throws IOException {

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile) // is a file
                    .filter(p -> p.getFileName().toString().endsWith(".jpg")
                            || p.getFileName().toString().endsWith(".jpeg")
                            || p.getFileName().toString().endsWith(".gif"))
                    .collect(Collectors.toList());
        }

        Collections.sort(result);

        return result;
    }

    //-------------------------------------------------------------------
    public static List<Path> listHTMLFilePaths(Path path) throws IOException {

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path, 1)) {
            result = walk
                    .filter(Files::isRegularFile) // is a file
                    .filter(p -> p.getFileName().toString().endsWith(".html"))
                    .collect(Collectors.toList());
        }

        Collections.sort(result);

        return result;
    }

    // ----------------------------------------------
    public static List<Path> listHTMLFilePathsRecursive(Path path) throws IOException {

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile) // is a file
                    .filter(p -> p.getFileName().toString().endsWith(".html"))
                    .collect(Collectors.toList());
        }

        Collections.sort(result);

        return result;
    }

}
