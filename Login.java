import java.awt.Font;

public class Login {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login l = new Login();
		l.initUI();
	}


public void initUI() {
	javax.swing.JFrame frame = new javax.swing.JFrame();//初始化窗体结构
	frame.setTitle("登录");
	frame.setSize(800,800);
	frame.setDefaultCloseOperation(3);
	frame.setLocationRelativeTo(null);
		
	javax.swing.ImageIcon image = new javax.swing.ImageIcon("C:\\Users\\Apple\\Pictures\\Saved Pictures\\冠军.jpg");
	javax.swing.JLabel Limage = new javax.swing.JLabel(image);
	frame.add(Limage,java.awt.BorderLayout.NORTH);//上部图片部分
	javax.swing.JPanel centerPanel = new javax.swing.JPanel();//中部面板设置
	javax.swing.JLabel Lname = new javax.swing.JLabel("账号：");
	Lname.setFont(new Font("楷体",Font.BOLD,20));
	centerPanel.add(Lname);//加在面板上！！！！
	javax.swing.JTextField Tname = new javax.swing.JTextField();
	java.awt.Dimension dim = new java.awt.Dimension(300,30);
	Tname.setPreferredSize(dim);
	centerPanel.add(Tname);//加在面板上！！！！
	javax.swing.JLabel Lcode = new javax.swing.JLabel("密码：");
	Lcode.setFont(new Font("楷体",Font.BOLD,20));
	centerPanel.add(Lcode);//加在面板上！！！！
	javax.swing.JPasswordField Pcode = new javax.swing.JPasswordField();
	java.awt.Dimension Pdim = new java.awt.Dimension(300,30);
	Pcode.setPreferredSize(Pdim);
	centerPanel.add(Pcode);//加在面板上！！！！
	javax.swing.JCheckBox C1 =new javax.swing.JCheckBox("记住密码");
	C1.setFont(new Font("楷体",Font.BOLD,20));
	centerPanel.add(C1);//加在面板上！！！！
	javax.swing.JCheckBox C2 = new javax.swing.JCheckBox("自动登录");
	C2.setFont(new Font("楷体",Font.BOLD,20));
	centerPanel.add(C2);//加在面板上！！！！
	javax.swing.JButton Blogin = new javax.swing.JButton("登录");
	Blogin.setPreferredSize(new java.awt.Dimension(300,40));
	Blogin.setFont(new Font("楷体",Font.BOLD,20));
	centerPanel.add(Blogin);//加在面板上！！！！
	frame.add(centerPanel,java.awt.BorderLayout.CENTER);//中部面板设置完成
	javax.swing.JPanel westPanel = new javax.swing.JPanel();//设置左边的面板
	javax.swing.ImageIcon Icon = new javax.swing.ImageIcon("C:\\Users\\Apple\\Pictures\\Saved Pictures\\队标.jpg");
	westPanel.add(new javax.swing.JLabel(Icon) );//加在面板上！！！！
	westPanel.setPreferredSize(new java.awt.Dimension(200,0));
	westPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));//流式布局设置为靠右对齐
	frame.add(westPanel,java.awt.BorderLayout.WEST);//设置完成
	javax.swing.JPanel eastPanel = new javax.swing.JPanel();
	eastPanel.setPreferredSize(new java.awt.Dimension(200,0));
	frame.add(eastPanel,java.awt.BorderLayout.EAST);//右边的面板
	frame.setVisible(true);
	
	
}
}