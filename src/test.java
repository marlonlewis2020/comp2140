import Authentication.Authentication;

class test {

    /**
     * Authentication Tests
     */
    private Authentication authenticationTests(){
        try {
            //testing login feature
            Authentication auth = new Authentication();
            System.out.println("[AUTHENTICATED USER] role: "+auth.authenticate("mlewis","1403730359"));

            Authentication none = new Authentication(); 
            System.out.println("[ANON USER] role: "+none.authenticate("user", "pw"));
            
            // displating user's menu options
            for(String s : auth.getUserMenu()){
                System.out.println("AUTHENTICATED USER'S] Menu option: "+s);
            }

            // CHECKING AUTHENTICATION STATUS
            System.out.println("[AUTHENTICATED] MESSAGE: " + auth.getAuth_message());
            System.out.println("[NOT AUTHENTICATED] MESSAGE: " + none.getAuth_message()); 

            // SIGNING OUT AND GETTING AUTHENTICATION STATUS MESSAGE
            auth.authenticate("", "");
            System.out.println("auth signed out: " + auth.getAuth_message());

            // SIGNING IN AND GETTING AUTHENTICATION
            none.authenticate("mlewis","1403730359");
            System.out.println("'none' authenticated: " + none.getAuth_message());
            return none;
            
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("!!!!!AUTHENTICATION EXCEPTION ENCOUNTERED!!!!!");
            return null;
        }
    }

    /**        
    * Stock class test            
    */
    private String stockTests(){
        try {
            Stock gibbs = new Stock (StockType.Beads,"Gibbits", 150,50);
            gibbs.createStock();
            
            System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits"));
            System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 
            Stock.updateStock('+',499, "Gibbits"); 
            Stock.updateStock('+',499, "Panther"); 
            Stock.updateStock('-',500, "Gibbits");
            Stock.updateStock('-',500, "Panther");
            System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits")); 
            System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 
            for(Stock s:Stock.viewStock()){
                System.out.println(s.toString());
            }
    
            Stock sec = new Stock(StockType.Beads,"Panther", 9150,50);
            ;
            System.out.println("[TOSTRING() FROM CLASS TEST]: "+sec.toString());
            sec.createStock();
            System.out.println("[VIEWITEM(STOCKNAME) TEST]" + Stock.viewItem(sec.getStockName()).toString());
            return "Stock Tests completed successfully; \n";
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("!!!!!STOCK EXCEPTION ENCOUNTERED!!!!!");
            return "Stock Tests failed; \n";
        }
    }

    /**
     * Bracelet Tests
     */
    private String braceletTests(){
        try{
            Stock bark = new Stock(StockType.Beads,"Bark",200,50);
            bark.createStock();
            //Populating bracelet array with data that was in the databse before.
            Bracelet.populate();
            //Creating bracelet objects
            Bracelet bracelet1 = new Bracelet("King", 1200, "Gibbits-5;Panther-5","Gibbits-7;Panther-7","Gibbits-10;Panther-10","Royalty");
            Bracelet bracelet2= new Bracelet("Roots", 2200, "Gibbits-5;Bark-5","Gibbits-7;Bark-7","Gibbits-10;Bark-10","Nature");
            Bracelet bracelet3 = new Bracelet("River", 5000, "Bark-5;Panther-5","Bark-7;Panther-7","Bark-10;Panther-10","Nature");
            Bracelet bracelet4 = new Bracelet("Summer", 5000, "Bark-5;Panther-5","Bark-7;Panther-7","Bark-10;Panther-10","Seasons");
            Bracelet bracelet5 = new Bracelet("Summer", 5000, "Bark-5;Panther-5","Bark-7;Panther-7","Bark-10;Panther-10","Seasons");
            

            
            //Adding bracelets to database
            bracelet1.addToDatabase();
            bracelet2.addToDatabase();
            bracelet3.addToDatabase();
            bracelet4.addToDatabase();
            bracelet5.addToDatabase();


            //Estimate quantity of bracelets that can be made based on avalaible beads.
            System.out.println(bracelet1.estimateQty());
            System.out.println(bracelet2.estimateQty());
            System.out.println(bracelet3.estimateQty());
            
            //Update Bracelet
            Bracelet.updateBracelet("Roots", new Bracelet("Roots",2200, "Gibbits-5;Bark-5","Gibbits-7;Bark-7","Gibbits-10;Bark-10","Rasta"));

            //Delete Bracelet
            Bracelet.deleteBracelet("King");

            return "Bracelet Tests completed successfully; \n";

            
        }
        catch(Exception e){
            // System.out.println(e.getMessage()); 
            System.out.println("!!!!!BRACELET EXCEPTION ENCOUNTERED!!!!!");
            return "Bracelet Tests failed; \n";
        }
    }

    private String confirmations(Authentication a,String b, String c){
        String x = "\n\nAuthentication Tests failed; \n";
        if(a!=null){x = "\n\nAuthentication Tests completed successfully; \n";}
        a.logout();
        return x+b+c;
    }
    
    public static void main(String[] args) {
        /**
         * -----  COMMENT OUT TEST METHODS YOU DO NOT WANT TO RUN!!!!!              -----
         * -----  ENSURE AUTHENTICATIONTESTS() ALWAYS RUNS BEFORE YOUR CODE!!!!!    -----
         * System.out.println("password123".hashCode());
        */

        /*        * CREATING TEST OBJECT        */
        test t = new test();
        
        /*        * RUNNING TESTS               */
        Authentication a = t.authenticationTests(); //Authentication
        String b = t.stockTests(); //Stock
        String c = t.braceletTests(); //Bracelet

        /*        * END OF TESTS. CLOSING       */       
        System.out.println(t.confirmations(a,b,c));
        // PRINTING CONFIRMATIONS
}

}