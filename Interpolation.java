import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import jdk.internal.org.objectweb.asm.tree.FrameNode;
public class Interpolation {
    static String sx = new String();
    static String sy = new String();
    static String sw = new String();
    static double [] doublex;
    static double [] doubley;
    static double [] doublew;
    public static double []xx = new double[]{0.4,0.55,0.65,0.80,0.95,1.05};
    public static double []yy = new double[]{0.41075,0.57815,0.69675,0.90,1.00,1.25382};
    public static void main(String[] args) {
        System.out.println("Test Success!");
        Interpolation pola = new Interpolation();
        //需要读入数据
        //具体数据类型：...

        pola.initData();//初始化数据
        pola.setData();//设置数据
        pola.initUI();//初始化UI界面
        
        Lagrange l2 = new Lagrange();
        l2.setLagData(xx, yy);
        System.out.println(l2.calcluate(0.596));
    }
    public void setData()
    {
        
    }
    public void initData()
    {

    }
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
        doublew = new double[strw.length + 1];
        for (int i = 0;i < strw.length;i++)
            doublew[i] = Double.parseDouble(strw[i]);
    }
    public void initUI()
    {
        /**
         * 这里是对frame的设置
         */
        JFrame frame = new JFrame("Bruce");//初始化窗体结构
        frame.setSize(800,600);//设置容器尺寸
        //frame.setLocation(200,200);
        frame.setLayout(null);//设置布局
        
        frame.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        /*
        JPanel panel1 = new JPanel();//创建文本框面板
        panel1.setBorder(new EmptyBorder(50,50,50,50));
        panel1.setBackground(Color.red);
        
        JPanel panel2 = new JPanel();//创建文本框面板
        panel2.setBorder(new EmptyBorder(50,50,50,50));
        panel2.setBackground(Color.white);
        */
        
        /**
         * 这里是对labels的设置
         */
        JLabel label = new JLabel("love you ,maybe",JLabel.CENTER);
        frame.add(label);
        /**
         * JTextField的设置
         * 创建文本框，指定可见列数为8列
         */
        final JTextField jFieldX = new JTextField(80);
        //jFieldX.setFont(new Font(null, Font.PLAIN, 20));
        frame.add(jFieldX);
        final JTextField jFieldY = new JTextField(80);
        frame.add(jFieldY);
        final JTextField jFieldW = new JTextField(80);
        frame.add(jFieldW);

        /**
         * 这里是对Buttons的设置
         */
        JButton button1 = new JButton("开始计算");//
        //button.setBounds(80, 80, 200, 100);//设置按钮在容器中的位置
        //button.setBounds(int x,int y,int width,int height)  (x,y)代表的是左上角顶点的位置,width和height
        //的是按钮的宽度
        button1.setFont(new Font(null, Font.PLAIN, 20));
        //panel2.add(button);//按钮加在容器上
        frame.add(button1); 
        button1.addActionListener(new ActionListener()//对按钮增加监听
        {   
            //此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                sx = jFieldX.getText();
                sy = jFieldY.getText();
                sw = jFieldW.getText();
                processInput(sw);
                processInput(sx, sy);

                System.out.println(sx);
                System.out.println(sy);
                
                System.out.println(" ");
                for (int i = 0;i < doublex.length;i++)
                {
                    System.out.print(doublex[i] + " ");
                }
                System.out.println(" ");
                for (int i = 0;i < doubley.length;i++)
                {
                    System.out.print(doubley[i] + " ");
                }
                System.out.println(" ");
                Lagrange l = new Lagrange();
                l.setLagData(doublex,doubley);
                for (int i = 0 ; i < doublew.length;i++)
                    System.out.println(l.calcluate(doublew[i]));
            }   
        });
        
        /**
         * 这里是函数结尾的必要设置
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//界面结束后关闭程序
        frame.setLocationRelativeTo(null);//在屏幕上居中显示框架
        frame.setVisible(true);//界面可视化，需要放在最后面，对所有的组件进行渲染。 
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
    public double calcluate(double xx)
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
    public void setLagData(double xq[],double yq[])
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
 * 分段Lagrange插值类
 */
class SegLag
{

}