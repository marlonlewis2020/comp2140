package Bracelets;

/**
 * @author Taye-Vaughn Jones
 */

import Authentication.Authentication;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.lang.reflect.Array;
import java.sql.Connection;

public class Bracelet{
    private String name;
    private String collection;
    //private Size size;
    private int ID;
    private static int nextID = 0;
    private double cost;
    //private String beadQty_small;
    //private String beadQty_med;
    //private String beadQty_lg;
    private static ArrayList <Bracelet> bracelets = new ArrayList <Bracelet>();
    private ArrayList <String> beadQty = new ArrayList <String>();  //["bead-integernumberofamtrequired","bead2-integernumberofamtrequired",...]
    //private String beadsString;//("bead-intgernumberofamtrequired;bead2-integernumberofamtrequired...")
    //private ArrayList <String> beads = new ArrayList <String>(); 
    


    //Constructor
    /**
     * 
     * @param name
     * @param cost
     * @param beadQty_small
     * @param beadQty_med
     * @param beadQty_lg
     * @param beadsString
     */
    public Bracelet(String name, double cost, String beadQty_small, String beadQty_med,String beadQty_lg,String collection){
        this.name = name;
        this.cost = cost;
        this.ID = nextID;
        this.collection = collection;
        nextID++;
      
        //Recording the number of beadsString needed to make each size bracelet
        beadQty.add(beadQty_small);
        beadQty.add(beadQty_med);
        beadQty.add(beadQty_lg);
    }


    public Bracelet(){};

    //Getters
    /**
     * 
     * @return bracelet name
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     * @return bracelets array
     */
    public ArrayList <Bracelet> getBracelets(){
        return bracelets;
    }

    /**
     * 
     * @return bracelet collection
     */
    public String getCollection(){
        return this.collection;
    }

    /**
     * return bracelet ID
     * @return
     */
    public int getID(){
        return this.ID;
    }
    
    /**
     * 
     * @return bracelet cost
     */
    public double getCost(){
        return this.cost;
    }


    /**
     * 
     * @return bead quantity for a small sized bracelet
     */
    public String getSmallBeadQty(){
        return beadQty.get(0);
    }

    /**
     * 
     * @return bead quantity for a medium sized bracelet
     */
    public String getMedBeadQty(){
        return beadQty.get(1);
    }

    /**
     * 
     * @return bead quantity for a large sized bracelet
     */
    public String getLgBeadQty(){
        return beadQty.get(2);
    }
 
      
    //Setters

    /**
     * 
     * @param name name of a bracelet
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * 
     * @param ID bracelet ID
     */
    public void setID(int ID){
        this.ID = ID;
    }

    /**
     * 
     * @param collection bracelet collection
     */
    public void setCollection(String collection){
        this.collection = collection;
    }

    /**
     * 
     * @param cost bracelet cost
     */
    public void setCost(double cost){
        this.cost = cost;
    }



    /**
     * Add bracelet to database
     * @param b Bracelet object
     */
    public void addToDatabase(Bracelet b){

      try{
        
        Connection conn = Authentication.dbconnect();
        String query = "insert into bracelets (id,name,collection,cost, smallBeads, mediumBeads, largeBeads)"
          + " values (?, ?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, b.getID());
        preparedStmt.setString(2, b.getName());
        preparedStmt.setString(3, b.getCollection());
        preparedStmt.setDouble(4, b.getCost());
        preparedStmt.setString(5, b.getSmallBeadQty());
        preparedStmt.setString(6,  b.getMedBeadQty());
        preparedStmt.setString(7,  b.getLgBeadQty());
        // execute the preparedstatement
        preparedStmt.execute();
      }
      catch(Exception e)
      {
        System.err.println("Got an exception!");
        System.err.println(e.getMessage());
      }
  }


  //populate bracelet array with info from database
  public static void populate(){
    

      try {
            //Creating a DB Connect Object to connect to database
            Connection conn = Authentication.dbconnect();
            // SQL command data stored in String datatype
            String sql = "select * from bracelet";
            PreparedStatement preparedStStmt = conn.prepareStatement(sql);
            ResultSet rs = preparedStStmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String collection = rs.getString("collection");
                double cost = rs.getDouble("cost");
                String bead_quantity_small = rs.getString("smallBeads");
                String bead_quantity_medium = rs.getString("mediumBeads");
                String bead_quantity_large = rs.getString("largeBeads");
                Bracelet b = new Bracelet(name,cost,bead_quantity_small,bead_quantity_medium,bead_quantity_large,collection);
                b.setID(id);
                bracelets.add(b);
            }

        }
 
        // Catch block to handle exception
        catch (SQLException e) {
            // Print exception pop-up on scrreen
            System.out.println(e);
        }
    }

  
    
    /**
     * 
     * @param name bracelet name
     * @return bracelet object
     */
    public static Bracelet searchByName(String name){
        for(int i = 0; i < bracelets.size(); i++){
            if(((bracelets.get(i)).getName()).equals(name)){
                //pass to authentication function
                return bracelets.get(i);
            }
        }

        return null;
    }

    //Display bracelet details when given the name
    /**
     * 
     * @param name bracelet name
     */
    public static void displayBracelet(String name){
        Bracelet bracelet;
        bracelet = searchByName(name);
        if(bracelet == null){
            return;
        }
        else{
            System.out.println(bracelet.getName());
            System.out.println(bracelet.getCollection());
            System.out.println(bracelet.getCost());
            System.out.println(bracelet.getID());
        }

    }

    //Return index of bracelet
    /**
     * 
     * @param name bracelet name
     * @return index of bracelet in array
     */
    private static int getBraceletIndex(String name){
        for(int i=0; i < bracelets.size(); i++){
            if(((bracelets.get(i)).getName()).equals(name)){
                return i;
            }
        }
        return -1;
    }

    //Delete a bracelet
    /**
     * 
     * @param name bracelet name
     */
    public static void deleteBracelet(String name){
        Bracelet bracelet = searchByName(name);
        try {  
            Connection conn = Authentication.dbconnect();
            PreparedStatement st = conn.prepareStatement("DELETE FROM bracelet WHERE name = ?");
            st.setString(1,name);
            st.executeUpdate();
            if(getBraceletIndex(name) == -1){
                System.out.println("Bracelet does not exist");
            }
            bracelets.remove(getBraceletIndex(name));
        } catch(Exception e) {
            System.out.println(e);
        }
    }



    //Estimate bracelet quantity

    public ArrayList <Integer> estimateQty(){     
        int total = 0;   
        int smallMin = 99999;
        int medMin = 99999;
        int largeMin = 99999;
        String [] beadTypesSmall;
        String [] beadTypesMed;
        String [] beadTypesLg;
        ArrayList <Integer> qty = new ArrayList <Integer>();
        
        beadTypesSmall = (getSmallBeadQty()).split(";");
        beadTypesMed = (getMedBeadQty()).split(";");
        beadTypesLg = (getLgBeadQty()).split(";");

        for(int i = 0; i < beadTypesSmall.length; i++){
            if(((Stock.getQuantity((beadTypesSmall[i].split("-"))[0])) / (beadTypesSmall[i].split("-"))[1]) < smallMin)
            smallMin = (Stock.getQuantity((beadTypesSmall[i].split("-"))[0])) / Integer.parseInt((beadTypesSmall[i].split("-"))[1]);
        }
        for(int i = 0; i < beadTypesMed.length; i++){
            if(((Stock.getQuantity((beadTypesMed[i].split("-"))[0])) / (beadTypesMed[i].split("-"))[1]) < medMin)
            medMin = (Stock.getQuantity((beadTypesMed[i].split("-"))[0])) /  Integer.parseInt((beadTypesMed[i].split("-"))[1]);
        }
        for(int i = 0; i < beadTypesLg.length; i++){
            if(((Stock.getQuantity((beadTypesLg[i].split("-"))[0])) / (beadTypesLg[i].split("-"))[1]) < largeMin)
            largeMin = (Stock.getQuantity((beadTypesLg[i].split("-"))[0])) /  Integer.parseInt((beadTypesLg[i].split("-"))[1]);
        }
        
        qty.add(smallMin);
        qty.add(medMin);
        qty.add(largeMin);

        return qty;
    }

    

}