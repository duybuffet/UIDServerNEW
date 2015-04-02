package run;

import control.ServerControl;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.ServerFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Duy Buffet
 */
public class Test {

    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerFrame view = new ServerFrame();
                    view.setVisible(true);
                    view.setResizable(false);
                    ServerControl control = new ServerControl(view);
                    // test github dang hung
                } catch (RemoteException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
