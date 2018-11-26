import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        String nameOfFile,another;
        Path nameOfDirectory;
        try {
            nameOfFile=args[0];
            args[1]=args[1].replaceAll("\\\\","/");
            nameOfDirectory= Paths.get(args[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
            return;
        }
        File directory = new File(nameOfDirectory.toString());
        while(!directory.isDirectory()){
            System.out.println("Директория не верна, попробуйте еще раз.");

            another=in.nextLine();
            nameOfDirectory=Paths.get(another);
            directory=new File(nameOfDirectory.toString());
        }
        try {
         List<Path> answers=   Files.walk(nameOfDirectory)
                    .filter(Files::isRegularFile)
                    .filter(f->f.getFileName().toString().equals(nameOfFile))
                    .collect(Collectors.toList());

         if(answers.isEmpty()){
             System.out.println("Файл не найден.");
         }else{
             if(answers.size()>1){
                 System.out.println("Найдено несколько файлов с одинаковым именем: ");
                 answers.forEach(System.out::println);
             }else{
                 System.out.println("Найден один файл: ");
                 answers.forEach(System.out::println);
             }
         }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
