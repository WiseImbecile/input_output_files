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


                    //scnr.next();
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



       // return integer_sum;
    }

    //main
    public static void main(String[] args) {
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
    }
}