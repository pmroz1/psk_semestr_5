public class zad1 {

    public static void main(String[] args)
    {
        Triangle tr = new Triangle (0,0,50,0,0);
        System.out.println(tr);

        tr = tr.ChangeHeight(30);
        System.out.println(tr);

        tr = tr.ChangeWidth(50);
        System.out.println(tr);

        tr = tr.Rotate();
        System.out.println(tr);

        tr = tr.ChangeX(10);
        System.out.println(tr);

        tr = tr.ChangeY(10);
        System.out.println(tr);
    }

}