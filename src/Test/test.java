package Test;

import java.util.ArrayList;
import Order.*;
import Authentication.*;
import Inventory.*;

class test {
    String teststring = "[-----  %s  -----]";
    /**
     * Authentication Tests
     */
    private Authentication authenticationTests(){
        String c = "AUTHENTICATION TESTS\n\n";
        System.out.println(String.format(teststring,c));
        try {
            //testing login feature
            Authentication auth = new Authentication();
            System.out.println("[AUTHENTICATED USER] role: "+auth.authenticate("mlewis","password123"));
            System.out.println("\nuser authenticated: " + auth.getAuth_message());

            Authentication none = new Authentication(); 
            // System.out.println("[ANON USER] role: "+none.authenticate("user", "pw"));
            
            // displating user's menu options
            for(String s : auth.getUserMenu()){
                System.out.println("[AUTHENTICATED USER] Menu option: "+s);
            }

            // CHECKING AUTHENTICATION STATUS
            System.out.println("[AUTHENTICATED] MESSAGE: " + auth.getAuth_message());
            //System.out.println("[NOT AUTHENTICATED] MESSAGE: " + none.getAuth_message()); 

            // SIGNING OUT AND GETTING AUTHENTICATION STATUS MESSAGE
            auth.authenticate("", "");
            System.out.println( auth.getAuth_message()+"\n");

            // SIGNING IN AND GETTING AUTHENTICATION
            //none.authenticate("Calzy","beautiful");
            System.out.println("\n[AUTHENTICATED USER] role: "+none.authenticate("Calzy","beautiful"));
            System.out.println("user authenticated: " + none.getAuth_message());
            // displating user's menu options
            for(String s : none.getUserMenu()){
                System.out.println("[AUTHENTICATED USER] Menu option: "+s);
            }

            // CHECKING AUTHENTICATION STATUS
            System.out.println("[AUTHENTICATED] MESSAGE: " + none.getAuth_message()+"\n\n");
            //System.out.println("[NOT AUTHENTICATED] MESSAGE: " + none.getAuth_message());
            return none;
            
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("\n!!!!!AUTHENTICATION EXCEPTION ENCOUNTERED!!!!!\n\n");
            return null;
        }
    }

    /**        
    * Stock class test            
    */
    private String stockTests(){
        String c = "STOCK TESTS\n\n";
        System.out.println(String.format(teststring,c));
        try {
            // CREATING BEAD IN DB
            Stock gibbs = new Stock (StockType.Beads,"Gibbits", 0,50);
            gibbs.createStock();
            
            // PRINTING UPDATING AND REPRINTING BEAD QUANITY
            System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits"));
            System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 
            Stock.updateStock('+',500, "Gibbits"); 
            Stock.updateStock('+',500, "Panther"); 
            Stock.updateStock('-',500, "Gibbits");
            Stock.updateStock('-',500, "Panther");
            System.out.println("\nUPDATED STOCK QUANTITY");
            System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits")); 
            System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 

            // VIEWING ALL STOCK
            System.out.println("\n[ALL STOCK]");
            for(Stock s:Stock.viewStock(1)){
                System.out.println(s.toString());
            }

            System.out.println("[CREATING STOCK ITEMS]");

            // CREATING LOW STOCK ITEM
            Stock not = new Stock (StockType.Beads,"iron", 0,200);
            not.createStock();

            Stock white = new Stock (StockType.Beads,"white", 0,50);
            white.createStock();

            Stock black = new Stock (StockType.Beads,"black", 0,200);
            black.createStock();

            Stock brown = new Stock (StockType.Beads,"brown", 0,200);
            brown.createStock();

            Stock red = new Stock (StockType.Beads,"red", 0,20);
            red.createStock();

            Stock green = new Stock (StockType.Beads,"green", 0,200);
            green.createStock();

            Stock yellow = new Stock (StockType.Beads,"yellow", 0,200);
            yellow.createStock();

            // VIEWING LOW STOCK ITEMS
            System.out.println("\n\n[LOW STOCK]");
            for(Stock s:Stock.viewStock(0)){
                System.out.println(s.toString());
            }

            System.out.println("\n\n[ADDING PANTHER BEAD]");
            Stock sec = new Stock(StockType.Beads,"Panther", 0,50);
            ;
            System.out.println("\n\n[TOSTRING() FROM CLASS TEST]: "+sec.toString());
            sec.createStock();
            System.out.println("[VIEWITEM(STOCKNAME) TEST]" + Stock.viewItem(sec.getStockName()).toString()+"\n\n");
            return "Stock Tests completed successfully;";
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("\n!!!!!STOCK EXCEPTION ENCOUNTERED!!!!!\n\n");
            return "Stock Tests failed;";
        }
    }

    /**
     * Bracelet Tests
     */
    private String braceletTests(){
        String c = "BRACELET TESTS\n\n";
        System.out.println(String.format(teststring,c));
        try{
            Bracelet.populate();
            Bracelet b; 
            b = new Bracelet("b1", 3.00, "yellow-2;green-4", "yellow-5;green-8", "yellow-10;green-16", "diatta");
            //System.out.println(b.getName());
            //System.out.println(b.getBracelets());
            //System.out.println(b.getID());
            //System.out.println(b.getCollection());
            //System.out.println(Bracelet.getBraceletIndex("b1"));
            // Bracelet b = Bracelet.searchByName("b1");
            Bracelet.addToArray(b);
            b.addToDatabase();
            System.out.println(b.getName());
            System.out.println(b.getSmallBeadQty());
            System.out.println(b.getLgBeadQty());
            System.out.println(b.getMedBeadQty()+"\n\n");
            //Bracelet.deleteBracelet("b1");
            return "Bracelet Tests completed successfully; \n";
        }
        catch(Exception e){
            System.out.println("\n!!!!!BRACELET EXCEPTION ENCOUNTERED!!!!!\n");
            return "Bracelet Tests failed; \n";
        }
    }

    private String orderTests(){
        String c = "ORDER TESTS\n\n";
        System.out.println(String.format(teststring,c));
        try {
            // CREATING ORDER 
            // Order fdr = new Order("8763792241", "Olivia Blackwood", "SMALL,MEDIUM", "2,10", "b1,b1", "Half Way Tree"); // Requires a public static getCost(String braceletName) method from Bracelet to test and run        System.out.println("[Order object created]");
            Order old = new Order("8764449876", "Old McDonald", "4,8", "b1,b1", "Harbour View");
            // ADDING ORDER TO THE DATABASE
            // System.out.println("[ORDER STATUS] Added to Database: "+fdr.addToDatabase());
            System.out.println("[ORDER STATUS] OLD STOCK FORMAT - Added to Database: "+old.addToDatabase());

            System.out.println("[GETTING ORDERS FROM DB]");

            ArrayList<Order> orders = Order.populate();
            for(Order o:orders){
                System.out.println(o.toString());
            }

            int n = orders.size();
            System.out.println("\n[There are now "+n+" orders in the database!");
            if (n>4){
                System.out.println(String.format("\n[DELETING %d ORDER/S]",n-2));
                for(int i = 1; i < n-1; i++){
                    int m=Order.orders.get(i).getOrderNo();
                    Order.orders.get(i).deleteOrder();
                    System.out.println(String.format("[ORDER # %d DELETED]\n",m));
                }
            }
            
            System.out.println("\n\n");
            return "Order Tests completed successfully; \n";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n!!!!!ORDER EXCEPTION ENCOUNTERED!!!!!\n");
            return "Order Tests failed; \n";
        }
    }

    private String userTests(){
        String c = "USER TESTS\n\n";
        System.out.println(String.format(teststring,c));
        try{
            Authentication auth = new Authentication();
            // Adding Users
            User callay = new User ("Calzy", "beautiful", Role.ADMIN);
            callay.addUser();
            System.out.println("\n\n[USER ADDED] "+callay.getUserName().toUpperCase()+" ADDED TO DATABASE");
            System.out.println(String.format("[USER] ROLE: %s","Calzy".toUpperCase(),auth.authenticate("Calzy", "beautiful")));


            User marlon = new User ("Marly", "blessed", Role.ADMIN);
            marlon.addUser();
            System.out.println("[USER ADDED] "+marlon.getUserName().toUpperCase()+" ADDED TO DATABASE");
            System.out.println(String.format("USER '%s'] ROLE: %s",marlon.getUserName().toUpperCase(),auth.authenticate("Marly", "blessed")));

            User bob = new User ("bob", "marley", Role.PRODUCTION_MANAGER);
            bob.addUser();
            System.out.println("[USER ADDED] "+bob.getUserName().toUpperCase()+" ADDED TO DATABASE");
            System.out.println(String.format("USER '%s'] ROLE: %s",bob.getUserName().toUpperCase(),auth.authenticate("Marly", "blessed")));

            return "User Tests completed successfully; \n";
        }
        catch(Exception e){
            System.out.println("\n!!!!!ORDER EXCEPTION ENCOUNTERED!!!!!\n");
            return "User Tests failed; \n";
        }
    }

    private String customerTests(){
        String c = "CUSTOMER TESTS\n\n";
        System.out.println(String.format(teststring,c));
        try {
            //Testing getcusID Preventing Duplication
            // mercedes.addToDatabase();
            // marlon.addToDatabase();
            // gabriel.addToDatabase();
            
            System.out.println("[CUSTOMER ID] Found: "+Customer.getCusId("Mercedes", "8768164681", "Sedecrem"));
            System.out.println("[CUSTOMER ID] Found: "+Customer.getCusId("Mercedes", "8768164681", "Sedecrem"));
            System.out.println("[CUSTOMER ID] Found: "+Customer.getCusId("Callay", "8769654681", "Spanish"));
            System.out.println("[CUSTOMER ID] Found: "+Customer.getCusId("Taye-Vaughn", "8769654781", "Mobay"));
            System.out.println("[CUSTOMER ID] Found: "+Customer.getCusId("Kimani", "8769654781", "Mobay"));
            System.out.println("[CUSTOMER ID] Found: "+Customer.getCusId("Gabriel", "8769654781", "Mobay"));

            //Deleting customer
            Customer.deleteCustomer(Customer.getCusId("Taye-Vaughn", "8769654781", "Mobay"));

            //Testing populate()
            for (int i = 0; i < Customer.getCustomers().size(); i++)
            {
                System.out.println(Customer.getCustomers().get(i).getcustomerName());
            } 
            System.out.println("\n\n");
            return "Customer Tests completed successfully; \n";
        } catch (Exception e) {
            System.out.println("\n!!!!!CUSTOMER EXCEPTION ENCOUNTERED!!!!!\n");
            return "Customer Tests failed; \n";
        }

    }

    private String confirmations(Authentication a,String b, String c, String d, String e, String f){
        String x = "\n\nAuthentication Tests failed; \n";
        if(a!=null){x = "\n\nAuthentication Tests completed successfully; \n";}
        a.logout();
        return x+b+c+d+e+f;
    }
    
    public static void main(String[] args) {
        
        test t = new test();
        
        /*        * RUNNING TESTS               */
        Authentication a = t.authenticationTests(); //Authentication
        String b = t.stockTests(); //"[STOCK TEST PENDING]"; //Stock //t.stockTests(); //Stock
        String c = t.braceletTests(); //"[BRACELET TEST PENDING]; \n"; //t.braceletTests(); //Bracelet
        String d = t.orderTests(); //"[ORDER TEST PENDING]; \n"; //t.orderTests(); //Order//"[ORDER TEST PENDING]; \n"; //Order
        String e = t.customerTests(); //"[CUSTOMER TEST PENDING]; \n"; //t.customerTests(); //Customer
        String f = t.userTests(); //"[USER TEST PENDING]; \n"; //t.userTests(); //Stock//"[USER TEST PENDING]; \n"; //User
        

        t.confirmations(a,b,c,d,e,f);
    }
}