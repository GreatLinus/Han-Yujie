package os;

import java.util.ArrayList;

public class node {
    PCB data=new PCB();
    String pre;
    ArrayList<node> next;

    public node(String name,int time,int status,int priority,
                    int memory_size,String prename)
    {
        this.data.name=name;
        this.data.time=time;
        this.data.status=status;
        this.data.priority=priority;
        this.data.memory_size=memory_size;
        //this.data.cooperation=cooperation;
        this.pre = prename;

    }

}


