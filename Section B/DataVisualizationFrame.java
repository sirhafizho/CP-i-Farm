/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Kisyok Indran Veerasamy
 */
public class DataVisualizationFrame extends javax.swing.JFrame {
    private List<String> farmList;
    private List<String> farmerList;
    private List<String> choiceList;
    private List<String> plantList;
    private List<String> fertilizerList;
    private List<String> pesticideList;
    private List<String> rowList;
    private List<String> fieldList;

    
    private Boolean shouldSearch = false;
    private int initValue = 0;
    private int toLoad = 100;

    JScrollBar sb;
    JViewport vp;
    /**
     * Creates new form DataVisualzationFrame
     */
    public DataVisualizationFrame() {
        initComponents();
    
        // get the values from DB to be displayed to users
       DataVisualization visualization = new DataVisualization();
       farmList = visualization.getFarmsFarmer("farms");
       farmerList = visualization.getFarmsFarmer("farmer");
       choiceList = Arrays.asList("Plant", "Fertilizer", "Pesticide");
       plantList = visualization.getChoice("plants");
       fertilizerList = visualization.getChoice("fertilizers");
       pesticideList = visualization.getChoice("pesticides");;
       rowList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
       fieldList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
       
       // wrap the combobox in decorator class
       JComboBoxDecorator.decorate(farmComboBox, true, farmList); 
       JComboBoxDecorator.decorate(farmerComboBox, true, farmerList); 
       JComboBoxDecorator.decorate(choiceComboBox, true, choiceList); 
       JComboBoxDecorator.decorate(chosenTypeComboBox, true, plantList);
       JComboBoxDecorator.decorate(rowComboBox, true, rowList);
       JComboBoxDecorator.decorate(fieldComboBox, true, fieldList);

       // to close db connection and exit the system when user clicks exit button on GUI
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Closing DB connection and exiting system");
                visualization.mysqlCon.closeConn();
                System.exit(0);
            }
        });

        sb = jScrollPane1.getVerticalScrollBar();
        vp = jScrollPane1.getViewport();
        vp.addChangeListener((l) -> {
            if(!shouldSearch)
                return;
            if(sb.getValue() == sb.getMinimum())
                System.out.println("top");
            if(sb.getValue() +sb.getVisibleAmount() == sb.getMaximum()){
                if(shouldSearch){
                    System.out.println("bottom"); 
                    initValue += toLoad;
                    System.out.println("loading data "+initValue+" to "+(initValue+toLoad));
                    getData();
                }
                
            }
                
        });
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        display1 = new javax.swing.JRadioButton();
        display2 = new javax.swing.JRadioButton();
        display3 = new javax.swing.JRadioButton();
        display4 = new javax.swing.JRadioButton();
        display5 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        farmComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        farmerComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rowComboBox = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        displayButton = new javax.swing.JButton();
        dateACalendar = new com.toedter.calendar.JCalendar();
        jLabel5 = new javax.swing.JLabel();
        dateBCalendar = new com.toedter.calendar.JCalendar();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        choiceComboBox = new javax.swing.JComboBox<>();
        fieldComboBox = new javax.swing.JComboBox<>();
        exportOutput = new javax.swing.JButton();
        chosenTypeComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        display1.setBackground(new java.awt.Color(130, 131, 133));
        buttonGroup1.add(display1);
        display1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display1.setForeground(new java.awt.Color(255, 255, 255));
        display1.setText("Display all activity logs for a target farm");
        display1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display1ActionPerformed(evt);
            }
        });

        display2.setBackground(new java.awt.Color(130, 131, 133));
        buttonGroup1.add(display2);
        display2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display2.setForeground(new java.awt.Color(255, 255, 255));
        display2.setText("Display all activity logs for a target farmer");
        display2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display2ActionPerformed(evt);
            }
        });

        display3.setBackground(new java.awt.Color(130, 131, 133));
        buttonGroup1.add(display3);
        display3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display3.setForeground(new java.awt.Color(255, 255, 255));
        display3.setText("Display all activity logs for a target farm and plant / fertilizer / pesticide");
        display3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display3ActionPerformed(evt);
            }
        });

        display4.setBackground(new java.awt.Color(130, 131, 133));
        buttonGroup1.add(display4);
        display4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display4.setForeground(new java.awt.Color(255, 255, 255));
        display4.setText("Display all activity logs for a target farm and plant / fertilizer / pesticide between date A  and date B (inclusive)");
        display4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display4ActionPerformed(evt);
            }
        });

        display5.setBackground(new java.awt.Color(130, 131, 133));
        buttonGroup1.add(display5);
        display5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display5.setForeground(new java.awt.Color(255, 255, 255));
        display5.setText("Display summarized logs by plants, fertilizers and pesticides for a target farm and plant /  fertilizer / pesticide between date A and date B (inclusive) for selected field and row  number");
        display5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display5ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(22, 125, 127));

        farmComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        farmComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        farmComboBox.setEnabled(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Farm");

        farmerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        farmerComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        farmerComboBox.setEnabled(false);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Farmer");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Type");

        rowComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        rowComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rowComboBox.setEnabled(false);
        rowComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowComboBoxActionPerformed(evt);
            }
        });

        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Row");

        displayButton.setBackground(new java.awt.Color(255, 255, 255));
        displayButton.setText("Display Logs");
        displayButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        displayButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        displayButton.setEnabled(false);
        displayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayButtonActionPerformed(evt);
            }
        });

        dateACalendar.setEnabled(false);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date A");

        dateBCalendar.setEnabled(false);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date B");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Field");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fertilizer");

        choiceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plant", "Fertilizer", "Pesticide" }));
        choiceComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choiceComboBox.setEnabled(false);
        choiceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiceComboBoxActionPerformed(evt);
            }
        });

        fieldComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fieldComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fieldComboBox.setEnabled(false);
        fieldComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldComboBoxActionPerformed(evt);
            }
        });

        exportOutput.setBackground(new java.awt.Color(255, 255, 255));
        exportOutput.setText("Export");
        exportOutput.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exportOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportOutput.setEnabled(false);
        exportOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportOutputActionPerformed(evt);
            }
        });

        chosenTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chosenTypeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chosenTypeComboBox.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel81))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rowComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(farmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateBCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateACalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(farmerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chosenTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(displayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(farmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(farmerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rowComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81)
                    .addComponent(fieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chosenTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateACalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(dateBCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        outputTextArea.setBackground(new java.awt.Color(211, 206, 196));
        outputTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outputTextArea.setColumns(20);
        outputTextArea.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        outputTextArea.setRows(5);
        outputTextArea.setEnabled(false);
        jScrollPane1.setViewportView(outputTextArea);
        DefaultCaret caret = (DefaultCaret)outputTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(display5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(display1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(display4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(display3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(display2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(display1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getData(){
        DataVisualization visualization = new DataVisualization();
        String farmer = farmerComboBox.getSelectedItem().toString();
        String farm = farmComboBox.getSelectedItem().toString();
        String type = chosenTypeComboBox.getSelectedItem().toString();
        Date startDate = dateACalendar.getDate();
        Date endDate = dateBCalendar.getDate();

        String field = fieldComboBox.getSelectedItem().toString();
        String row = rowComboBox.getSelectedItem().toString();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
       if(display1.isSelected()){
            if(initValue == 0){
                System.out.println("setting search to true");
                shouldSearch = true;
            }
           String activityLog = visualization.printActivityLog(visualization.displayActivityLogsFarm(farm, initValue, toLoad));
           if(activityLog == "No records found\n"){
            System.out.println("setting search to false");
            shouldSearch = false;
           }
           outputTextArea.append(activityLog);
           
           
       } else if(display2.isSelected()){
            if(initValue == 0){
                System.out.println("setting search to true");
                shouldSearch = true;
            }
           String activityLog = visualization.printActivityLog(visualization.displayActivityLogsFarmer(farmer, initValue, toLoad));
           
           if(activityLog == "No records found\n"){
            System.out.println("setting search to false");
            shouldSearch = false;
           }
           outputTextArea.append(activityLog);
       } else if(display3.isSelected()){
            if(initValue == 0){
                System.out.println("setting search to true");
                shouldSearch = true;
            }
           String activityLog = visualization.printActivityLog(visualization.displayActivityLogsFarmType(farm, type, initValue, toLoad));
           
           if(activityLog == "No records found\n"){
            System.out.println("setting search to false");
            shouldSearch = false;
           }
           outputTextArea.append(activityLog);
       } else if(display4.isSelected()){
            
           if(startDate.before(endDate)){
                if(initValue == 0){
                    System.out.println("setting search to true");
                    shouldSearch = true;
                }
               String fromDate = dateFormat.format(startDate);
               String toDate = dateFormat.format(endDate);
               String activityLog = visualization.printActivityLog(visualization.displayActivityLogsFarmTypeDate(farm, type, fromDate, toDate, initValue, toLoad));
               
               if(activityLog == "No records found\n"){
                System.out.println("setting search to false");
                shouldSearch = false;
               }
               outputTextArea.append(activityLog);
           } else {
               outputTextArea.setText("");
               outputTextArea.setText("Invalid date choices (start date should be lower than end date)");
           }
       } else if(display5.isSelected()){
           if(startDate.before(endDate)){
                if(initValue == 0){
                    System.out.println("setting search to true");
                    shouldSearch = true;
                }
               String fromDate = dateFormat.format(startDate);
               String toDate = dateFormat.format(endDate);
               String activityLog = visualization.printSummarizedActivityLog(visualization.displayActivityLogsFarmTypeDateFieldRow(farm, type, fromDate, toDate, field, row, initValue, toLoad));
               
               if(activityLog == "No records found\n"){
                System.out.println("setting search to false");
                shouldSearch = false;
               }
               outputTextArea.append(activityLog);
           } else {
               outputTextArea.setText("");
               outputTextArea.setText("Invalid date choices (start date should be lower than end date)");
           }
       }
    }

    private void displayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayButtonActionPerformed
        getData();
        exportOutput.setEnabled(true);
    }//GEN-LAST:event_displayButtonActionPerformed

    private void display1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display1ActionPerformed
        this.farmComboBox.setEnabled(true);
        this.farmerComboBox.setEnabled(false);
        this.choiceComboBox.setEnabled(false);
        this.chosenTypeComboBox.setEnabled(false);
        this.rowComboBox.setEnabled(false);
        this.fieldComboBox.setEnabled(false);
        this.dateACalendar.setEnabled(false);
        this.dateBCalendar.setEnabled(false);
        this.displayButton.setEnabled(true);
        initValue = 0;
        outputTextArea.setText("");
        shouldSearch = false;
    }//GEN-LAST:event_display1ActionPerformed

    private void display2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display2ActionPerformed
        this.farmComboBox.setEnabled(false);
        this.farmerComboBox.setEnabled(true);
        this.choiceComboBox.setEnabled(false);
        this.chosenTypeComboBox.setEnabled(false);
        this.rowComboBox.setEnabled(false);
        this.fieldComboBox.setEnabled(false);
        this.dateACalendar.setEnabled(false);
        this.dateBCalendar.setEnabled(false);
        this.displayButton.setEnabled(true);
        initValue = 0;
        outputTextArea.setText("");
        shouldSearch = false;
    }//GEN-LAST:event_display2ActionPerformed

    private void display3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display3ActionPerformed
        this.farmComboBox.setEnabled(true);
        this.farmerComboBox.setEnabled(false);
        this.choiceComboBox.setEnabled(true);
        this.chosenTypeComboBox.setEnabled(false);
        this.rowComboBox.setEnabled(false);
        this.fieldComboBox.setEnabled(false);
        this.dateACalendar.setEnabled(false);
        this.dateBCalendar.setEnabled(false);
        this.displayButton.setEnabled(true);
        initValue = 0;
        outputTextArea.setText("");
        shouldSearch = false;
    }//GEN-LAST:event_display3ActionPerformed

    private void rowComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowComboBoxActionPerformed
        
    }//GEN-LAST:event_rowComboBoxActionPerformed

    private void display4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display4ActionPerformed
        this.farmComboBox.setEnabled(true);
        this.farmerComboBox.setEnabled(false);
        this.choiceComboBox.setEnabled(true);
        this.chosenTypeComboBox.setEnabled(false);
        this.rowComboBox.setEnabled(false);
        this.fieldComboBox.setEnabled(false);
        this.dateACalendar.setEnabled(true);
        this.dateBCalendar.setEnabled(true);
        this.displayButton.setEnabled(true);
        initValue = 0;
        outputTextArea.setText("");
        shouldSearch = false;
    }//GEN-LAST:event_display4ActionPerformed

    private void choiceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiceComboBoxActionPerformed
        String selected = this.choiceComboBox.getSelectedItem().toString();
        
        if(selected == "Plant"){
            jLabel8.setText("Plant");
            chosenTypeComboBox.setModel(new DefaultComboBoxModel(this.plantList.toArray()));
            this.chosenTypeComboBox.setEnabled(true);
        }else if(selected == "Fertilizer"){
            jLabel8.setText("Fertilizer");
            chosenTypeComboBox.setModel(new DefaultComboBoxModel(this.fertilizerList.toArray()));
            this.chosenTypeComboBox.setEnabled(true);
        }else if(selected == "Pesticide"){
            jLabel8.setText("Pesticide");
            chosenTypeComboBox.setModel(new DefaultComboBoxModel(this.pesticideList.toArray()));
            this.chosenTypeComboBox.setEnabled(true);
        }
            
        
    }//GEN-LAST:event_choiceComboBoxActionPerformed

    private void fieldComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldComboBoxActionPerformed
        
    }//GEN-LAST:event_fieldComboBoxActionPerformed

    private void display5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display5ActionPerformed
        this.farmComboBox.setEnabled(true);
        this.farmerComboBox.setEnabled(false);
        this.choiceComboBox.setEnabled(true);
        this.chosenTypeComboBox.setEnabled(false);
        this.rowComboBox.setEnabled(true);
        this.fieldComboBox.setEnabled(true);
        this.dateACalendar.setEnabled(true);
        this.dateBCalendar.setEnabled(true);
        this.displayButton.setEnabled(true);
        initValue = 0;
        outputTextArea.setText("");
        shouldSearch = false;
    }//GEN-LAST:event_display5ActionPerformed

    private void exportOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportOutputActionPerformed
        try {
            FileWriter myWriter = new FileWriter("Data Visualization Log.txt");
            myWriter.write(this.outputTextArea.getText());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportOutputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])  throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataVisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataVisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataVisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataVisualizationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataVisualizationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> choiceComboBox;
    private javax.swing.JComboBox<String> chosenTypeComboBox;
    private com.toedter.calendar.JCalendar dateACalendar;
    private com.toedter.calendar.JCalendar dateBCalendar;
    private javax.swing.JRadioButton display1;
    private javax.swing.JRadioButton display2;
    private javax.swing.JRadioButton display3;
    private javax.swing.JRadioButton display4;
    private javax.swing.JRadioButton display5;
    private javax.swing.JButton displayButton;
    private javax.swing.JButton exportOutput;
    private javax.swing.JComboBox<String> farmComboBox;
    private javax.swing.JComboBox<String> farmerComboBox;
    private javax.swing.JComboBox<String> fieldComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JComboBox<String> rowComboBox;
    // End of variables declaration//GEN-END:variables
}
