import java.util.ArrayList;

public class Main {





    public static void main(String[] args) {
        String PATH_TO_FILE ="C:\\Users\\alex-\\IdeaProjects\\fileChecker\\src\\testClasses\\newPackage\\BareBonessClass.java";
       String file= FileParser.loadFile(PATH_TO_FILE);
        System.out.println(file);
        String pack= DocumentDivision.recognizePackageDeclaration(file);
        System.out.println(pack);
        ArrayList<String> imports= DocumentDivision.recognizeImports(file);
        System.out.println(imports);
        String body= DocumentDivision.recognizeClassBody(file);
        System.out.println(body);



    }



}