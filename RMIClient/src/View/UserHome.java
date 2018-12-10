/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.ClientControl;
import Model.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author The
 */
public class UserHome extends javax.swing.JFrame {
    private User me; //nguoi dang dung 
    private User enemy; //doi thu
    private Thread threadOnline;//Luong lay danh sach online (1s cap nhat 1 lan)
    private Thread threadGetChalenge;//Luong hien thong bao loi thach dau(1s 1 lan)
    private Thread threadGetResponChalenge;//Luong nhan thong diep dong y tu doi thu(1 s 1 lan)
    /**
     * Creates new form UserHomeForm
     */
    public UserHome(User user) {
        initComponents();
        setMe(user);
        jLabel4.setText("Chào mừng "+me.getName());
        SetListOnline();
        getChalenge();
        getResponChalenge();
    }

    public User getMe() {
        return me;
    }

    public void setMe(User user) {
        this.me = user;
    }

    public User getEnemy() {
        return enemy;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Giai toan online");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Giải toán online");

        jList1.setForeground(new java.awt.Color(0, 142, 0));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Đang online:");

        jButton1.setText("Xem chi tiết");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel3.setText("Bảng xếp hạng:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Luật chơi\n.......\n........\n........\n.......\n..........\n........\n.........\n............\n..........\n............\n.........\n..............");
        jScrollPane3.setViewportView(jTextArea1);

        jLabel4.setText("Chào mừng ...");

        jMenu1.setText("File");

        jMenuItem3.setText("Thông tin bản thân");
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Đăng xuất");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Đóng");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About us");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jButton1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(751, 751, 751)
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(397, 397, 397)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        ClientControl lcc = new ClientControl(); //Ham nay bat suu kien neu tat ung dung
        lcc.remoteImOffline(this.getMe()); //ofline
        this.dispose();  //tat giao dien
        threadOnline.stop(); //tat luong
        threadGetChalenge.stop();
        threadGetResponChalenge.stop();
    }//GEN-LAST:event_formWindowClosing

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here: //Ham nay bat su kien an vao nguoi muon thach dau
        ClientControl cc = new ClientControl();
        User bithachdau = new User();
        String str = jList1.getSelectedValue();  //tach chuoi lay ra username
        String[] words=str.split(" ");
        bithachdau.setUserName(words[0]);
        bithachdau.setName(words[2]);
        if (cc.remoteCheckStatus(bithachdau) == 1)
        {
            int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn thách đấu với " + str +"?");
            if (click==JOptionPane.YES_OPTION) 
            {
                cc.remoteChalenge(me, bithachdau);
            }
        }
        else
        {
            if (cc.remoteCheckStatus(bithachdau) == 2)
            {
                showMessage("Người này đang trong trận!");
            }
        }
    }//GEN-LAST:event_jList1MouseClicked
   
    public void SetListOnline() //Danh sach online
    {
        User us = this.getMe();
        threadOnline = new Thread(new Runnable() {
            @Override
            public void run() {
                ClientControl cc = new ClientControl();
                while (true)
                {
                    ArrayList a = null;
                    User user = null;
                    DefaultListModel<String> model = new DefaultListModel<>();
                    a = cc.remoteListOnline(us); //lay danh sach tu server
                    for (int i = 0; i < a.size(); i++) {
                        user = (User) a.get(i);
                        model.addElement(user.getUserName()+" - "+user.getName());
                    }
                    jList1.setModel(model); //hien danh sach
                    try {
                        Thread.sleep(1000); //1s 1 lan
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        threadOnline.start(); //Bat dau luong
    }
    
    public void getChalenge() //Bat dau nhan loi thach dau
    {
        ClientControl cc = new ClientControl();
        threadGetChalenge = new Thread(new Runnable() {
            @Override
            public void run() {
                User thachdau = null;
                while (true)
                {
                    thachdau = cc.remoteGetChalenge(me); //Gui len server chinh nguoi dang dung, lay thachdau la phan tu dau tien tim thay tuong ung trong cai DSThachDau
                    if (thachdau!=null)
                    {
                        int click = JOptionPane.showConfirmDialog(null, "Bạn nhận được lời thách đấu từ " + thachdau.getName());
                        if (click==JOptionPane.YES_OPTION) 
                        {
                            if (cc.remoteCheckStatus(thachdau) == 1)//kiem tra neu nguoi thach dau dang online
                            {
                                cc.remoteResponChalenge(thachdau, me);
                                enemy = thachdau;
                                cc.remoteImInGate(me);
                                cc.remoteMakeTest(thachdau, me);
                                CloseUserHome();
                                Wait w1 = new Wait(me, enemy, USHome());
                                w1.setVisible(true);
                            }
                            else
                            {
                                if (cc.remoteCheckStatus(thachdau) == 2) //neu nguoi thach dau dang trong tran
                                {
                                    showMessage("Người này đang trong trận!");
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        threadGetChalenge.start();
    }
    
    public void getResponChalenge() //Nhan loi tra loi thach dau
    {
        ClientControl cc = new ClientControl();
        threadGetResponChalenge = new Thread(new Runnable() {
            @Override
            public void run() {
                User bithachdau = null;
                while (true)
                {
                    bithachdau = cc.remoteGetResponChalenge(me); //Gui dinh danh cua minh len server de nhan loi tra loi thach dau
                    if (bithachdau!=null)
                    {
                        cc.remoteImInGate(me); //Update trang thai dang trong tran
                        enemy = bithachdau;
                        CloseUserHome();
                        Wait w2 = new Wait(me, enemy, USHome()); //Chuyen sang giao dien cho
                        w2.setVisible(true);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        threadGetResponChalenge.start();
    }
    
    public void showMessage(String msg)
        {
            JOptionPane.showMessageDialog(this, msg);
        }
    
    public void CloseUserHome()
    {
        this.setVisible(false);
    }
    
    public UserHome USHome()
    {
        return this;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}