import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultHighlighter;



public class BtnHandler implements ActionListener
{
    BrowserGUI refg;
    Scanner scanner = new Scanner(System.in);
    int size = 1;
    String[] arr;
    int index = 0;
    String URL;
    LocalDateTime ldt = LocalDateTime.now();
    
    
    
    public BtnHandler(BrowserGUI gg)
    {
        this.refg = gg;
        //arr = new String[size];
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String btnText = e.getActionCommand();
        if (btnText.equals("<") && (refg.pnhnd.bStack.size()>=1))
        {
            try
            {
                ldt= LocalDateTime.now();
                refg.pnhnd.MoveForward(refg.tf.getText()); 
                
                URL=refg.pnhnd.TopOnBackward();
                if (URL.equals(refg.tf.getText()))
                {
                    URL=refg.pnhnd.TopOnBackward();
                }
                refg.frw.WriteFile(URL);
                refg.frw.WriteFile(ldt.toString());
                refg.tf.setText(URL);
                refg.jep.setPage(URL);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals(">") && (refg.pnhnd.fStack.size()>=1))
        {
            try
            {
                ldt= LocalDateTime.now();
                refg.pnhnd.MoveBackward(refg.tf.getText());
                URL=refg.pnhnd.TopOnForward();
                if (URL.equals(refg.tf.getText()))
                {
                    URL=refg.pnhnd.TopOnForward();
                }
                refg.frw.WriteFile(URL);
                refg.frw.WriteFile(ldt.toString());
                refg.tf.setText(URL);
                refg.jep.setPage(URL);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("r"))
        {
            try
            {
                if(refg.jep.getPage()==null)
                {
                    //System.out.println("refresh");
                    refg.jep.setPage(refg.first);
                }
                else
                {
                    System.out.println("refresh");
                    refg.jep.setPage(refg.tf.getText());
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("g"))
        {
            System.out.println("go");
            try
            {
                if (refg.check)
                {
                    String first;
                    if (refg.pnhnd.isEmptyBackward())
                    {
                        System.out.println("You are not allowed to access this website.");
                        //JOptionPane.showInternalMessageDialog( refg.fr,"You are not allowed to access this website.");
                        first=refg.first;
                    }
                    else
                    {
                        first=refg.pnhnd.bStack.peek();
                    }
                    
                    String getURL=refg.tf.getText();
                    
                    File f= new File("Firewall.data");
                    FileReader fr= new FileReader(f);
                    BufferedReader br= new BufferedReader(fr);
                
                    String keyWord=br.readLine();
                    while (keyWord!=null)
                    {
                        if (getURL.contains(keyWord))
                        {
                            refg.tf.setText(first);
                            return;
                        }
                        keyWord=br.readLine();
                    }
                    br.close();
                    fr.close();
                }
                refg.jep.setPage(refg.tf.getText());
                ldt= LocalDateTime.now();
                refg.frw.WriteFile(refg.tf.getText());
                refg.frw.WriteFile(ldt.toString());  
                refg.pnhnd.MoveBackward(refg.tf.getText());
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("h"))
        {
            try {
                
                JFrame historyFrame=new JFrame("History");
                historyFrame.setLayout(new GridBagLayout());
                GridBagConstraints c=new GridBagConstraints();
                
                JButton clhBtn=new JButton("Clear History");
                
                File f= new File("Data.data");
                FileReader fr= new FileReader(f);
                BufferedReader br= new BufferedReader(fr);
                
                String URL=br.readLine();
                String dt=br.readLine();
                DefaultListModel historyList=new DefaultListModel();
                System.out.println(URL+"-"+dt);
                
                while (URL!=null && dt!=null)
                {
                    historyList.addElement(URL+" - "+dt);
                    URL=br.readLine();
                    dt=br.readLine();
                }
                
                ListHandler listHnd=new ListHandler(refg);
                JList hjlist= new JList(historyList);
                hjlist.addListSelectionListener(listHnd);
                
                br.close();
                fr.close();
                
                clhBtn.addActionListener(this);
                
                c.gridx=0;
                c.gridy=0;
                c.gridwidth=4;
                c.gridheight=1;
                c.fill=GridBagConstraints.BOTH;
                historyFrame.add(clhBtn,c);
                
                c.gridx=0;
                c.gridy=1;
                c.gridwidth=4;
                c.gridheight=4;
                c.ipadx=300;
                c.ipady=300;
                hjlist.setSize(500,500);
                historyFrame.add(hjlist,c);
                JScrollPane hScroll=new JScrollPane(hjlist);
                historyFrame.add(hScroll,c);
                historyFrame.setSize(400, 400);
                historyFrame.setLocationRelativeTo(null);
                historyFrame.setVisible(true);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
//        else if (btnText.equals("b"))
//        {
//            
//        }
        else if (btnText.equals("hm"))
        {
            try
            {
                ldt= LocalDateTime.now();
                refg.frw.WriteFile(refg.first);
                refg.frw.WriteFile(ldt.toString());
                refg.tf.setText(refg.first);
                refg.jep.setPage(refg.first);
                refg.pnhnd.MoveBackward(refg.first);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("sh"))
        {
            try {
                String siteText=refg.jep.getText();
                StringTokenizer st=new StringTokenizer(siteText);
                
                String input=JOptionPane.showInputDialog("Please enter the search word");
                if (input==null)
                {
                    return;
                }
                else
                {
                    int count=0;
                    if (siteText.contains(input))
                    {
                        while (st.hasMoreTokens())
                        {
                            if (st.nextToken().contains(input))
                            {
                                count++;
                            }
                        }
                        JOptionPane.showMessageDialog(refg.fr, "Word has a count of: "+String.valueOf(count));
                    }
                    else
                    {
                        System.out.println("not found");
                        JOptionPane.showMessageDialog(refg.fr, "Word not found");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("Clear History"))
        {
            try 
            {
                refg.frw.clearFile("Data.data");
                        
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("s"))
        {
            try {
                
                JFrame favoriteFrame=new JFrame("Favorite");
                favoriteFrame.setLayout(new GridBagLayout());
                GridBagConstraints c=new GridBagConstraints();
                
                File f= new File("Favorite.data");
                FileReader fr= new FileReader(f);
                BufferedReader br= new BufferedReader(fr);
                
                JButton clfBtn=new JButton("Clear Favorite");
                clfBtn.addActionListener(this);
                
                String URL=br.readLine();                
                DefaultListModel favoriteList=new DefaultListModel();
                int i=0;
                while (URL!=null)
                {
                    favoriteList.addElement(URL);
                    URL=br.readLine();
                }
                ListHandler listHnd=new ListHandler(refg);
                JList fjlist= new JList(favoriteList);
                fjlist.addListSelectionListener(listHnd);
                
            
                br.close();
                fr.close();
                
                c.gridx=0;
                c.gridy=0;
                c.gridwidth=4;
                c.gridheight=1;
                c.fill=GridBagConstraints.BOTH;
                favoriteFrame.add(clfBtn,c);
                
                c.gridx=0;
                c.gridy=1;
                c.gridwidth=4;
                c.gridheight=4;
                c.ipadx=300;
                c.ipady=300;
                fjlist.setSize(500,500);
                favoriteFrame.add(fjlist,c);
                JScrollPane fScroll=new JScrollPane(fjlist);
                favoriteFrame.add(fScroll,c);
                
                favoriteFrame.setSize(400, 400);
                favoriteFrame.setLocationRelativeTo(null);
                //favoriteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                favoriteFrame.setVisible(true);
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("Clear Favorite"))
        {
            try
            {
                refg.frw.clearFile("Favorite.data");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("Add to Favorite"))
        {
            try
            {
                if(refg.tf.getText()!="https://")
                {
                    refg.frw.ftwrite(refg.tf.getText());
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (btnText.equals("Set as HomePage"))
        {
            //String
//            try
//            {
            String inp=JOptionPane.showInputDialog(null,"Please enter homepage URL: ");
            
//            }
//            catch (NumberFormatException ex)
//            {
//                ex.printStackTrace();
//                String inp=JOptionPane.showInputDialog("Please enter homepage URL: ");
//                refg.frw.hpwrite(inp);
//            }      
            while (!inp.contains("http")||!inp.contains("com")||inp==(null))
            {
                inp=JOptionPane.showInputDialog("Please enter homepage URL: ");
                //inp = "https://www.google.com";
            }
            refg.frw.hpwrite(inp);
            
        }
        else if (btnText.equals("Enable Firewall"))
        {
            refg.rb.setText("Disable Firewall");
            refg.check=true;
        }
        else if (btnText.equals("Disable Firewall"))
        {
            refg.rb.setText("Enable Firewall");
            refg.check=false;
        }
        else if (btnText.equals("Add to Firewall"))
        {
            String input=JOptionPane.showInputDialog("Please enter Firewall Keyword");
            refg.frw.fwwrite(input);
        }
        else if (btnText.equals("Show Firewall KeyWords"))
        {
                try {
                JFrame fwFrame = new JFrame("Firewall");
                fwFrame.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                
                File f = new File("Firewall.data");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String URL = br.readLine();                
                DefaultListModel firewallList = new DefaultListModel();

                while (URL != null) 
                {
                    firewallList.addElement(URL);
                    URL = br.readLine();
                }
                JList fwlist = new JList(firewallList);
                
                br.close();
                fr.close();
                
                c.gridx = 0;
                c.gridy = 0;
                c.ipadx = 300;
                c.ipady = 300;
                fwlist.setSize(500, 500);
                fwFrame.add(fwlist, c);
                fwFrame.setSize(400, 400);
                fwFrame.setLocationRelativeTo(null);
                //fwFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fwFrame.setVisible(true);
                
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }else if (btnText.equals("Clear Firewall"))
        {
            refg.frw.clearFile("Firewall.data");
        }
    }
}