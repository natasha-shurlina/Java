import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        boolean flagR = false;
        String dir = null;
        String filename = null;
        System.out.println(System.getProperty("user.dir"));

        for(int i =0; i < args.length; i++){
            if((args[i]).equals("-r") ) flagR = true;
            if((args[i]).equals("-d") && (args.length - 1 >= i + 1)) {  dir = args[i + 1];}
            if( i == (args.length -1)) filename = args[i];

        }

        if(dir == null) dir = System.getProperty("user.dir");

        List<String> results = new ArrayList<String>();

        Searcher.search(dir, filename, flagR, results);
        for(String el : results) System.out.println(el);
    }
}

class Searcher {


    public static List<String> search(String directory, String fileName, boolean r, List<String> results) {

        File dir = new File(directory);

        if (!dir.exists()) {
            System.out.println("Указанная дирректория не существует!!!");
            return null;
        }

        File[] fileArray = dir.listFiles();

        if (fileArray == null) {
            System.out.println("Папка пустая!!!");
            return null;
        }


        for (File file : fileArray) {

            if (file.isDirectory() && (r == true)) {
                    search(file.getPath(), fileName, true, results);
            }

            if (!file.isDirectory()){

                if((file.getName()).equals(fileName)) {

                    results.add(file.getAbsolutePath());

                }
            }
        }

        return results;
    }

}