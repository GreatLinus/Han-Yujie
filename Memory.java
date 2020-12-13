package os;

public class Memory {
    int me[] ;
    int num =0;
    int maxnum;
    public Memory(int size,int maxnum)
    {
        me = new int[size];
        this.maxnum = maxnum;
    }
}
