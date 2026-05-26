package setbq1;

abstract class Calculation {

    int add(int x, int y) {
        return x + y;
    }

    abstract int mul(int x, int y);
}

public class SetBQ1 extends Calculation {

    int mul(int x, int y) {
        return x * y;
    }

    int calcDiv(int x, int y) {
        return x / y;
    }

    public static void main(String[] args) {

        SetBQ1 d = new SetBQ1();
        
        System.out.println("Using Child Class Reference:");
        System.out.println("Add = " + d.add(10,5));
        System.out.println("Mul = " + d.mul(10,5));
        System.out.println("Div = " + d.calcDiv(10,5));

        Calculation c = new SetBQ1();

        System.out.println("\nUsing Abstract Class Reference:");
        System.out.println("Add = " + c.add(20,10));
        System.out.println("Mul = " + c.mul(20,10));
    }
}