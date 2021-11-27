import Authentication.Authentication;
class test {
    public static void main(String[] args) {
        System.out.println("password123".hashCode());
        Authentication auth = new Authentication();
        Authentication none = new Authentication();
        auth.authenticate("mlewis","1403730359");
        none.authenticate("user", "pw");

        System.out.println("'auth' authenticated: "+auth.getAuth_message());
        System.out.println("'none' failed authentication: "+none.getAuth_message());
        auth.authenticate("", "");
        System.out.println("auth signed out: "+auth.getAuth_message());
        none.authenticate("mlewis","1403730359");
        System.out.println("'none' authenticated: "+none.getAuth_message());
}
}
