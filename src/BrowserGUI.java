
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

public class BrowserGUI
{
    JFrame fr;
    JPanel mnp,srcp,shp;
    JButton prv,nxt,go,rfrs,hst,star,home,srch;
    JTextField tf;
    JEditorPane jep;
    BtnHandler hnd;
    boolean check = true;
    String first;
    pnHandler pnhnd;
    FileReaderWriter frw;
    JRadioButton rb;
    JMenuBar jmb;
    JMenu menu[];
    JMenuItem mi[];
    
    public BrowserGUI()
    {
        initGUI();
    }
    public void initGUI()
    {
        fr = new JFrame("Hassan's Browser");
        fr.setLayout(new BorderLayout());
        
        mnp = new JPanel();
        mnp.setLayout(new FlowLayout());
        
        srcp = new JPanel();
        srcp.setLayout(new FlowLayout());
        
        shp = new JPanel();
        shp.setLayout(new GridLayout(1,1));
        
        hnd = new BtnHandler(this);
        pnhnd = new pnHandler();
        frw = new FileReaderWriter();
        Font fbtn = new Font("San-Serif", Font.BOLD, 20), fmaj = new Font("San-Serif", Font.BOLD, 40), fmin = new Font("San-Serif", Font.BOLD, 20);
        
        ImageIcon picon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\prv.jpg");
        Image pdabimg = picon.getImage();
        Image pmod = pdabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        picon = new ImageIcon(pmod);
        prv = new JButton(picon);
        prv.setActionCommand("<");
        prv.addActionListener(hnd);
        prv.setBackground(Color.white);
        prv.setForeground(Color.red);
        prv.setFont(fbtn);
        //prv.setSize(5, 10);
        prv.setPreferredSize(new Dimension(30, 30));
        srcp.add(prv);
        
        ImageIcon nicon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\nxt.jpg");
        Image ndabimg = nicon.getImage();
        Image nmod = ndabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        nicon = new ImageIcon(nmod);
        nxt = new JButton(nicon);
        nxt.setActionCommand(">");
        nxt.addActionListener(hnd);
        nxt.setBackground(Color.white);
        nxt.setForeground(Color.red);
        nxt.setFont(fbtn);
        nxt.setPreferredSize(new Dimension(30, 30));
        srcp.add(nxt);
        
        ImageIcon ricon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\rfrs.jpg");
        Image rdabimg = ricon.getImage();
        Image rmod = rdabimg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ricon = new ImageIcon(rmod);
        rfrs = new JButton(ricon);
        rfrs.setActionCommand("r");
        rfrs.addActionListener(hnd);
        rfrs.setBackground(Color.LIGHT_GRAY);
        rfrs.setForeground(Color.red);
        rfrs.setFont(fbtn);
        rfrs.setPreferredSize(new Dimension(30, 30));
        srcp.add(rfrs);
        
        tf = new JTextField(40);
        tf.setFont(fmin);
        srcp.add(tf);
        
        ImageIcon sicon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\go.jpg");
        Image sdabimg = sicon.getImage();
        Image smod = sdabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        sicon = new ImageIcon(smod);
        go = new JButton(sicon);
        go.setActionCommand("g");
        go.addActionListener(hnd);
        go.setBackground(Color.LIGHT_GRAY);
        go.setForeground(Color.red);
        go.setPreferredSize(new Dimension(30, 30));
        srcp.add(go);
        
        ImageIcon hicon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\hst.jpg");
        Image hdabimg = hicon.getImage();
        Image hmod = hdabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        hicon = new ImageIcon(hmod);
        hst = new JButton(hicon);
        hst.setActionCommand("h");
        hst.addActionListener(hnd);
        hst.setBackground(Color.LIGHT_GRAY);
        hst.setForeground(Color.red);
        hst.setPreferredSize(new Dimension(30, 30));
        srcp.add(hst);
        
        ImageIcon bicon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\star.jpg");
        Image bdabimg = bicon.getImage();
        Image bmod = bdabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        bicon = new ImageIcon(bmod);
        star = new JButton(bicon);
        star.setActionCommand("s");
        star.addActionListener(hnd);
        star.setBackground(Color.LIGHT_GRAY);
        star.setForeground(Color.red);
        star.setPreferredSize(new Dimension(30, 30));
        srcp.add(star);
        
        ImageIcon hmicon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\home.jpg");
        Image hmdabimg = hmicon.getImage();
        Image hmmod = hmdabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        hmicon = new ImageIcon(hmmod);
        home = new JButton(hmicon);
        //home.setText("home");
        home.setActionCommand("hm");
        home.addActionListener(hnd);
        home.setBackground(Color.LIGHT_GRAY);
        home.setForeground(Color.red);
        home.setPreferredSize(new Dimension(30, 30));
        srcp.add(home);

        ImageIcon shicon = new ImageIcon("C:\\Users\\DELL\\Documents\\NetBeansProjects\\Browser\\src\\srch.jpg");
        Image shdabimg = shicon.getImage();
        Image shmod = shdabimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        shicon = new ImageIcon(shmod);
        srch = new JButton(shicon);
        //srch.setText("search");
        srch.setActionCommand("sh");
        srch.addActionListener(hnd);
        srch.setBackground(Color.LIGHT_GRAY);
        srch.setForeground(Color.red);
        srch.setPreferredSize(new Dimension(30, 30));
        srcp.add(srch);
        
        jmb=new JMenuBar();
        
        menu=new JMenu[3];
        menu[0] = new JMenu("Set HomePage");
        menu[1] = new JMenu("Set Favourite");
        menu[2] = new JMenu("Firewall");

        mi = new JMenuItem[6];
        mi[0]= new JMenuItem("Set as HomePage");
        mi[1]= new JMenuItem("Add to Favorite");
        mi[2]= new JMenuItem("Disable Firewall");
        mi[3]= new JMenuItem("Add to Firewall");
        mi[4]= new JMenuItem("Show Firewall KeyWords");
        mi[5]= new JMenuItem("Clear Firewall");
        
        rb=new JRadioButton("Disable Firewall",true);
        mi[2].add(rb);
        rb.addActionListener(hnd);
        
        mi[0].addActionListener(hnd);
        menu[0].add(mi[0]);
        
        mi[1].addActionListener(hnd);
        menu[1].add(mi[1]);
        
        for (int i=2;i<6;i++)
        {
            mi[i].addActionListener(hnd);
            menu[2].add(mi[i]);
        }
        
        jmb.add(menu[0]);
        jmb.add(menu[1]);
        jmb.add(menu[2]);
        
        mnp.add(jmb);
        fr.add(mnp,BorderLayout.NORTH);
        
        //srcp.setPreferredSize(new Dimension(1000, 30));
        fr.add(srcp,BorderLayout.CENTER);
        
        
        jep = new JEditorPane();
        jep.setEditable(false);
        
        first=frw.ReadHomePage();
        try
        {
            //tf.setText("https://");
            jep.setPage(first);
        }
        catch (IOException ex) 
        {
            System.out.println("Please connect to internet!");
        }
        
        jep.addHyperlinkListener
        (
            new HyperlinkListener() 
            {
                public void hyperlinkUpdate(HyperlinkEvent e) 
                {
                    if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                    {
                        try
                        {
                            jep.setPage(e.getURL().toString());
                            tf.setText(e.getURL().toString());
                        } 
                        catch (IOException ex) 
                        {
                            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        );
        
        shp.add(jep);
        shp.add(new JScrollPane(jep));
        
        fr.add(shp,BorderLayout.SOUTH);
        
        fr.setSize(1000,700);
        //fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fr.pack();
        fr.setVisible(true);
    }
    public void loadPage(String url)
    {
        try
        {
            jep.setPage(url);
            tf.setText(url);
        }
        catch(IOException ioexp)
        {
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);    
        }
    }
}

// http://www.google.com
