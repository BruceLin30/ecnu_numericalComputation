import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



import java.awt.Graphics;


public class Interpolation{
    static String sx = new String();
    static String sy = new String();
    static String sw = new String();
    static Lagrange lag = new Lagrange();
    static NewTon nTon = new NewTon();
    static SegLag seg = new SegLag();
    static String ModeType = new String("NULL");//默认的初始模式是空模式
    static int ModeTypeInt = 0;
    static double [] doublex;
    static double [] doubley;
    static double [] doublew;
    static Graphics g;
    static JFrame frame = new JFrame();
    //static DrawFunction frame = new DrawFunction();
    static String result = new String("");
    static JLabel Jresult;
    static JTextField jFieldMode = new JTextField(80);//模式选择
    static JTextField jFieldResult = new JTextField(80);
    //public static double []xx = new double[]{0.4,0.55,0.65,0.80,0.95,1.05};
    //public static double []yy = new double[]{0.41075,0.57815,0.69675,0.90,1.00,1.25382};
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.drawLine(100, 100, 300, 300);
    }
    public static void main(String[] args) {
        System.out.println("Test Success!");
        Interpolation pola = new Interpolation();
        
        pola.initMenuBar();//初始化菜单栏
        pola.initUI();//初始化UI界面
        
        //Lagrange l2 = new Lagrange();
        //l2.setData(xx, yy);
        //System.out.println(l2.calcluate(0.596));
        //NewTon newTon = new NewTon();
        //newTon.setData(xx, yy);
        //System.out.println(newTon.calculate(0.596));
        
    }
    /**
     * 处理输入的函数
     * @param sx
     * @param sy
     */
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
    /**
     * 更新结果的数值。可以被重写，需要被重写
     */
    public void updateUI(Lagrange l)
    {
        result = new String("");
        for (int i = 0 ; i < doublew.length;i++)
            result += String.valueOf(l.calculate(doublew[i]) + " ");
        System.out.println(result);
        jFieldResult.setText(result);
    }
    public void updateUI(NewTon nton)
    {
        result = new String("");
        for (int i = 0 ; i < doublew.length;i++)
            result += String.valueOf(nton.calculate(doublew[i]) + " ");
        System.out.println(result);
        jFieldResult.setText(result);
    }
    public void updateUI(SegLag segg)
    {
        result = new String("");
        for (int i = 0 ; i < doublew.length;i++)
            result += String.valueOf(segg.calculate(doublew[i]) + " ");
        System.out.println(result);
        jFieldResult.setText(result);
    }
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
        JLabel label = new JLabel("插值计算");
        label.setBounds(20, 20, 100, 20);
        label.setForeground(Color.BLUE);
        p.add(label);
        JLabel label2 = new JLabel("请输入X向量");
        label2.setBounds(20, 100, 100, 20);
        p.add(label2);
        JLabel label3 = new JLabel("请输入Y向量");
        label3.setBounds(20, 140, 100, 20);
        p.add(label3);
        JLabel label4 = new JLabel("请输入Z向量");
        label4.setBounds(20, 180, 100, 20);
        p.add(label4);
        JLabel label5 = new JLabel("结果向量：");
        label5.setBounds(20, 220, 100, 20);
        p.add(label5);
        
        //frame.add(label);
        /**
         * JTextField的设置
         * 创建文本框，指定可见列数为80列
         */
        jFieldMode.setText("当前模式：未选择");
        jFieldMode.setEditable(false);
        jFieldMode.setBounds(100, 20, 120, 30);
        jFieldMode.setForeground(Color.RED);
        p.add(jFieldMode);
        //frame.add(jFieldMode);
        final JTextField jFieldX = new JTextField(80);
        jFieldX.setBounds(100, 100, 200, 30);
        p.add(jFieldX);
        //frame.add(jFieldX);
        final JTextField jFieldY = new JTextField(80);
        jFieldY.setBounds(100, 140, 200, 30);
        p.add(jFieldY);
        //frame.add(jFieldY);
        final JTextField jFieldW = new JTextField(80);
        jFieldW.setBounds(100, 180, 200, 30);
        p.add(jFieldW);
        //frame.add(jFieldW);
        jFieldResult = new JTextField(80);
        jFieldResult.setEditable(false);
        jFieldResult.setBounds(100, 220, 200, 30);
        p.add(jFieldResult);
        //frame.add(jFieldResult); 

        /*
        p.add(jFieldMode);
        p.add(jFieldX);
        p.add(jFieldY);
        p.add(jFieldW);
        p.add(jFieldResult);
        */
        
        

        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        button1.setBounds(100, 270, 200, 40);//设置按钮在容器中的位置

        p.add(button1);
        //button.setBounds(int x,int y,int width,int height)  (x,y)代表的是左上角顶点的位置,width和height
        //的是按钮的宽度
        //button1.setFont(new Font(null, Font.PLAIN, 20));
        //panel2.add(button);//按钮加在容器上
        //frame.add(button1); 
        button1.addActionListener(new ActionListener()//对按钮增加监听
        {   
            //此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
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
        JMenuItem lag,newt,seg;
        JMenuBar menuBar = new JMenuBar();
        
        lag = new JMenuItem("拉格朗日插值");
        newt = new JMenuItem("牛顿插值");
        seg = new JMenuItem("分段插值");
        Menu = new JMenu("插值类型");
        Menu.add(lag);
        Menu.add(newt);
        Menu.add(seg);
        Menu.setSelected(true);
        menuBar.add(Menu);
        frame.setJMenuBar(menuBar);
        lag.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateModeStr("lag");
                System.out.println("当前模式：拉格朗日");
            }
        });
        newt.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                updateModeStr("newton");
                System.out.println("当前模式：牛顿");
            }
        });
        seg.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                updateModeStr("seg");   
                System.out.println("当前模式:分段");
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
/**
 * 拉格朗日插值类
 */
class Lagrange
{
    public int n;//几次插值
    public double x[];
    public double y[];
    //x[],y[]代表的是所有(x,y)的已知点
    public double calculate(double xx)
    {
        double result = 0;
        for (int i = 0 ;i <= n;i++)
        {
            double tmp = 1;
            for (int j = 0;j <= n;j++)
                if (j != i) tmp *= (xx - x[j]);
            for (int j = 0;j <= n;j++)
                if (j != i) tmp /= (x[i] - x[j]);
            tmp *= y[i];
            result +=  tmp;
        }
        return result;
    }
    public void setData(double xq[],double yq[])
    {
        n = xq.length - 1;
        x = new double[n + 1];
        y = new double[n + 1];
        for (int i = 0 ; i < xq.length;i++)
            x[i] = xq[i];
        for (int i = 0 ; i < yq.length;i++)
            y[i] = yq[i];
    }

}
/**
 * 牛顿插值类
 */
class NewTon
{
    public int n;//几次插值
    public double x[];
    public double y[];
    public void setData(double xq[],double yq[])
    {
        n = xq.length - 1;
        x = new double[n + 1];
        y = new double[n + 1];
        for (int i = 0 ; i < xq.length;i++)
            x[i] = xq[i];
        for (int i = 0 ; i < yq.length;i++)
            y[i] = yq[i];
    }
    public double calculate(double xx)
    {
        double []f = new double [n + 1];//差商表f[n] 表示 f[x,x0,x1,x2...,xn];
        f[0] = y[0];//对于差商f[x0] = f(x0)
        //f[0]表示f[x,x0] ，f[1] = f[x,x0,x1] = doubley
        double []F = new double [n + 1];
        double result = 0;//存储结果
        //计算累乘积
        for (int i = 0;i <= n;i++)
        {
            double tmp = 1;
            for (int j = 0;j <= n;j++)
            {
                if(j != i)
                {
                    tmp *= (x[i] - x[j]);
                }
            }
            F[i] = tmp;
        }
        //计算n阶差商
        for (int i = 1 ; i <= n;i++)
        {
            double tmp = 0;
            for (int k = 0;k <= i;k++)
            {
                tmp += ((y[i])/(F[i]));
            }
            f[i] = tmp;
        }
        //计算牛顿插值多项式
        result += f[0];
        for (int i = 0 ; i < n;i++)
        {
            double tmp = f[i+1];
            for (int j = 0;j <= n;j++)
            {
                tmp *= (xx - x[0]);
            }
            result += tmp;
        }
        return result;
    }
}
/**
 * 分段Lagrange插值类
 */
class SegLag
{
    public int n;//几次插值
    public double x[];
    public double y[];
    public double m[];
    public double x0[];
    public void setData(double xq[],double yq[])
    {
        n = xq.length;
        x = new double[n + 1];
        y = new double[n + 1];
        m = new double[n + 1];
        for (int i = 0 ; i < xq.length;i++)
            x[i] = xq[i];
        for (int i = 0 ; i < yq.length;i++)
            y[i] = yq[i];
        for (int i = 0 ; i < yq.length;i++)
            m[i] = -yq[i];
        
    }
    public double calculate(double xx)
    {
        int a = n;
        
        double add_xj_xk=0;//连加
		double mul_x0_xk=1;//连乘
		double add_h2n1x=0;//近似值
        for (int j=0;j<a;j++) {//二重循环计算H2n+1(x)
            mul_x0_xk=1;
            add_xj_xk=0;
                for(int k=0;k<a;k++) {//三重循环计算l'j(xj)和lj(x)
                if (j!=k) {				
                    add_xj_xk+=1/(x[j]-x[k]);
                    mul_x0_xk*=((xx-x[k])/(x[k]-x[j]));
                    
                }
            }
        add_h2n1x+=(y[j]*(1-(2*(xx-x[j])*add_xj_xk))*(mul_x0_xk*mul_x0_xk))+(m[j]*(xx-x[j])*(mul_x0_xk*mul_x0_xk));
    }
    return add_h2n1x;
}
}