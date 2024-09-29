import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HTMLcreator {

    public String currendDirIndex = "./Index.html";

    //----------------------------------------------------------------
    public static void createIndexFile(Path directoryPath, Path startDirectoryPath) throws IOException {

        List<Path> subDirectoryPaths = Findpath.listDirectoryPaths(directoryPath);

        List<String> relativeDirectoryString = new ArrayList<>();

        if (!(directoryPath.equals(startDirectoryPath))) {
            relativeDirectoryString.add("../Index.html");
        }

        for (Path path : subDirectoryPaths) {
            String s = "./" + path.getFileName().toString() + "/Index.html";
            relativeDirectoryString.add(s);
        }

        String startPageString = Utils.startPageLink(directoryPath, startDirectoryPath);

        List<Path> imageNamesPaths = Findpath.listFilePaths(directoryPath);

        List<Path> htmlPaths = Findpath.listHTMLFilePaths(directoryPath);

        List<String> relativeHtmlString = new ArrayList<>();

        for (Path path : htmlPaths) {
            String s = "./" + path.getFileName().toString();
            relativeHtmlString.add(s);
        }

        FileWriter fw;

        try {
            fw = new FileWriter(new File(directoryPath + "/Index.html"));

            BufferedWriter bw = new BufferedWriter(fw);

            // start page
            bw.write(String.format(
                    "<!DOCTYPE html>\n<html>\n\n<body>\n\n  <h1><a href=\"%s\">Start page</a></h1>\n\n  <hr>\n\n  <h2>Directories:</h2>\n\n  <ul>\n    \n  ",
                    startPageString));

            // directories
            // ha nem startdirectoryba csinalja
            if (!(directoryPath.equals(startDirectoryPath))) {

                bw.write(String.format("    <li><a href=\"%s\">&lt;&lt;</a></li>\n", relativeDirectoryString.get(0)));

                for (int i = 2; i < relativeDirectoryString.size(); i++) {

                    bw.write(String.format("    <li><a href=\"%s\">%s</a></li>\n", relativeDirectoryString.get(i),
                            subDirectoryPaths.get(i - 1).getFileName().toString()));

                }
            } else {

                for (int i = 1; i < relativeDirectoryString.size(); i++) {

                    bw.write(String.format("    <li><a href=\"%s\">%s</a></li>\n", relativeDirectoryString.get(i),
                            subDirectoryPaths.get(i).getFileName().toString()));

                }

            }

            bw.write("  </ul>\n  <hr>\n\n  <h3>Images:</h3>\n\n  <ul>\n");

            // images
            for (int i = 0; i < relativeHtmlString.size(); i++) {
                bw.write(String.format("    <li><a href=\"%s\">%s</a></li>\n", relativeHtmlString.get(i),
                        imageNamesPaths.get(i).getFileName().toString()));
            }

            bw.write("  </ul>\n\n</body>\n\n</html>");

            bw.close();

        } catch (IOException except) {
            except.printStackTrace();
        }

    }

    //----------------------------------------------------------------
    public static void createImageFile(Path directoryPath, Path startDirectoryPath) throws IOException {

        List<Path> imagePaths = Findpath.listFilePaths(directoryPath);

        if (imagePaths.size() == 0) {
            return;
        }

        List<String> imageNameString = new ArrayList<>();

        for (Path path : imagePaths) {
            String s = path.getFileName().toString();
            imageNameString.add(s);
        }

        List<String> htmlPathsString = new ArrayList<>();

        for (String string : imageNameString) {
            String fileNameWithHtml = string.replaceFirst("[.][^.]+$", ".html"); //html elérése, a vege .html
            String s = "./" + fileNameWithHtml;
            htmlPathsString.add(s);
        }

        // csak azért kell hogy le createlje a fáljt
        List<String> newFileName = new ArrayList<>();

        for (String string : imageNameString) {
            String fileNameWithHtml = string.replaceFirst("[.][^.]+$", ".html");
            String s = fileNameWithHtml;
            newFileName.add(s);
        }

        String startPageString = Utils.startPageLink(directoryPath, startDirectoryPath);

        FileWriter fw;

        for (int i = 0; i < htmlPathsString.size(); i++) {

            try {
                fw = new FileWriter(new File(directoryPath.toString() + File.separator + newFileName.get(i)));

                BufferedWriter bw = new BufferedWriter(fw);

                // start page
                bw.write(String.format(
                        "<!DOCTYPE html>\n<html>\n\n<body>\n\n    <h1><a href=\"%s\">Start page</a></h1>\n\n    <hr>\n\n",
                        startPageString));

                // ^^ to index.html

                bw.write(String.format("    <p><a href=\"%s\">^^</a></p>\n\n    <p>\n\n\n", "./Index.html"));

                if (i == 0) { //kattinthato-e

                    bw.write(String.format(
                            "        <span>&lt;&lt;</span>\n\n        <span id=\"imagename\">%s</span>\n\n",
                            imageNameString.get(i)));

                    bw.write(String.format("\n        <a href=\"%s\"><span>&gt;&gt;</span></a>\n    </p>\n\n",
                            htmlPathsString.get(i + 1)));

                    bw.write(
                            String.format("    <a href=\"%s\"><img src=\"%s\" alt=\"image\"></a>\n\n</body>\n\n</html>",
                                    htmlPathsString.get(i + 1), imageNameString.get(i)));

                    bw.close();
                } else if (i == htmlPathsString.size() - 1) {
                    bw.write(String.format(
                            "        <a href=\"%s\"><span>&lt;&lt;</span></a>\n\n        <span id=\"imagename\">%s</span>\n\n        <span>&gt;&gt;</span>\n    </p>\n\n    <img src=\"%s\" alt=\"image\">\n\n</body>\n\n</html>",
                            htmlPathsString.get(i - 1), imageNameString.get(i), imageNameString.get(i)));

                    bw.close();

                } else {

                    bw.write(String.format(
                            "        <a href=\"%s\"><span>&lt;&lt;</span></a>\n\n        <span id=\"imagename\">%s</span>\n\n        <a href=\"%s\"><span>&gt;&gt;</span></a>\n    </p>\n\n    <a href=\"%s\"><img src=\"%s\" alt=\"image\"></a>\n\n</body>\n\n</html>",
                            htmlPathsString.get(i - 1), imageNameString.get(i), htmlPathsString.get(i + 1),
                            htmlPathsString.get(i + 1), imageNameString.get(i)));

                    bw.close();
                }

            } catch (IOException except) {
                except.printStackTrace();
            }

        }

    }
}
