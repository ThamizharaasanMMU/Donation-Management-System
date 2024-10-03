package distsystem;

import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.security.auth.callback.ChoiceCallback;

class Position{
    public static int pos;

    Position() {}

    Position(int pos) {
        Position.pos = pos;
    }

    public static int getPosition(){
        return pos;
    }
}



class donorReg{
    String simple;

    donorReg(){}

    donorReg(String simple ) throws IOException{
        this.simple = simple;
        //ArrayList<Donor> d1 = readDonorFromFile();
        ArrayList<Donor> d1 = new ArrayList<>();

        System.out.println ();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please register an account");
            System.out.print("Name = " );
            String name = input.nextLine();
            System.out.print("Phone Number = ");
            String Pnumber = input.nextLine();
            System.out.print("Password = ");
            String password = input.nextLine();
        
            List<String> dlines = Files.readAllLines(Paths.get("distsystem","donors.csv"));

            if (dlines.get(0).equals("")){
                d1.add(new Donor(name.toUpperCase(), password, Pnumber)); 

                

            }
            else if (!dlines.get(0).equals("")) {
                d1 = readDonorFromFile();
                d1.add(new Donor(name.toUpperCase(), password, Pnumber)); 
                saveDonorToFile (d1);
                

               
                
            }

            

            



            System.out.println("Your Account has been successfully created !!!");
            System.out.println("Use your name and password for the login system");
            System.out.println("You will be redirected to main menu");
            
            System.out.println("");
            System.out.println("");
            new mainMenu("");

    }

    private static ArrayList<Donor> readDonorFromFile() throws IOException {
        ArrayList<Donor> d1 = new ArrayList<>();

        // read donors.csv into a list of lines.
        List<String> lines = Files.readAllLines(Paths.get("distsystem","donors.csv"));
        for (int i = 0; i < lines.size(); i++) {
            // split a line by commas
            String[] items = lines.get(i).split(",");
            // items[0] is id, items[1] is name

            d1.add (new Donor(items[0], items[1], items[2]));
        }
        return d1;
    }

    private static void saveDonorToFile(ArrayList<Donor> d1) throws IOException 
    {
        // read donors.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < d1.size(); i++)
            sb.append (d1.get(i).toCSVString() + "\n");
        Files.write(Paths.get("distsystem","donors.csv"), sb.toString().getBytes());
    }


}


class ngoReg{
    String simple2;

    ngoReg(){}

    ngoReg(String simple2 ) throws IOException{
        this.simple2 = simple2;
        ArrayList<nonGov> d2 = readNgoFromFile();

        System.out.println ();
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please register an account");
            System.out.print("Name = " );
            String name = input.nextLine();
            System.out.print("Password = ");
            String password = input.nextLine();
            System.out.print("Manpower Count = ");
            int manPower = input.nextInt();
            

            d2.add(new nonGov(name.toUpperCase(), password, manPower)); 



            System.out.println("Your Account has been successfully created !!!");
            System.out.println("Use your name and password for the login system");
            System.out.println("You will be redirected to main menu");
            saveNonGovToFile (d2);
            System.out.println("");
            System.out.println("");
            new mainMenu("");

    }

    private static ArrayList<nonGov> readNgoFromFile() throws IOException {
        ArrayList<nonGov> d2 = new ArrayList<>();

        // read ngo.csv into a list of lines.
        List<String> lines2 = Files.readAllLines(Paths.get("distsystem","ngo.csv"));
        for (int i = 0; i < lines2.size(); i++) {
            // split a line by commas
            String[] items = lines2.get(i).split(",");
            // items[0] is id, items[1] is name
            int manPower = Integer.parseInt(items[2]);

            d2.add (new nonGov(items[0], items[1], manPower));
        }
        return d2;
    }

    private static void saveNonGovToFile(ArrayList<nonGov> d2) throws IOException 
    {
        // read donors.csv into a list of lines.
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < d2.size(); i++)
            sb2.append (d2.get(i).toCSVString() + "\n");
        Files.write(Paths.get("distsystem","ngo.csv"), sb2.toString().getBytes());
    }


}

class donorLogin{
    String simple2;
    public int store = -1;
    
    donorLogin(){}

    donorLogin(String simple2) throws IOException{
        this.simple2 = simple2;
        ArrayList<String> logDonorName = new ArrayList<>();
        ArrayList<String> logDonorPass = new ArrayList<>();
        //ArrayList<Integer> store = new ArrayList<>();
        //int store = -1;
        // read students.csv into a list of lines.
        List<String> lines = Files.readAllLines(Paths.get("distsystem","donors.csv"));
        for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");
            // items[0] is id, items[1] is name
            //System.out.println(items[2]); // testing
            logDonorName.add(items[0]);
            logDonorPass.add(items[1]);

            
        }
        int count = 0;

        while (count < 3){
            System.out.println(" ");
            System.out.println("Attempt #" + (count + 1) + " out of 3");
            System.out.println(" ");
            System.out.println("Login : Please enter your name and password");
            Scanner input = new Scanner(System.in);
            System.out.print("Name = " );
            String donorName = input.nextLine();   
            System.out.print("Password = ");
            String donorPass = input.nextLine();
        

        
            if (logDonorName.indexOf(donorName.toUpperCase()) == -1){
                count++;
                System.out.println("ACCESS DENIED");
                System.out.println("INVALID USERNAME. TRY AGAIN");

            }
            else if (logDonorName.indexOf(donorName.toUpperCase()) != -1){
                store = (logDonorName.indexOf(donorName.toUpperCase()));
                if (logDonorPass.indexOf(donorPass) == store){
                    count = 4;
                    System.out.println(" ");
                    System.out.println("ACCESS GRANTED. WELCOME " + donorName.toUpperCase());
                    new Position(store);
                    //loginpage()
                    //add loginpage here
                    new donorHome("");
                    

                }
                else if (logDonorPass.indexOf(donorPass) != store){
                    count++;
                    System.out.println("ACCESS DENIED");
                    System.out.println("INVALID PASSWORD. TRY AGAIN");

                }

            }
            else{
                System.out.println("ACCESS DENIED");
                System.out.println("INVALID USERNAME/PASSWORD. TRY AGAIN");
                count++;


            }
        }

            System.out.println("You were given attempts to login.");
            System.out.println("You failed to login using correct username and password. ");
            System.out.println("You will be redirected to main menu");
            System.out.println(" ");
            System.out.println(" ");
            new mainMenu("");
            


    
    }

}


class ngoLogin{
    String simple3;
    ngoLogin(){}

    ngoLogin(String simple3) throws IOException{
        this.simple3 = simple3;
        ArrayList<String> logNgoName = new ArrayList<>();
        ArrayList<String> logNgoPass = new ArrayList<>();
        //ArrayList<Integer> store = new ArrayList<>();
        int store = -1;

        // read students.csv into a list of lines.
        List<String> lines = Files.readAllLines(Paths.get("distsystem","ngo.csv"));
        for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");
            // items[0] is id, items[1] is name
            //System.out.println(items[2]); // testing
            logNgoName.add(items[0]);
            logNgoPass.add(items[1]);

            
        }
        int count = 0;

        while (count < 3){
            System.out.println(" ");
            System.out.println("Attempt #" + (count + 1) + " out of 3");
            System.out.println(" ");
            System.out.println("Login : Please enter your name and password");
            Scanner input = new Scanner(System.in);
            System.out.print("Name = " );
            String ngoName = input.nextLine();   
            System.out.print("Password = ");
            String ngoPass = input.nextLine();
        

        
            if (logNgoName.indexOf(ngoName.toUpperCase()) == -1){
                count++;
                System.out.println("ACCESS DENIED");
                System.out.println("INVALID USERNAME. TRY AGAIN");

            }
            else if (logNgoName.indexOf(ngoName.toUpperCase()) != -1){
                store = (logNgoName.indexOf(ngoName.toUpperCase()));
                if (logNgoPass.indexOf(ngoPass) == store){
                    count = 4;
                    System.out.println(" ");
                    System.out.println("ACCESS GRANTED. WELCOME " + ngoName.toUpperCase());
                    new Position(store);
                    //loginpage()
                    new nonGovHome("");
                    

                }
                else if (logNgoPass.indexOf(ngoPass) != store){
                    count++;
                    System.out.println("ACCESS DENIED");
                    System.out.println("INVALID PASSWORD. TRY AGAIN");

                }

            }
            else{
                System.out.println("ACCESS DENIED");
                System.out.println("INVALID USERNAME/PASSWORD. TRY AGAIN");
                count++;


            }
        }

            System.out.println("You were given 3 attempts to login.");
            System.out.println("You failed to login using correct username and password. ");
            System.out.println("You will be redirected to main menu");
            new mainMenu("");

    }



}

//admini login
class admLogin{
    String simple3;
    admLogin(){}

    admLogin(String simple3) throws IOException{
        this.simple3 = simple3;
        ArrayList<String> logAdmName = new ArrayList<>();
        ArrayList<String> logAdmPass = new ArrayList<>();


        //ArrayList<Integer> store = new ArrayList<>();


        // read students.csv into a list of lines.
        List<String> lines = Files.readAllLines(Paths.get("distsystem","admin.csv"));
        for (int i = 0; i < lines.size(); i++) {
            // split a line by comma
            String[] items = lines.get(i).split(",");
            // items[0] is id, items[1] is name
            //System.out.println(items[2]); // testing
            logAdmName.add(items[0]);
            logAdmPass.add(items[1]);

            
        }
        int count = 0;

        while (count < 3){
            System.out.println(" ");
            System.out.println("Attempt #" + (count + 1) + " out of 3");
            System.out.println(" ");
            System.out.println("Login : Please enter your name and password");
            Scanner input = new Scanner(System.in);
            System.out.print("Name = " );
            String admName = input.nextLine();   
            System.out.print("Password = ");
            String admPass = input.nextLine();



        

        
            if (logAdmName.indexOf(admName.toUpperCase()) == -1 || logAdmPass.indexOf(admPass) == -1) {
                count++;
                System.out.println("ACCESS DENIED");
                System.out.println("INVALID USERNAME. TRY AGAIN");

            }
            else if (logAdmName.indexOf(admName.toUpperCase()) == 0 && logAdmPass.indexOf(admPass) == 0){
                count = 4;
                System.out.println(" ");
                System.out.println("ACCESS GRANTED. WELCOME " + admName.toUpperCase());

                //adminpage()
                new adminHome("");
                
                    

                }


            }

        

            System.out.println("You were given 3 attempts to login.");
            System.out.println("You failed to login using correct username and password. ");
            System.out.println("You will be redirected to main menu");
            new mainMenu("");

    }



}















class mainMenu{
    String empty;

    mainMenu(){}

    mainMenu(String empty) throws IOException {
        this.empty = empty;
        Scanner input = new Scanner(System.in);




        System.out.println("----------MAIN MENU----------");
        System.out.println("| 1. Donor registration     |");
        System.out.println("| 2. NGO registration       |");
        System.out.println("| 3. Donor login            |");
        System.out.println("| 4. NGO login              |");
        System.out.println("| 5. Distribution Center    |");
        System.out.println("| 6. Exit                   |");
        System.out.println("|___________________________|");




        System.out.print("Enter your choice : ");
        try{

            int choice = input.nextInt();


            while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
                System.out.println("ERROR : INVALID INPUT");
                System.out.println("Choice number must be in range 1-5. Try again.");
                System.out.print("Enter your choice : ");

                choice = input.nextInt();
                
            }

            if (choice == 1){
                new donorReg("");
            }
            else if (choice == 2){
                new ngoReg("");
            }
            else if (choice == 3){
                new donorLogin(" ");
            }
            else if (choice == 4){
                new ngoLogin("");
            }
            else if (choice == 5) {
                new admLogin("");
            }
            else if (choice == 6){
                System.out.println("GOODBYE !!");
                
                System.exit(1);
                
            }
        }catch(InputMismatchException ex){
            System.out.println("ERROR : Choice number must be a positive number");

        }



    }


}

class donateAids{
    public int dPos;   // obtain donor's position from donors.csv



    donateAids(){}

    donateAids(int dPos ) throws IOException{
        this.dPos = dPos;
        //ArrayList<DonorAids> donor = readDonorAidsFromFile();
        ArrayList<DonorAids> donor = new ArrayList<>();

        System.out.println ();
        
        Scanner input = new Scanner(System.in);

        ArrayList<String> donorsName = new ArrayList<>();
        ArrayList<String> donorsPhoneNum = new ArrayList<>();

        List<String> dlines = Files.readAllLines(Paths.get("distsystem","donors.csv"));
        if (dlines.size() > 0) {
            for (int i = 0; i < dlines.size(); i++) {
                // split a line by comma
                String[] items = dlines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorsName.add(items[0]);
                donorsPhoneNum.add(items[2]);
                //donorAids.add(items[3]);
                //System.out.println(items[2]); // testing
                //logDonorName.add(items[0]);
                // logDonorPass.add(items[1]);

                

                
            }
        }
        else{
            System.out.println("the file is empty");
        }
        
        System.out.println("Enter the aids and quantity of the aids to be donated");
            System.out.print("Aids = " );
            String aids = input.nextLine();
            System.out.print("Aids Quantity = ");
            int aidsQuantity = input.nextInt();

            List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
            //System.out.println(lines.get(0));
            if (lines.get(0).equals("")){
                donor.add(new DonorAids(donorsName.get(dPos), donorsPhoneNum.get(dPos), aids.toUpperCase(), aidsQuantity, "-", "-")); 

            }
            else if (!lines.get(0).equals("")) {
                donor = readDonorAidsFromFile();
                donor.add(new DonorAids(donorsName.get(dPos), donorsPhoneNum.get(dPos), aids.toUpperCase(), aidsQuantity, "-", "-")); 
                
            }

            //donor = readDonorAidsFromFile();
            //donor.add(new DonorAids(donorsName.get(dPos), donorsPhoneNum.get(dPos), aids.toUpperCase(), aidsQuantity, "-", "-")); 

            

            



            System.out.println("Your donation item have been listed !!!");
            // System.out.println("Use your name and password for the login system");
            System.out.println("You will be redirected to homepage");
            saveDonorAidsToFile (donor);
            System.out.println("");
            System.out.println("");
            new donorHome("");

    }

    private static ArrayList<DonorAids> readDonorAidsFromFile() throws IOException {
        ArrayList<DonorAids> donor = new ArrayList<>();

        // read donors.csv into a list of lines.
        List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
        for (int i = 0; i < lines.size(); i++) {
            // split a line by commas
            String[] items = lines.get(i).split(",");
            // items[0] is id, items[1] is name
            int aidsQuantity = Integer.parseInt(items[3]);

            donor.add (new DonorAids(items[0], items[1], items[2], aidsQuantity, items[4], items[5]));
        }
        return donor;
    }

    private static void saveDonorAidsToFile(ArrayList<DonorAids> donor) throws IOException 
    {
        // read donors.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < donor.size(); i++)
            sb.append (donor.get(i).toCSVString() + "\n");
        Files.write(Paths.get("distsystem","donation.csv"), sb.toString().getBytes());
    }


}

//for aidsRequired and DonateAids, I used the same class called DonorAids 
//because the parameter in DonorAids is used to write donation.csv and aidsarequired.csv


class aidsRequired{
    public int nPos;   // obtain donor's position from donors.csv



    aidsRequired () {}

    aidsRequired(int nPos ) throws IOException{
        this.nPos = nPos;
        //ArrayList<DonorAids> donor = readDonorAidsFromFile();
        ArrayList<DonorAids> nGovDet = new ArrayList<>();

        System.out.println ();
        
        Scanner input = new Scanner(System.in);

        ArrayList<String> ngoName = new ArrayList<>();
        ArrayList<String> ngoManpower = new ArrayList<>();

        List<String> dlines = Files.readAllLines(Paths.get("distsystem","ngo.csv"));
        if (dlines.size() > 0) {
            for (int i = 0; i < dlines.size(); i++) {
                // split a line by comma
                String[] items = dlines.get(i).split(",");
                // items[0] is id, items[1] is name
                ngoName.add(items[0]);
                ngoManpower.add(items[2]);
                //donorAids.add(items[3]);
                //System.out.println(items[2]); // testing
                //logDonorName.add(items[0]);
                // logDonorPass.add(items[1]);

                

                
            }
        }
        else{
            System.out.println("the file is empty");
        }
        
        System.out.println("Enter the aids and quantity of the aids required.");
            System.out.print("Aids = " );
            String aids = input.nextLine();
            System.out.print("Aids Quantity = ");
            int aidsQuantity = input.nextInt();

            List<String> lines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
            if (lines.get(0).equals("")){
                nGovDet.add(new DonorAids("-", "-", aids.toUpperCase(), aidsQuantity, ngoName.get(nPos), ngoManpower.get(nPos))); 

            }
            else if (!lines.get(0).equals("")) {
                nGovDet = readDonorAidsFromFile();
                nGovDet.add(new DonorAids("-", "-", aids.toUpperCase(), aidsQuantity, ngoName.get(nPos), ngoManpower.get(nPos))); 
                
            }

            



            System.out.println("Your required aids have been listed !!!");
            // System.out.println("Use your name and password for the login system");
            System.out.println("You will be redirected to homepage");
            saveDonorAidsToFile (nGovDet);
            // System.out.println(nGovDet);
            System.out.println("");
            System.out.println("");
            new nonGovHome("");

    }

    private static ArrayList<DonorAids> readDonorAidsFromFile() throws IOException {
        ArrayList<DonorAids> nGovDet = new ArrayList<>();

        // read donors.csv into a list of lines.
        List<String> lines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
        for (int i = 0; i < lines.size(); i++) {
            // split a line by commas
            String[] items = lines.get(i).split(",");
            // items[0] is id, items[1] is name
            int aidsQuantity = Integer.parseInt(items[3]);

            nGovDet.add (new DonorAids(items[0], items[1], items[2], aidsQuantity, items[4], items[5]));
        }
        return nGovDet;
    }

    private static void saveDonorAidsToFile(ArrayList<DonorAids> nGovDet) throws IOException 
    {
        // read donors.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nGovDet.size(); i++)
            sb.append(nGovDet.get(i).toCSVString() + "\n");
        Files.write(Paths.get("distsystem","aidsRequired.csv"), sb.toString().getBytes());
    }


}
//donationtable for donors
class donationTable{
    public int dPos;
    donationTable() {}

    donationTable(int dPos) throws IOException {
        this.dPos = dPos;

        ArrayList<String> dName = new ArrayList<>(); //from donors.csv
        
        ArrayList<String> donorName = new ArrayList<>();  // from donation.csv
        ArrayList<String> donorPhone = new ArrayList<>();
        ArrayList<String> donorAid = new ArrayList<>();
        ArrayList<Integer> aidQuantity = new ArrayList<>();
        ArrayList<String> nGoName = new ArrayList<>();
        ArrayList<String> nGoMan = new ArrayList<>();
        List<String> dlines = Files.readAllLines(Paths.get("distsystem", "donors.csv"));
        if (dlines.size() > 0) {
            for (int i = 0; i < dlines.size(); i++) {
                String[] items = dlines.get(i).split(",");
                dName.add(items[0]);



            }
        }



        List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                
                // split a line by comma
                String[] items = lines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorName.add(items[0]);
                int count = Integer.parseInt(items[3]);
                //donorDet.add(new DonorAids(items[0], items[1], items[2], count, "-", "-"));
                donorPhone.add(items[1]);
                donorAid.add(items[2]);
                aidQuantity.add(count);
                nGoName.add(items[4]);
                nGoMan.add(items[5]);
                //System.out.println(items[2]); // testing
                //logDonorName.add(items[0]);
                // logDonorPass.add(items[1]);

                

                
            }
        }
        else{
            System.out.println("the file is empty");
        }
        System.out.printf("%-20s%-20s%-20s%-25s%-25s%-25s \n", "Donor", "Phone", "Aids", "Quantity", "  NGO NAME", "Manpower Count");
        //System.out.println(lines.get(0));
        //System.out.println(donorAids.size());
        for (int j = 0; j < donorName.size(); j++) {
            if (donorName.get(j).equals(dName.get(dPos))) {
                //System.out.println(donorDet.get(j));
                System.out.printf("%-20s%-20s%-25s%-25d%-25s%-25s \n",donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(j), nGoMan.get(j) );
                
            }

        }

        //after view the list, the user automatically will go to homepage
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        new donorHome("");



    }

    
}



class nGodonationTable{
    public int dPos;
    nGodonationTable() {}

    nGodonationTable(int dPos) throws IOException {
        this.dPos = dPos;

        ArrayList<String> dName = new ArrayList<>(); //from ngo.csv
        
        ArrayList<String> donorName = new ArrayList<>();  // from aidsRequired.csv
        ArrayList<String> donorPhone = new ArrayList<>();
        ArrayList<String> donorAid = new ArrayList<>();
        ArrayList<Integer> aidQuantity = new ArrayList<>();
        ArrayList<String> nGoName = new ArrayList<>();
        ArrayList<String> nGoMan = new ArrayList<>();
        List<String> dlines = Files.readAllLines(Paths.get("distsystem", "ngo.csv"));
        if (dlines.size() > 0) {
            for (int i = 0; i < dlines.size(); i++) {
                String[] items = dlines.get(i).split(",");
                dName.add(items[0]);



            }
        }



        List<String> lines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                
                // split a line by comma
                String[] items = lines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorName.add(items[0]);
                int count = Integer.parseInt(items[3]);
                //donorDet.add(new DonorAids(items[0], items[1], items[2], count, "-", "-"));
                donorPhone.add(items[1]);
                donorAid.add(items[2]);
                aidQuantity.add(count);
                nGoName.add(items[4]);
                nGoMan.add(items[5]);
                //System.out.println(items[2]); // testing
                //logDonorName.add(items[0]);
                // logDonorPass.add(items[1]);

                

                
            }
        }
        else{
            System.out.println("the file is empty");
        }
        System.out.printf("%-20s%-20s%-20s%-25s%-25s%-25s \n", "Donor", "Phone", "Aids", "Quantity", "  NGO NAME", "Manpower Count");
        //System.out.println(lines.get(0));
        //System.out.println(donorAids.size());
        for (int j = 0; j < nGoName.size(); j++) {
            if (nGoName.get(j).equals(dName.get(dPos))) {
                //System.out.println(donorDet.get(j));
                System.out.printf("%-20s%-20s%-25s%-25d%-25s%-25s \n",donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(j), nGoMan.get(j) );
                
            }

        }

        //after view the list, the user automatically will go to homepage
        System.out.println("  ");
        System.out.println("  ");
        System.out.println("  ");
        new nonGovHome("");



    }

    
}

class donorHome{
    String empty2;

    donorHome(){}

    donorHome(String empty2) throws IOException {
        this.empty2 = empty2;
        Scanner input = new Scanner(System.in);




        System.out.println("----------DONOR HOMEPAGE----------");
        System.out.println("| 1. Donate aids                 |");
        System.out.println("| 2. View donation table         |");
        System.out.println("| 3. Logout                      |");
        System.out.println("|________________________________|");




        System.out.print("Enter your choice : ");
        try{

            int choice = input.nextInt();


            while (choice != 1 && choice != 2 && choice != 3){
                System.out.println("ERROR : INVALID INPUT");
                System.out.println("Choice number must be in range 1-3. Try again.");
                System.out.print("Enter your choice : ");

                choice = input.nextInt();
                
            }

            if (choice == 1) {
                new donateAids(Position.getPosition());
            }
            else if (choice == 2){
                new donationTable(Position.getPosition());
            }
            else if (choice == 3){
                new mainMenu("");
            }

        }catch(InputMismatchException ex){
            System.out.println("ERROR : Choice number must be a positive number");

        }

    }

}

class nonGovHome{
    String empty3;

    nonGovHome(){}

    nonGovHome(String empty3) throws IOException {
        this.empty3 = empty3;
        Scanner input = new Scanner(System.in);




        System.out.println("----------NGO HOMEPAGE-----------");
        System.out.println("| 1. Required aids               |");
        System.out.println("| 2. View donation table         |");
        System.out.println("| 3. Logout                      |");
        System.out.println("|________________________________|");




        System.out.print("Enter your choice : ");
        try{

            int choice = input.nextInt();


            while (choice != 1 && choice != 2 && choice != 3){
                System.out.println("ERROR : INVALID INPUT");
                System.out.println("Choice number must be in range 1-3. Try again.");
                System.out.print("Enter your choice : ");

                choice = input.nextInt();
                
            }

            if (choice == 1){
                new aidsRequired(Position.getPosition());

            }
            else if (choice == 2){
                new nGodonationTable(Position.getPosition());
            }
            else if (choice == 3){
                new mainMenu("");
            }

        }catch(InputMismatchException ex){
            System.out.println("ERROR : Choice number must be a positive number");

        }

    }

}

class adminTableView {
    String blank;
     
    adminTableView() {}

    adminTableView(String blank) throws IOException {
        this.blank = blank;

        ArrayList<String> donorName = new ArrayList<>();
        ArrayList<String> donorPhone = new ArrayList<>();
        ArrayList<String> donorAid = new ArrayList<>();
        ArrayList<Integer> aidQuantity = new ArrayList<>();

        ArrayList<String> nGoName = new ArrayList<>();
        ArrayList<String> nGoMan = new ArrayList<>();
        ArrayList<String> nGoAid = new ArrayList<>();
        ArrayList<Integer> QuantityRequired = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                
                // split a line by comma
                String[] items = lines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorName.add(items[0]);
                int count = Integer.parseInt(items[3]);

                donorPhone.add(items[1]);
                donorAid.add(items[2]);
                aidQuantity.add(count);

                
            }
        }
        else{
            System.out.println("the file is empty");
        }


        List<String> nlines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
        if (nlines.size() > 0) {
            for (int i = 0; i < nlines.size(); i++) {
                
                // split a line by comma
                String[] items = nlines.get(i).split(",");
                // items[0] is id, items[1] is name
                nGoName.add(items[4]);
                int count = Integer.parseInt(items[3]);

                nGoMan.add(items[5]);
                nGoAid.add(items[2]);
                QuantityRequired.add(count);

                
            }
        }
        else{
            System.out.println("the file is empty");
        }

        
        System.out.printf("%-20s%-20s%-20s%-25s \n", "Donor", "Phone", "Aids", "Quantity");



        for (int j = 0; j < donorName.size(); j++) {
            System.out.printf("%-20s%-20s%-25s%-25d \n",donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j) );
                
            

        }

        System.out.println("\n\n");


        System.out.printf("%-20s%-20s%-20s%-25s \n", "NGO name", "Manpower Count", "Aids", "Quantity");



        for (int j = 0; j < nGoName.size(); j++) {
            System.out.printf("%-20s%-20s%-25s%-25d \n",nGoName.get(j), nGoMan.get(j), nGoAid.get(j), QuantityRequired.get(j) );
                
            

        }
    }
}



class oneToOne {
    String empty5;

    oneToOne() {}

    oneToOne(String empty5) throws IOException {
        this.empty5 = empty5;



        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        int indexA;
        int indexB;

        ArrayList<String> donorName = new ArrayList<>();
        ArrayList<String> donorPhone = new ArrayList<>();
        ArrayList<String> donorAid = new ArrayList<>();
        ArrayList<Integer> aidQuantity = new ArrayList<>();
        ArrayList<String> blank = new ArrayList<>();


        ArrayList<DonorAids> allDet = new ArrayList<>();
        ArrayList<DonorAids> addDet = new ArrayList<>();

        ArrayList<String> nGoName = new ArrayList<>();
        ArrayList<String> nGoMan = new ArrayList<>();
        ArrayList<String> nGoAid = new ArrayList<>();
        ArrayList<Integer> QuantityRequired = new ArrayList<>();
        //ArrayList<String> blankd = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                
                // split a line by comma
                String[] items = lines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorName.add(items[0]);
                int count = Integer.parseInt(items[3]);

                donorPhone.add(items[1]);
                donorAid.add(items[2]);
                aidQuantity.add(count);
                blank.add(items[4]);
                allDet.add(new DonorAids(items[0], items[1], items[2], count, items[4], items[5]));

                
            }
        }
        else{
            System.out.println("the file is empty");
        }


        List<String> nlines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
        if (nlines.size() > 0) {
            for (int i = 0; i < nlines.size(); i++) {
                
                // split a line by comma
                String[] items = nlines.get(i).split(",");
                // items[0] is id, items[1] is name
                nGoName.add(items[4]);
                int count = Integer.parseInt(items[3]);

                nGoMan.add(items[5]);
                nGoAid.add(items[2]);
                QuantityRequired.add(count);
                //blankd.add(items[0]);

                addDet.add(new DonorAids(items[0], items[1], items[2], count, items[4], items[5]));

                
            }
        }
        else{
            System.out.println("the file is empty");
        }



        for (int i = 0; i < nGoAid.size(); i++) {
            for (int j = 0; j < donorAid.size(); j++) {
                if (donorAid.get(j).compareTo(nGoAid.get(i)) == 0 && (aidQuantity.get(j) == QuantityRequired.get(i)) && blank.get(j).compareTo("-") == 0) {
                    //System.out.println("j = " + j + " i = " + i);
                    System.out.println(donorName.get(j) + " DONATES " + aidQuantity.get(j) + " " + donorAid.get(j) + " TO " + nGoName.get(i) );
                    a.add(j);
                    b.add(i);
                    //System.out.println(allDet.get(j));
                    allDet.add(new DonorAids(donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(i), nGoMan.get(i)));
                    addDet.add(new DonorAids(donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(i), nGoMan.get(i)));

                }

            }
        }

        if (a.size() != 0) {
            indexA = (a.get(0));
            allDet.remove(indexA);
            for (int num = 1; num  < a.size(); num++) {
                //System.out.println((num));
                allDet.remove(a.get(num)-num);

            }

            indexB = b.get(0);
            addDet.remove(indexB);
            for (int num = 1; num < b.size(); num++) {
                addDet.remove(b.get(num)-num);
            }


            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            for (int i = 0; i < addDet.size(); i++) {
                sb1.append(addDet.get(i).toCSVString() + "\n");
                
            }
            Files.write(Paths.get("distsystem","aidsRequired.csv"), sb1.toString().getBytes());

            for (int i = 0; i < allDet.size(); i++) {
                sb2.append(allDet.get(i).toCSVString() + "\n");
                
            }
            Files.write(Paths.get("distsystem","donation.csv"), sb2.toString().getBytes());
        }
        else{
            System.out.println("There is no more datas for 1-to-1 matching");

            //go to admin home page

        }
        System.out.println("\n\n\n\n");
        new adminHome("");


    
    }


}


class oneToMany {

    String empty6;
    oneToMany() {}

    oneToMany(String empty6) throws IOException{
        this.empty6 = empty6;

        Scanner input = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();
        ArrayList<Integer> d = new ArrayList<>();

        int indexA;
        int indexB;
        int indexC;
        int indexD;

        ArrayList<String> donorName = new ArrayList<>();
        ArrayList<String> donorPhone = new ArrayList<>();
        ArrayList<String> donorAid = new ArrayList<>();
        ArrayList<Integer> aidQuantity = new ArrayList<>();
        ArrayList<String> blank = new ArrayList<>();


        ArrayList<DonorAids> allDet = new ArrayList<>();
        ArrayList<DonorAids> addDet = new ArrayList<>();
        ArrayList<DonorAids> tableDet = new ArrayList<>();

        ArrayList<String> nGoName = new ArrayList<>();
        ArrayList<String> nGoMan = new ArrayList<>();
        ArrayList<String> nGoAid = new ArrayList<>();
        ArrayList<Integer> QuantityRequired = new ArrayList<>();
        ArrayList<String> blankd = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                
                // split a line by comma
                String[] items = lines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorName.add(items[0]);
                int count = Integer.parseInt(items[3]);

                donorPhone.add(items[1]);
                donorAid.add(items[2]);
                aidQuantity.add(count);
                blank.add(items[4]);
                allDet.add(new DonorAids(items[0], items[1], items[2], count, items[4], items[5]));

                
            }
        }
        else{
            System.out.println("the file is empty");
        }


        List<String> nlines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
        if (nlines.size() > 0) {
            for (int i = 0; i < nlines.size(); i++) {
                
                // split a line by comma
                String[] items = nlines.get(i).split(",");
                // items[0] is id, items[1] is name
                nGoName.add(items[4]);
                int count = Integer.parseInt(items[3]);

                nGoMan.add(items[5]);
                nGoAid.add(items[2]);
                QuantityRequired.add(count);
                blankd.add(items[0]);

                addDet.add(new DonorAids(items[0], items[1], items[2], count, items[4], items[5]));

                
            }
        }
        else{
            System.out.println("the file is empty");
        }



        for (int i = 0; i < nGoAid.size(); i++) {
            for (int j = 0; j < donorAid.size(); j++) {
                if (donorAid.get(j).compareTo(nGoAid.get(i)) == 0 &&  blank.get(j).compareTo("-") == 0 && blankd.get(i).compareTo("-") == 0) {
                    //System.out.println("j = " + j + " i = " + i);
                    //System.out.println(donorName.get(j));


                    //System.out.println(donorName.get(j) + " donates " + aidQuantity.get(j) + " " + donorAid.get(j) + " to " + nGoName.get(i) );
                    a.add(j);  // to store donor's position
                    //b.add(i);  // to store ngo's position
                    //System.out.println(allDet.get(j));
                    //allDet.add(new DonorAids(donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(i), nGoMan.get(i)));
                    //addDet.add(new DonorAids(donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(i), nGoMan.get(i)));

                }

            }
        }

        
        //System.out.println(donorAid.get(a.get(0)));
        //System.out.println(a);
        //System.out.println(b);

        Set<Integer> st = new HashSet<Integer>(a);

        for (Integer s : st) {



            if (Collections.frequency(a, s) > 1) {
                b.add(s);    //to get the position of donor which has no of occurence > 1
            }

        }

        if (b.size() == 0 || a.size() == 0) {
            System.out.println("There are no any datas for one-to-many matching");
            System.out.println("\n\n");
            new adminHome("");
        }

        System.out.println("POSSIBLE AIDS FOR ONE-TO-MANY MATCHING");
        System.out.println("\n");

        System.out.println("DONOR-AIDS-QUANTITY");
        for (int i = 0; i < b.size(); i++) {
            System.out.println((i+1) + ". " +  donorName.get(b.get(i)) + "-"  
             + donorAid.get(b.get(i)) + "-" + aidQuantity.get(b.get(i)));
        }

        System.out.print("Select the aids to be matched : ");
        int choice = input.nextInt();

        while (choice < 0 || choice > b.size()) {
            
            System.out.print("INVALID INPUT. TRY AGAIN : ");
            choice = input.nextInt();
            

        }
        //System.out.println(choice);

        for (int j = 0; j < nGoAid.size(); j++) {
            if (nGoAid.get(j).compareTo(donorAid.get(b.get(choice-1))) == 0) {
                c.add(j);  //to store ngos position in aidsRequired.csv
                //System.out.println((j+1) + ". " + nGoName.get(j) + "-" +    nGoAid.get(j) + "-" + QuantityRequired.get(j));

            }
        }

        System.out.println("\n");

        System.out.println("NGO-AIDS-QUANTITY");

        for (int q = 0; q < c.size(); q++) {
            indexA = c.get(q);
            System.out.println((q+1) + ". " + nGoName.get(indexA) + "-" +    nGoAid.get(indexA) + "-" + QuantityRequired.get(indexA));

        }

        

        System.out.print("Enter how many numbers of NGOs to be matched : ");

        int choiceNum = input.nextInt();

        while (choiceNum < 0 || choiceNum > c.size()) {
            
            System.out.print("INVALID INPUT. TRY AGAIN : ");
            choiceNum = input.nextInt();
            

        }
        int count = 0;
        int sum = 0;
        System.out.println("Select the NGOs : ");
        do{
            
            
            int select = input.nextInt();
            while (select < 0 || select > c.size()) {
            
                System.out.print("INVALID INPUT. TRY AGAIN : ");
                select= input.nextInt();
            }
            indexB = c.get(select-1);
            d.add(indexB);
            sum += QuantityRequired.get(indexB);


            count++;

        } while (count < choiceNum);

        //System.out.println(sum);

        //System.out.println(aidQuantity.get(b.get(choice-1)) == sum);
        //System.out.println(d);
        int donorNum = b.get(choice-1);
        if (aidQuantity.get(donorNum) == sum) {
            for (int i = 0; i < d.size(); i++) {
                indexC = d.get(i);
                indexD = b.get(choice-1);
                allDet.add(new DonorAids(donorName.get(indexD), donorPhone.get(indexD), donorAid.get(indexD),
                QuantityRequired.get(indexC), nGoName.get(indexC), nGoMan.get(indexC)));
                addDet.add(new DonorAids(donorName.get(indexD), donorPhone.get(indexD), donorAid.get(indexD),
                QuantityRequired.get(indexC), nGoName.get(indexC), nGoMan.get(indexC)));
                tableDet.add(new DonorAids(donorName.get(donorNum), donorPhone.get(donorNum), donorAid.get(donorNum),
                QuantityRequired.get(indexC), nGoName.get(indexC), nGoMan.get(indexC)));
            }

            allDet.remove(donorNum);
            int ngoPos = d.get(0);
            addDet.remove(ngoPos);
            for (int num = 1; num  < d.size(); num++) {
                //System.out.println((num));
                addDet.remove(d.get(num)-num);

            }

        }
        else if (aidQuantity.get(donorNum) != sum) {
            //go to main page
            System.out.println("Sum of aids quantity of NGOs is both equal to aids quantity of donor");
            System.out.println("\n\n");
            new adminHome("");

        }


        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < addDet.size(); i++) {
            sb1.append(addDet.get(i).toCSVString() + "\n");
            
        }
        Files.write(Paths.get("distsystem","aidsRequired.csv"), sb1.toString().getBytes());

        for (int i = 0; i < allDet.size(); i++) {
            sb2.append(allDet.get(i).toCSVString() + "\n");
            
        }
        Files.write(Paths.get("distsystem","donation.csv"), sb2.toString().getBytes());

        System.out.println("DONOR PHONE AIDS QUANTITY NGO MANPOWER");
        //System.out.println(tableDet);
        for (int j = 0; j < tableDet.size(); j++) {
            System.out.println(tableDet.get(j));
 
        }
        System.out.println("\n\n");
        new adminHome("");


        

    }
}


class manyToOne {
    String empty6;
    manyToOne() {}

    manyToOne(String empty6) throws IOException {
        this.empty6 = empty6;

        Scanner input = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();
        ArrayList<Integer> d = new ArrayList<>();

        int indexA;
        int indexB;
        int indexC;
        int indexD;

        ArrayList<String> donorName = new ArrayList<>();
        ArrayList<String> donorPhone = new ArrayList<>();
        ArrayList<String> donorAid = new ArrayList<>();
        ArrayList<Integer> aidQuantity = new ArrayList<>();
        ArrayList<String> blank = new ArrayList<>();


        ArrayList<DonorAids> allDet = new ArrayList<>();
        ArrayList<DonorAids> addDet = new ArrayList<>();
        ArrayList<DonorAids> tableDet = new ArrayList<>();

        ArrayList<String> nGoName = new ArrayList<>();
        ArrayList<String> nGoMan = new ArrayList<>();
        ArrayList<String> nGoAid = new ArrayList<>();
        ArrayList<Integer> QuantityRequired = new ArrayList<>();
        ArrayList<String> blankd = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("distsystem","donation.csv"));
        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                
                // split a line by comma
                String[] items = lines.get(i).split(",");
                // items[0] is id, items[1] is name
                donorName.add(items[0]);
                int count = Integer.parseInt(items[3]);

                donorPhone.add(items[1]);
                donorAid.add(items[2]);
                aidQuantity.add(count);
                blank.add(items[4]);
                allDet.add(new DonorAids(items[0], items[1], items[2], count, items[4], items[5]));

                
            }
        }
        else{
            System.out.println("the file is empty");
        }


        List<String> nlines = Files.readAllLines(Paths.get("distsystem","aidsRequired.csv"));
        if (nlines.size() > 0) {
            for (int i = 0; i < nlines.size(); i++) {
                
                // split a line by comma
                String[] items = nlines.get(i).split(",");
                // items[0] is id, items[1] is name
                nGoName.add(items[4]);
                int count = Integer.parseInt(items[3]);

                nGoMan.add(items[5]);
                nGoAid.add(items[2]);
                QuantityRequired.add(count);
                blankd.add(items[0]);

                addDet.add(new DonorAids(items[0], items[1], items[2], count, items[4], items[5]));

                
            }
        }
        else{
            System.out.println("the file is empty");
        }



        for (int i = 0; i < nGoAid.size(); i++) {
            for (int j = 0; j < donorAid.size(); j++) {
                if (donorAid.get(j).compareTo(nGoAid.get(i)) == 0 &&  blank.get(j).compareTo("-") == 0 && blankd.get(i).compareTo("-") == 0) {
                    //System.out.println("j = " + j + " i = " + i);
                    //System.out.println(donorName.get(j));


                    //System.out.println(donorName.get(j) + " donates " + aidQuantity.get(j) + " " + donorAid.get(j) + " to " + nGoName.get(i) );
                    a.add(i);  // to store donor's position
                    //b.add(i);  // to store ngo's position
                    //System.out.println(allDet.get(j));
                    //allDet.add(new DonorAids(donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(i), nGoMan.get(i)));
                    //addDet.add(new DonorAids(donorName.get(j), donorPhone.get(j), donorAid.get(j), aidQuantity.get(j), nGoName.get(i), nGoMan.get(i)));

                }

            }
        }

        // //System.out.println(donorAid.get(a.get(0)));
        // //System.out.println(a);
        // //System.out.println(b);

        Set<Integer> st = new HashSet<Integer>(a);

        for (Integer s : st) {



            if (Collections.frequency(a, s) > 1) {
                b.add(s);    //to get the position of donor which has no of occurence > 1
            }

        }

        if (b.size() == 0 || a.size() == 0) {
            System.out.println("There are no any datas for many-to-one matching");
            System.out.println("\n\n");
            new adminHome("");
        }

        System.out.println("POSSIBLE AIDS FOR MANY-TO-ONE MATCHING");
        System.out.println("\n");

        System.out.println("NGO-AIDS-QUANTITY");
        for (int i = 0; i < b.size(); i++) {
            System.out.println((i+1) + ". " +  nGoName.get(b.get(i)) + "-"  
             + nGoAid.get(b.get(i)) + "-" + QuantityRequired.get(b.get(i)));
        }

        System.out.print("Select the NGOs and aids to be matched : ");
        int choice = input.nextInt();

        while (choice < 0 || choice > b.size()) {
            
            System.out.print("INVALID INPUT. TRY AGAIN : ");
            choice = input.nextInt();
            

        }
        System.out.println(choice);

        for (int j = 0; j < donorAid.size(); j++) {
            if (donorAid.get(j).compareTo(nGoAid.get(b.get(choice-1))) == 0 && blank.get(j).compareTo("-") == 0) {
                c.add(j);  //to store ngos position in aidsRequired.csv
                //System.out.println((j+1) + ". " + nGoName.get(j) + "-" +    nGoAid.get(j) + "-" + QuantityRequired.get(j));

            }
        }

        System.out.println("\n");

        System.out.println("DONOR-AIDS-QUANTITY");

        for (int q = 0; q < c.size(); q++) {

            indexA = c.get(q);
            System.out.println((q+1) + ". " + donorName.get(indexA) + "-" +    donorAid.get(indexA) + "-" + aidQuantity.get(indexA));

        }

        

        System.out.print("Enter the number of donors to be matched : ");

        int choiceNum = input.nextInt();

        while (choiceNum < 0 || choiceNum > c.size()) {
            
            System.out.print("INVALID INPUT. TRY AGAIN : ");
            choiceNum = input.nextInt();
            

        }
        int count = 0;
        int sum = 0;
        System.out.println("Select the Donors : ");
        do{
            
            
            int select = input.nextInt();
            while (select < 0 || select > c.size()) {
            
                System.out.print("INVALID INPUT. TRY AGAIN : ");
                select= input.nextInt();
            }
            indexB = c.get(select-1);
            d.add(indexB);
            sum += aidQuantity.get(indexB);


            count++;

        } while (count < choiceNum);

        //System.out.println("Sum = " + sum);

        // //System.out.println(aidQuantity.get(b.get(choice-1)) == sum);
        // //System.out.println(d);
        int donorNum = b.get(choice-1);
        if (QuantityRequired.get(donorNum) == sum) {
            for (int i = 0; i < d.size(); i++) {
                indexC = d.get(i);
                indexD = b.get(choice-1);
                allDet.add(new DonorAids(donorName.get(indexC), donorPhone.get(indexC), donorAid.get(indexC),
                aidQuantity.get(indexC), nGoName.get(indexD), nGoMan.get(indexD)));
                addDet.add(new DonorAids(donorName.get(indexC), donorPhone.get(indexC), donorAid.get(indexC),
                aidQuantity.get(indexC), nGoName.get(indexD), nGoMan.get(indexD)));
                tableDet.add(new DonorAids(donorName.get(indexC), donorPhone.get(indexC), donorAid.get(indexC),
                aidQuantity.get(indexC), nGoName.get(indexD), nGoMan.get(indexD)));
            }


            //System.out.println(addDet);
            // System.out.println(addDet);
            addDet.remove(donorNum);

            int ngoPos = d.get(0);
            allDet.remove(ngoPos);
            for (int num = 1; num  < d.size(); num++) {
                //System.out.println((num));
                allDet.remove(d.get(num)-num);

            }

        }
        else if (aidQuantity.get(donorNum) != sum) {
            //go to main page
            System.out.println("Sum of aids quantity of NGOs is not equal to aids quantity of donor");
            System.out.print("\n\n");
            new adminHome("");

        }
        
        // System.out.println(addDet);

        // System.out.println(allDet);



        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < addDet.size(); i++) {
            sb1.append(addDet.get(i).toCSVString() + "\n");
            
        }
        Files.write(Paths.get("distsystem","aidsRequired.csv"), sb1.toString().getBytes());

        for (int i = 0; i < allDet.size(); i++) {
            sb2.append(allDet.get(i).toCSVString() + "\n");
            
        }
        Files.write(Paths.get("distsystem","donation.csv"), sb2.toString().getBytes());

        System.out.println("Result : \n\n");

        System.out.println("DONOR PHONE AIDS QUANTITY NGO MANPOWER");
        //System.out.println(tableDet);
        for (int j = 0; j < tableDet.size(); j++) {
            System.out.println(tableDet.get(j));
 
        }

        System.out.println("\n\n");
        new adminHome("");
    }
}


class adminHome{
    String empty4;

    adminHome(){}

    adminHome(String empty4) throws IOException {
        this.empty4 = empty4;
        Scanner input = new Scanner(System.in);


        

        System.out.println("-------DISTRIBUTION CENTER--------");
        System.out.println("| 1. View aids, donors and NGO   |");
        System.out.println("| 2. Match aids 1-1              |");
        System.out.println("| 3. Match aids 1-to-Many        |");
        System.out.println("| 4. Match aids Many-to-1        |");
        System.out.println("| 5. Match aids Many-to-Many     |");
        System.out.println("| 6. Logout                      |");
        System.out.println("|________________________________|");




        System.out.print("Enter your choice : ");
        try{

            int choice = input.nextInt();


            while (choice < 1 || choice > 6){
                System.out.println("ERROR : INVALID INPUT");
                System.out.println("Choice number must be in range 1-6. Try again.");
                System.out.print("Enter your choice : ");

                choice = input.nextInt();
                
            }

            if (choice == 1){
                System.out.println("\n\n\n");

                new adminTableView("");
                //System.out.println("not ready");
                System.out.println("\n\n\n");
                
                new adminHome("");
                

            }
            else if (choice == 2){
                System.out.println("\n\n\n");
                new oneToOne("");


                
            }
            else if (choice == 3){
                System.out.println("\n\n\n");
                
                new oneToMany(""); 
            }
            else if (choice == 4){
                System.out.println("\n\n\n");
                
                new manyToOne(""); 
            }
            else if (choice == 5){
                System.out.println("not ready");
                System.exit(1);
            }
            else if (choice == 6){
                System.out.println("\n\n\n");
                new mainMenu("");
                
            }


        }catch(InputMismatchException ex){
            System.out.println("ERROR : Choice number must be a positive number");

        }

    }

}


public class TestMenu {
    public static void main(String[] args) throws IOException{
        new mainMenu("");
        //System.out.println(new donorLogin("").getDonorPos());
        //new donorLogin("");

        //new nonGov("hhse","hhjtj", 12);
        // new donorPosition(1);
        //System.out.println("Donor position = " + Position.getPosition());
        // new donorPosition(5);
        // System.out.println(donorPosition.getPosition());
        
        
        


        
    } 

}


   


        
    
            
        
    
    
		
        
    
    




