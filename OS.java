package os;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class OS implements ActionListener{

    Memory memory = new Memory(25,5);
    dispatch dis = new dispatch(memory,5);



    private JFrame f;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel Label6;
    private JLabel Label7;
    private JLabel Label8;
    private JLabel Labelname = new JLabel("进程名");
    private JLabel Labelpriority = new JLabel("时间");
    private JLabel Labelt = new JLabel("状态");
    private JLabel Labelpar= new JLabel("优先级");
    private JLabel Labelsize= new JLabel("大小");

    private JButton runButton;
    private JButton pendButton;
    private JButton activeButton;
    private JButton addButton;

    private JTextField text1 = new JTextField(3);
    private JTextField text2 = new JTextField(3);
    private JTextField text3 = new JTextField(3);
    private JTextField text4 = new JTextField(3);
    private JTextField text5 = new JTextField(3);
    private JTextField text6 = new JTextField(3);//p_add

    private JTextField text7 = new JTextField(5);
    private JTextField text8 = new JTextField(5);//cpu

    private JTextField text9 = new JTextField(3);//挂起
    private JTextField text10 = new JTextField(10);

    private JTextField text11 = new JTextField(30);
    private JTextField text12 = new JTextField();
    private JTextField text13 = new JTextField();


    JTextArea  ta1;
    JTextArea  ta2=new JTextArea(8,11);
    JTextArea  ta3=new JTextArea(8,11);
    JTextArea  ta4=new JTextArea(8,11);

    private JOptionPane box;

    JPanel panel_add = new JPanel();
    JPanel panel_reserve = new JPanel();
    JPanel panel_memory = new JPanel();
    JPanel panel_queue = new JPanel();
    JPanel panel_cpu = new JPanel();
    JPanel panel_finish = new JPanel();
    JPanel panel_pend = new JPanel();
    Color tColor[] = new Color[10];
    private JTextField memoryText[] = new JTextField[25];

    void initcolor()
    {

        tColor[0] = new Color(255,255,128);
        tColor[1] = new Color(0,255,255);
        tColor[2] = new Color(255,128,128);
        tColor[3] = new Color(128,255,128);
        tColor[4] = new Color(255,0,0);
        tColor[5] = new Color(255,0,255);
        tColor[6] = new Color(255,128,0);
        tColor[7] = new Color(0,128,255);
        tColor[8] = new Color(208,208,0);
        tColor[9] = new Color(215,174,174);

    }

    public void init()
    {

        JFrame f = new JFrame("进程调度");
        f.setSize(800,1000);
        f.setLocation(200,200);
        int r = 43;
        int g = 186;
        int b = 240;
        Color bgColor = new Color(r, g, b);
        int j =1;
        initcolor();

        // 设置背景颜色
        f.setBackground(bgColor);

        panel_add.setLayout(new FlowLayout());//使用流式布局法
        panel_add.setBackground(bgColor);


        runButton = new JButton("运行");
        pendButton = new JButton("挂起");
        activeButton = new JButton("解挂");
        addButton =new JButton("添加进程");

        addButton.addActionListener(this);
        pendButton.addActionListener(this);
        activeButton.addActionListener(this);
        runButton.addActionListener(this);



        Label1=new JLabel("后备队列");
        Label2=new JLabel("就绪队列");
        Label3=new JLabel("挂起队列");
        Label4=new JLabel("阻塞队列");
        Label5=new JLabel("CPU0");
        Label6=new JLabel("CPU1");
        Label7=new JLabel();
        Label8 =new JLabel();



        panel_add.add(new JLabel("进程添加"));
        panel_add.add(Labelname);
        panel_add.add(Labelpriority);
        panel_add.add(Labelt);
        panel_add.add(Labelpar);
        panel_add.add(Labelsize);
        panel_add.add(new JLabel("前驱"));

        panel_add.add(text1);
        panel_add.add(text2);
        panel_add.add(text3);
        panel_add.add(text4);
        panel_add.add(text5);
        panel_add.add(text6);
        panel_add.add(addButton);


        panel_reserve.add(new JLabel("后备进程"));
        panel_reserve.add( new JLabel("进程名"));
        panel_reserve.add(new JLabel("时间"));
        panel_reserve.add(new JLabel("状态"));
        panel_reserve.add(new JLabel("优先级"));
        panel_reserve.add(new JLabel("大小"));


        panel_reserve.add(ta1=new JTextArea(8,25));


        panel_cpu.add(new JLabel("处理机"));
        panel_cpu.add(new JLabel("CPU0"));

        panel_cpu.add(text7);
        panel_cpu.add(new JLabel("CPU1"));
        panel_cpu.add(text8);

        panel_queue.add(new JLabel("就绪队列"));

        panel_queue.add(ta2);
        panel_queue.add(new JLabel("挂起队列"));
        panel_queue.add(ta3);
        panel_queue.add(new JLabel("阻塞"));
        panel_queue.add(ta4);


        panel_pend.add(new JLabel("输入欲操作进程名"));
        panel_pend.add(text9);;
        panel_pend.add(pendButton);
        panel_pend.add(activeButton);

        panel_finish.add(new JLabel("已完成进程"));
        panel_finish.add(text10);
        panel_finish.add(runButton);

        panel_memory.add(new JLabel("内存"));
        //panel_memory.add(text11);



        panel_memory.setLayout(new GridLayout());
        for(int i=0;i<memoryText.length;i++)
        {
            memoryText[i] = new JTextField(2);
            memoryText[i].setText(i +"");
            panel_memory.add(memoryText[i]);
        }



        Box vBox = Box.createVerticalBox();
        vBox.add(panel_add);
        //f.add(panel_add);
        vBox.add(panel_reserve);
        vBox.add(panel_cpu);
        vBox.add(panel_memory);
        vBox.add(panel_queue);
        vBox.add(panel_pend);
        vBox.add(panel_finish);

        f.setContentPane(vBox);

        f.pack();
        f.setLocationRelativeTo(null);
/**
        f.getContentPane().add(panel_cpu);
        f.getContentPane().add(panel_finish);
        f.getContentPane().add(panel_queue);

        f.getContentPane().add(panel_memory);
        */
        f.setVisible(true);
        drawMemory();


    }

    void refrash()
    {
        ta1.setText("");
        ta2.setText("");
        ta3.setText("");
        ta4.setText("");
        for(node node : dis.reserve_array)
        {
            ta1.append(node.data.name+" "+node.data.time+" "+node.data.status+" "
                    +node.data.priority+" "+node.data.memory_size+" "+node.pre+"\r\n");
        }

        for(node node : dis.ready_array)
        {
            ta2.append(node.data.name+" "+node.data.time+" "+node.data.status+" "
                    +node.data.priority+" "+node.data.memory_size+" "+node.pre+"\r\n");
        }

        for(node node : dis.pend_array)
        {
            ta3.append(node.data.name+" "+node.data.time+" "+node.data.status+" "
                    +node.data.priority+" "+node.data.memory_size+" "+node.pre+"\r\n");
        }

        for(node node : dis.wait_array)
        {
            ta4.append(node.data.name+" "+node.data.time+" "+node.data.status+" "
                    +node.data.priority+" "+node.data.memory_size+" "+node.pre+"\r\n");
        }


        for (int i=0;i<memory.me.length;i++ )
        {
            int te = memory.me[i];
            if( te != 0)
            {
                memoryText[i].setBackground(tColor[te]);
                memoryText[i].setText(""+te);
            }
            if(te == 0)
            {
                memoryText[i].setBackground(null);
                memoryText[i].setText("0");

            }
        }


        dis.wake_scan();
    }


    void drawMemory()
    {
        //g.drawRect(10,10,10,10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //try {
            if (e.getSource() == addButton) {
                if (!text1.getText().equals("")) {
                    String name = text1.getText();
                    int time = Integer.parseInt(text2.getText());
                    int status = Integer.parseInt(text3.getText());
                    int priority = Integer.parseInt(text4.getText());
                    int memorysize = Integer.parseInt(text5.getText());
                    String prename = text6.getText();

                    node node = new node(name, time, status, priority, memorysize, prename);
                    dis.addnode2reserve(node);
                    refrash();

                }
            } else if (e.getSource() == runButton) {

                dis.loadnode();
                node node1 = dis.ready_array.get(0);
                text7.setText(node1.data.name);
                String str=node1.data.name;
                if (node1.data.time==1) {
                    String s = text10.getText() + " " + str;
                    text10.setText(s);
                }
                dis.run();
                dis.sortqueue(dis.ready_array);



                if (dis.ready_array.size() >1) {

                    node node2 = dis.ready_array.get(0);
                    if (node2.data.name.equals(node1.data.name))
                    {
                        node2=dis.ready_array.get(1);
                    }
                    text8.setText(node2.data.name);
                    String str2=node1.data.name;
                    if (node2.data.time==1) {
                        String s = text10.getText() + " " + str2;
                        text10.setText(s);
                    }
                    dis.run();
                    dis.sortqueue(dis.ready_array);
                } else
                    text8.setText("");


                refrash();

            } else if (e.getSource() == pendButton) {
                node node = dis.findnode(dis.ready_array, text9.getText());
                if (!node.data.name.equals("0")) {
                    dis.suspend(node);
                    refrash();
                }

            } else if (e.getSource() == activeButton) {
                node node = dis.findnode(dis.pend_array, text9.getText());
                if (!node.data.name.equals("0")) {
                    dis.active(node);
                    refrash();
                }

            }

        }
       // catch(Exception ex) {}
    //}


    public static void main(String[] args) {
        OS os = new OS();
        os.init();
    }
}
