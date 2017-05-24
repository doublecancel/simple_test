/**
 * Created by Administrator on 2017/5/22.
 */
public class Test extends Parent{


    private Temp temp1 = new Temp(1);

    {
        System.out.println("构造块");
    }


    public Test(){
        System.out.println("test construct............");
    }

    static {
        System.out.println("static..............");
    }


}
