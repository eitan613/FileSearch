import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;


public class FileSearch {
    private File file = new File("C:\\");
    private String fileName;
    private boolean found;
    private int fileNumber;


    public static void main(String[] args) {
        FileSearch fs = new FileSearch();
        fs.breadthFirstSearch();
        fs.depthFirstSearch();
    }

    private FileSearch(){
        fileName = JOptionPane.showInputDialog("Please enter the file name you want to search for.");
    }
    private void depthFirstSearch(){
        fileNumber = 1;
        Stack<File> filesToSearch = new Stack<>();
        File temp;
        ArrayList<String> filesFound = new ArrayList<>();
        for (File f: file.listFiles())
            filesToSearch.push(f);
        while (!filesToSearch.isEmpty())
        {
            temp = filesToSearch.pop();
            if(temp.isFile()){
                if (temp.toString().contains(fileName)){
                    found = true;
                    System.out.println("Depth first file number " + fileNumber + ": " + temp.getAbsolutePath());
                    filesFound.add(fileNumber++ + ": " + temp.getAbsolutePath());
                }
            }
            else if (temp.isDirectory() && temp.listFiles() != null){
                Collections.addAll(filesToSearch, temp.listFiles());
            }
        }
        try {
            Files.write(Paths.get(String.format("Path for file found depth first-%s-found at %s.txt",fileName,
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()))),filesFound,
                    StandardOpenOption.CREATE_NEW);
        }
        catch (IOException ignored){}
        System.out.println("---------------------\n");
        System.out.println("Completed depth first search.\n");
        System.out.println("---------------------");
        if (!found)
            System.out.println("Sorry. Your file does not exist.");

    }

    private void breadthFirstSearch(){
        fileNumber = 1;
        Queue<File> filesToSearch = new LinkedList<>();
        File temp;
        ArrayList<String> filesFound = new ArrayList<>();
        Collections.addAll(filesToSearch, file.listFiles());
        while (!filesToSearch.isEmpty())
        {
            temp = filesToSearch.poll();
            if(temp.isFile()){
                if (temp.toString().contains(fileName)){
                    found = true;
                    System.out.println("Breadth first file number " + fileNumber + ": " + temp.getAbsolutePath());
                    filesFound.add(fileNumber++ + ": " + temp.getAbsolutePath());
                }
            }
            else if (temp.isDirectory() && temp.listFiles() != null){
                Collections.addAll(filesToSearch, temp.listFiles());
            }
        }
        try {
            Files.write(Paths.get(String.format("Path for file found breadth first-%s-found at %s.txt",fileName,
                    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()))),filesFound,
                    StandardOpenOption.CREATE_NEW);
        }
        catch (IOException ignored){
        }
        System.out.println("---------------------\n");
        System.out.println("Completed breadth first search.\n");
        System.out.println("---------------------");
        if (!found)
            System.out.println("Sorry. Your file does not exist.");
        }
    }


