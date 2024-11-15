import java.util.*;
import java.io.*;


/**This program takes in an in.txt file and calculates the sum of every integer and the sum of every double
 * and creates and inserts each sum into it's own .txt file.
 * @author: Samuel Reynebeau
 * @date: 11/14/24
 */
public class Lab8{
    
    /**
     * takes in in.txt file & calculates int & double sum and inserts into own .txt files
     * @param file file object
     */
    public static void create_sum_file(File file){

        int integer_sum = 0;
        int int_num = 0;
        double double_num = 0.0;
        double double_sum = 0.0;

        //checking to make sure file will open
        try{
            file.canExecute();
        }
        catch(SecurityException s){
            System.out.println("File can not open!!");
        }
        
        //declaring outside of try block so initializing to null
        Scanner scnr = null;
        try{
            //Reading in file object
            scnr = new Scanner(file);
            
            //while scanner still has input 
            while(scnr.hasNext()){
                try{
                    //finding each integer in file
                    int_num = scnr.nextInt();
                    integer_sum += int_num;
                }
                //catches if not integer & checks for double
                catch(InputMismatchException e){
                    try{
                        double_num = scnr.nextDouble();
                        double_sum += double_num;

                    }
                    //if not double, then go to next line
                    catch(InputMismatchException d){
                        scnr.next();
                    }
                }
            }  
        }
        //handle the thrown exception
        catch(FileNotFoundException e){
            System.out.print("File is not found");

        }
        //close if file isn't null
        finally{
            if(scnr != null){
                scnr.close();
            }
        }

        //create int file
        File int_file_path = new File("./int_total.txt");
        PrintWriter create_file = null;
        //creating file with filepath using PrintWriter class, println is inputing sum
        try{
            create_file = new PrintWriter(int_file_path);
            create_file.println(integer_sum);
        }
        catch(FileNotFoundException f){
            System.out.print("file not found");

        }
        finally{
            if(create_file != null){
                create_file.close();
            }
        }

        //create double file, same process as int file created above ^^
        File double_file_path = new File("./double_total.txt");
        PrintWriter create_file_double = null;
        
        try{
            create_file_double = new PrintWriter(double_file_path);
            create_file_double.println(double_sum);
        }
        catch(FileNotFoundException g){
            System.out.print("file not found");

        }
        finally{
            if(create_file_double != null){
                create_file_double.close();
            }
        }
    }



    public static void java_brace_conversion(File file){

        int count = 0;
        String modified_array [];
        String preserve_array [];
        try{
            file.canExecute();
        }
        catch(SecurityException s){
            System.out.println("File can not open!!");
        }

        //scanner to null
        Scanner scnr = null; 
        
        //creating scanner object and reading file
        try{
            scnr = new Scanner(file);
            //getting size needed for array from the count
            while(scnr.hasNextLine()){
                scnr.nextLine();
                count++;
            }

        }
        
        catch(FileNotFoundException e){
            System.out.println("File is not Found!");
        }
        
        finally{
            if(scnr != null){
                scnr.close();
            }
        }


        //initializing array to size count
        preserve_array = new String[count];
        //new scanner
        Scanner scnr2 = null;

        try{
            //initializing scanner with file
            scnr2 = new Scanner(file);
            //reading file and putting into array to preserve
            while(scnr2.hasNextLine()){
                for(int i = 0; i < preserve_array.length; i++){
                    preserve_array[i] = scnr2.nextLine();
                }

            }
        }
        
        catch(FileNotFoundException a){
            System.out.print("File is not Found!");
        }

        finally{
            if(scnr2 != null){
                scnr2.close();
            }
        }
        //start of modifying array
        modified_array = new String[count];
        //opening 3rd scanner
        Scanner scnr3 = null;
        String curly_brace = "{";

        try{
            scnr3 = new Scanner(file);

            while(scnr3.hasNextLine()){
                for(int i = 0; i < modified_array.length; i++){
                    modified_array[i] = scnr3.nextLine();
                    if(modified_array[i].contains(curly_brace)){
                        //getting rid of any unwanted beginning spaces
                        modified_array[i] = " {";
                        //once curly brace is found, add it to previous element in array and set present to null
                        modified_array[i-1] += modified_array[i];
                        modified_array[i] = "";
                    }

                }


            }


        }

        catch(FileNotFoundException f){
            System.out.print("File not Found!");
        }

        finally{
            if(scnr3 != null){
                scnr3.close();
            }
        }

        //modified file creation**
        //File path
        File file_path = new File("ClassName.java");
        PrintWriter class_name = null;
        try{
            //Create new file
            class_name = new PrintWriter(file_path);
            //print array to file
            for(int i = 0; i < modified_array.length; i++){
                class_name.println(modified_array[i]);
            }
        }

        //handle that exception fool
        catch(FileNotFoundException b){
            System.out.print("File is not Found!");
        }
        finally{
            if(class_name != null){
                class_name.close();
            }
        }

        //preserved file creation**
        //File path
        File file_path2 = new File("ClassName_newlinebraces.java");
        PrintWriter preserve_file = null;
        try{
            //create new file
            preserve_file = new PrintWriter(file_path2);
            //print array to file
            for(String word : preserve_array){
                preserve_file.println(word);
            }
        }

        catch(FileNotFoundException z){
            System.out.print("File is not Found");
        }

        finally{
            if(preserve_file != null){
                preserve_file.close();
            }
        }
    }
    //main
    public static void main(String[] args) {
        //testing 1st program**
        File file = new File("in.txt");
        //calling method
        create_sum_file(file);

        //creating file objects for each file created
        File int_file = new File("int_total.txt");
        
        File double_file = new File("double_total.txt");
        
        //checking if each file exists after creation
        if(int_file.exists() && double_file.exists()){
            System.out.println("Eureka!! It works!! It's time to party!!");
        }

        //testing 2nd program**
        File file2 = new File("ClassName.java");

        java_brace_conversion(file2);



    }
}