/**
 * Created by Administrator on 2017/5/22.
 */
public class Parent {


    private Temp temp = new Temp(0);

    {
        System.out.println("构造块");
    }



    public Parent(){
        System.out.println("Parent construct..............");
    }



    static {
        System.out.println("parent static.................");
    }


}
