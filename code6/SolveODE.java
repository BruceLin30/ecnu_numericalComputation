import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.swing.*;

import java.awt.Graphics;

public class SolveODE {

    static String strb;
    static ArrayList<String> strA = new ArrayList<String>();

    static JLabel labelImg = new JLabel();//载入函数图片的jlabel
    static double step;//方程步长
    static double low,high;//方程上下限
    static double yStart;//初始值
    static Fun fun = new Fun();
    static String SolveType = new String("NULL");
    // 默认的初始模式是空模式
    static String ModeType = new String("NULL");
    //
    static int SolveTypeInt = 0;// 1 2 3 4
    // 表示选择的函数 有1，2，3，4四个函数
    static Graphics g;
    static int flag = 0;//函数的选择
    // 暂时不会用到画图
    static JFrame frame = new JFrame();
    // 定义了一个Frame
    static String result = new String("");
    /*
     * 下面对JTextField进行static的初始化定义，方便下面直接对其修改
     */
    static JTextField jFieldMode = new JTextField(120);// 模式选择
    static JTextField jFieldResult = new JTextField(120);
    static JTextField jFieldResultLam = new JTextField(120);//设置特征值
    static JTextField jFieldResult2 = new JTextField(120);//设置步长
    static JTextField jFieldResult3 = new JTextField(120);//设置上下限
    static JTextField jFieldResult4 = new JTextField(120);//设置初始值
    public static void main(String[] args) {
        System.out.println("Test Success!");
        SolveODE NI = new SolveODE();

        NI.initMenuBar();// 初始化菜单栏
        NI.initUI();// 初始化UI界面
    }
    /**
     * 处理A的输入
     * @param sa
     */
    public void processInput(String sa) {
        String[] tmpa = sa.split("\n|\\s+");
        int len = tmpa.length;
        for (int i = 0; i < tmpa.length; i++) {
            strA.add(tmpa[i]);
        }
        //System.out.println(strA);

        //System.out.println(dim);
    }
    
    public void initUI() {

        /**
         * 这里是对frame的设置
         */
        frame.setSize(800, 600);// 设置容器尺寸
        frame.setLayout(new BorderLayout());
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
        p.setLayout(null);
        p.setOpaque(false);

        /**
         * 这里是对labels的设置
         */
        JLabel label = new JLabel("当前选择的常微分方程为：");
        label.setBounds(20, 50, 200, 20);
        label.setForeground(Color.BLUE);
        p.add(label);

        JLabel label1 = new JLabel("当前选择的常微分方程解法：");
        label1.setBounds(20, 20, 200, 20);
        label1.setForeground(Color.BLUE);
        p.add(label1);

        // JLabel label6 = new JLabel("请输入需要求解的方程组的B：");
        // label6.setBounds(20, 310, 200, 20);
        // label6.setForeground(Color.BLUE);
        // p.add(label6);

        JLabel label7 = new JLabel("对应的数值解：");
        label7.setBounds(20, 385, 200, 20);
        label7.setForeground(Color.BLUE);
        p.add(label7);

        jFieldResult.setText("当前结果：未显示");
        jFieldResult.setEditable(false);
        jFieldResult.setBounds(20, 410, 600, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult);

        /*
        JLabel label9 = new JLabel("按模最大或最小的特征值：");
        label9.setBounds(20, 445, 200, 20);
        label9.setForeground(Color.BLUE);
        p.add(label9);
        */

        /*
        jFieldResultLam.setText("当前结果：未显示");
        jFieldResultLam.setEditable(false);
        jFieldResultLam.setBounds(20, 470, 600, 30);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResultLam);
        */
        
        jFieldMode.setText("当前求解方法：未选择");
        jFieldMode.setEditable(false);
        jFieldMode.setBounds(250, 20, 150, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldMode);

        /*
        final JTextArea jarea = new JTextArea("请输入方程组的A", 200, 200);
        jarea.setBounds(20, 90, 200, 200);
        p.add(jarea);
        */

        JLabel label8 = new JLabel("步长设置:");
        label8.setBounds(500, 20, 200, 20);
        label8.setForeground(Color.BLUE);
        p.add(label8);

        jFieldResult2.setText("0.1");
        jFieldResult2.setEditable(true);
        jFieldResult2.setBounds(580, 18, 70, 25);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult2);

        JLabel label11 = new JLabel("上下限设置: ");
        label11.setBounds(500, 60, 200, 20);
        label11.setForeground(Color.BLUE);
        p.add(label11);

        jFieldResult3.setText("0 1");
        jFieldResult3.setEditable(true);
        jFieldResult3.setBounds(580, 58, 70, 25);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult3);

        JLabel label12 = new JLabel("初始值: ");
        label12.setBounds(500, 100, 200, 20);
        label12.setForeground(Color.BLUE);
        p.add(label12);

        jFieldResult4.setText("1");
        jFieldResult4.setEditable(true);
        jFieldResult4.setBounds(580, 98, 70, 25);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult4);

        labelImg = new JLabel();
        labelImg.setBounds(20,60, 300, 180);
        labelImg.setIcon(new ImageIcon("img/fun1.png"));
        p.add(labelImg);


        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(20, 320, 200, 40);// 设置按钮在容器中的位置
        p.add(button1);

        button1.addActionListener(new ActionListener()// 对按钮增加监听
        {
            // 此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                //处理输入步长和上下限
                String []ab = jFieldResult3.getText().split(" ");
                low = Double.parseDouble(ab[0]);
                high = Double.parseDouble(ab[1]);
                step = Double.parseDouble(jFieldResult2.getText());
                yStart = Double.parseDouble(jFieldResult4.getText());
                fun.setData(low, high, step, yStart, flag);
                //System.out.println(fun.calEuler());
                if (SolveTypeInt == 1)
                    jFieldResult.setText(fun.calEuler());
                if (SolveTypeInt == 2)
                    jFieldResult.setText(fun.calEulerImproved());
                if (SolveTypeInt == 3)
                    jFieldResult.setText(fun.calRK());

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
        JMenu Menu1,Menu2;
        JMenuItem funItem1, funItem2,funItem3;
        JMenuItem funItem4, funItem5,funItem6;
        JMenuBar menuBar = new JMenuBar();
        funItem1 = new JMenuItem("Euler法");
        funItem2 = new JMenuItem("改进Euler法");
        funItem3 = new JMenuItem("R-K法");
        funItem4 = new JMenuItem("样例1");
        funItem5 = new JMenuItem("样例2");
        //funItem6 = new JMenuItem("样例3");
        Menu1 = new JMenu("求解方法选择");
        Menu2 = new JMenu("求解方程组选择");
        Menu1.add(funItem1);
        Menu1.add(funItem2);
        Menu1.add(funItem3);
        Menu2.add(funItem4);
        Menu2.add(funItem5);
        //Menu2.add(funItem6);
        Menu1.setSelected(true);
        Menu2.setSelected(true);
        menuBar.add(Menu1);
        menuBar.add(Menu2);
        frame.setJMenuBar(menuBar);
        funItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("lag");
                updateModeStr(1);
                System.out.println("Euler法");
            }
        });
        funItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                updateModeStr(2);
                System.out.println("改进Euler法");
            }
        });
        funItem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                updateModeStr(3);
                System.out.println("R-K法");
            }
        });
        funItem4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                //updateModeStr(3);
                labelImg.setIcon(new ImageIcon("img/fun1.png"));
                //System.out.println("R-K法");
                flag = 1;
            }
        });
        funItem5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                //updateModeStr(3);
                labelImg.setIcon(new ImageIcon("img/fun2.png"));
                flag = 2;
                //System.out.println("R-K法");
            }
        });
    }

    /***
     * 更新函数的select选中和积分方法select选中的UI
     * 
     * @param num
     */
    public void updateModeStr(int num)// mode表示模式的意思，即插值的类型
    {
        if (num == 1) {
            SolveType = new String("Euler法");
            SolveTypeInt = 1;
            jFieldMode.setText(SolveType);
        } else if (num == 2) {
            SolveType = new String("改进Euler法");
            SolveTypeInt = 2;
            jFieldMode.setText(SolveType);
        } else if (num == 3) {
            SolveType = new String("R-K法");
            SolveTypeInt = 3;
            jFieldMode.setText(SolveType);
        } 
    }
}

class Fun {
    private double a,b;//上下限`
    private double h,y0;//
    private double funNumber;//选择某个函数
    /**
     * 
     * @param aa 方程下限
     * @param bb 方程上限
     * @param step 步长
     * @param ys 初始值
     * @param num 选择的方程的编号
     */
    public void setData(double aa, double bb,double step,double ys,int num)
    {
        a = aa;
        b = bb;
        h = step;
        y0 = ys;
        funNumber = num;
    }
    public double fun(double x,double y)
    {
        if (funNumber == 1)
            return 4 * x / y - x * y;
        if (funNumber == 2)
            return x * x - y * y;
        if (funNumber == 3)
            return y * y * Math.cos(x);
        return 0;
    }
    public String calEuler()
    {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2); 
        double x = a;
        double y = y0;
        String res = new String("");
        while (x + h < b)
        {
            res += String.valueOf(nf.format(y));
            //System.out.println(y);
            res += " ";
            double tmp = y;//y -> yn
            y += h * fun(x,tmp);//y -> yn+1
            x += h;
        }   
        res += String.valueOf(nf.format(y));
        return res;
    }
    public String calEulerImproved()
    {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2); 
        double x = a;
        double y = y0;
        String res = new String("");
        while (x + h < b)
        {
            res += String.valueOf(nf.format(y));
            //System.out.println(y);
            res += " ";
            double tmp = y;//y = tmp ->yn
            double tmpx = x + h;//tmpx ->xn+1
            y += h / 2 * (fun(x, tmp) + fun(x + h, tmp + h * fun(x, tmp))); //y->yn+1
            x += h;
        }
        res += String.valueOf(nf.format(y));
        return res;
    }
    public String calRK()
    {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2); 
        double x = a;
        double y = y0;
        String res = new String("");
        while (x + h < b)
        {
            res += String.valueOf(nf.format(y));
            //System.out.println(y);
            res += " ";
            double k1,k2,k3,k4;
            k1 = fun(x, y);
            k2 = fun(x + 0.5 * h, y + 0.5 * h * k1);
            k3 = fun(x + 0.5 * h, y + 0.5 * h * k2);
            k4 = fun(x + h, y + h * k3);
            y += h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            x += h;
        }
        res += String.valueOf(nf.format(y));
        return res;
    }
}