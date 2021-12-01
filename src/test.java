import Authentication.Authentication;

class test {
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        /**
         * Authentication Tests
         */
        Authentication auth = new Authentication();
        //Authentication none = new Authentication();
        auth.authenticate("mlewis","password123");
        // //none.authenticate("user", "pw");

        
        Customer.populate();

        //Testing getcusID Preventing Duplication
        // mercedes.addToDatabase();
        // marlon.addToDatabase();
        // gabriel.addToDatabase();
        System.out.println(Customer.getCusId("Mercedes", "8768164681", "Sedecrem"));
        System.out.println(Customer.getCusId("Mercedes", "8768164681", "Sedecrem"));
        System.out.println(Customer.getCusId("Callay", "8769654681", "Spanish"));
        System.out.println(Customer.getCusId("Taye-Vaughn", "8769654781", "Mobay"));
        System.out.println(Customer.getCusId("Kimani", "8769654781", "Mobay"));
        System.out.println(Customer.getCusId("Gabriel", "8769654781", "Mobay"));

        //Deleting customer
        Customer.deleteCustomer(Customer.getCusId("Taye-Vaughn", "8769654781", "Mobay"));


        //Testing populate()
        for (int i = 0; i < Customer.getCustomers().size(); i++)
        {
            System.out.println(Customer.getCustomers().get(i).getcustomerName());
        } 

        //Testing update customer()
    


        auth.logout();




}
}