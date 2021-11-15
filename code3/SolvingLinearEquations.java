import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import java.awt.Graphics;

public class SolvingLinearEquations {

    //static String[] strA;
    static String strb;
    static ArrayList<String> strA = new ArrayList<String>();
    static double [][]a;
    static double []b;
    static public int dim;
    static String test1a = new String("4 2 -3 -1 2 1 0 0 0 0\n8 6 -5 -3 6 5 0 1 0 0\n4 2 -2 -1 3 2 -1 0 3 1\n0 -2 1 5 -1 3 -1 1 9 4\n-4 2 6 -1 6 7 -3 3 2 3\n8 6 -8 5 7 17 2 6 -3 5\n0 2 -1 3 -4 2 5 3 0 1\n16 10 -11 -9 17 34 2 -1 2 2\n4 6 2 -7 13 9 2 0 12 4\n0 0 -1 8 -3 -24 -8 6 3 -1");
    static String test1b = new String("5 12 3 2 3 46 13 38 19 -21");
    static String test2a = new String("4 2 -4 0 2 4 0 0\n2 2 -1 -2 1 3 2 0\n-4 -1 14 1 -8 -3 5 6\n0 -2 1 6 -1 -4 -3 3\n2 1 -8 -1 22 4 -10 -3\n4 3 -3 -4 4 11 1 -4\n0 2 5 -3 -10 1 14 2\n0 0 6 3 -3 -4 2 19");
    static String test2b = new String("0 -6 20 23 9 -22 -15 45");
    static String test3a = new String("4 -1 0 0 0 0 0 0 0 0\n-1 4 -1 0 0 0 0 0 0 0\n0 -1 4 -1 0 0 0 0 0 0\n0 0 -1 4 -1 0 0 0 0 0\n0 0 0 -1 4 -1 0 0 0 0\n0 0 0 0 -1 4 -1 0 0 0\n0 0 0 0 0 -1 4 -1 0 0\n0 0 0 0 0 0 -1 4 -1 0\n0 0 0 0 0 0 0 -1 4 -1\n0 0 0 0 0 0 0 0 -1 4");
    static String test3b = new String("7 5 -13 2 6 -12 14 -4 5 -5");
    static Fun fun = new Fun();
    static String FunType = new String("NULL");
    // 默认的初始模式是空模式
    static String ModeType = new String("NULL");
    //
    static int FunTypeInt = 0;//1 2 3 4
    //表示选择的函数 有1，2，3，4四个函数
    static int ModeTypeInt = 0;//5 6 7
    //表示使用的积分的类型，有三种不同的积分类型
    static Graphics g;
    //暂时不会用到画图
    static JFrame frame = new JFrame();
    //定义了一个Frame
    static String result = new String("");
    /*
    下面对JTextField进行static的初始化定义，方便下面直接对其修改
    */
    static JLabel Jresult;
    static JTextField jFieldMode = new JTextField(120);// 模式选择
    static JTextField JFieldFun = new JTextField(120);
    static JTextField jFieldResult = new JTextField(120);
    static JTextField jFieldResult2 = new JTextField(120);
    static JLabel labelImg = new JLabel();//载入函数图片的jlabel
    //static fun f = new fun();
    public static void main(String[] args) {
        System.out.println("Test Success!");
        SolvingLinearEquations NI = new SolvingLinearEquations();

        NI.initMenuBar();// 初始化菜单栏
        NI.initUI();// 初始化UI界面

        /*
        double ex1[][] = new double[3][3];
        double ex2[] = new double[3];
        int cnt = 1;
        for (int i = 0;i < 3;i++)
        {
            for (int j = 0 ; j < 3;j++)
            {
                ex1[i][j] = cnt;
                cnt ++;
            }
        }
        for (int i = 0;i < 3;i++ )
        {
            ex2[i] = i+ 1;
        }
        Fun fun = new Fun();
        fun.setData(ex2, ex1);
        fun.printData();
        */
        
        

    }
    public void processInput(String sa,String sb)
    {
        String tmpb[] = sb.split(" ");
        b = new double[tmpb.length];
        a = new double[tmpb.length][tmpb.length];
        for (int i = 0;i < tmpb.length;i++)
        {
            b[i] = Double.parseDouble(tmpb[i]);
        }
        String []tmpa = sa.split("\n|\\s+");
        for (int i = 0 ; i < tmpa.length;i++)
        {
            strA.add(tmpa[i]);
        }
        dim = b.length;
    }
    /**
     * 更新结果的UI
     * @param res
     */
    public void updateUI(double res)
    {
        String restr = String.valueOf(res);
        jFieldResult.setText(restr);
    }
    /**
     * 更新错误信息的UI
     * @param error
     */
    public void updateUI(String error)
    {
        String err = new String(error);
        jFieldResult.setText(err);
    }
    /**
     * 更新结果2的UI
     * @param res
     */
    public void updateUI2(double res)
    {
        String restr = String.valueOf(res);
        jFieldResult2.setText(restr);
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
        JLabel label = new JLabel("输入需要求解方程组的A：");
        label.setBounds(20, 50, 130, 20);
        label.setForeground(Color.BLUE);
        p.add(label);

        JLabel label1 = new JLabel("当前选择的方程组解法：");
        label1.setBounds(20, 20, 200, 20);
        label1.setForeground(Color.BLUE);
        p.add(label1);

         /*
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
        labelImg = new JLabel();
        labelImg.setBounds(470,20, 300, 180);
        labelImg.setIcon(new ImageIcon("img/fun1.png"));
        p.add(labelImg);
        JLabel label5 = new JLabel("积分结果：");
        label5.setBounds(20, 240, 100, 20);
        p.add(label5);
       */ 
        JLabel label6 = new JLabel("请输入需要求解的方程组的B：");
        label6.setBounds(20,310,200,20);
        label6.setForeground(Color.BLUE);
        p.add(label6);
        

        // frame.add(label);
        /**
         * JTextField的设置 创建文本框，指定可见列数为80列
         */

         /*
         
         
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
        */
        final JTextArea jarea = new JTextArea("请输入方程组的A",200,200);
        jarea.setBounds(20, 90, 200, 200);
        p.add(jarea);


        final JTextField jFieldX = new JTextField(80);
        jFieldX.setBounds(20, 350, 200, 30);
        p.add(jFieldX);
        
        


        
        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(250, 350, 200, 40);// 设置按钮在容器中的位置
        p.add(button1);
        

        button1.addActionListener(new ActionListener()// 对按钮增加监听
        {
            // 此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                //处理输入
                //System.out.println(jarea.getText());
                //System.out.println(jarea.getText().length());
                for (int i = strA.size() - 1 ; i >= 0;i--)
                {
                    strA.remove(i);
                }
                //因为是ArrayList，所以每次使用前需要清空
                processInput(jarea.getText(),jFieldX.getText());
                //将jArea中的字符串处理成字符串数组
                //System.out.println(strA.size());
                //for (int i = 0 ;i < strA.size();i++) System.out.println(strA.get(i));
                int cnt = 0;
                for (int i = 0 ; i< dim;i++)
                {
                    for (int j = 0; j < dim;j++)
                    {
                        a[i][j] = Double.parseDouble(strA.get(cnt++));
                    }
                }
                
                //测试输出
                /*
                for (int i = 0 ; i < dim;i++)
                {
                    for (int j = 0 ; j < dim;j++)
                    {
                        System.out.print(a[i][j] + " ");
                    }
                    System.out.println(" ");
                }
                */
                fun.setData(b, a,dim);
                fun.calGAussEWPP();
                fun.printResult();
            }
        });

        

       
        JButton button2 = new JButton("测试样例1---线性方程组");//
        button2.setBounds(20, 400, 200, 40);// 设置按钮在容器中的位置
        p.add(button2);
        button2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test1a);
                jFieldX.setText(test1b);

            }
        });

        JButton button3 = new JButton("测试样例2---对称正定线性方程组");
        button3.setBounds(20, 440, 200, 40);
        p.add(button3);
        button3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test2a);
                jFieldX.setText(test2b);

            }
        });

        JButton button4 = new JButton("测试样例2---对称正定线性方程组");
        button4.setBounds(20, 480, 200, 40);
        p.add(button4);
        button4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test3a);
                jFieldX.setText(test3b);

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
                labelImg.setIcon(new ImageIcon("img/fun1.png"));
            }
        });
        funItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                updateModeStr(2);
                System.out.println("当前函数：2");
                labelImg.setIcon(new ImageIcon("img/fun2.png"));
            }
        });
        funItem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(3);
                labelImg.setIcon(new ImageIcon("img/fun3.png"));
                System.out.println("当前函数: 3");
            }

        });
        funItem4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(4);
                labelImg.setIcon(new ImageIcon("img/fun4.png"));
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
    /***
     * 更新函数的select选中和积分方法select选中的UI
     * @param num
     */
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
class Fun
{
    public int dimension;
    public double [][]A;
    public double []B;
    public double []X;
    public void setData(double b[],double a[][],int dim)
    {
        dimension = dim;
        //System.out.println(dimension);
        //System.out.println(123213123);
        A = new double[dimension + 2][dimension + 2];
        B = new double[dimension + 2];
        X = new double[dimension + 2];
        for (int i = 1;i <= dimension ;i++)
        {
            B[i] = b[i - 1];
        }
        for (int i = 1;i <= dimension;i++)
        {
            for (int j = 1; j <= dimension;j++)
            {
                A[i][j] = a[i - 1][j - 1];
            }
        }
    }
    public void printData()
    {
        for (int i = 0;i < dimension;i++)
        {
            System.out.print(B[i] + " ");
        }
        System.out.println("");
        for (int i = 0 ; i < dimension;i++)
        {
            for (int j = 0 ; j < dimension;j++)
            {
                System.out.print(A[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public void printResult()
    {
        for (int i = 1 ; i <= dimension;i++)
        {
            System.out.print(Math.round(B[i]) + " ");
        }
        System.out.println("");
        for (int i = 1;i <= dimension;i++)
        {
            System.out.print(B[i] + " ");
        }
        System.out.println("");
    }
    /**
     * 高斯顺序消去法
     * @return B
     */
    public double[] calGauss()
    {
        for (int k = 1;k <= dimension - 1;k++)
        {
            for (int i = k + 1;i <= dimension;i++)
            {
                A[i][k] =  A[i][k] / A[k][k];
                for (int j = k + 1;j <= dimension;j++)
                {
                    A[i][j] = A[i][j] - A[i][k] * A[k][j];
                }
                B[i] = B[i] - A[i][k] * B[k];
            }
        }
        for (int i = dimension;i >= 1;i--)
        {
            for (int j = i + 1;j <= dimension;j++)
                B[i] = B[i] - A[i][j] * B[j];
            B[i] = B[i] / A[i][i];
        }
        return B;
    }
    /**
     * 高斯列主元消元法
     * @return
     */
    public double[] calGAussEWPP()
    {
        int k;
        double t;
        for (int i = 1; i <= dimension;i++)
        {
            k = i;
            for (int j = i + 1;j <= dimension;j++)
            {
                if (Math.abs(A[k][i]) < Math.abs(A[k][j]))
                {
                    k = j;
                }
            }
            for (int j = i;j <= dimension;j++)
            {
                t = A[i][j];
                A[i][j] = A[k][j];
                A[k][j] = t;
            }
            t = B[i];
            B[i] = B[k];
            B[k] = t;
            for (int j = i + 1;j <= dimension;j++)
            {
                A[j][i] = A[j][i] / A[i][i];
                for (k = i + 1;k <= dimension;k++)
                {
                    A[j][k] = A[j][k] - A[j][i] * A[i][k];
                }
                B[j] = B[j] - A[j][i] * B[i];
            }
        }
        for (int i = dimension;i >= 1;i--)
        {
            for (int j = i + 1;j <= dimension;j++)
            {
                B[i] = B[i] - A[i][j]*B[j];
            }
            B[i] = B[i] / A[i][i];
        }
        return B;
    }
}