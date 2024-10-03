package distsystem;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Person{
    String name;
    String password;
    public Person(){}
    public Person(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name.toUpperCase();
    } 

    public String getPassword(){
        return password;
    }  

    public String toString() {
        return name.toUpperCase() + " " + password;
    }
}


public class Donor extends Person {
    String Pnumber;
    String aids;
    String quantity;
    
    //private ArrayList<Ngo> NeededAids = new ArrayList<>();

    //created a constructor for Donor class
    public Donor(){}
   
    public Donor (String name, String password, String Pnumber){
        super(name,password);
        this.Pnumber = Pnumber; 
        

        //this.aids = aids;  
       // this.quantity = quantity;  

    }

    public String getName(){
        return name.toUpperCase();
    } 

    public String getNumber(){
        return Pnumber;
    } 

    public String getPassword(){
        return password;
    }    

	public String getQuantity(){
        return quantity;
    } 
	
	public String getAids(){
        return aids;
    }	

    public String toString() {
        return name + " " + password +  " " + Pnumber ;
    }
    
    public String toCSVString() {
       return name + "," + password + ","  + Pnumber ;
    }
}

class DonorAids {
    String name;
    String Pnumber;
    String aids;
    int aidsQuantity;
    String ngoName;
    String ngoManpower;



    public DonorAids() {}

    public DonorAids(String name, String Pnumber, String aids, int aidsQuantity, String ngoName, String ngoManpower) {
        this.name = name;
        this.Pnumber = Pnumber;
        this.aids = aids;
        this.aidsQuantity = aidsQuantity;
        this.ngoName = ngoName;
        this.ngoManpower = ngoManpower;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return Pnumber;
    } 

    public String getAids(){
        return aids;
    }
    
	public int getQuantity(){
        return aidsQuantity;
    }

    public String toString() {
        return name + " " + Pnumber +  " " +  aids + " " + aidsQuantity + " " + ngoName + " " + ngoManpower ;
    }

    public String toCSVString() {
        return name + "," + Pnumber +  "," +  aids + "," + aidsQuantity + "," + ngoName+ "," + ngoManpower  ;
     }
}



class aidsNeeded extends DonorAids {
    String name;
    String Pnumber;
    String aids;
    int aidsQuantity;
    String ngoName;
    String ngoManpower;



    public aidsNeeded() {}

    public aidsNeeded(String name, String Pnumber, String aids, int aidsQuantity, String ngoName, String ngoManpower) {
        super(name, Pnumber, aids, aidsQuantity, ngoName, ngoManpower);
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return Pnumber;
    } 

    public String getAids(){
        return aids;
    }
    
	public int getQuantity(){
        return aidsQuantity;
    }

    public String toString() {
        return name + " " + Pnumber +  " " +  aids + " " + aidsQuantity + " " + ngoName + " " + ngoManpower ;
        //System.out.printf("%-10s%-10s%-10s%-10d%-10s%-10s \n", name,Pnumber, aids, aidsQuantity, ngoName, ngoManpower);
    }

    public String toCSVString() {
        return name + "," + Pnumber +  "," +  aids + "," + aidsQuantity + "," + ngoName+ "," + ngoManpower  ;
     }
}

 
class nonGov extends Person{
    String name;
    String password;
    int manPower;
    public nonGov(){}

    public nonGov(String name, String password, int manPower){
        this.name = name;
        this.password = password;
        this.manPower = manPower;
        
        
    }
    public String getName(){
        return name.toUpperCase();
    }
    public String getPassword(){
        return password;
    }
    public int getCount(){
        return manPower;
    }
    public String toString() {
        return name + " " + password +  " " + manPower ;
    }
    public String toCSVString() {
        return name + "," + password + ","  + manPower ;
     }

}



   







   

