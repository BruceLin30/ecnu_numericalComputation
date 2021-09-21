import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Interpolation {
    static String s = new String ();
    //public static double []xx = new double[]{0.4,0.55,0.65,0.80,0.95,1.05};
    //public static double []yy = new double[]{0.41075,0.57815,0.69675,0.90,1.00,1.25382};
    public static void main(String[] args) {
        System.out.println("Test Success!");
        Interpolation pola = new Interpolation();
        //需要读入数据
        //具体数据类型：...

        pola.initData();//初始化数据
        pola.setData();//设置数据
        pola.initUI();//初始化UI界面
        
        //Lagrange l = new Lagrange();
        //l.setLagData(xx, yy);
        //System.out.println(l.calcluate(0.596));
    }
    public void setData()
    {
        
    }
    public void initData()
    {

    }
    public void initUI()
    {
        /**
         * 这里是对frame的设置
         */
        javax.swing.JFrame frame = new javax.swing.JFrame("Bruce");//初始化窗体结构
        frame.setSize(800,600);//设置容器尺寸
        frame.setLocation(200,200);
        frame.setLayout(null);//设置布局


        /**
         * 这里是对labels的设置
         */
        JLabel label = new JLabel("love you ,maybe",JLabel.CENTER);
        frame.add(label);
        label.setBounds(10, 10, 100, 100);

        /**
         * 这里是对Buttons的设置
         */
        JButton button = new JButton("Linzixuan");//
        button.setBounds(80, 80, 200, 100);//设置按钮在容器中的位置
        //button.setBounds(int x,int y,int width,int height)  (x,y)代表的是左上角顶点的位置,width和height
        //的是按钮的宽度
        frame.add(button);//按钮加在容器上
        button.addActionListener(new ActionListener()//对按钮增加监听
        {   
            //此处需要使用的是匿名类，需要重写actionPerformed函数，否则会出错
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println(123);
            }   
        });

        /**
         * 这里是函数结尾的必要设置
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//界面结束后关闭程序
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

