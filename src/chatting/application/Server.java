package chatting.application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Server implements ActionListener{
    JTextField text1;
    JPanel a1;
    static Box vertical=Box.createVerticalBox();
    static JFrame f=new JFrame();
     static DataOutputStream dou;
    Server()
    {
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,53);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back= new JLabel(i3);
        back.setBounds(5,14,25,25);
        f.add(back);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        }
        );
         
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(47, 47, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,0 , 50, 50);
        p1.add(profile);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video= new JLabel(i9);
        video.setBounds(270,12,30,30);
        f.add(video);
        p1.add(video);
        
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/icons22.png"));
        Image i11 = i10.getImage().getScaledInstance(26, 27, Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone= new JLabel(i12);
        phone.setBounds(317,15,30,25);
        f.add(phone);
        p1.add(phone);
        
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel morevert= new JLabel(i15);
        morevert.setBounds(360,15,10,25);
        f.add(morevert);
        p1.add(morevert);
            
        JLabel name= new JLabel("Server");
        name.setBounds(110,12,100,15);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN SERIF",Font.BOLD,18));
        p1.add(name);
        
        JLabel status= new JLabel("Actvive Now");
        status.setBounds(110,32,100,17);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN SERIF",Font.BOLD,10));
        p1.add(status);
        
        a1=new JPanel();
        a1.setBounds(5,58,390,550);
        f.add(a1);
        
        text1= new JTextField();
        text1.setBounds(5,610,310,40);
        text1.setFont(new Font("SANSERIF",Font.PLAIN,16));
        f.add(text1);
        
        JButton send=new JButton("Send"); 
        send.setBounds(316,610,79,40);
        send.setBackground(new Color(7,94,84));
        send.setFont(new Font("SANSERIF",Font.PLAIN,18));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        f.add(send);
                
                
        f.getContentPane().setBackground(Color.white);
        f.setSize(400,650);
        f.setLocation(200,0);
        f.setUndecorated(true);
        f.setVisible(true);      
    }
       
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
        String out=text1.getText();
       
        JPanel p2= formatLabel(out);
        
        
        a1.setLayout(new BorderLayout());//top-botom-left-right
        JPanel right=new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        a1.add(vertical,BorderLayout.PAGE_START);
        
        dou.writeUTF(out);
        text1.setText("");
        f.repaint();
        f.invalidate();
        f.validate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static JPanel formatLabel(String out)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        panel.add(output);
        return panel;
    }
    public static void main(String args[]) {
        
        new Server();
        try
        {
            ServerSocket skt= new ServerSocket(6001);
            while(true)
            {
                Socket s=skt.accept();
                DataInputStream din =new DataInputStream(s.getInputStream());//To recieve message
                dou=new DataOutputStream(s.getOutputStream());//To send messsage
                while(true)
                {
                    String msg=din.readUTF();//to read the message,we use read.UTF() it returns String
                    JPanel panel=formatLabel(msg);
                    
                    JPanel left =new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
}
