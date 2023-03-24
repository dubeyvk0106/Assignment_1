import java.io.*;
import java.util.Scanner;


class Management {
    String filename;

    Management(String filename) {
        this.filename = filename;
    }

    void addEmployee(Employee employee) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(employee.name + "," + employee.id + "," + employee.age + "," + employee.salary);

        pw.close();
        fw.close();
    }

    void updateEmployee(Scanner sc) throws IOException {
         System.out.println("Enter Id to update :");
         String id = sc.next();
         boolean empIDFound = false;
        File tempFile = new File("temp_employee.txt");
        File origFile = new File("vivek.txt");

        BufferedWriter pw = new BufferedWriter(new FileWriter(tempFile));
        BufferedReader br = new BufferedReader(new FileReader(origFile));

        String line;
        
        try{
            while((line = br.readLine()) !=null){

                String[] data = line.split(",");
                String empID =data[1];
                if(empID.equals(id)){
                    System.out.println("Found");
                    System.out.println("Enter new name");
                    String name = sc.next();
                    data[0] = name;
                    System.out.println("Enter new age");
                    int age = sc.nextInt();
                    System.out.println("Enter new salary");
                    double salary = sc.nextDouble();
                    empIDFound = true;
                    line = name +"  "+ id +"  " + age + " " + salary;
                }
                pw.write(line);
                pw.newLine();

            }
            pw.close();
            br.close();

            if(origFile.exists()){
                origFile.delete();
                
            }

            tempFile.renameTo(origFile);
            if(empIDFound){
                System.out.println("Updated successfully.");
            }
            else{
                System.out.println("Data not found");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error has occurred.File not found.");
            e.printStackTrace();
        }
    }

    Employee findEmployee(int id) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int employeeId = Integer.parseInt(parts[1]);
            if (employeeId == id) {
                String name = parts[0];
                int age = Integer.parseInt(parts[2]);
                double salary = Double.parseDouble(parts[3]);
                return new Employee(name, id, age, salary);
            }
        }

        br.close();
        fr.close();

        return null;
    }

    void printAllEmployees() throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int id = Integer.parseInt(parts[1]);
            int age = Integer.parseInt(parts[2]);
            double salary = Double.parseDouble(parts[3]);
            System.out.println(new Employee(name, id, age, salary));
        }

        br.close();
        fr.close();
    }

    void deleteEmployee(Scanner sc) throws IOException{

        System.out.println("Enter id to delete.");
        String id = sc.next();
        boolean empIDFound = false;
        File tempFile = new File("temp_employee.txt");
        File origFile = new File("vivek.txt");

        BufferedWriter pw = new BufferedWriter(new FileWriter(tempFile));
        BufferedReader br = new BufferedReader(new FileReader(origFile));

        String line;
        try{
            while((line = br.readLine()) !=null){

                String[] data = line.split(",");
                String empID =data[1];
                if(empID.equals(id)){
                    System.out.println("Found");
                    empIDFound = true;
                    continue;
                }
                pw.write(line);
                pw.newLine();


            }
            pw.close();
            br.close();

            if(origFile.exists()){
                origFile.delete();
                
            }

            tempFile.renameTo(origFile);
            if(empIDFound){
                System.out.println("Deleted successfully.");
            }
            else{
                System.out.println("Data not found");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error has occurred.File not found.");
            e.printStackTrace();
        }

    }
}
