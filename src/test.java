
import Authentication.Authentication;
class test {
    
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        /**
         * Authentication Tests
         */
        try {
            Authentication auth = new Authentication();
            Authentication none = new Authentication(); 
            System.out.println("role: "+auth.authenticate("mlewis","1403730359"));
            System.out.println("role: "+none.authenticate("user", "pw"));
            
            //testing login user's menu options
            for(String s : auth.getUserMenu()){
                System.out.println("Authenticated User's Menu item: "+s);
            }

            //confirming login/logout changes to state
            System.out.println("authenticated: "+auth.getAuth_message());
            System.out.println("NOT authenticated: "+none.getAuth_message()); 
            auth.authenticate("", "");
            System.out.println("auth signed out: "+auth.getAuth_message());
            none.authenticate("mlewis","1403730359");
            System.out.println("'none' authenticated: "+none.getAuth_message());
            
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("!!!!!AUTHENTICATION EXCEPTION ENCOUNTERED!!!!!");
        }
        

        /**
        * Stock class test
        */
        // try {
        //     Stock gibbs = new Stock (StockType.Beads,"Gibbits", 150,50);
        //     gibbs.createStock();
            
        //     //System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits"));
        //     //System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 
        //     //Stock.updateStock('+',499, "Gibbits"); 
        //     //Stock.updateStock('+',499, "Panther"); 
        //     //Stock.updateStock('-',500, "Gibbits");
        //     //Stock.updateStock('-',500, "Panther");
        //     System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits")); 
        //     System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 
        //     for(Stock s:Stock.viewStock()){
        //         System.out.println(s.toString());
        //     }
    
        //     Stock sec = new Stock(StockType.Beads,"Panther", 9150,50);
        //     ;
        //     System.out.println("[TOSTRING() FROM CLASS TEST]: "+sec.toString());
        //     sec.createStock();
        //     System.out.println("[VIEWITEM(STOCKNAME) TEST]" + Stock.viewItem(sec.getStockName()).toString());
        // } catch (Exception e) {
        //     // System.out.println(e.getMessage()); 
        //     System.out.println("!!!!!STOCK EXCEPTION ENCOUNTERED!!!!!");
        // }


        /**
        * Bracelet Tests
        */

        Stock quartz = new Stock(StockType.Beads,"Quartz",300,40);
        Stock hearts = new Stock(StockType.Beads,"Hearts",100,40);
        Stock diamonds = new Stock(StockType.Beads,"Diamonds",2200,30);
        Stock gibbits = new Stock(StockType.Beads,"Gibbit",2200,30);
        quartz.createStock();
        hearts.createStock();
        diamonds.createStock();
        gibbits.createStock();
        Bracelet.populate();
        //Bracelet bracelet1 = new Bracelet("King",1300.00,"Gibbit-10;Quartz-20","Gibbit-20;Quartz-30","Gibbit-30-Quartz-40","Royalty");
        //Bracelet bracelet2 = new Bracelet("Queen",1500.00,"Hearts-20;Diamonds-20","Hearts-30;Diamonds-30","Hearts-40;Diamonds-40","Royalty");
        //Bracelet bracelet3 = new Bracelet("Graphite",2500.00,"Quartz-20;Diamonds-10","Quartz-30;Diamonds-20","Quartz-40;Diamonds-30","Dark");

        //bracelet1.addToDatabase();
        //bracelet2.addToDatabase();
        //bracelet3.addToDatabase();
        try{
            Bracelet.populate();
            System.out.println(Bracelet.getBracelets().get(0).estimateQty());
            System.out.println(Bracelet.getBracelets().get(1).estimateQty());
            System.out.println(Bracelet.getBracelets().get(2).estimateQty());
            //System.out.println(b.getName());
            //System.out.println(b.getBracelets());
            //System.out.println(b.getID());
            //System.out.println(b.getCollection());
            //System.out.println(Bracelet.getBraceletIndex("b1"));
            // Bracelet b = Bracelet.searchByName("b1");
            //Bracelet.deleteBracelet("b1");
        }
        catch(Exception e){
            // System.out.println(e.getMessage()); 
            System.out.println("!!!!!BRACELET EXCEPTION ENCOUNTERED!!!!!");
        }

}

}