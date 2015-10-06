class Throws1Demo {
    public int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }
     
    public static void main(String[] args) {
        Throws1Demo t = new Throws1Demo();
         
        try {
            System.out.println(t.divide(15, 20));
            System.out.println(t.divide(10, 2));
            System.out.println(t.divide(63, 9));
            System.out.println(t.divide(2, 0));
        }
        catch (ArithmeticException ex) {
            System.out.println("發生算術錯誤");
        }
    }
}