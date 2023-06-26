import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {
    ArrayList<String>lines;

    public FileParser(){
        lines=new ArrayList<>();
    }


    public static String addSpaceBefore(String str, char... characters) {
        String res = str;
        for (char c : characters) {
            String regex = "";
            regex = String.valueOf(c);
            res = res.replace(regex, " " + c);
        }
        return res;
    }

    public static String addSpaceAfter(String line, char... characters) {
        String res = line;
        for (char c : characters) {
            String regex = "";
            regex = String.valueOf(c);
            res = res.replace(regex, c + " ");
        }
        return res;
    }

    static String removeUnnecessarySpaces(String in) {
        return in.trim().replaceAll("\\s{2,}", " ");
    }

    static String removeCommentFromLine(String line) {
        return line.replaceAll("//.*", " ");
    }


    public static String loadFile(String path) {
        StringBuilder sb = new StringBuilder();

        BufferedReader buffer;
        FileReader reader;
        try {
            reader = new FileReader(path);
            buffer = new BufferedReader(reader);

            String line = "";

            while ((line = buffer.readLine()) != null) {

                if (line.length() < 1) {continue;}

                line = removeCommentFromLine(line);
                line = removeUnnecessarySpaces(line);
                line = addSpaceBefore(line, '{', '}', '[', ']');
                line = addSpaceAfter(line, '{', '}', '[', ']', ';');

                //remove all spaces around "."
                line = line.replaceAll("\\s*\\.\\s*", ".");
                sb.append(line);


            }
            reader.close();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
