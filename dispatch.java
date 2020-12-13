package os;

import java.util.*;

public class dispatch {
    ArrayList<node> ready_array = new ArrayList<node>();
    ArrayList<node> pend_array = new ArrayList<node>();
    ArrayList<node> reserve_array = new ArrayList<node>();
    ArrayList<node> wait_array = new ArrayList<node>();
    Memory memory;
    int segment = 6;



    public dispatch(Memory memory,int segment)
    {
        this.memory = memory;
        this.segment =segment;
    }


    boolean malloc(node node)
    {

        int a = Integer.parseInt(node.data.name);
        int size =node.data.memory_size;
        for(int i=0;i<memory.me.length-size;i++)
        {
            boolean flag=true;
            for(int j=0;j<size;j++)
            {
                if(memory.me[i+j] != 0)
                    flag=false;
            }
            if(flag)
            {
                for(int j=0;j<size;j++)
                {
                    memory.me[i+j] = a;

                }
                node.data.memory_point =i;
                memory.num++;
                return true;
            }
        }

        return false;

    }

    void free(node node)
    {
        for(int i =node.data.memory_point;i<node.data.memory_point+node.data.memory_size;i++)
        {
            memory.me[i]=0;
        }
        memory.num--;
    }

    boolean addnode2ready()
    {

        node node = reserve_array.get(0);
        if(malloc(node)){
            node.data.status=1;
            ready_array.add(node);
            reserve_array.remove(0);
            return true;
        }
        else
            return false;
    }

    void sortqueue(ArrayList<node> array)
    {
        Collections.sort(array, new Comparator<node>()
        {

            @Override
            public int compare(node o1, node o2) {
                if(o1.data.priority<=o2.data.priority)
                    return 1;
                else
                    return -1;

            }
        });
    }


    String run()
    {
        node head = ready_array.get(0);
        if(!findnode(pend_array,head.pre).data.name.equals("0"))
        {
            block(head);
        }
        else{
        head.data.time -= 1;
        head.data.priority -= 1;


        if (head.data.time <= 0) {
            ready_array.remove(head);
            free(head);
            // printMemory();
            return head.data.name;
        }
    }
        return "";
    }

    void operate()
    {

        run();
        sortqueue(ready_array);
    }

    void printList()
    {
        for(node eachnode: ready_array)
        {
           System.out.println(eachnode.data.name+" "
                  +eachnode.data.time+" "+eachnode.data.priority);
        }
    }

    void printnode()
    {
        node head = ready_array.get(0);
        System.out.println(head.data.name+" "
                +head.data.time+" "+head.data.priority);
    }


    void printMemory()
    {
        System.out.println("-----------------------------");
        for(int i=0;i<memory.me.length;i++)
        {
            System.out.print(memory.me[i]);
        }
        System.out.println();
        System.out.println("-----------------------------");
    }

    void block(node node)
    {
        ready_array.remove(node);
        node.data.status=3;
        wait_array.add(node);

    }


    void wakeup(node node)
    {
        wait_array.remove(node);
        node.data.status=1;
        ready_array.add(node);

    }

    void wake_scan()
    {
        for(node node : wait_array )
        {
            if(findnode(pend_array,node.pre).data.name.equals("0"))
                wakeup(node);
        }
        sortqueue(ready_array);
    }

    void suspend(node node)
    {
        ready_array.remove(node);
        node.data.status=4;
        free(node);
        pend_array.add(node);
    }

    void active(node node)
    {

        if(malloc(node)){
            pend_array.remove(node);
            node.data.status=1;

            ready_array.add(node);

            sortqueue(ready_array);
            wake_scan();
        }

    }


    public node findnode(ArrayList<node> array, String name){
            for(node eachnode : array)
            {
                if(eachnode.data.name.equals(name))
                {
                    return eachnode;
                }
            }
            node n = new node("0",0,0,0,0,"0");
            return n;
    }

    void addnode2reserve(node node)
    {
        reserve_array.add(node);
        sortqueue(reserve_array);
    }

    void loadnode()
    {
        while(ready_array.size()<segment&&reserve_array.size()>0
                        && addnode2ready())
        { }
    }






}
