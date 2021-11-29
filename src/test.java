import Authentication.Authentication;
class test {
    
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        /**
         * Authentication Tests
         */
        Authentication auth = new Authentication();
        Authentication none = new Authentication();
        auth.authenticate("mlewis","1403730359");
        none.authenticate("user", "pw");
        
        //testing login user's menu options
        for(String s : auth.getUserMenu()){
            System.out.println("Authenticated User's Menu item: "+s);
        }
        //testing unauthenticated user's menu options
        for(String s : none.getUserMenu()){
            System.out.println("Logged Out User's Menu item: "+s);
        }

        //confirming login/logout changes to state
        System.out.println("'auth' authenticated: "+auth.getAuth_message());
        System.out.println("'none' failed authentication: "+none.getAuth_message());
        auth.authenticate("", "");
        System.out.println("auth signed out: "+auth.getAuth_message());
        none.authenticate("mlewis","1403730359");
        System.out.println("'none' authenticated: "+none.getAuth_message());

        

        /**
        * Stock class test
        */
        //Stock gibbs = new Stock (StockType.Beads,"Gibbits", 150,50);
        //gibbs.createStock();
        int quantity = Stock.getQuantity("Gibbits");
        System.out.println("Quantity = "+ quantity);
        // Stock.updateStock('+',12, "Gibbits");
        // Stock.updateStock('-',500, "Gibbits");
        quantity = Stock.getQuantity("Gibbits");
        System.out.println("Quantity = "+ quantity);


        /**
        * Bracelet Tests
        */

        // Stock quartz = new Stock(StockType.Beads,"Qaurtz",300,40);
        // Stock hearts = new Stock(StockType.Beads,"Hearts",100,40);
        // Stock diamonds = new Stock(StockType.Beads,"Diamonds",2200,30);
        // quartz.createStock();
        // hearts.createStock();
        // diamonds.createStock();
        
        Bracelet.populate();
        //Bracelet bracelet1 = new Bracelet("King",1300.00,"Gibbit-10;Quartz-20","Gibbit-20;Quartz-30","Gibbit-30-Quartz-40","Royalty");
        //Bracelet bracelet2 = new Bracelet("Queen",1500.00,"Hearts-20;Diamonds-20","Hearts-30;Diamonds-30","Hearts-40;Diamonds-40","Royalty");
        //Bracelet bracelet3 = new Bracelet("Graphite",2500.00,"Quartz-20;Diamonds-10","Quartz-30;Diamonds-20","Quartz-40;Diamonds-30","Dark");

        //bracelet1.addToDatabase();
        //bracelet2.addToDatabase();
        //bracelet3.addToDatabase();

        Bracelet.updateBracelet("Graphite", new Bracelet("Prince",1300.00,"Gibbit-10;Quartz-20","Gibbit-20;Quartz-30","Gibbit-30-Quartz-60","Royalty"));

        for(int i =0; i < Bracelet.getBracelets().size(); i++){
            System.out.println(Bracelet.getBracelets().get(i).getName());
        }

        
        

    }
}