import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;

import javax.swing.*;

import java.awt.Graphics;

public class SolveCharacteristicValue {

    static String strb;
    static ArrayList<String> strA = new ArrayList<String>();
    static double[][] a;
    static double[] b;
    static public int dim;
    static String test1a = new String("-1 2 1\n2 -4 1\n1 1 -6");
    static String test2a = new String("4 -2 7 3 -1 8\n-2 5 1 1 4 7\n7 1 7 2 3 5\n3 1 2 6 5 1\n-1 4 3 5 3 2\n8 7 5 1 2 4");
    static String test3a = new String("2 -1 0 0 0\n-1 2 -1 0 0\n0 -1 2 -1 0\n0 0 -1 2 -1\n0 0 0 -1 2");
    static String test4a = new String("2 1 3 4\n1 -3 1 5\n3 1 6 -2\n4 5 -2 -1");
    static String test5a = new String("-1 2 1\n2 -4 1\n1 1 -6");
    static double eps;
    static Fun fun = new Fun();
    static String FunType = new String("NULL");
    // 默认的初始模式是空模式
    static String ModeType = new String("NULL");
    //
    static int FunTypeInt = 0;// 1 2 3 4
    // 表示选择的函数 有1，2，3，4四个函数
    static Graphics g;
    static int flag = 0;
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
    static JTextField jFieldResult2 = new JTextField(120);//设置精度
    static JTextField jFieldResult3 = new JTextField(120);//设置初始值
    public static void main(String[] args) {
        System.out.println("Test Success!");
        SolveCharacteristicValue NI = new SolveCharacteristicValue();

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
        dim = (int)Math.sqrt(len);
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
        JLabel label = new JLabel("输入需要求解方程组的A：");
        label.setBounds(20, 50, 200, 20);
        label.setForeground(Color.BLUE);
        p.add(label);

        JLabel label1 = new JLabel("当前选择的方程组解法：");
        label1.setBounds(20, 20, 200, 20);
        label1.setForeground(Color.BLUE);
        p.add(label1);

        // JLabel label6 = new JLabel("请输入需要求解的方程组的B：");
        // label6.setBounds(20, 310, 200, 20);
        // label6.setForeground(Color.BLUE);
        // p.add(label6);

        JLabel label7 = new JLabel("对应的特征向量：");
        label7.setBounds(400, 365, 200, 20);
        label7.setForeground(Color.BLUE);
        p.add(label7);

        jFieldResult.setText("当前结果：未显示");
        jFieldResult.setEditable(false);
        jFieldResult.setBounds(400, 390, 300, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult);

        JLabel label9 = new JLabel("按模最大或最小的特征值：");
        label9.setBounds(400, 425, 200, 20);
        label9.setForeground(Color.BLUE);
        p.add(label9);

        jFieldResultLam.setText("当前结果：未显示");
        jFieldResultLam.setEditable(false);
        jFieldResultLam.setBounds(400, 450, 300, 30);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResultLam);
        
        jFieldMode.setText("当前求解方法：未选择");
        jFieldMode.setEditable(false);
        jFieldMode.setBounds(250, 20, 150, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldMode);


        final JTextArea jarea = new JTextArea("请输入方程组的A", 200, 200);
        jarea.setBounds(20, 90, 200, 200);
        p.add(jarea);

        JLabel label8 = new JLabel("精度设置：");
        label8.setBounds(500, 20, 200, 20);
        label8.setForeground(Color.BLUE);
        p.add(label8);

        jFieldResult2.setText("0.00001");
        jFieldResult2.setEditable(true);
        jFieldResult2.setBounds(580, 18, 120, 25);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult2);

        JLabel label11 = new JLabel("初始值设置：");
        label11.setBounds(500, 60, 200, 20);
        label11.setForeground(Color.BLUE);
        p.add(label11);

        jFieldResult3.setText("NULL");
        jFieldResult3.setEditable(true);
        jFieldResult3.setBounds(580, 58, 120, 25);
        //jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult3);



        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(400, 320, 300, 40);// 设置按钮在容器中的位置
        p.add(button1);

        button1.addActionListener(new ActionListener()// 对按钮增加监听
        {
            // 此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理输入
                for (int i = strA.size() - 1; i >= 0; i--) {
                    strA.remove(i);
                }
                // 因为是ArrayList，所以每次使用前需要清空
                processInput(jarea.getText());
                // 将jArea中的字符串处理成字符串数组
                a = new double[dim][dim];
                int cnt = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        a[i][j] = Double.parseDouble(strA.get(cnt++));
                    }
                }
                double u[] = new double[dim];
                if (jFieldResult3.getText().equals("NULL"))
                {
                    String tm = new String("");
                    for (int i = 0;i < dim;i++)
                    {
                        u[i] = 1.0;
                        tm += "1.0 ";
                    }
                    jFieldResult3.setText(tm);
                    
                }
                else
                {   
                    String tm[] = jFieldResult3.getText().split(" ");
                    if (tm.length != dim)
                    {
                        String tm2 = new String("");
                        for (int i = 0;i < dim;i++)
                        {
                            u[i] = 1.0;
                            tm2 += "1.0 ";
                        }
                        jFieldResult3.setText(tm2);
                    }
                    else
                    {
                        for (int i = 0 ; i < dim;i++)
                        {
                            u[i] = Double.parseDouble(tm[i]);
                        }
                    }
                } 
                eps = Double.valueOf(jFieldResult2.getText());
                fun.setData(a, dim,u,eps);
                if (FunTypeInt == 1)
                {
                    jFieldResult.setText(fun.cal()[0]);
                    jFieldResultLam.setText(fun.cal()[1]);
                }
                else if (FunTypeInt == 2)
                {
                    if (flag == 0)
                        {
                            jFieldResult.setText(fun.cal2()[0]);
                            jFieldResultLam.setText(fun.cal2()[1]);
                        }
                    else if (flag == 1)
                    {
                        jFieldResult.setText(fun.cal()[0]);
                        jFieldResultLam.setText(fun.cal()[1]);
                    }
                }

            }
        });

        JButton button2 = new JButton("测试样例1");//
        button2.setBounds(20, 320, 200, 40);// 设置按钮在容器中的位置
        p.add(button2);
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test1a);
                flag = 1;
            }
        });

        JButton button3 = new JButton("测试样例2");
        button3.setBounds(20, 360, 200, 40);
        p.add(button3);
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test2a);
                flag = 0;

            }
        });

        JButton button4 = new JButton("测试样例3");
        button4.setBounds(20, 400, 200, 40);
        p.add(button4);
        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test3a);
                flag = 0;
            }
        });
        JButton button5 = new JButton("测试样例4");
        button5.setBounds(20, 440, 200, 40);
        p.add(button5);
        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test4a);
                flag = 0;
            }
        });

        JButton button6 = new JButton("测试样例5");
        button6.setBounds(20, 480, 200, 40);
        p.add(button6);
        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test5a);
                flag = 1;
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
        JMenu Menu1;
        JMenuItem funItem1, funItem2;
        JMenuBar menuBar = new JMenuBar();
        funItem1 = new JMenuItem("幂法");
        funItem2 = new JMenuItem("反幂法");
        Menu1 = new JMenu("求解方法选择");
        Menu1.add(funItem1);
        Menu1.add(funItem2);
        Menu1.setSelected(true);
        menuBar.add(Menu1);
        frame.setJMenuBar(menuBar);
        funItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("lag");
                updateModeStr(1);
                System.out.println("幂法");

            }
        });
        funItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                updateModeStr(2);
                System.out.println("反幂法");
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
            FunType = new String("幂法");
            FunTypeInt = 1;
            jFieldMode.setText(FunType);
        } else if (num == 2) {
            FunType = new String("反幂法");
            FunTypeInt = 2;
            jFieldMode.setText(FunType);
        } 
    }
}

class Fun {
    public double[][] A;
    public double[] V;
    public double[] U;
    public double s,mu0,mu = -1000;
    public double eps = 0.0001;
    private int N;
    public void setData(double a[][], int dim,double u[],double e) {
        N = a.length;
        eps = e;
        // System.out.println(dimension);
        // System.out.println(123213123);
        A = new double[N + 2][N + 2];
        U = new double[N + 2];
        V = new double[N + 2];
        int i,j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                A[i][j] = a[i][j];
            }
        }
        for (i = 0;i < N;i++)
        {
            U[i] = u[i];
        }
    }
    /**
     * 幂法
     * @return
     */
    public String[] cal()
    {
        int i,j;
        do{
            mu0 = mu;
            for (i = 0;i < N;i++)
            {
                s = 0;
                for (j = 0;j < N;j++) s += A[i][j] * U[j];
                V[i] = s;
            }
            mu = Math.abs(V[0]);
            for (i = 0 ;i < N;i++)
            {
                if (mu < Math.abs(V[i])) mu = V[i];
            }
            for (i = 0 ; i < N;i++)
            {
                U[i] = V[i] / mu;
            }
        }while (Math.abs(mu - mu0) >= eps);
        System.out.println("///////");
        for(i = 0 ; i < N;i++)
        {
            System.out.println(U[i]);
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(4);     
        System.out.println(mu);
        String res[] = new String[2];
        res[0] = "";
        res[1] = "";
        for (i = 0; i < N; i++) {
            res[0] += String.valueOf(nf.format(U[i]) + " ");
        }
        res[1] = String.valueOf(nf.format(mu0));
        return res;
    }
    /**
     * 反幂法
     */
    public String[] cal2()
    {
        int n ;
        int i,j,k;
        double xmax = -9999,oxmax = 0;
        double [][]L = new double[N + 2][N + 2];
        double [][]U = new double[N + 2][N + 2];
        double []x = new double[N + 2];
        double []nx = new double[N + 2];
        for (i = 0 ; i < N;i++)
        {
            x[i] = 1;
        }
        oxmax = 0;
        
        for (i = 0 ; i < N;i++)
        {
            U[i][i] = 1;
        }
        for (k = 0;k < N;k++)
        {
            for (i = k;i < N ;i++)
            {
                L[i][k] = A[i][k];
                for (j = 0 ; j <= k - 1;j++)
                    L[i][k] -= (L[i][j] * U[j][k]);
            }
            for (j = k + 1;j < N;j++)
            {
                U[k][j] = A[k][j];
                for (i = 0;i <= k - 1;i++)
                {
                    U[k][j] -= (L[k][i] * U[i][j]);
                }
                U[k][j] /= L[k][k];
            }
        }
        for (i = 0;i < N;i++)
        {
            x[i] = 1;
        }
        for (i = 0;i < 100;i++)
        {
            for (j = 0;j < N;j++)
            {
                nx[j] = x[j];
                for (k = 0;k <= j - 1;k++)
                {
                    nx[j] -= L[j][k] * nx[k];
                }
                nx[j] /= L[j][j];
            }
            for (j = N - 1;j >= 0;j--)
            {
                x[j] = nx[j];
                for (k = j + 1;k < N;k++)
                {
                    x[j] -= U[j][k] * x[k];
                }
            }
            xmax = 0;
            for (j = 0;j < N;j++)
            {
                if (Math.abs(x[j]) > xmax)
                {
                    xmax = Math.abs(x[j]);
                }
            }
            for (j = 0;j < N;j++)
            {
                x[j] /= xmax;
            }
            if (Math.abs(xmax - oxmax) < eps)
            {
                break;
            }
            else
            {
                oxmax = xmax;
            }
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(4);     
        System.out.println(mu);
        String res[] = new String[2];
        res[0] = "";
        res[1] = "";
        for (i = 0; i < N; i++) {
            res[0] += String.valueOf(nf.format(x[i]) + " ");
        }
        res[1] = String.valueOf(nf.format(1/ xmax));
        return res;
    }

}