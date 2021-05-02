import java.io.File;

public class Main {

    public static void main(String[] args) {

        boolean flag_r = false;
        boolean flag_d = false;
        String dir = null;
        String filename = null;

        //перебираем все элементы массива args(массив входных параметров)
        for(int i =0; i < args.length; i++){
            if((args[i]).equals("-r") ) flag_r = true;
            if((args[i]).equals("-r") ) flag_r = true;
            if((args[i]).equals("-d")){
                flag_d = true;
                dir = args[i + 1];
            }
            if( i == (args.length -1)) filename = args[i];

        }

        //если дирректория для поиска файла не была передана в виде параметра, установливаем значение корневой дирректории проекта
        if(dir == null) dir = System.getProperty("user.dir");


        // Выводим все параметры перед передачей их в функцию
//        System.out.println("flag_r: " + flag_r);
//        System.out.println("flag_d: " + flag_d);
//        System.out.println("dir: " + dir);
//        System.out.println("filename: " + filename);


        //вызываем статический метод поиска указанного файла, передавая емк параметры, получен. из командной строки
        System.out.println(Searcher.search(System.getProperty("user.dir"),"text3.txt",false));
    }
}

class Searcher {


    static boolean flag = false;

    public static String search(String directory, String fileName, boolean r) {

        //создаем объект клдасса File, в конструктор передаем имя дирректории, полученной из параметров командной строки
        File dir = new File(directory);

        // проверяем наличие этой дирректории
        if (dir.exists()) {

            // Получаем массив всех файлов содержащихся в созданной дирректории
            File[] fileArray = dir.listFiles();


            //проверяем, не пустая ли дирректория
            if (fileArray != null) {

                // проверяем значение параметра -r, отвечающего за поиск в поддиректриях. В данном случае в поддиректориях не ищем указанный файл
                if (r == false) {

                    // перебираем все файлы
                    for (File file : fileArray) {
                        // если перебираемый элемент является файлом(не дирректорией) приступаем к сравнению его имени с именем fileName
                        if (!file.isDirectory()){

                            if((file.getName()).equals((String)fileName)) {

                                //возвращаем абсолютный путь файла, если файл найден
                                return file.getAbsolutePath();
                            }
                        }
                    }

                    // возвращаем null, если дошли до этой строки. Т.е. если файл не был найден и не вызвался "return file.getAbsolutePath();"
                    return null;


                }
                // если флаг -r был задан ищем во всех поддиректориях указанной дирректории

                else {
                    for (File file : fileArray) {
                        // проверяем является ли дирректорией и проверяем, не был ли найден уже интересующий нас файл. Может так получиться, что файл будет найден в самой первой дирректории
                        //но при этом будут иметься и другие дирректории, чтобы не проходить по ним и не искать в них при уэе найденном файле, проверяем, не был ли найден файл
                        if (file.isDirectory() && flag == false) {
                            String result;

                            //вызываем рекурсивно метод search(), в качестве первого параметра передаем абсолютный путь той дирректории на которой остановились в цикле for each
                            if( (result  = search(file.getPath(), fileName, true)) != null) return result ;
                        }else{

                            // если имя файла совпало с искомым, возвращаем его абсолютный путь
                            if((file.getName()).equals((String)fileName)){
                                // устанавливаем флаг true
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
