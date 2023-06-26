import java.util.ArrayList;
import java.util.Arrays;

public class DocumentDivision {
    //these functions assume the input has gone through the fileParser methods

    static String recognizePackageDeclaration(String input) {
        String[] contents = input.split(" ");
        if (contents[0].trim().equals("package")) {
            return contents[0] + " " + contents[1];
        } else return "";
    }

    static ArrayList<String> recognizeImports(String input) {
        ArrayList<String> imports = new ArrayList<>();
        String[] contents = input.split(" ");

        ArrayList<Integer> targetIndices = new ArrayList<>();

        for (int i = 0; i < contents.length; i++) {

            if (contents[i].equals("class")) {
                break;
            } else if (contents[i].equals("import")) {

                targetIndices.add(i);
            }
        }
        for (Integer targetIndex : targetIndices) {
            imports.add(contents[targetIndex] + " " + contents[targetIndex + 1]);
        }

        return imports;
    }


    public static String recognizeClassDeclaration(String input) {
        String[] contents = input.split(" ");
        int targetIndex = 0;
        for (int i = 0; i < contents.length; i++) {
            if (contents[i].equals("class")) {
                targetIndex = i;
                break;
            }
        }

        int beginIndex = 0;

        ArrayList<String> variables = new ArrayList<>();
        variables.addAll(Arrays.asList(Variables.ACCESS_VARIABLES));
        variables.addAll(Arrays.asList(Variables.NON_ACCES_VARIABLES));
        variables.add("class");

        for (int i = targetIndex; i > 0; i--) {
            if (!variables.contains(contents[i].trim())) {

                break;
            }
            beginIndex = i;
        }
        int endIndex = targetIndex + 1;

        StringBuilder sb = new StringBuilder();
        for (int i = beginIndex; i <= endIndex; i++) {
            sb.append(contents[i]).append(" ");
        }

        return sb.toString();
    }

    public static String recognizeClassBody(String input) {
        String[] contents = input.split(" ");
        int targetIndex = 0;

        for (int i = 0; i < contents.length; i++) {
            if (contents[i].trim().equals("{")) {
                targetIndex = i;
                break;
            }
        }
        int endIndex = 0;
        int bracket = 1;

        for (int i = targetIndex + 1; i <= contents.length; i++) {
            if (bracket == 0) {
                endIndex = i - 1;
                break;
            } else if (contents[i].trim().equals("{")) {
                bracket++;
            } else if (contents[i].trim().equals("}")) {
                bracket--;
            }

        }
        StringBuilder sb = new StringBuilder();

        for (int i = targetIndex; i <= endIndex; i++) {
            sb.append(contents[i]).append(" ");
        }
        return sb.toString();
    }
}
