public class Fraction {
    private int n;
    private int d;
    public Fraction(int n, int d){
        this.n = n;
        if(d == 0)throw new ArithmeticException();
        this.d = d;
    }

    public void setNum(int n){
        this.n = n;
    }

    public int getNum(){
        return n/this.gcd();
    }

    public void setDenom(int d){
        if(d == 0){
            throw new ArithmeticException();
        }
        this.d = d;
    }

    public int getDenom(){
        return d/this.gcd();
    }

    public Fraction add(Fraction a){
        int tempDenom = a.getDenom();
        a.setDenom(a.getDenom() * this.d);
        a.setNum(a.getNum() * this.d + tempDenom * this.n);
        int gcdNum = a.gcd();
        a.setDenom(a.getDenom() / gcdNum);
        a.setNum(a.getNum() / gcdNum);
        return a;
    }

    public int gcd(){
        int tempNum = n;
        int tempDenom = d;
        while(true) {
            int r0 = tempNum % tempDenom;
            if (r0 == 0) {
                return tempDenom;
            } else {
                tempNum = tempDenom;
                tempDenom = r0;
            }
        }
    }

    public boolean equals(Fraction a){
            return a.getNum() * this.getDenom() == a.getDenom() * this.getNum();
    }

    @Override
    public String toString(){
        return this.getNum() + "/" + this.getDenom();
    }

    public static void main(String[] args){
        Fraction temp = new Fraction(4,6);
        System.out.println(temp.gcd());
        System.out.println(temp.add(new Fraction(2,4)).getDenom());
        System.out.println(temp.add(new Fraction(2,4)).getNum());
        System.out.println(temp.equals(new Fraction(2,3)));
        System.out.println(temp.equals(new Fraction(2,4)));
        System.out.println(temp.toString());

        Fraction temp1 = new Fraction(4,0);

    }
}
