import java.awt.Color; 
import java.awt.Frame;
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.BorderFactory;
import javax.swing.JButton; 
import javax.swing.JComboBox;
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
import javax.swing.border.LineBorder; 
 
public class DrawFn extends JFrame implements ItemListener{
	private JTextField txt_c;
	private JTextField txt_b;
	private JTextField txt_a;
	public JComboBox chooseFun;
	//draw_actionAdapter adapter;
	public int A;
	public drawFnPanel panel = new drawFnPanel(); 
	public static void main(String[] args) {
		DrawFn frame=new DrawFn();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); 
		frame.setBackground(Color.BLACK);
		}
	public DrawFn() {
		super("画函数图像");
		final JLabel aLabel = new JLabel();
		aLabel.setForeground(Color.WHITE);
		aLabel.setText("a=");
		aLabel.setBounds(650, 10, 21, 18); //设置位置，大小
		getContentPane().add(aLabel);
		txt_a = new JTextField();
		txt_a.setBounds(680, 8, 60, 18);
		getContentPane().add(txt_a);
		//
		final JLabel bLabel = new JLabel();
		bLabel.setForeground(Color.WHITE);
		bLabel.setText("b="); 
		bLabel.setBounds(650, 30, 21, 18); 
		getContentPane().add(bLabel); 
		txt_b = new JTextField(); 
		txt_b.setBounds(680, 28, 60, 18); 
		getContentPane().add(txt_b);
		//
		final JLabel cLabel = new JLabel(); 
		cLabel.setForeground(Color.WHITE);
		cLabel.setText("c="); 
		cLabel.setBounds(650, 50, 32, 18);
		getContentPane().add(cLabel);
		//this.setContentPane(cLabel); 
		txt_c = new JTextField(); 
		txt_c.setBounds(680, 48, 60, 18); 
		getContentPane().add(txt_c);
		//this.setContentPane(txt_c);
 
		//设置按钮
		final JButton button = new JButton(); 
		button.addActionListener(new draw_actionAdapter(this));//什么意思？
		button.setText("绘制"); 
		button.setBounds(680, 80, 60, 18); 
		getContentPane().add(button);
		//this.setContentPane(button);
		
		//定义下拉菜单
		final JLabel choose = new JLabel(); 
		choose.setForeground(Color.WHITE);
		choose.setText("请选择函数类型："); 
		choose.setBounds(20, 5, 120, 20);
		getContentPane().add(choose);
		JPanel jp1=new JPanel();
		String []fun={"ax^2+bx+c","ae^bx+c","a*sin(PIx+b)+c","a*b^x+c","a*x^b+c","敬请期待"};
		chooseFun=new JComboBox(fun);
		chooseFun.setBounds(20, 30, 120, 20);
		jp1.add(chooseFun);
		chooseFun.setMaximumRowCount(3);
		getContentPane().add(chooseFun);
		
		
		
		chooseFun.addItemListener(this);
		getContentPane().add(panel);
		//this.setContentPane(panel);
		
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==e.SELECTED)
		{
			A=chooseFun.getSelectedIndex();
			//System.out.println(A);
		}
	}
	
	
 
	public void paintFn(ActionEvent e){
		panel.paintFn(Integer.parseInt(txt_a.getText()), Integer.parseInt(txt_b.getText()), Integer.parseInt(txt_c.getText()));
	}
	
	class draw_actionAdapter implements ActionListener{ 
		private DrawFn adapter; 
		public draw_actionAdapter(DrawFn adapter){ 
			this.adapter=adapter; 
			} 
		public void actionPerformed(ActionEvent e){
			//adapter.getA(e);
			adapter.paintFn(e);
			adapter.repaint();
			}
		} 
class drawFnPanel extends JPanel{
	private float fa; 
	private float fb; 
	private float fc;
	private int UnitLength=100;//可以任意改变该像素值
	public void paintFn(int a,int b,int c){
		fa=a;
		fb=b; 
		fc=c; 
		}
	
	public double Fun(double x){
		//System.out.println("A="+DrawFn.A);
		if(A==0)
			return fa*x*x+fb*x+fc;
		else if(A==1)
			return fa*Math.pow(Math.E, fb*x)+fc ;//这里可以输入任何函数
		else if(A==2)
			return fa*Math.sin(Math.PI*x+fb)+fc;
		else if(A==3)
			return fa*Math.pow(fb, x)+fc;
		else if(A==4)
			return fa*Math.pow(x,fb)+fc;
		else
			return 0;
	}
	 int width,height;
	 int X,Y;
	 //重载paintComponent函数
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		width = this.getWidth();//获得宽度
		height = this.getHeight();//获得高度
		X=width/2;
		Y=height/2;//获得原点坐标
		this.drawAxes(g);
		this.function(g);
	}
	//画坐标轴
	private void drawAxes(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawLine(0, Y, width, Y);
		g.drawLine(X, 0, X, height);
		g.drawString("0",X + 2,Y +12); //画原点数字
		for(int i=1;i*UnitLength<width;i++)
		{
			g.drawLine(X+i*UnitLength,Y-1,X+i*UnitLength,Y-6);//画X轴正向的小竖线
			g.drawLine(X - i*UnitLength, Y-1, X - i*UnitLength, Y-6);//画X轴负向的小竖线
			g.drawString(String.valueOf(i), X + i*UnitLength-3, Y + 12);  // x轴正向数字
			g.drawString(String.valueOf(i*-1), X - i*UnitLength-3, Y + 12);  // x轴负向数字
			//画Y轴
			g.drawLine(X+1,Y+i*UnitLength,X+6,Y+i*UnitLength);
			g.drawLine(X+1,Y-i*UnitLength,X+6,Y-i*UnitLength);
			g.drawString(String.valueOf(i), X-12, Y - i*UnitLength-3);
			g.drawString(String.valueOf(i*-1), X-12, Y + i*UnitLength-3);
		}
	}
	//实现任意函数函数图像
	public void function(Graphics g1)
	{
		Point2D temp1,temp2;
		double x,y;//我们看到的坐标值
		Graphics2D g = (Graphics2D)g1;
		g.setColor(Color.WHITE);
		x = -1.0*X/UnitLength;
		//temp1返回面板的实际坐标值（以像素为单位）
		y = Fun(x);
		temp1 = new Point2D.Double(this.alterX(x * UnitLength), this.alterY(y * UnitLength));
		for(int i = 0 ; i < width; i++){
			x =x + 1.0/UnitLength;//前进一个像素
			y = Fun(x);
			if ( Math.abs(y) < Y){
			temp2 = new Point2D.Double(this.alterX(x * UnitLength), this.alterY(y * UnitLength));
			g.draw(new Line2D.Double(temp1, temp2));
			temp1 = temp2;
			}
		}
		//repaint();
	}
	  //新坐标对应的原坐标
	private double alterX(double x){
		return  x + X;
	}
	private double alterY(double y){
		return -1 *( y - Y);
	}
} 
}