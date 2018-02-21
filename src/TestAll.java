import java.util.Scanner;

class Main {
    
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter a polynomial like,  X^5 or X^2 - X + 1: ");  
        Polynomial p= new Polynomial(sc.nextLine());  
        System.out.println("Enter another polynomial: ");  
        Polynomial q= new Polynomial(sc.nextLine());
        testAll(p, q);
    }
    public static void testAll(Polynomial p, Polynomial q) throws Exception {
      System.out.println("Polynomials\np = " + p + "\nq = " + q);
      System.out.println("Sum " + p.add(q));
      System.out.println("Difference " + p.subtract(q));
      System.out.println("Product " + p.multiply(q));
      System.out.println("Quotient " + p.divide(q));
      System.out.println("Remainder " + p.remainder(q));
   }
}