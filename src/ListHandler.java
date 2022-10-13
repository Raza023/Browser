
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class ListHandler implements ListSelectionListener
{
    BrowserGUI refg;
    public ListHandler(BrowserGUI gg)
    {
        refg=gg;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse)
    {
        if (lse.getValueIsAdjusting())
        {
            JList list1 = (JList)lse.getSource();                   //object on which event initially occured.
            String selected=list1.getSelectedValue().toString();
            StringTokenizer st= new StringTokenizer(selected);
            String URL=st.nextToken();
            LocalDateTime ldt = LocalDateTime.now();
            refg.frw.WriteFile(URL+"-"+ldt.toString());
            try
            {
                refg.jep.setPage(URL);
            }
            catch (IOException e)
            {
                Logger.getLogger(ListHandler.class.getName()).log(Level.SEVERE, null, e);
            }
            refg.pnhnd.MoveBackward(URL);
        }
    }
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
