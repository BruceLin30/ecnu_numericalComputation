import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Graphics;
public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800,600);//设置容器尺寸
        frame.setLayout(null);//设置布局
        //frame.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        JTextField field = new JTextField(20);
        field.setEditable(true);
        JPanel p = new JPanel();
        p.add(field);
        p.setBackground(Color.BLUE);
        p.setSize(400,200);
        frame.getContentPane().add(p);
        frame.setVisible(true);
    }
}
