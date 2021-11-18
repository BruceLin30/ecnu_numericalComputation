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

    static String strb;
    static ArrayList<String> strA = new ArrayList<String>();
    static double[][] a;
    static double[] b;
    static public int dim;
    static String test1a = new String(
            "4 2 -3 -1 2 1 0 0 0 0\n8 6 -5 -3 6 5 0 1 0 0\n4 2 -2 -1 3 2 -1 0 3 1\n0 -2 1 5 -1 3 -1 1 9 4\n-4 2 6 -1 6 7 -3 3 2 3\n8 6 -8 5 7 17 2 6 -3 5\n0 2 -1 3 -4 2 5 3 0 1\n16 10 -11 -9 17 34 2 -1 2 2\n4 6 2 -7 13 9 2 0 12 4\n0 0 -1 8 -3 -24 -8 6 3 -1");
    static String test1b = new String("5 12 3 2 3 46 13 38 19 -21");
    static String test2a = new String(
            "4 2 -4 0 2 4 0 0\n2 2 -1 -2 1 3 2 0\n-4 -1 14 1 -8 -3 5 6\n0 -2 1 6 -1 -4 -3 3\n2 1 -8 -1 22 4 -10 -3\n4 3 -3 -4 4 11 1 -4\n0 2 5 -3 -10 1 14 2\n0 0 6 3 -3 -4 2 19");
    static String test2b = new String("0 -6 20 23 9 -22 -15 45");
    static String test3a = new String(
            "4 -1 0 0 0 0 0 0 0 0\n-1 4 -1 0 0 0 0 0 0 0\n0 -1 4 -1 0 0 0 0 0 0\n0 0 -1 4 -1 0 0 0 0 0\n0 0 0 -1 4 -1 0 0 0 0\n0 0 0 0 -1 4 -1 0 0 0\n0 0 0 0 0 -1 4 -1 0 0\n0 0 0 0 0 0 -1 4 -1 0\n0 0 0 0 0 0 0 -1 4 -1\n0 0 0 0 0 0 0 0 -1 4");
    static String test3b = new String("7 5 -13 2 6 -12 14 -4 5 -5");
    static Fun fun = new Fun();
    static String FunType = new String("NULL");
    // 默认的初始模式是空模式
    static String ModeType = new String("NULL");
    //
    static int FunTypeInt = 0;// 1 2 3 4
    // 表示选择的函数 有1，2，3，4四个函数
    static Graphics g;
    // 暂时不会用到画图
    static JFrame frame = new JFrame();
    // 定义了一个Frame
    static String result = new String("");
    /*
     * 下面对JTextField进行static的初始化定义，方便下面直接对其修改
     */
    static JTextField jFieldMode = new JTextField(120);// 模式选择
    static JTextField jFieldResult = new JTextField(120);

    public static void main(String[] args) {
        System.out.println("Test Success!");
        SolvingLinearEquations NI = new SolvingLinearEquations();

        NI.initMenuBar();// 初始化菜单栏
        NI.initUI();// 初始化UI界面
    }
    public void processInput(String sa, String sb) {
        String tmpb[] = sb.split(" ");
        b = new double[tmpb.length];
        a = new double[tmpb.length][tmpb.length];
        for (int i = 0; i < tmpb.length; i++) {
            b[i] = Double.parseDouble(tmpb[i]);
        }
        String[] tmpa = sa.split("\n|\\s+");
        for (int i = 0; i < tmpa.length; i++) {
            strA.add(tmpa[i]);
        }
        dim = b.length;
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

        JLabel label6 = new JLabel("请输入需要求解的方程组的B：");
        label6.setBounds(20, 310, 200, 20);
        label6.setForeground(Color.BLUE);
        p.add(label6);

        JLabel label7 = new JLabel("结果向量：");
        label7.setBounds(400, 400, 200, 20);
        label7.setForeground(Color.BLUE);
        p.add(label7);
        jFieldResult.setText("当前结果：未显示");
        jFieldResult.setEditable(false);
        jFieldResult.setBounds(400, 450, 300, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldResult);

        jFieldMode.setText("当前求解方法：未选择");
        jFieldMode.setEditable(false);
        jFieldMode.setBounds(250, 20, 200, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldMode);

        final JTextArea jarea = new JTextArea("请输入方程组的A", 200, 200);
        jarea.setBounds(20, 90, 200, 200);
        p.add(jarea);

        final JTextField jFieldX = new JTextField(80);
        jFieldX.setBounds(20, 350, 200, 30);
        p.add(jFieldX);

        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(400, 350, 300, 40);// 设置按钮在容器中的位置
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
                processInput(jarea.getText(), jFieldX.getText());
                // 将jArea中的字符串处理成字符串数组

                int cnt = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        a[i][j] = Double.parseDouble(strA.get(cnt++));
                    }
                }

                fun.setData(b, a, dim);

                if (FunTypeInt == 1) {
                    jFieldResult.setText(fun.gauss());
                } else if (FunTypeInt == 2) {
                    jFieldResult.setText(fun.calGaussEWPP());
                } else if (FunTypeInt == 3) {
                    jFieldResult.setText(fun.Square());
                } else if (FunTypeInt == 4) {
                    jFieldResult.setText(fun.SquareImproved());
                } else if (FunTypeInt == 5) {
                    jFieldResult.setText(fun.zhuigai());
                }
            }
        });

        JButton button2 = new JButton("测试样例1---线性方程组");//
        button2.setBounds(20, 400, 200, 40);// 设置按钮在容器中的位置
        p.add(button2);
        button2.addActionListener(new ActionListener() {

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
        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jarea.setText(test2a);
                jFieldX.setText(test2b);

            }
        });

        JButton button4 = new JButton("测试样例3---三对角型线性方程组");
        button4.setBounds(20, 480, 200, 40);
        p.add(button4);
        button4.addActionListener(new ActionListener() {

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
        JMenu Menu1;
        JMenuItem funItem1, funItem2, funItem3, funItem4, funItem5;
        JMenuBar menuBar = new JMenuBar();

        funItem1 = new JMenuItem("高斯消元法");
        funItem2 = new JMenuItem("高斯列主元法");
        funItem3 = new JMenuItem("平方根法");
        funItem4 = new JMenuItem("平方根法改进版");
        funItem5 = new JMenuItem("追赶法");

        Menu1 = new JMenu("求解方法选择");

        Menu1.add(funItem1);
        Menu1.add(funItem2);
        Menu1.add(funItem3);
        Menu1.add(funItem4);
        Menu1.add(funItem5);
        Menu1.setSelected(true);
        menuBar.add(Menu1);
        frame.setJMenuBar(menuBar);
        funItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("lag");
                updateModeStr(1);
                System.out.println("高斯消元法");

            }
        });
        funItem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("newton");
                updateModeStr(2);
                System.out.println("高斯列主元法");
            }
        });
        funItem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(3);
                System.out.println("平方根法");
            }

        });
        funItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(4);
                System.out.println("平方根法改进版");
            }
        });
        funItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // updateModeStr("seg");
                updateModeStr(5);
                System.out.println("追赶法");
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
            FunType = new String("高斯消元法");
            FunTypeInt = 1;
            jFieldMode.setText(FunType);
        } else if (num == 2) {
            FunType = new String("高斯列主元法");
            FunTypeInt = 2;
            jFieldMode.setText(FunType);
        } else if (num == 3) {
            FunType = new String("平方根法");
            FunTypeInt = 3;
            jFieldMode.setText(FunType);
        } else if (num == 4) {
            FunType = new String("改进的平方根法");
            FunTypeInt = 4;
            jFieldMode.setText(FunType);
        } else if (num == 5) {
            FunType = new String("追赶法");
            FunTypeInt = 5;
            jFieldMode.setText(FunType);
        }
    }
}

class Fun {
    public int dimension;
    public double[][] A;
    public double[] B;
    public double[] X;
    public void setData(double b[], double a[][], int dim) {
        dimension = dim;
        // System.out.println(dimension);
        // System.out.println(123213123);
        A = new double[dimension + 2][dimension + 2];
        B = new double[dimension + 2];
        X = new double[dimension + 2];
        for (int i = 1; i <= dimension; i++) {
            B[i] = b[i - 1];
        }
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                A[i][j] = a[i - 1][j - 1];
            }
        }
    }
    /**
     * 高斯列主元消元法
     * 
     * @return
     */
    public String calGaussEWPP() {
        int k;
        double t;
        for (int i = 1; i <= dimension; i++) {
            k = i;
            for (int j = i + 1; j <= dimension; j++) {
                if (Math.abs(A[k][i]) < Math.abs(A[j][i])) {
                    k = j;
                }
            }
            for (int j = i; j <= dimension; j++) {
                t = A[i][j];
                A[i][j] = A[k][j];
                A[k][j] = t;
            }
            t = B[i];
            B[i] = B[k];
            B[k] = t;
            for (int j = i + 1; j <= dimension; j++) {
                A[j][i] = A[j][i] / A[i][i];
                for (k = i + 1; k <= dimension; k++) {
                    A[j][k] = A[j][k] - A[j][i] * A[i][k];
                }
                B[j] = B[j] - A[j][i] * B[i];
            }
        }
        for (int i = dimension; i >= 1; i--) {
            for (int j = i + 1; j <= dimension; j++) {
                B[i] = B[i] - A[i][j] * B[j];
            }
            B[i] = B[i] / A[i][i];
        }
        String res = new String("");
        for (int i = 1; i <= dimension; i++) {
            res += String.valueOf(Math.round(B[i]) + " ");
        }
        return res;
    }

    public String Square() {
        double tmp[][] = new double[dimension + 2][dimension + 2];
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                tmp[i - 1][j - 1] = A[i][j];
            }
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                A[i][j] = tmp[i][j];
            }
        }
        for (int i = 1; i <= dimension; i++) {
            B[i - 1] = B[i];
        }

        int n = dimension;
        double l[][] = new double[dimension + 2][dimension + 2];
        double g[] = new double[dimension + 2];
        double y[] = new double[dimension + 2];
        double sum;
        for (int i = 0; i < n; i++) { // 分解: A = LDL^T
            sum = 0;
            for (int j = 0; j <= i - 1; j++) {
                for (int k = 0; k <= j - 1; k++)
                    sum += (g[k] * l[i][k] * l[j][k]);
                l[i][j] = (A[i][j] - sum) / g[j];
            }

            sum = 0;
            for (int k = 0; k <= i - 1; k++)
                sum += (g[k] * l[i][k] * l[i][k]);
            g[i] = A[i][i] - sum;
        }

        for (int i = 0; i < n; i++) { // 求y: L(DL^Tx) = b即Ly = b
            sum = 0;
            for (int k = 0; k <= i - 1; k++)
                sum += (l[i][k] * y[k]);
            y[i] = B[i] - sum;
        }

        for (int i = n - 1; i >= 0; i--) { // 求x: L^Tx = D^-1b
            sum = 0;
            for (int k = i + 1; k < n; k++)
                sum += (l[k][i] * X[k]);
            X[i] = y[i] / g[i] - sum;
        }

        String res = new String("");
        for (int i = 0; i < dimension; i++) {
            res += String.valueOf(Math.round(X[i]) + " ");
        }

        return res;
    }

    public String SquareImproved() {
        double tmp[][] = new double[dimension + 2][dimension + 2];
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                tmp[i - 1][j - 1] = A[i][j];
            }
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                A[i][j] = tmp[i][j];
            }
        }
        for (int i = 1; i <= dimension; i++) {
            B[i - 1] = B[i];
        }
        for (int i = 0; i < dimension; i++) {
            A[i][dimension] = B[i];
        }
        int n = dimension;
        int i, r, k;
        for (r = 0; r <= n - 1; r++) {
            for (i = r; i <= n; i++)
                for (k = 0; k <= r - 1; k++)
                    A[r][i] -= A[r][k] * A[k][i];
            for (i = r + 1; i <= n - 1; i++) {
                A[i][r] = A[r][i] / A[r][r];
            }
        }
        for (i = n - 1; i >= 0; i--) {
            for (r = n - 1; r >= i + 1; r--)

                A[i][n] -= A[i][r] * X[r];
            X[i] = A[i][n] / A[i][i];
        }
        String res = new String("");
        for (i = 0; i < dimension; i++) {
            res += String.valueOf(Math.round(X[i]) + " ");
        }
        return res;
    }

    public String gauss() {
        double tmp1[][] = new double[dimension + 2][dimension + 2];
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                tmp1[i - 1][j - 1] = A[i][j];
            }
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                A[i][j] = tmp1[i][j];
            }
        }
        for (int i = 1; i <= dimension; i++) {
            B[i - 1] = B[i];
        }
        for (int i = 0; i < dimension; i++) {
            A[i][dimension] = B[i];
        }
        int i, j, k;
        double tmp;
        // Guass消元
        int n = dimension;
        for (k = 0; k < (n - 1); k++) {
            for (i = (k + 1); i < n; i++) {
                if (Math.abs(A[k][k]) < 1e-6) {
                    continue;
                }
                tmp = A[i][k] / A[k][k];
                for (j = (k + 1); j < (n); j++) {
                    A[i][j] -= tmp * A[k][j];
                }
                B[i] -= tmp * B[k];
                A[i][k] = 0;
            }
        }
        X[n - 1] = B[n - 1] / A[n - 1][n - 1];
        for (i = (n - 2); i >= 0; i--) {

            X[i] = B[i];
            for (j = (i + 1); j < n; j++) {
                X[i] -= A[i][j] * X[j];
            }
            X[i] /= A[i][i];
        }
        String res = new String("");
        for (i = 0; i < dimension; i++) {
            res += String.valueOf(Math.round(X[i]) + " ");
        }
        return res;
    }
    public String zhuigai()
    {
        int i,j;
        double p;
        double tmp[][] = new double[dimension + 2][dimension + 2];
        for (i = 1; i <= dimension; i++) {
            for (j = 1; j <= dimension; j++) {
                tmp[i - 1][j - 1] = A[i][j];
            }
        }
        for (i = 0; i < dimension; i++) {
            for (j = 0; j < dimension; j++) {
                A[i][j] = tmp[i][j];
            }
        }
        for (i = 1; i <= dimension; i++) {
            B[i - 1] = B[i];
        }
        for (i = 0; i < dimension; i++) {
            A[i][dimension] = B[i];
        }
        int n = dimension;
        for(i=1;i<=n-1;i++)
        {
            p=A[i][i-1]/A[i-1][i-1];
            A[i][i-1]=0;
            A[i][i]-=p*A[i-1][i];
            A[i][n]-=p*A[i-1][n];
    
        }
        X[n-1]=A[n-1][n]/A[n-1][n-1];
        for(j=n-2;j>=0;j--)
        {
            X[j]=(A[j][n]-X[j+1]*A[j][j+1])/A[j][j];
        }
        String res = new String("");
        for (i = 0; i < dimension; i++) {
            res += String.valueOf(Math.round(X[i]) + " ");
        }
        return res;
    }
}