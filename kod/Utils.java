import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    
    //----------------------------------------------------------------
    public static boolean isValid(String argument) {

        Path path = Paths.get(argument);

        boolean result = false;

        if (Files.isDirectory(path) || Files.isRegularFile(path)) {
            result = true;
        }
        
        return result;
    }

    //----------------------------------------------------------------
    public static String startPageLink(Path path, Path startDirectoryPath) {
        
        Path relativePath = path.relativize(startDirectoryPath);

        String result = "";

        if (path.equals(startDirectoryPath)) {
            result = "./Index.html";
        }else{
            result = relativePath.toString() + "/Index.html";
        }

        return result;

    }
}
