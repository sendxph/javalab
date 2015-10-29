class Demo1 {
    public static void main(String[] args) {
        int num = 123;
        System.out.println("Origin number is " + num);

        num += 2;
        System.out.println("+=2 is " + num);

        num <<= 2;
        System.out.println("<<=2 is " + num);

        num >>= 2;
        System.out.println(">>=2 is " + num);
    }
}
