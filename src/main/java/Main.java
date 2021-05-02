import java.io.File;

public class Main {

    public static void main(String[] args) {

        boolean flag_r = false;
        boolean flag_d = false;
        String dir = null;
        String filename = null;

        for(int i =0; i < args.length; i++){
            if((args[i]).equals("-r") ) flag_r = true;
            if((args[i]).equals("-r") ) flag_r = true;
            if((args[i]).equals("-d")){
                flag_d = true;
                dir = args[i + 1];
            }
            if( i == (args.length -1)) filename = args[i];

        }

        if(dir == null) dir = System.getProperty("user.dir");


        // Выводим все параметры перед передачей их в функцию
//        System.out.println("flag_r: " + flag_r);
//        System.out.println("flag_d: " + flag_d);
//        System.out.println("dir: " + dir);
//        System.out.println("filename: " + filename);


        System.out.println(Searcher.search(System.getProperty("user.dir"),"text3.txt",false));
    }
}

class Searcher {


    static boolean flag = false;

    public static String search(String directory, String fileName, boolean r) {

        File dir = new File(directory);

        if (dir.exists()) {

            File[] fileArray = dir.listFiles();


            if (fileArray != null) {

                if (r == false) {

                    for (File file : fileArray) {

                        if (!file.isDirectory()){

                            if((file.getName()).equals((String)fileName)) {

                                return file.getAbsolutePath();
                            }
                        }
                    }

                    return null;


                }
                // если флаг -r был задан ищем во всех поддиректориях указанной дирректории

                else {
                    for (File file : fileArray) {
                        if (file.isDirectory() && flag == false) {
                            String result;


                            if( (result  = search(file.getPath(), fileName, true)) != null) return result ;
                        }else{

                            if((file.getName()).equals((String)fileName)){

                                flag = true;
                                return file.getAbsolutePath();
                            }
                        }
                    }


                }
            } else System.out.println("Папка пустая");
        }else {
            System.out.println("Указанная дирректория не существует");
            return null;
        }
        return null;
    }
}
