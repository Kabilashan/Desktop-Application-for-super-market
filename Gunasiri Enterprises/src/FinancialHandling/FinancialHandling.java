/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinancialHandling;

import DB.DBConnect;
import static DB.DBConnect.connect;
import Reports.FinancialHandling.reportGen;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Nuwan Chamikara
 */
public class FinancialHandling extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    String primaryKey;
    PreparedStatement ps = null;
    
    /**
     * Creates new form Home
     */
    public FinancialHandling() {
        try {
            initComponents();
            cashflow_tableLoad();
            pettycash_tableLoad();
            loans_tableLoad();
            tax_tableLoad();
            
            Update_table();
            con = DBConnect.connect();
            //date_time();
            //Update_table();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
        }     
//Set Full Screen
            Toolkit tk = Toolkit.getDefaultToolkit();
            
            int xsize = (int) tk.getScreenSize().getWidth();
            int ysize = (int) tk.getScreenSize().getHeight();
            
            this.setSize(xsize, ysize);
            
//Set Full FullScreen End

            datecombx.getDateEditor().setEnabled(false);
            sdatecombx.getDateEditor().setEnabled(false);
            edatecombx.getDateEditor().setEnabled(false);
            datecmbx.getDateEditor().setEnabled(false);
            sdatecmbx.getDateEditor().setEnabled(false);
            edatecmbx.getDateEditor().setEnabled(false);

    }

//Cash Flow Table Load
    public void cashflow_tableLoad(){
       
        String query = "SELECT * FROM cashflow";
        try{
           Connection con =  connect();
           Statement st = con.createStatement();
           rs = st.executeQuery(query);
           cashflow_table.setModel(DbUtils.resultSetToTableModel(rs));
           
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
//End   Cash Flow Table Load
    
//Loans Table Load
    public void loans_tableLoad(){
       
        String query = "SELECT * FROM loans";
        try{
           Connection con =  connect();
           Statement st = con.createStatement();
           rs = st.executeQuery(query);
           loans_table.setModel(DbUtils.resultSetToTableModel(rs));
           
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
//Loans Flow Table Load

//Pettycash Table Load
    public void pettycash_tableLoad(){
       
        String query = "SELECT * FROM pettycash";
        try{
           Connection con =  connect();
           Statement st = con.createStatement();
           rs = st.executeQuery(query);
           pettycash_table.setModel(DbUtils.resultSetToTableModel(rs));
           
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
//End Pettycash Flow Table Load    
    
//tax Table Load
    public void tax_tableLoad(){
       
        String query = "SELECT * FROM taxes";
        try{
           Connection con =  connect();
           Statement st = con.createStatement();
           rs = st.executeQuery(query);
           tax_table.setModel(DbUtils.resultSetToTableModel(rs));
           
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
//End tax Table Load    
    
//Clear All Cash Flow
    public void clearAll(){
            
            
            datecombx.setDate(null);
            descripbx.setText("");
            amountbx1.setText("");
            typecombx.setSelectedIndex(0);
            amountbx.setText("");
            amountbx2.setText("");
            amountbx3.setText("");
            transbx.setText("");
            datecmbx.setCalendar(null);
            typecmbx.setSelectedIndex(0);
            descriptionbx.setText("");
            sdatecombx.setCalendar(null);
            edatecombx.setCalendar(null);
            statuscombx.setSelectedIndex(0);
            typecmbx1.setSelectedIndex(0);
            sdatecmbx.setCalendar(null);
            edatecmbx.setCalendar(null);
            statuscombx1.setSelectedIndex(0);
            descriptionbx1.setText("");
    }
//Clear All Cash Flow
    
//Set Colour for buttons
    void setColor(JPanel panel){
        
        panel.setBackground(new Color(85,65,118));
    }
    
    void resetColor(JPanel panel){
        
        panel.setBackground(new Color(64,43,100));
    }
//end set colour for buttons
    
    private void Update_table(){
        try{
        String sql ="select * from items_information";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        cashflow_table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        
        
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void date_time(){
        
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Date d = new Date();
                        String date = d.toString();
                        String arr[] = date.split(" ");
                        String newdate = arr[5] + "-" + arr[1] + "-" + arr[2];
                        Date.setText(newdate);
                        Time.setText(arr[3]);
                    }
                }
            }).start(); 
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        popupMenu1 = new java.awt.PopupMenu();
        sidePanel = new javax.swing.JPanel();
        pettycashbtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logobtn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cashflowbtn = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        loansbtn = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        taxbtn = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Messagebtn = new javax.swing.JPanel();
        messagebtn = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        reportsbtn = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        upPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        reports = new javax.swing.JPanel();
        taxreport = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jLabel46 = new javax.swing.JLabel();
        tax = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        typecmbx1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        amountbx3 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        statuscombx1 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        descriptionbx1 = new javax.swing.JTextField();
        addbtn3 = new javax.swing.JButton();
        resetbtn2 = new javax.swing.JButton();
        updatebtn3 = new javax.swing.JButton();
        cancelbtn3 = new javax.swing.JButton();
        sdatecmbx = new com.toedter.calendar.JDateChooser();
        edatecmbx = new com.toedter.calendar.JDateChooser();
        jScrollPane6 = new javax.swing.JScrollPane();
        tax_table = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        tfilt = new javax.swing.JTextField();
        loans = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        amountbx1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        statuscombx = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        descriptionbx = new javax.swing.JTextField();
        addbtn1 = new javax.swing.JButton();
        resetbtn1 = new javax.swing.JButton();
        updatebtn1 = new javax.swing.JButton();
        cancelbtn1 = new javax.swing.JButton();
        edatecombx = new com.toedter.calendar.JDateChooser();
        sdatecombx = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        loans_table = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lfilt = new javax.swing.JTextField();
        pettycash = new javax.swing.JPanel();
        typecmbx = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        transbx = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        addbtn2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        updatebtn2 = new javax.swing.JButton();
        cancelbtn2 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        amountbx2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        pettycash_table = new javax.swing.JTable();
        datecmbx = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        pcfilt = new javax.swing.JTextField();
        cashflow = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        descripbx = new javax.swing.JTextField();
        typecombx = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        addbtn = new javax.swing.JButton();
        resetbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        cashflow_table = new javax.swing.JTable();
        datecombx = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        amountbx = new javax.swing.JTextField();
        cfamountlb = new javax.swing.JLabel();
        cfdescriptlb = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cfdatelb = new javax.swing.JLabel();
        cftypelb = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        cffilt = new javax.swing.JTextField();
        messages = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        From_m = new javax.swing.JTextField();
        To_m = new javax.swing.JTextField();
        topic = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        A_path = new javax.swing.JTextField();
        Aname = new javax.swing.JTextField();
        Send = new javax.swing.JButton();
        upperPanel = new javax.swing.JPanel();
        logoutbtn = new javax.swing.JButton();
        Time = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        Date1 = new javax.swing.JLabel();
        Date2 = new javax.swing.JLabel();
        Date3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jEditorPane1);

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidePanel.setBackground(new java.awt.Color(51, 0, 102));
        sidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pettycashbtn.setBackground(new java.awt.Color(85, 55, 118));
        pettycashbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pettycashbtnMouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Petty Cash");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cash.png"))); // NOI18N

        javax.swing.GroupLayout pettycashbtnLayout = new javax.swing.GroupLayout(pettycashbtn);
        pettycashbtn.setLayout(pettycashbtnLayout);
        pettycashbtnLayout.setHorizontalGroup(
            pettycashbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pettycashbtnLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pettycashbtnLayout.setVerticalGroup(
            pettycashbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pettycashbtnLayout.createSequentialGroup()
                .addGroup(pettycashbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        sidePanel.add(pettycashbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 290, -1));

        logobtn.setBackground(new java.awt.Color(85, 55, 118));

        jLabel2.setBackground(new java.awt.Color(85, 55, 118));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/GE.png"))); // NOI18N

        javax.swing.GroupLayout logobtnLayout = new javax.swing.GroupLayout(logobtn);
        logobtn.setLayout(logobtnLayout);
        logobtnLayout.setHorizontalGroup(
            logobtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logobtnLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        logobtnLayout.setVerticalGroup(
            logobtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logobtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        sidePanel.add(logobtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 19, -1, -1));

        cashflowbtn.setBackground(new java.awt.Color(85, 55, 118));
        cashflowbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashflowbtnMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cash Flow");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Flow.png"))); // NOI18N

        javax.swing.GroupLayout cashflowbtnLayout = new javax.swing.GroupLayout(cashflowbtn);
        cashflowbtn.setLayout(cashflowbtnLayout);
        cashflowbtnLayout.setHorizontalGroup(
            cashflowbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashflowbtnLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cashflowbtnLayout.setVerticalGroup(
            cashflowbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cashflowbtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(29, 29, 29))
            .addGroup(cashflowbtnLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        sidePanel.add(cashflowbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 290, 57));

        loansbtn.setBackground(new java.awt.Color(85, 55, 118));
        loansbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loansbtnMouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Loans");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Money Bag.png"))); // NOI18N

        javax.swing.GroupLayout loansbtnLayout = new javax.swing.GroupLayout(loansbtn);
        loansbtn.setLayout(loansbtnLayout);
        loansbtnLayout.setHorizontalGroup(
            loansbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loansbtnLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        loansbtnLayout.setVerticalGroup(
            loansbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loansbtnLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(loansbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sidePanel.add(loansbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 315, -1, -1));

        taxbtn.setBackground(new java.awt.Color(85, 55, 118));
        taxbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taxbtnMouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tax");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Tax.png"))); // NOI18N

        javax.swing.GroupLayout taxbtnLayout = new javax.swing.GroupLayout(taxbtn);
        taxbtn.setLayout(taxbtnLayout);
        taxbtnLayout.setHorizontalGroup(
            taxbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taxbtnLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        taxbtnLayout.setVerticalGroup(
            taxbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taxbtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(taxbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        sidePanel.add(taxbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 290, 57));

        Messagebtn.setBackground(new java.awt.Color(85, 55, 118));
        Messagebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MessagebtnMouseClicked(evt);
            }
        });

        messagebtn.setBackground(new java.awt.Color(255, 255, 255));
        messagebtn.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        messagebtn.setForeground(new java.awt.Color(255, 255, 255));
        messagebtn.setText("Message");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/message.png"))); // NOI18N

        javax.swing.GroupLayout MessagebtnLayout = new javax.swing.GroupLayout(Messagebtn);
        Messagebtn.setLayout(MessagebtnLayout);
        MessagebtnLayout.setHorizontalGroup(
            MessagebtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MessagebtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(messagebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        MessagebtnLayout.setVerticalGroup(
            MessagebtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessagebtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(messagebtn)
                .addGap(29, 29, 29))
            .addGroup(MessagebtnLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        sidePanel.add(Messagebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 465, 290, 57));

        reportsbtn.setBackground(new java.awt.Color(85, 55, 118));
        reportsbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportsbtnMouseClicked(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Reports");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/repo.png"))); // NOI18N

        javax.swing.GroupLayout reportsbtnLayout = new javax.swing.GroupLayout(reportsbtn);
        reportsbtn.setLayout(reportsbtnLayout);
        reportsbtnLayout.setHorizontalGroup(
            reportsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportsbtnLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        reportsbtnLayout.setVerticalGroup(
            reportsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportsbtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(29, 29, 29))
            .addGroup(reportsbtnLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        sidePanel.add(reportsbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 290, 57));

        upPanel.setBackground(new java.awt.Color(110, 89, 222));

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Administration/Financial Handling");

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setForeground(new java.awt.Color(153, 153, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/notificationW.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Financial Handling ");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Sectiontopic.png"))); // NOI18N

        javax.swing.GroupLayout upPanelLayout = new javax.swing.GroupLayout(upPanel);
        upPanel.setLayout(upPanelLayout);
        upPanelLayout.setHorizontalGroup(
            upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upPanelLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        upPanelLayout.setVerticalGroup(
            upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addGroup(upPanelLayout.createSequentialGroup()
                        .addGroup(upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(upPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel15)))
                .addGap(131, 131, 131))
        );

        mainpanel.setBackground(new java.awt.Color(204, 204, 204));
        mainpanel.setLayout(new java.awt.CardLayout());

        reports.setBackground(new java.awt.Color(204, 204, 204));

        taxreport.setBackground(new java.awt.Color(204, 102, 255));
        taxreport.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        taxreport.setText("Tax");
        taxreport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxreportActionPerformed(evt);
            }
        });

        jToggleButton11.setBackground(new java.awt.Color(204, 102, 255));
        jToggleButton11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jToggleButton11.setText("Petty Cash");
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });

        jToggleButton12.setBackground(new java.awt.Color(204, 102, 255));
        jToggleButton12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jToggleButton12.setText("Loans");
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton12ActionPerformed(evt);
            }
        });

        jToggleButton13.setBackground(new java.awt.Color(204, 102, 255));
        jToggleButton13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jToggleButton13.setText("CashFlow");
        jToggleButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton13ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setText("Reports  :");

        javax.swing.GroupLayout reportsLayout = new javax.swing.GroupLayout(reports);
        reports.setLayout(reportsLayout);
        reportsLayout.setHorizontalGroup(
            reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsLayout.createSequentialGroup()
                .addGroup(reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportsLayout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addGroup(reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taxreport, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(reportsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(646, Short.MAX_VALUE))
        );
        reportsLayout.setVerticalGroup(
            reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(taxreport, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(616, Short.MAX_VALUE))
        );

        mainpanel.add(reports, "card8");

        tax.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setText("TYPE                  :");

        typecmbx1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        typecmbx1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " - - - -", " Income Tax", " Value Added Tax (VAT)", " Nation Building Tax (NBT)", " Economic Service Charge (ESC)", " Income Tax", " Liquor License Fee" }));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel32.setText("PERIOD             :");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel33.setText("Start Date  :");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setText("End Date  :");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel35.setText("AMOUNT           :");

        amountbx3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        amountbx3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountbx3ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel36.setText("STATUS              :");

        statuscombx1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        statuscombx1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " - - - -", " Paid ", " Not Paid " }));
        statuscombx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statuscombx1ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel37.setText("DESCRIPTION  :");

        descriptionbx1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        descriptionbx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionbx1ActionPerformed(evt);
            }
        });

        addbtn3.setBackground(new java.awt.Color(102, 102, 102));
        addbtn3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        addbtn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        addbtn3.setText("ADD");
        addbtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn3ActionPerformed(evt);
            }
        });

        resetbtn2.setBackground(new java.awt.Color(102, 102, 102));
        resetbtn2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        resetbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        resetbtn2.setText("RESET");
        resetbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbtn2ActionPerformed(evt);
            }
        });

        updatebtn3.setBackground(new java.awt.Color(102, 102, 102));
        updatebtn3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        updatebtn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Restart.png"))); // NOI18N
        updatebtn3.setText("UPDATE");
        updatebtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn3ActionPerformed(evt);
            }
        });

        cancelbtn3.setBackground(new java.awt.Color(102, 102, 102));
        cancelbtn3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        cancelbtn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        cancelbtn3.setText("CANCEL");
        cancelbtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtn3ActionPerformed(evt);
            }
        });

        tax_table.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tax_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Type", "Start Date", "End Date", "Amount", "Status", "Description"
            }
        ));
        tax_table.setRowHeight(30);
        tax_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tax_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tax_table);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel40.setText("Taxes");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel44.setText("Search :");

        tfilt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfiltActionPerformed(evt);
            }
        });
        tfilt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfiltKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout taxLayout = new javax.swing.GroupLayout(tax);
        tax.setLayout(taxLayout);
        taxLayout.setHorizontalGroup(
            taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taxLayout.createSequentialGroup()
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(taxLayout.createSequentialGroup()
                                .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descriptionbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statuscombx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(amountbx3, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(taxLayout.createSequentialGroup()
                                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sdatecmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(94, 94, 94)
                                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(edatecmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(taxLayout.createSequentialGroup()
                                        .addComponent(typecmbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel44))))
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(taxLayout.createSequentialGroup()
                                .addComponent(addbtn3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetbtn2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updatebtn3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelbtn3)))
                        .addGap(18, 18, 18)
                        .addComponent(tfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1540, Short.MAX_VALUE))
                .addContainerGap())
        );
        taxLayout.setVerticalGroup(
            taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taxLayout.createSequentialGroup()
                .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taxLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typecmbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sdatecmbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edatecmbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(amountbx3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statuscombx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(descriptionbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addbtn3)
                            .addComponent(resetbtn2)
                            .addComponent(updatebtn3)
                            .addComponent(cancelbtn3)))
                    .addGroup(taxLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(taxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
        );

        mainpanel.add(tax, "card7");

        loans.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setText("PERIOD        :");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel23.setText("Start Date  :");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel24.setText("End Date  :");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel25.setText("AMOUNT     :");

        amountbx1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        amountbx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountbx1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel26.setText("STATUS        :");

        statuscombx.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        statuscombx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " - - - -", " Paid ", " Not Paid " }));
        statuscombx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statuscombxActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel27.setText("DESCRIPTION  :");

        descriptionbx.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        descriptionbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionbxActionPerformed(evt);
            }
        });

        addbtn1.setBackground(new java.awt.Color(102, 102, 102));
        addbtn1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        addbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        addbtn1.setText("ADD");
        addbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn1ActionPerformed(evt);
            }
        });

        resetbtn1.setBackground(new java.awt.Color(102, 102, 102));
        resetbtn1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        resetbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        resetbtn1.setText("RESET");
        resetbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbtn1ActionPerformed(evt);
            }
        });

        updatebtn1.setBackground(new java.awt.Color(102, 102, 102));
        updatebtn1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        updatebtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Restart.png"))); // NOI18N
        updatebtn1.setText("UPDATE");
        updatebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn1ActionPerformed(evt);
            }
        });

        cancelbtn1.setBackground(new java.awt.Color(102, 102, 102));
        cancelbtn1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        cancelbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        cancelbtn1.setText("CANCEL");
        cancelbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtn1ActionPerformed(evt);
            }
        });

        loans_table.setAutoCreateRowSorter(true);
        loans_table.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        loans_table.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loans_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Description", "Start Date", "End date", "Amount", "Status"
            }
        ));
        loans_table.setRowHeight(30);
        loans_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loans_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(loans_table);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setText("Loans");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel42.setText("Search :");

        lfilt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lfiltActionPerformed(evt);
            }
        });
        lfilt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lfiltKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout loansLayout = new javax.swing.GroupLayout(loans);
        loans.setLayout(loansLayout);
        loansLayout.setHorizontalGroup(
            loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loansLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loansLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loansLayout.createSequentialGroup()
                                .addComponent(addbtn1)
                                .addGap(10, 10, 10)
                                .addComponent(resetbtn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updatebtn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelbtn1))
                            .addGroup(loansLayout.createSequentialGroup()
                                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26))
                                .addGap(63, 63, 63)
                                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statuscombx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(amountbx1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(loansLayout.createSequentialGroup()
                                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(descriptionbx, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(loansLayout.createSequentialGroup()
                                                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(loansLayout.createSequentialGroup()
                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loansLayout.createSequentialGroup()
                                                        .addComponent(sdatecombx, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(261, 261, 261)))
                                                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(edatecombx, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane4)))
                    .addComponent(jLabel27)
                    .addComponent(jLabel39))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loansLayout.setVerticalGroup(
            loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loansLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel39)
                .addGap(28, 28, 28)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(descriptionbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(sdatecombx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edatecombx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amountbx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(statuscombx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addbtn1)
                    .addComponent(resetbtn1)
                    .addComponent(updatebtn1)
                    .addComponent(cancelbtn1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );

        mainpanel.add(loans, "card5");

        pettycash.setBackground(new java.awt.Color(204, 204, 204));

        typecmbx.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        typecmbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " - - - -", " Employee Salary", " Light Bill", " Water Bill", " Telephone Bill", " Service", " Other" }));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel28.setText("Transaction  :");

        transbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transbxActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel29.setText("Date  :");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel30.setText("TYPE  :");

        addbtn2.setBackground(new java.awt.Color(102, 102, 102));
        addbtn2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        addbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        addbtn2.setText("ADD");
        addbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 102, 102));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton5.setText("RESET");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        updatebtn2.setBackground(new java.awt.Color(102, 102, 102));
        updatebtn2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        updatebtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Restart.png"))); // NOI18N
        updatebtn2.setText("UPDATE");
        updatebtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn2ActionPerformed(evt);
            }
        });

        cancelbtn2.setBackground(new java.awt.Color(102, 102, 102));
        cancelbtn2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        cancelbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        cancelbtn2.setText("CANCEL");
        cancelbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtn2ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel31.setText("AMOUNT  :");

        amountbx2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        amountbx2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountbx2ActionPerformed(evt);
            }
        });

        pettycash_table.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pettycash_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Transaction", "Type", "Amount"
            }
        ));
        pettycash_table.setRowHeight(30);
        pettycash_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pettycash_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(pettycash_table);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel38.setText("Petty Cash");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel45.setText("Search :");

        pcfilt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcfiltActionPerformed(evt);
            }
        });
        pcfilt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pcfiltKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pettycashLayout = new javax.swing.GroupLayout(pettycash);
        pettycash.setLayout(pettycashLayout);
        pettycashLayout.setHorizontalGroup(
            pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pettycashLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pettycashLayout.createSequentialGroup()
                        .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(pettycashLayout.createSequentialGroup()
                                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pettycashLayout.createSequentialGroup()
                                        .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel30))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(amountbx2, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(typecmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(transbx, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pettycashLayout.createSequentialGroup()
                                        .addComponent(addbtn2)
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton5)
                                        .addGap(10, 10, 10)
                                        .addComponent(updatebtn2)
                                        .addGap(10, 10, 10)
                                        .addComponent(cancelbtn2)))
                                .addGap(0, 666, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(pettycashLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pettycashLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datecmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pettycashLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pcfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(316, 316, 316))))
        );
        pettycashLayout.setVerticalGroup(
            pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pettycashLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pcfilt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datecmbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(transbx, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(typecmbx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amountbx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pettycashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addbtn2)
                    .addComponent(jButton5)
                    .addComponent(updatebtn2)
                    .addComponent(cancelbtn2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(305, 305, 305))
        );

        mainpanel.add(pettycash, "card6");

        cashflow.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel19.setText("Date  :");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel20.setText("DESCRIPTION  :");

        descripbx.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        descripbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripbxActionPerformed(evt);
            }
        });
        descripbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descripbxKeyReleased(evt);
            }
        });

        typecombx.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        typecombx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " - - - -", " Cash", " Debit", " " }));
        typecombx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typecombxActionPerformed(evt);
            }
        });
        typecombx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typecombxKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typecombxKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel21.setText("TYPE  :");

        addbtn.setBackground(new java.awt.Color(102, 102, 102));
        addbtn.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        addbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save.png"))); // NOI18N
        addbtn.setText("ADD");
        addbtn.setMaximumSize(new java.awt.Dimension(235, 57));
        addbtn.setMinimumSize(new java.awt.Dimension(235, 57));
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        resetbtn.setBackground(new java.awt.Color(102, 102, 102));
        resetbtn.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        resetbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        resetbtn.setText("RESET");
        resetbtn.setMaximumSize(new java.awt.Dimension(235, 57));
        resetbtn.setMinimumSize(new java.awt.Dimension(235, 57));
        resetbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbtnActionPerformed(evt);
            }
        });

        updatebtn.setBackground(new java.awt.Color(102, 102, 102));
        updatebtn.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        updatebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Restart.png"))); // NOI18N
        updatebtn.setText("UPDATE");
        updatebtn.setMaximumSize(new java.awt.Dimension(235, 57));
        updatebtn.setMinimumSize(new java.awt.Dimension(235, 57));
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        cancelbtn.setBackground(new java.awt.Color(102, 102, 102));
        cancelbtn.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        cancelbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        cancelbtn.setText("CANCEL");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        cashflow_table.setAutoCreateRowSorter(true);
        cashflow_table.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cashflow_table.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cashflow_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Description", "Amount", "Type"
            }
        ));
        cashflow_table.setRowHeight(30);
        cashflow_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashflow_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(cashflow_table);

        datecombx.setDateFormatString("yyyy-MM-dd");
        datecombx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                datecombxKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel22.setText("AMOUNT  :");

        amountbx.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        amountbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountbxActionPerformed(evt);
            }
        });
        amountbx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountbxKeyReleased(evt);
            }
        });

        cfamountlb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cfamountlb.setForeground(new java.awt.Color(255, 0, 0));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setText("Cash Flow");

        cfdatelb.setForeground(new java.awt.Color(255, 0, 0));

        cftypelb.setForeground(new java.awt.Color(255, 0, 0));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel43.setText("Search :");

        cffilt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cffiltActionPerformed(evt);
            }
        });
        cffilt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cffiltKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout cashflowLayout = new javax.swing.GroupLayout(cashflow);
        cashflow.setLayout(cashflowLayout);
        cashflowLayout.setHorizontalGroup(
            cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashflowLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cashflowLayout.createSequentialGroup()
                        .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cashflowLayout.createSequentialGroup()
                                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(31, 31, 31)
                                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(cashflowLayout.createSequentialGroup()
                                        .addComponent(typecombx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cftypelb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(cashflowLayout.createSequentialGroup()
                                        .addComponent(amountbx, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cfamountlb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(descripbx, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(cfdescriptlb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cashflowLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datecombx, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cfdatelb, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cashflowLayout.createSequentialGroup()
                                .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelbtn)))
                        .addGap(549, 549, Short.MAX_VALUE))
                    .addGroup(cashflowLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(cashflowLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cffilt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(366, 366, 366))))
        );
        cashflowLayout.setVerticalGroup(
            cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashflowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cffilt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(datecombx, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cfdatelb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(descripbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cfdescriptlb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(amountbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cfamountlb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cftypelb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cashflowLayout.createSequentialGroup()
                        .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(typecombx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(cashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelbtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(290, Short.MAX_VALUE))
        );

        mainpanel.add(cashflow, "card4");

        messages.setBackground(new java.awt.Color(204, 204, 204));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel41.setText("Send Messages ");

        jLabel49.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel49.setText("From :");

        jLabel50.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel50.setText("To :");

        jLabel54.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel54.setText("Subject :");

        From_m.setAutoscrolls(false);
        From_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                From_mActionPerformed(evt);
            }
        });

        To_m.setAutoscrolls(false);
        To_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                To_mActionPerformed(evt);
            }
        });

        topic.setAutoscrolls(false);
        topic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topicActionPerformed(evt);
            }
        });

        text.setColumns(20);
        text.setRows(5);
        jScrollPane8.setViewportView(text);

        jLabel55.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel55.setText("Message :");

        jButton3.setText("Attach");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Send.setBackground(new java.awt.Color(102, 102, 255));
        Send.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        Send.setForeground(new java.awt.Color(255, 255, 255));
        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout messagesLayout = new javax.swing.GroupLayout(messages);
        messages.setLayout(messagesLayout);
        messagesLayout.setHorizontalGroup(
            messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(messagesLayout.createSequentialGroup()
                        .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, messagesLayout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(messagesLayout.createSequentialGroup()
                            .addComponent(topic, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(messagesLayout.createSequentialGroup()
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                            .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(messagesLayout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Aname, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(A_path, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(577, Short.MAX_VALUE)))
                    .addGroup(messagesLayout.createSequentialGroup()
                        .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(From_m, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(To_m, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(932, Short.MAX_VALUE))))
        );
        messagesLayout.setVerticalGroup(
            messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagesLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(From_m, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(To_m, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(topic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addGroup(messagesLayout.createSequentialGroup()
                        .addComponent(A_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(Aname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(85, 85, 85)
                .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainpanel.add(messages, "card7");

        upperPanel.setBackground(new java.awt.Color(204, 204, 204));
        upperPanel.setPreferredSize(new java.awt.Dimension(296, 45));

        logoutbtn.setBackground(new java.awt.Color(0, 0, 102));
        logoutbtn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logoutbtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutbtn.setText("Logout");
        logoutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtnActionPerformed(evt);
            }
        });

        Time.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        Time.setForeground(new java.awt.Color(255, 255, 255));

        Date.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 255, 255));

        Date1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        Date1.setForeground(new java.awt.Color(255, 255, 255));

        Date2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        Date2.setForeground(new java.awt.Color(255, 255, 255));

        Date3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        Date3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Date3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Date2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Date1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutbtn)
                .addContainerGap())
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(upperPanelLayout.createSequentialGroup()
                                .addComponent(logoutbtn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Date1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Date2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(Date3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(upPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1564, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(upPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void logoutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutbtnActionPerformed

    private void descripbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descripbxActionPerformed

    private void typecombxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typecombxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typecombxActionPerformed

    
/*---------- Cash Flow Add Button ----------*/
    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(datecombx.getDate());
        String descrip = descripbx.getText();
        String amount = amountbx.getText();
        String type = typecombx.getSelectedItem().toString();
        
        if(!date.equals("")){
            
            if(!descrip.equals("")){
                
                if(!amount.equals("")){
                    
                    if(!type.equals("")){
                        
                        addbtn.setEnabled(false);
                        String query = "INSERT INTO cashflow(date, description, amount, type) VALUES('"+date+"', '"+descrip+"', '"+amount+"','"+type+"')";
            
                        try{
                            Connection con =  connect();
                            Statement st = con.createStatement();
                            st.executeUpdate(query);
                            JOptionPane.showMessageDialog(rootPane, "Record has been saved successfully");
                            clearAll();
                            cashflow_tableLoad();
              
                        }catch(SQLException e){
                            System.out.println(e);
                        } catch (ClassNotFoundException ex) {
                             Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        
                    }
                    else{
                        addbtn.setEnabled(false);
                        JOptionPane.showMessageDialog(rootPane, "Please Select a Type");
                    }
                }
                else{
                    addbtn.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Please Fill the Amount");
                }
            }
            else{
               addbtn.setEnabled(false);
               JOptionPane.showMessageDialog(rootPane, "Please Fill the Description"); 
            }
        }
        else{
            addbtn.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Please Fill the Date");
        }
    }//GEN-LAST:event_addbtnActionPerformed
/*---------- Cash Flow Add Button End----------*/
    
    
/*---------- Cash Flow Add Button -------------*/   
    private void resetbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbtnActionPerformed
        clearAll();
    }//GEN-LAST:event_resetbtnActionPerformed
/*---------- Cash Flow Update Button End ------*/
    
    
    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String dte = sdf.format(datecombx.getDate());
        String descrip = descripbx.getText();
        String amount = amountbx.getText();
        String type = typecombx.getSelectedItem().toString();

        if(datecombx.equals("") || descripbx.equals("") || typecombx.equals("")){

            JOptionPane.showMessageDialog(rootPane, "Please Fill the Feilds");

        }
        else{

            int x = JOptionPane.showConfirmDialog(null, "Do you really want to update ?");

            if(x==0){

                String query = "UPDATE cashflow set date = '"+dte+"',description = '"+descrip+"', amount = '"+amount+"',type = '"+type+"'WHERE id='"+ primaryKey +"'";

                try{
                    Connection con =  connect();
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(rootPane, "Record has been Update Successfully");
                    clearAll();
                    cashflow_tableLoad();

                }catch(SQLException e){
                    System.out.println(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{

                JOptionPane.showMessageDialog(rootPane, "Update discarded !");

            }
        }
    }//GEN-LAST:event_updatebtnActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");

        if(x == 0){

            try{
                String query = "DELETE FROM cashflow WHERE id = '"+ primaryKey +"'";
                Connection con =  connect();
                Statement st = con.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, "Record has been Delete Successfully");
                clearAll();
                cashflow_tableLoad();

            }catch(HeadlessException | ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Delete discarded !");
        }

    }//GEN-LAST:event_cancelbtnActionPerformed

    private void cashflow_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashflow_tableMouseClicked

        try{
            int row = cashflow_table.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) cashflow_table.getModel();
            SimpleDateFormat ddate = new SimpleDateFormat("yyyy-MM-dd");

            primaryKey = model.getValueAt(row,0).toString();

            datecombx.setDate(ddate.parse(model.getValueAt(row,1).toString()));
            descripbx.setText(model.getValueAt(row,2).toString());
            amountbx.setText(model.getValueAt(row, 3).toString());
            typecombx.setSelectedItem(model.getValueAt(row,4).toString());

        }catch(ParseException e){
            System.out.println(e);
        }

    }//GEN-LAST:event_cashflow_tableMouseClicked

    private void amountbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountbxActionPerformed

    private void amountbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountbxKeyReleased
        
        try{
            int i = Integer.parseInt(amountbx.getText());
            cfamountlb.setText(null);
            addbtn.setEnabled(true);
        }
        catch(NumberFormatException e){
            cfamountlb.setText("Input Numbers Only ! ");
            addbtn.setEnabled(false);
        }

    }//GEN-LAST:event_amountbxKeyReleased

    private void pettycashbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pettycashbtnMouseClicked
        
        setColor(pettycashbtn);
        resetColor(cashflowbtn);
        resetColor(loansbtn);
        resetColor(taxbtn);
        resetColor(Messagebtn);
        resetColor(reportsbtn);
        
        //removing panels
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        
        //adding panel
        mainpanel.add(pettycash);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_pettycashbtnMouseClicked

    private void amountbx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountbx1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountbx1ActionPerformed

    private void statuscombxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statuscombxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statuscombxActionPerformed

    private void descriptionbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionbxActionPerformed

    private void addbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn1ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String descrip = descriptionbx.getText();
        String date1 = sdf.format(sdatecombx.getDate());
        String date2 = sdf.format(edatecombx.getDate());
        String amount = amountbx1.getText();
        String status = statuscombx.getSelectedItem().toString();
        
        
        
        if(!descrip.equals("")){
            
            if(!date1.equals("")){
                
                if(!date2.equals("")){
                    
                    if(!amount.equals("")){
                        
                        if(!status.equals("")){
                            
                            String query = "INSERT INTO loans(description, startdate, enddate, amount, status) VALUES('"+descrip+"', '"+date1+"',  '"+date2+"','"+amount+"', '"+status+"')";

                            try{
                                Connection con =  connect();
                                Statement st = con.createStatement();
                                st.executeUpdate(query);
                                JOptionPane.showMessageDialog(rootPane, "Record has been saved successfully");
                                clearAll();
                                loans_tableLoad();

                            }catch(SQLException e){
                                System.out.println(e);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else{
                            addbtn.setEnabled(false);
                            JOptionPane.showMessageDialog(rootPane, "Please Select a Status");
                        }
                    }
                    else{
                        addbtn.setEnabled(false);
                        JOptionPane.showMessageDialog(rootPane, "Please Select a Amount");
                    }
                }
                else{
                    addbtn.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Please Fill the End Date");
                }
            }
            else{
               addbtn.setEnabled(false);
               JOptionPane.showMessageDialog(rootPane, "Please Fill the Start Date"); 
            }
        }
        else{
            addbtn.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Please Fill the Description");
        }
    }//GEN-LAST:event_addbtn1ActionPerformed

    private void resetbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbtn1ActionPerformed
        clearAll();
    }//GEN-LAST:event_resetbtn1ActionPerformed

    private void updatebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn1ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String descrip = descriptionbx.getText();
        String date1 = sdf.format(sdatecombx.getDate());
        String date2 = sdf.format(edatecombx.getDate());
        String amount = amountbx1.getText();
        String status = statuscombx.getSelectedItem().toString();

        if(descriptionbx.equals("") || sdatecombx.equals("") || edatecombx.equals("")||amountbx.equals("")||statuscombx.equals("")){

            JOptionPane.showMessageDialog(rootPane, "Please Fill the Feilds");

        }
        else{

            int x = JOptionPane.showConfirmDialog(null, "Do you really want to update ?");

            if(x==0){

                String query = "UPDATE loans set description = '"+descrip+"', startdate= '"+date1+"', enddate= '"+date2+"', amount='"+amount+"', status='"+status+"'WHERE id='"+ primaryKey +"'";

                try{
                    Connection con =  connect();
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(rootPane, "Record has been Update Successfully");
                    clearAll();
                    loans_tableLoad();

                }catch(SQLException e){
                    System.out.println(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{

                JOptionPane.showMessageDialog(rootPane, "Update discarded !");

            }
        }

    }//GEN-LAST:event_updatebtn1ActionPerformed

    private void cancelbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtn1ActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");

        if(x == 0){

            try{
                String query = "DELETE FROM loans WHERE id = '"+ primaryKey +"'";
                Connection con =  connect();
                Statement st = con.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, "Record has been Delete Successfully");
                clearAll();
                loans_tableLoad();

            }catch(HeadlessException | ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Delete discarded !");
        }

    }//GEN-LAST:event_cancelbtn1ActionPerformed

    private void loans_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loans_tableMouseClicked

        try{
            int row = loans_table.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) loans_table.getModel();
            SimpleDateFormat ddate = new SimpleDateFormat("yyyy-MM-dd");

            primaryKey = model.getValueAt(row,0).toString();

            descriptionbx.setText(model.getValueAt(row, 1).toString());
            sdatecombx.setDate(ddate.parse(model.getValueAt(row,2).toString()));
            edatecombx.setDate(ddate.parse(model.getValueAt(row,3).toString()));
            amountbx1.setText(model.getValueAt(row, 4).toString());
            statuscombx.setSelectedItem(model.getValueAt(row,5).toString());

        }catch(ParseException e){
            System.out.println(e);
        }

    }//GEN-LAST:event_loans_tableMouseClicked

    private void addbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn2ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(datecmbx.getDate());
        String trans = transbx.getText();
        String type = typecmbx.getSelectedItem().toString();
        String amount = amountbx2.getText();

        
        if(!date.equals("")){
            
            if(!trans.equals("")){
                
                if(!type.equals("")){
                    
                    if(!amount.equals("")){
                        
                        addbtn.setEnabled(false);
                        
                        String query = "INSERT INTO pettycash(date, transaction, type, amount) VALUES('"+date+"', '"+trans+"', '"+type+"','"+amount+"')";

                        try{
                            Connection con =  connect();
                            Statement st = con.createStatement();
                            st.executeUpdate(query);
                            JOptionPane.showMessageDialog(rootPane, "Record has been saved successfully");
                            clearAll();
                            pettycash_tableLoad();

                        }catch(SQLException e){
                            System.out.println(e);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
                    else{
                        addbtn.setEnabled(false);
                        JOptionPane.showMessageDialog(rootPane, "Please Enter a Amount");
                    }
                }
                else{
                    addbtn.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Please Fill the Type");
                }
            }
            else{
               addbtn.setEnabled(false);
               JOptionPane.showMessageDialog(rootPane, "Please Fill the Transaction"); 
            }
        }
        else{
            addbtn.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Please Fill the Date");
        }
        
    }//GEN-LAST:event_addbtn2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        clearAll();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void updatebtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn2ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String dte = sdf.format(datecmbx.getDate());
        String tranbx = transbx.getText();
        String type = typecmbx.getSelectedItem().toString();
        String amount = amountbx2.getText();

        if(datecmbx.equals("") || transbx.equals("") || typecmbx.equals("") || amountbx.equals("")){

            JOptionPane.showMessageDialog(rootPane, "Please Fill the Feilds");

        }
        else{

            int x = JOptionPane.showConfirmDialog(null, "Do you really want to update ?");

            if(x==0){

                String query = "UPDATE pettycash set date = '"+dte+"', transaction = '"+tranbx+"', type = '"+type+"', amount= '"+amount+"'WHERE id='"+ primaryKey +"'";

                try{
                    Connection con =  connect();
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(rootPane, "Record has been Update Successfully");
                    clearAll();
                    pettycash_tableLoad();

                }catch(SQLException e){
                    System.out.println(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{

                JOptionPane.showMessageDialog(rootPane, "Update discarded !");

            }
        }
    }//GEN-LAST:event_updatebtn2ActionPerformed

    private void cancelbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtn2ActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");

        if(x == 0){

            try{
                String query = "DELETE FROM pettycash WHERE id = '"+ primaryKey +"'";
                Connection con =  connect();
                Statement st = con.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, "Record has been Delete Successfully");
                clearAll();
                pettycash_tableLoad();

            }catch(HeadlessException | ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Delete discarded !");
        }

    }//GEN-LAST:event_cancelbtn2ActionPerformed

    private void amountbx2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountbx2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountbx2ActionPerformed

    private void pettycash_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pettycash_tableMouseClicked

        try{
            int row = pettycash_table.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) pettycash_table.getModel();
            SimpleDateFormat ddate = new SimpleDateFormat("yyyy-MM-dd");

            primaryKey = model.getValueAt(row,0).toString();

            datecmbx.setDate(ddate.parse(model.getValueAt(row,1).toString()));
            transbx.setText(model.getValueAt(row,2).toString());
            typecmbx.setSelectedItem(model.getValueAt(row,3).toString());
            amountbx2.setText(model.getValueAt(row, 4).toString());

        }catch(ParseException e){
            System.out.println(e);
        }

    }//GEN-LAST:event_pettycash_tableMouseClicked

    private void amountbx3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountbx3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountbx3ActionPerformed

    private void statuscombx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statuscombx1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statuscombx1ActionPerformed

    private void descriptionbx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionbx1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionbx1ActionPerformed

    private void addbtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn3ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String typ = typecmbx.getSelectedItem().toString();
        String date1 = sdf.format(sdatecmbx.getDate());
        String date2 = sdf.format(edatecmbx.getDate());
        String amoun = amountbx3.getText();
        String stat = statuscombx.getSelectedItem().toString();
        String descrip = descriptionbx.getText();

        if(!typ.equals("")){
            
            if(!date1.equals("")){
                
                if(!date2.equals("")){
                    
                    if(!amoun.equals("")){
                    
                        if(!stat.equals("")){
                        
                            if(!descrip.equals("")){
                            
                                String query = "INSERT INTO taxes(type, sdate, edate, amount, status, description) VALUES('"+typ+"', '"+date1+"', '"+date2+"','"+amoun+"' ,'"+stat+"' ,'"+descrip+"')";

                                try{
                                    Connection con =  connect();
                                    Statement st = con.createStatement();
                                    st.executeUpdate(query);
                                    JOptionPane.showMessageDialog(rootPane, "Record has been saved successfully");
                                    clearAll();
                                    tax_tableLoad();

                                }catch(SQLException e){
                                    System.out.println(e);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                            }
                            else{
                                addbtn.setEnabled(false);
                                JOptionPane.showMessageDialog(rootPane, "Please Enter the description");
                            }
                        }
                        else{
                                addbtn.setEnabled(false);
                                JOptionPane.showMessageDialog(rootPane, "Please Select a ststus");
                        }
                        
                    }
                    else{
                        addbtn.setEnabled(false);
                        JOptionPane.showMessageDialog(rootPane, "Please Select the amount");
                    }
                }
                else{
                    addbtn.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Please Fill the End Date");
                }
            }
            else{
               addbtn.setEnabled(false);
               JOptionPane.showMessageDialog(rootPane, "Please Fill the Start Date"); 
            }
        }
        else{
            addbtn.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Please Fill the Type");
        }
    }//GEN-LAST:event_addbtn3ActionPerformed

    private void resetbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbtn2ActionPerformed
        clearAll();
    }//GEN-LAST:event_resetbtn2ActionPerformed


    private void updatebtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn3ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String typ = typecmbx1.getSelectedItem().toString();
        String date1 = sdf.format(sdatecmbx.getDate());
        String date2 = sdf.format(edatecmbx.getDate());
        String amoun = amountbx3.getText();
        String stat = statuscombx1.getSelectedItem().toString();
        String descrip = descriptionbx1.getText();

        if(!typ.equals("")){
            
            if(!date1.equals("")){
                
                if(!date2.equals("")){
                    
                    if(!amoun.equals("")){
                    
                        if(!stat.equals("")){
                        
                            if(!descrip.equals("")){
                            
                                    int x = JOptionPane.showConfirmDialog(null, "Do you really want to update ?");

                                    if(x==0){

                                        String query = "UPDATE taxes set type = '"+typ+"', sdate= '"+date1+"', edate= '"+date2+"', amount= '"+amoun+"', status='"+stat+"', description = '"+descrip+"'WHERE id='"+ primaryKey +"'";

                                    try{
                                        Connection con =  connect();
                                        Statement st = con.createStatement();
                                        st.executeUpdate(query);
                                        JOptionPane.showMessageDialog(rootPane, "Record has been Update Successfully");
                                        clearAll();
                                        tax_tableLoad();

                                    }catch(SQLException e){
                                        System.out.println(e);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(FinancialHandling.class.getName()).log(Level.SEVERE, null, ex);
                                    }    
                                    }
                                    else{

                                        JOptionPane.showMessageDialog(rootPane, "Update discarded !");
                                    }
                            }        
                            else{
                                addbtn.setEnabled(false);
                                JOptionPane.showMessageDialog(rootPane, "Please Enter the description");
                            }
                        }
                        else{
                                addbtn.setEnabled(false);
                                JOptionPane.showMessageDialog(rootPane, "Please Select a ststus");
                        }
                        
                    }
                    else{
                        addbtn.setEnabled(false);
                        JOptionPane.showMessageDialog(rootPane, "Please Select the amount");
                    }
                }
                else{
                    addbtn.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Please Fill the End Date");
                }
            }
            else{
               addbtn.setEnabled(false);
               JOptionPane.showMessageDialog(rootPane, "Please Fill the Start Date"); 
            }
        }
        else{
            addbtn.setEnabled(false);
            JOptionPane.showMessageDialog(rootPane, "Please Fill the Type");
        }        
    }//GEN-LAST:event_updatebtn3ActionPerformed

    private void cancelbtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtn3ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");

        if(x == 0){

            try{
                String query = "DELETE FROM taxes WHERE id = '"+ primaryKey +"'";
                Connection con =  connect();
                Statement st = con.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, "Record has been Delete Successfully");
                clearAll();
                tax_tableLoad();

            }catch(HeadlessException | ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Delete discarded !");
        }

    }//GEN-LAST:event_cancelbtn3ActionPerformed

    private void tax_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tax_tableMouseClicked
        try{
            int row = tax_table.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tax_table.getModel();
            SimpleDateFormat ddate = new SimpleDateFormat("yyyy-MM-dd");

            primaryKey = model.getValueAt(row,0).toString();

            typecmbx1.setSelectedItem(model.getValueAt(row,1).toString());
            sdatecmbx.setDate(ddate.parse(model.getValueAt(row,2).toString()));
            edatecmbx.setDate(ddate.parse(model.getValueAt(row,3).toString()));
            amountbx3.setText(model.getValueAt(row,4).toString());
            statuscombx1.setSelectedItem(model.getValueAt(row, 5).toString());
            descriptionbx1.setText(model.getValueAt(row,6).toString());

        }catch(ParseException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_tax_tableMouseClicked

    private void taxreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxreportActionPerformed

        reportGen IRgenarate = new reportGen("./src/Reports/FinancialHandling/Tax.jasper");
        
    }//GEN-LAST:event_taxreportActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        // TODO add your handling code here:
        reportGen IRgenarate = new reportGen("./src/Reports/FinancialHandling/PettyCash.jasper");
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        // TODO add your handling code here:
        reportGen IRgenarate = new reportGen("./src/Reports/FinancialHandling/Loans.jasper");
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jToggleButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton13ActionPerformed
        // TODO add your handling code here:
        reportGen IRgenarate = new reportGen("./src/Reports/FinancialHandling/CashFlow.jasper");
    }//GEN-LAST:event_jToggleButton13ActionPerformed

    private void cashflowbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashflowbtnMouseClicked
        
        setColor(cashflowbtn);
        resetColor(pettycashbtn);
        resetColor(loansbtn);
        resetColor(taxbtn);
        resetColor(Messagebtn);
        resetColor(reportsbtn);
        
        //removing panels
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        
        //adding panel
        mainpanel.add(cashflow);
        mainpanel.repaint();
        mainpanel.revalidate();

        
    }//GEN-LAST:event_cashflowbtnMouseClicked

    private void loansbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loansbtnMouseClicked
  
        setColor(loansbtn);
        resetColor(pettycashbtn);
        resetColor(cashflowbtn);
        resetColor(taxbtn);
        resetColor(Messagebtn);
        resetColor(reportsbtn);
        
        //removing panels
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        
        //adding panel
        mainpanel.add(loans);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_loansbtnMouseClicked

    private void taxbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxbtnMouseClicked

        setColor(taxbtn);
        resetColor(pettycashbtn);
        resetColor(cashflowbtn);
        resetColor(loansbtn);
        resetColor(Messagebtn);
        resetColor(reportsbtn);
        
        //removing panels
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        
        //adding panel
        mainpanel.add(tax);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_taxbtnMouseClicked

    private void MessagebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MessagebtnMouseClicked

        setColor(Messagebtn);
        resetColor(pettycashbtn);
        resetColor(cashflowbtn);
        resetColor(taxbtn);
        resetColor(loansbtn);
        resetColor(reportsbtn);
        
        //removing panels
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        
        //adding panel
        mainpanel.add(messages);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_MessagebtnMouseClicked

    private void reportsbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsbtnMouseClicked
        setColor(reportsbtn);
        resetColor(pettycashbtn);
        resetColor(cashflowbtn);
        resetColor(taxbtn);
        resetColor(Messagebtn);
        resetColor(loansbtn);
        
        //removing panels
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        
        //adding panel
        mainpanel.add(reports);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_reportsbtnMouseClicked
    
    private void datecombxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datecombxKeyReleased
    
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String d = sdf.format(datecombx.getDate());
        
        if(d.equals(sdf.format(datecombx.getDate()))){
            cfdatelb.setText(null);
            addbtn.setEnabled(true); 
        }
        else{
            cfdatelb.setText("Input Numbers Only ! ");
            addbtn.setEnabled(false);
        }
        
        

        /*try{
            int i = Integer.parseInt(amountbx.getText());
            cfamountlb.setText(null);
            addbtn.setEnabled(true);
        }
        catch(NumberFormatException e){
            cfamountlb.setText("Input Numbers Only ! ");
            addbtn.setEnabled(false);
        }*/
    
            

           
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        //String date = sdf.format(datecombx.getDate());
        
        /*try {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
            Date d = sdf.parse(sdf.format(datecombx.getDate()));
            cfdatelb.setText(null);
            addbtn.setEnabled(true);
        } catch (ParseException e) {
            cfdatelb.setText("Input Numbers Only ! ");
            addbtn.setEnabled(false);
        }*/

       
        

    
    }//GEN-LAST:event_datecombxKeyReleased

    private void descripbxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descripbxKeyReleased
        
        
    }//GEN-LAST:event_descripbxKeyReleased

    private void typecombxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typecombxKeyReleased
        
        addbtn.setEnabled(false);
        
        if(typecombx.getSelectedIndex() == -1)
        {	
            addbtn.setEnabled(false);
        }
        else
        {
            addbtn.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_typecombxKeyReleased

    private void transbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transbxActionPerformed

    private void From_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_From_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_From_mActionPerformed

    private void To_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_To_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_To_mActionPerformed

    private void topicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_topicActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser();
        j.showOpenDialog(null);
        File f =j.getSelectedFile();
        attachment_path = f.getAbsolutePath();
        A_path.setText(attachment_path);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
        // TODO add your handling code here:
        String From = From_m.getText();
        String To = To_m.getText();
        String Subject = topic.getText();
        String Txt_content = text.getText();

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");

        Session session =Session.getDefaultInstance(props,
            new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("gunasirien1996@gmail.com","sliit123");
                }

            }
        );
        try{
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(From ));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(To));
            message.setSubject(Subject);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(Txt_content);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            javax.activation.DataSource  source = new FileDataSource(attachment_path);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(A_path.getText());
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            JOptionPane.showMessageDialog(null,"Message sent");

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_SendActionPerformed

    private void lfiltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lfiltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lfiltActionPerformed

    private void lfiltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lfiltKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model1 = (DefaultTableModel) loans_table.getModel();
        String search_Box = lfilt.getText();//.toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
        loans_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search_Box));
    }//GEN-LAST:event_lfiltKeyReleased

    private void cffiltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cffiltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cffiltActionPerformed

    private void cffiltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cffiltKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) cashflow_table.getModel();
        String search_Box = cffilt.getText();//.toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model2);
        cashflow_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search_Box));
    }//GEN-LAST:event_cffiltKeyReleased

    private void tfiltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfiltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfiltActionPerformed

    private void tfiltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfiltKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) tax_table.getModel();
        String search_Box = tfilt.getText();//.toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model2);
        tax_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search_Box));
    }//GEN-LAST:event_tfiltKeyReleased

    private void pcfiltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcfiltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pcfiltActionPerformed

    private void pcfiltKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pcfiltKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) pettycash_table.getModel();
        String search_Box = pcfilt.getText();//.toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model2);
        pettycash_table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search_Box));
    }//GEN-LAST:event_pcfiltKeyReleased

    private void typecombxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typecombxKeyPressed
        // TODO add your handling code here:
        addbtn.setEnabled(false);
        
        if(typecombx.getSelectedIndex() == -1)
        {	
            addbtn.setEnabled(false);
        }
        else
        {
            addbtn.setEnabled(true);
        }
    }//GEN-LAST:event_typecombxKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinancialHandling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinancialHandling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinancialHandling().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A_path;
    private javax.swing.JTextField Aname;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date1;
    private javax.swing.JLabel Date2;
    private javax.swing.JLabel Date3;
    private javax.swing.JTextField From_m;
    private javax.swing.JPanel Messagebtn;
    private javax.swing.JButton Send;
    private javax.swing.JLabel Time;
    private javax.swing.JTextField To_m;
    private javax.swing.JButton addbtn;
    private javax.swing.JButton addbtn1;
    private javax.swing.JButton addbtn2;
    private javax.swing.JButton addbtn3;
    private javax.swing.JTextField amountbx;
    private javax.swing.JTextField amountbx1;
    private javax.swing.JTextField amountbx2;
    private javax.swing.JTextField amountbx3;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton cancelbtn1;
    private javax.swing.JButton cancelbtn2;
    private javax.swing.JButton cancelbtn3;
    private javax.swing.JPanel cashflow;
    private javax.swing.JTable cashflow_table;
    private javax.swing.JPanel cashflowbtn;
    private javax.swing.JLabel cfamountlb;
    private javax.swing.JLabel cfdatelb;
    private javax.swing.JLabel cfdescriptlb;
    private javax.swing.JTextField cffilt;
    private javax.swing.JLabel cftypelb;
    private com.toedter.calendar.JDateChooser datecmbx;
    private com.toedter.calendar.JDateChooser datecombx;
    private javax.swing.JTextField descripbx;
    private javax.swing.JTextField descriptionbx;
    private javax.swing.JTextField descriptionbx1;
    private com.toedter.calendar.JDateChooser edatecmbx;
    private com.toedter.calendar.JDateChooser edatecombx;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JTextField lfilt;
    private javax.swing.JPanel loans;
    private javax.swing.JTable loans_table;
    private javax.swing.JPanel loansbtn;
    private javax.swing.JPanel logobtn;
    private javax.swing.JButton logoutbtn;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JLabel messagebtn;
    private javax.swing.JPanel messages;
    private javax.swing.JTextField pcfilt;
    private javax.swing.JPanel pettycash;
    private javax.swing.JTable pettycash_table;
    private javax.swing.JPanel pettycashbtn;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JPanel reports;
    private javax.swing.JPanel reportsbtn;
    private javax.swing.JButton resetbtn;
    private javax.swing.JButton resetbtn1;
    private javax.swing.JButton resetbtn2;
    private com.toedter.calendar.JDateChooser sdatecmbx;
    private com.toedter.calendar.JDateChooser sdatecombx;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JComboBox<String> statuscombx;
    private javax.swing.JComboBox<String> statuscombx1;
    private javax.swing.JPanel tax;
    private javax.swing.JTable tax_table;
    private javax.swing.JPanel taxbtn;
    private javax.swing.JToggleButton taxreport;
    private javax.swing.JTextArea text;
    private javax.swing.JTextField tfilt;
    private javax.swing.JTextField topic;
    private javax.swing.JTextField transbx;
    private javax.swing.JComboBox<String> typecmbx;
    private javax.swing.JComboBox<String> typecmbx1;
    private javax.swing.JComboBox<String> typecombx;
    private javax.swing.JPanel upPanel;
    private javax.swing.JButton updatebtn;
    private javax.swing.JButton updatebtn1;
    private javax.swing.JButton updatebtn2;
    private javax.swing.JButton updatebtn3;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables

    String attachment_path;

}
