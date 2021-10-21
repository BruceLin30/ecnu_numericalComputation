import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.Graphics;

public class NumericalIntegrating {

    static String strValueN = new String();
    static String strValueABE = new String();
    static int valueN;
    static double valueA,valueB,valueE;
    static int flag = 0;//0表示没有e，1表示有e
    static String FunType = new String("NULL");// 默认的初始模式是空模式
    static String ModeType = new String("NULL");
    static int FunTypeInt = 0;//1 2 3 4
    static int ModeTypeInt = 0;//5 6 7
    static Graphics g;
    static JFrame frame = new JFrame();
    // static DrawFunction frame = new DrawFunction();
    static String result = new String("");
    static JLabel Jresult;
    static JTextField jFieldMode = new JTextField(120);// 模式选择
    static JTextField JFieldFun = new JTextField(120);
    static JTextField jFieldResult = new JTextField(80);
    static fun f = new fun();
    public static void main(String[] args) {
        System.out.println("Test Success!");
        NumericalIntegrating NI = new NumericalIntegrating();

        NI.initMenuBar();// 初始化菜单栏
        NI.initUI();// 初始化UI界面
        /*
        fun f = new fun();
        f.setData(0, 1, 20, 4);
        f.setEpsilon(0.001);
        System.out.println(f.calculateFun(1));
        System.out.println(f.calculateFun(2));
        System.out.println(f.calculateFun(3));
        */
    }
    /*
     * public void processInput(String sx,String sy) { String [] strx =
     * sx.split(" "); String [] stry = sy.split(" "); doublex = new
     * double[strx.length]; doubley = new double[stry.length]; for (int i = 0; i <
     * strx.length;i++) doublex[i] = Double.parseDouble(strx[i]); for (int i = 0;i <
     * stry.length;i++) doubley[i] = Double.parseDouble(stry[i]); } public void
     * processInput(String sw) { String [] strw = sw.split(" "); doublew = new
     * double[strw.length]; for (int i = 0;i < strw.length;i++) doublew[i] =
     * Double.parseDouble(strw[i]); }
     */
    public void processInput(String strABEN)
    {
        String [] str = strABEN.split(" ");
        if (str.length == 1)//一个参数，只有N
        {
            valueN = Integer.valueOf(str[0]);
        }
        else if (str.length == 2)//两个参数，没有e,只有AB
        {
            valueA = Double.parseDouble(str[0]);
            valueB = Double.parseDouble(str[1]);
            flag = 0;
        }
        else if (str.length == 3)//三个参数ABE
        {
            valueA = Double.parseDouble(str[0]);
            valueB = Double.parseDouble(str[1]);
            valueE = Double.parseDouble(str[2]);
            flag = 1;
        }
    }
    public void updateUI(double res)
    {
        String restr = String.valueOf(res);
        jFieldResult.setText(restr);
    }
    public void initUI() {

        /**
         * 这里是对frame的设置
         */
        frame.setSize(800, 600);// 设置容器尺寸
        frame.setLayout(new BorderLayout());
        // frame.setLayout(null);//设置布局
        // frame.addPanel();

        /**
         * 中间容器
         */
        JPanel p2 = new JPanel() {

            public void paint(Graphics g) {
                super.paint(g);
                g.drawLine(350, 100, 500, 400);
            }
        };
        JPanel p = new JPanel();
        // p.setSize(300,300);
        // p.setPreferredSize(new Dimension(300,300));
        p.setLayout(null);
        p.setOpaque(false);
        // p.setSize(200,200);
        // p.setBackground(Color.BLUE);

        /**
         * 这里是对labels的设置
         */
        JLabel label = new JLabel("数值积分函数：");
        label.setBounds(20, 20, 130, 20);
        label.setForeground(Color.BLUE);
        p.add(label);
        JLabel labelfun = new JLabel("数值积分公式：");
        labelfun.setBounds(20, 70, 130, 20);
        labelfun.setForeground(Color.BLUE);
        p.add(labelfun);
        JLabel label2 = new JLabel("请输入积分上下限(请输入非分数)：");
        label2.setBounds(20, 120, 130, 20);
        p.add(label2);
        JLabel labelPrompt = new JLabel("输入格式：a b e(若有)");
        labelPrompt.setBounds(20, 135, 130, 20);
        p.add(labelPrompt);
        JLabel label3 = new JLabel("请输入积分步长中的n(偶数)：");
        label3.setBounds(20, 180, 180, 20);
        p.add(label3);
        /*
         * JLabel label3 = new JLabel("请输入Y向量"); label3.setBounds(20, 140, 100, 20);
         * p.add(label3); JLabel label4 = new JLabel("请输入Z向量"); label4.setBounds(20,
         * 180, 100, 20); p.add(label4);
         */
        JLabel label5 = new JLabel("积分结果：");
        label5.setBounds(20, 240, 100, 20);
        p.add(label5);

        // frame.add(label);
        /**
         * JTextField的设置 创建文本框，指定可见列数为80列
         */
        jFieldMode.setText("当前函数：未选择");
        jFieldMode.setEditable(false);
        jFieldMode.setBounds(250, 20, 200, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldMode);

        JFieldFun.setText("当前积分公式：未选择");
        JFieldFun.setEditable(false);
        JFieldFun.setBounds(250, 70, 200, 30);
        JFieldFun.setForeground(Color.RED);
        p.add(JFieldFun);
        
        final JTextField jFieldX = new JTextField(80);
        jFieldX.setBounds(250, 120, 200, 30);
        p.add(jFieldX);
        final JTextField jFieldn = new JTextField(80);
        jFieldn.setBounds(250, 180, 200, 30);
        p.add(jFieldn);
        jFieldResult = new JTextField(80);
        jFieldResult.setEditable(false);
        jFieldResult.setBounds(250, 240, 200, 30);
        p.add(jFieldResult);

        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(250, 310, 200, 40);// 设置按钮在容器中的位置

        p.add(button1);

        button1.addActionListener(new ActionListener()// 对按钮增加监听
        {
            // 此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * sx = jFieldX.getText(); sy = jFieldY.getText(); sw = jFieldW.getText();
                 * processInput(sw); processInput(sx, sy);
                 * 
                 * if (FunTypeInt == 1) { lag.setData(doublex, doubley);
                 * System.out.println("click2"); updateUI(lag); } if (FunTypeInt == 2) {
                 * nTon.setData(doublex, doubley); updateUI(nTon); } if (FunTypeInt == 3) {
                 * seg.setData(doublex,doubley); updateUI(seg); }
                 */
                String tmpStrABE = new String("");
                String tmpStrN = new String("");
                tmpStrABE = jFieldX.getText();
                tmpStrN = jFieldn.getText();

                processInput(tmpStrABE);
                processInput(tmpStrN);
                
                if (flag == 0)
                {
                    f.setData(valueA, valueB, valueN, FunTypeInt);
                }
                else if (flag == 1)
                {
                    f.setData(valueA, valueB, valueN, FunTypeInt);
                    f.setEpsilon(valueE);
                }
                System.out.println(ModeTypeInt);
                System.out.println(f.calculateFun(ModeTypeInt));
                updateUI(f.calculateFun(ModeTypeInt));
                
                /*
                System.out.println(valueA);
                System.out.println(valueB);
                System.out.println(valueE);
                System.out.println(valueN);
                */
                
            }
        });

        /**
         * 这里是函数结尾的必要设置
         */

        frame.getContentPane().add(p2);
        frame.getContentPane().add(p);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 界面结束后关闭程序
        frame.setLocationRelativeTo(null);// 在屏幕上居中显示框架
        frame.setVisible(true);// 界面可视化，需要放在最后面，对所有的组件进行渲染。
    }

    public void initMenuBar() {
        JMenu Menu1, Menu2;
        JMenuItem funItem1, funItem2, funItem3, funItem4;
        JMenuItem trape, simpson, romberg;
        JMenuBar menuBar = new JMenuBar();

        funItem1 = new JMenuItem("函数1");
        funItem2 = new JMenuItem("函数2");
        funItem3 = new JMenuItem("函数3");
        funItem4 = new JMenuItem("函数4");
        trape = new JMenuItem("复合梯形公式");
        simpson = new JMenuItem("复合Simpson公式");
        romberg = new JMenuItem("Romberg算法");
        Menu1 = new JMenu("积分函数选择");
        Menu2 = new JMenu("积分公式选择");

        Menu1.add(funItem1);
        Menu1.add(funItem2);
        Menu1.add(funItem3);
        Menu1.add(funItem4);
        Menu1.setSelected(true);
        Menu2.add(trape);
        Menu2.add(simpson);
        Menu2.add(romberg);
        Menu2.setSelected(true);
        menuBar.add(Menu1);
        menuBar.add(Menu2);
        frame.setJMenuBar(menuBar);
        funItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("lag");
                updateModeStr(1);
                System.out.println("当前函数：1");
            }
        });
        funItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                updateModeStr(2);
                System.out.println("当前函数：2");
            }
        });
        funItem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(3);
                System.out.println("当前函数: 3");
            }

        });
        funItem4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(4);
                System.out.println("当前函数：4");
            }

        });
        trape.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                // updateModeStr(4);
                updateModeStr(5);
                System.out.println("当前积分公式：复化梯形积分");
            }
        });
        simpson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                // updateModeStr(4);
                updateModeStr(6);
                System.out.println("当前积分公式：复化Simpson积分");
            }
        });
        romberg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                // updateModeStr(4);
                updateModeStr(7);
                System.out.println("当前积分公式：Romberg积分");
            }
        });
    }

    public void updateModeStr(int num)// mode表示模式的意思，即插值的类型
    {
        if (num == 1) {
            FunType = new String("当前函数：1");
            FunTypeInt = 1;
            jFieldMode.setText(FunType);
        } else if (num == 2) {
            FunType = new String("当前函数：2");
            FunTypeInt = 2;
            jFieldMode.setText(FunType);
        } else if (num == 3) {
            FunType = new String("当前函数：3");
            FunTypeInt = 3;
            jFieldMode.setText(FunType);
        } else if (num == 4) {
            FunType = new String("当前函数：4");
            FunTypeInt = 4;
            jFieldMode.setText(FunType);
        }
        else if (num == 5)
        {
            ModeType = new String("当前积分公式：复化梯形积分");
            ModeTypeInt = 1;
            JFieldFun.setText(ModeType);
        }
        else if (num == 6)
        {
            ModeType = new String("当前积分模式：复化Simpson积分");
            ModeTypeInt = 2;
            JFieldFun.setText(ModeType);
        }
        else if (num == 7)
        {
            ModeType = new String("当前积分模式：Romberg积分");
            ModeTypeInt = 3;
            JFieldFun.setText(ModeType);
        }
    }
}

class fun{
    public double a, b, h,epsilon;
    public int n;
    public int selectedFun;

    public void setData(double aa, double bb, int nn, int f) {
        a = aa;
        b = bb;
        n = nn;
        h = (b - a) / n;
        selectedFun = f;
    }
    public void setEpsilon(double e)
    {
        epsilon = e;
    }
    /**
     * @param x
     * @param num 1表示第一个函数，2表示第二个函数，以此类推
     * @return
     */
    private double calculate(double x, int num) {
        if (num == 1)
            return Math.sqrt(4 - (Math.sin(x)) * (Math.sin(x)));
        if (num == 2)
        {
            if (x == 0)
                return (double)(Math.cos(x));//洛必达法则，0/0型
            else 
                return (double) (Math.sin(x) / x);
        }
        if (num == 3)
            return (double) ((Math.pow(Math.E, x)) / (4 + x * x));
        if (num == 4)
            return (double) (Math.log(1 + x) / (1 + x * x));
        return -1;
    }

    /**
     * modetype == 1 复合梯形
     * modetype == 2 simpson
     * modetype == 3 Romberg
     * @return
     */
    public double calculateFun(int modetype) {
        if (modetype == 1) 
        {
            double res = 0;
            double cur = a;
            int times = n;
            while (times > 0) {
                res += h / 2 * (calculate(cur, selectedFun) + calculate(cur + h, selectedFun));
                times--;
                cur += h;
            }
            return res;
        }
        if (modetype == 2)
        {
            double res = 0;
            double cur = a;
            int times = n / 2;
            while (times > 0)
            {
                res += h / 3 * (calculate(cur, selectedFun) + 4 * calculate(cur + h, selectedFun) + calculate(cur + 2 * h, selectedFun));
                times--;
                cur += 2 * h;
            }
            return res;
        }
        if (modetype == 3)
        {
            int m = 1, k = 1;
            double hh = (b - a) / 2.0;
            double T0 = hh * (calculate(a,selectedFun) + calculate(b,selectedFun)), T = 3;
            double F = 0;
            while(Math.abs(T - T0) >= 3 * epsilon)
            {
            if(m != 1)
                T0 = T;
            F = 0;
            k = (int) Math.pow(2., m - 1);
            for(int i = 1; i <= k ; i++)
            {
                F += calculate(a + (2 * i - 1) * hh , selectedFun);
            }
            T = T0 / 2.0 + hh * F;
            m += 1;
            hh /= 2.0;  	
            }
            return T;
        }
        return -1;
    }
}
