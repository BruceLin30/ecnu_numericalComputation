import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



import java.awt.Graphics;


public class NumericalIntegrating{

    static String ModeType = new String("NULL");//默认的初始模式是空模式
    static int ModeTypeInt = 0;

    static Graphics g;
    static JFrame frame = new JFrame();
    //static DrawFunction frame = new DrawFunction();
    static String result = new String("");
    static JLabel Jresult;
    static JTextField jFieldMode = new JTextField(80);//模式选择
    static JTextField jFieldResult = new JTextField(80);
    
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.drawLine(100, 100, 300, 300);
    }
    public static void main(String[] args) {
        System.out.println("Test Success!");
        NumericalIntegrating pola = new NumericalIntegrating();
        
        pola.initMenuBar();//初始化菜单栏
        pola.initUI();//初始化UI界面
        
        //Lagrange l2 = new Lagrange();
        //l2.setData(xx, yy);
        //System.out.println(l2.calcluate(0.596));
        //NewTon newTon = new NewTon();y
        //newTon.setData(xx, yy);
        //System.out.println(newTon.calculate(0.596));
        
    }
/*
public void processInput(String sx,String sy)
    {
        String [] strx = sx.split(" ");
        String [] stry = sy.split(" ");
        doublex = new double[strx.length];
        doubley = new double[stry.length];
        for (int i = 0; i < strx.length;i++)
            doublex[i] = Double.parseDouble(strx[i]);
        for (int i = 0;i < stry.length;i++)
            doubley[i] = Double.parseDouble(stry[i]);
    }
    public void processInput(String sw)
    {
        String [] strw = sw.split(" ");
        doublew = new double[strw.length];
        for (int i = 0;i < strw.length;i++)
            doublew[i] = Double.parseDouble(strw[i]);
    }
*/
    
    
    public void initUI()
    {
        

        /**
         * 这里是对frame的设置
         */
        frame.setSize(800,600);//设置容器尺寸
        frame.setLayout(new BorderLayout());
        //frame.setLayout(null);//设置布局
        //frame.addPanel();

        /**
         * 中间容器
         */
        JPanel p2 = new JPanel(){

            public void paint(Graphics g)
            {
                super.paint(g);
                g.drawLine(350, 100, 500, 400);
            }
        };
        JPanel p = new JPanel();
        //p.setSize(300,300);
        //p.setPreferredSize(new Dimension(300,300));
        p.setLayout(null);
        p.setOpaque(false);
        //p.setSize(200,200);
        //p.setBackground(Color.BLUE);
        
        /**
         * 这里是对labels的设置
         */
        JLabel label = new JLabel("数值积分");
        label.setBounds(20, 20, 100, 20);
        label.setForeground(Color.BLUE);
        p.add(label);
        JLabel label2 = new JLabel("请输入积分上下限：");
        label2.setBounds(20, 100, 100, 20);
        p.add(label2);
        /*
        JLabel label3 = new JLabel("请输入Y向量");
        label3.setBounds(20, 140, 100, 20);
        p.add(label3);
        JLabel label4 = new JLabel("请输入Z向量");
        label4.setBounds(20, 180, 100, 20);
        p.add(label4);
        */
        JLabel label5 = new JLabel("积分结果：");
        label5.setBounds(20, 160, 100, 20);
        p.add(label5);
        
        
        
        //frame.add(label);
        /**
         * JTextField的设置
         * 创建文本框，指定可见列数为80列
         */
        jFieldMode.setText("当前函数：未选择");
        jFieldMode.setEditable(false);
        jFieldMode.setBounds(200, 20, 120, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldMode);
        //frame.add(jFieldMode);
        final JTextField jFieldX = new JTextField(80);
        jFieldX.setBounds(200, 100, 200, 30);
        p.add(jFieldX);
        //frame.add(jFieldX);
        /*
        final JTextField jFieldY = new JTextField(80);
        jFieldY.setBounds(100, 140, 200, 30);
        p.add(jFieldY);
        */
        //frame.add(jFieldY);
        /*
        final JTextField jFieldW = new JTextField(80);
        jFieldW.setBounds(100, 180, 200, 30);
        p.add(jFieldW);
        */
        //frame.add(jFieldW);
        jFieldResult = new JTextField(80);
        jFieldResult.setEditable(false);
        jFieldResult.setBounds(200, 160, 200, 30);
        p.add(jFieldResult);
        

        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(200, 225, 200, 40);//设置按钮在容器中的位置

        p.add(button1);

        button1.addActionListener(new ActionListener()//对按钮增加监听
        {   
            //此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                sx = jFieldX.getText();
                sy = jFieldY.getText();
                sw = jFieldW.getText();
                processInput(sw);
                processInput(sx, sy);

                if (ModeTypeInt == 1)
                {
                    lag.setData(doublex, doubley);
                    System.out.println("click2");
                    updateUI(lag);
                }
                if (ModeTypeInt == 2)
                {
                    nTon.setData(doublex, doubley);
                    updateUI(nTon);
                }
                if (ModeTypeInt == 3)
                {
                    seg.setData(doublex,doubley);
                    updateUI(seg);
                }
                */

            }   
        });
        
        /**
         * 这里是函数结尾的必要设置
         */

        frame.getContentPane().add(p2);
        frame.getContentPane().add(p);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//界面结束后关闭程序
        frame.setLocationRelativeTo(null);//在屏幕上居中显示框架
        frame.setVisible(true);//界面可视化，需要放在最后面，对所有的组件进行渲染。 
    }
    public void initMenuBar()
    {
        JMenu Menu;
        JMenuItem funItem1,funItem2,funItem3,funItem4;
        JMenuBar menuBar = new JMenuBar();
        
        funItem1 = new JMenuItem("函数1");
        funItem2 = new JMenuItem("函数2");
        funItem3 = new JMenuItem("函数3");
        funItem4 = new JMenuItem("函数4");
        Menu = new JMenu("积分函数选择");
        Menu.add(funItem1);
        Menu.add(funItem2);
        Menu.add(funItem3);
        Menu.add(funItem4);
        Menu.setSelected(true);
        menuBar.add(Menu);
        frame.setJMenuBar(menuBar);
        funItem1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //updateModeStr("lag");
                System.out.println("当前函数：1");
            }
        });
        funItem2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //updateModeStr("newton");
                System.out.println("当前函数：2");
            }
        });
        funItem3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //updateModeStr("seg");   
                System.out.println("当前函数: 3");
            }
            
        });
        funItem4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //updateModeStr("seg");   
                System.out.println("当前函数：4");
            }
            
        });
    }
    public void updateModeStr(String M)//mode表示模式的意思，即插值的类型
    {
        if (M == "lag"){
            ModeType = new String("当前：拉格朗日插值");
            ModeTypeInt = 1;
            jFieldMode.setText(ModeType);
        }
        else if (M == "newton")
        {
            ModeType = new String("当前：牛顿插值");
            ModeTypeInt = 2;
            jFieldMode.setText(ModeType);
        }
        else if (M == "seg")
        {
            ModeType = new String("当前：分段插值");
            ModeTypeInt = 3;
            jFieldMode.setText(ModeType);
        }
            
    }
}
