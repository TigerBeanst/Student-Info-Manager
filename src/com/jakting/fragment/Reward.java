package com.jakting.fragment;

import com.jakting.utils.DbProcess;
import com.jakting.utils.TableAdjust;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Reward extends JFrame implements ActionListener {
    JLabel jLStudentInfoTable = null;//学生信息表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLSID = null;//序号
    JLabel jLSStudentID = null;//学号
    JLabel jLSReward = null;//获奖奖项
    JLabel jLSRecTime = null;//获奖时间
    JLabel jLSDesc = null;//获奖详情

    JTextField jTFQueryField = null;//查询字段
    JTextField jTFSID = null;//学号
    JTextField jTFSStudentID = null;//学号
    JTextField jTFSReward = null;//获奖奖项
    JTextField jTFSRecTime = null;//获奖时间
    JTextField jTFSDesc = null;//获奖详情

    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBInsert = null;//插入
    JButton jBUpdate = null;//更新
    JButton jBDeleteCurrentRecord = null;//删除当前记录
    JButton jBDeleteAllRecords = null;//删除所有记录

    //JComboBox jCBSelectQueryField = null;
    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JComboBox<String> jCBSelectRewardField = null;//查询字段
    JPanel jP1, jP2, jP3, jP4, jP5, jP6 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable studentJTable = null;
    JScrollPane studentJScrollPane = null;
    Vector studentVector = null;
    Vector titleVector = null;
    private static DbProcess dbProcess;
    String SelectQueryFieldStr = "学号";
    String SelectRewardFieldStr = "校特等奖学金";

    //构造函数
    public Reward() {
        jLStudentInfoTable = new JLabel("学生获奖情况统计表");
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLSID = new JLabel("序号");
        jLSStudentID = new JLabel("学号");
        jLSReward = new JLabel("奖项");
        jLSRecTime = new JLabel("时间");
        jLSDesc = new JLabel("详情");

        jTFQueryField = new JTextField(10);//查询字段
        jTFSID = new JTextField(10);//学号
        jTFSStudentID = new JTextField(10);//学号
        //jTFSReward = new JTextField(10);//奖项
        jTFSRecTime = new JTextField(10);//时间
        jTFSDesc = new JTextField(10);//详情

        jTFSRecTime.setToolTipText("格式：年/月/日，例如：2018-12-25");

        jBQuery = new JButton("查询");

        jBQueryAll = new JButton("查询所有记录");
        jBInsert = new JButton("插入");
        jBUpdate = new JButton("更新");
        jBDeleteCurrentRecord = new JButton("删除当前记录");
        jBDeleteAllRecords = new JButton("删除所有记录");

        studentVector = new Vector();
        titleVector = new Vector();

        // 定义表头
        titleVector.add("序号");
        titleVector.add("学号");
        titleVector.add("奖项");
        titleVector.add("时间");
        titleVector.add("详情");
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(800, 250));
        //int[] i = {0, 1, 2, 3};
        //TableAdjust.setSomeColumnSize(studentJTable, i, 52, 52, 52);
        TableAdjust.setOneColumnSize(studentJTable, 0, 52, 52, 52);
        TableAdjust.setOneColumnSize(studentJTable, 1, 100, 100, 52);
        TableAdjust.setOneColumnSize(studentJTable, 2, 100, 100, 52);
        TableAdjust.setOneColumnSize(studentJTable, 3, 100, 100, 52);
        TableAdjust.setOneColumnSize(studentJTable, 4, 420, 420, 52);
        studentJScrollPane = new JScrollPane(studentJTable);
        //分别设置水平和垂直滚动条自动出现
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //为表格添加监听器
        studentJTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
                System.out.println("mouseClicked(). row = " + row);
                Vector v = new Vector();
                v = (Vector) studentVector.get(row);

                jTFSID.setText(Integer.toString((int) v.get(0)));// 学号
                jTFSID.setEditable(false);
                jTFSStudentID.setText(Integer.toString((int) v.get(1)));// 学号
                //jCBSelectRewardField.setSelectedItem(v.get(1));
                jTFSReward.setText((String) v.get(2));// 奖项
                System.out.println("jTFSReward:==" + (String) v.get(2));
                jTFSRecTime.setText((String) v.get(3));// 时间
                jTFSDesc.setText((String) v.get(4));// 详情
            }
        });


        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBInsert.addActionListener(this);
        jBUpdate.addActionListener(this);
        jBDeleteCurrentRecord.addActionListener(this);
        jBDeleteAllRecords.addActionListener(this);

        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("学号");
        jCBSelectQueryField.addItem("奖项");
        jCBSelectQueryField.addItem("时间");
        jCBSelectQueryField.addItem("详情");
        jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectQueryFieldStr = (String) event.getItem();
                        System.out.println("选中：" + SelectQueryFieldStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });

        //奖项下拉框 校特等奖学金/校一等奖学金/校二等奖学金/校三等奖学金/系一等奖学金/系二等奖学金/系三等奖学金
        jCBSelectRewardField = new JComboBox<String>();//查询字段
        jCBSelectRewardField.addItem("校特等奖学金");
        jCBSelectRewardField.addItem("校一等奖学金");
        jCBSelectRewardField.addItem("校二等奖学金");
        jCBSelectRewardField.addItem("校三等奖学金");
        jCBSelectRewardField.addItem("系一等奖学金");
        jCBSelectRewardField.addItem("系二等奖学金");
        jCBSelectRewardField.addItem("系三等奖学金");
        jCBSelectRewardField.addItemListener(new ItemListener() {//下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectRewardFieldStr = (String) event.getItem();
                        System.out.println("选中：" + SelectRewardFieldStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });

        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP6 = new JPanel();
        jPTop = new JPanel();
        jPBottom = new JPanel();

        //jP0.add(jToolBar, );

        jP1.add(jLStudentInfoTable);
        jP2.add(studentJScrollPane);
        jP2.setPreferredSize(new Dimension(1000, 1000));

        jP3.add(jLSelectQueryField);
        jP3.add(jCBSelectQueryField);
        jP3.add(jLEqual);
        jP3.add(jTFQueryField);
        jP3.add(jBQuery);
        jP3.add(jBQueryAll);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(50, 50));

        jP4.add(jLSStudentID);
        jP4.add(jTFSStudentID);
        jP4.add(jLSReward);
        jP4.add(jCBSelectRewardField);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(50, 50));

        jP5.add(jLSRecTime);
        jP5.add(jTFSRecTime);
        jP5.add(jLSDesc);
        jP5.add(jTFSDesc);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(50, 50));

        jP6.add(jBInsert);
        jP6.add(jBUpdate);
        jP6.add(jBDeleteCurrentRecord);
        jP6.add(jBDeleteAllRecords);
        jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP6.setPreferredSize(new Dimension(50, 50));

        jPTop.add(jP1);
        jPTop.add(jP2);
        jPTop.setLayout(new BoxLayout(jPTop, BoxLayout.Y_AXIS));
        jPBottom.setLayout(new GridLayout(4, 1));
        jPBottom.add(jP3);
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP6);
        //jPBottom.setLayout(new BoxLayout(jPBottom, BoxLayout.Y_AXIS));


        dbProcess = new DbProcess();
    }

    public JPanel addAndSelect(JPanel jStu) {
        JPanel jStuA = jStu;
        jStuA.add(jPTop);
        jStuA.add(jPBottom);
        jStuA.setLayout(new BoxLayout(jStuA, BoxLayout.Y_AXIS));
        //jStu.setPreferredSize(new Dimension(700,480));
        {
            //点击窗体时取消表格的选中状态
            jStuA.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    studentJTable.clearSelection();
                    jTFSStudentID.setText("");
                    //jTFSReward.setText("");
                    jTFSRecTime.setText("");
                    jTFSDesc.setText("");
                }
            });
        }
        return jStuA;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("查询")
                && !jTFQueryField.getText().isEmpty()) {
            System.out.println("actionPerformed(). 查询");
            String sQueryField = jTFQueryField.getText().trim();
            queryProcess(sQueryField);
            jTFQueryField.setText("");
        } else if (e.getActionCommand().equals("查询所有记录")) {
            System.out.println("actionPerformed(). 查询所有记录");
            queryAllProcess();
        } else if (e.getActionCommand().equals("插入")
                && !jTFSStudentID.getText().isEmpty()
                && !jTFSRecTime.getText().isEmpty()
                && !jTFSDesc.getText().isEmpty()) {
            System.out.println("actionPerformed(). 插入");
            insertProcess();
        } else if (e.getActionCommand().equals("更新")
                && !jTFSStudentID.getText().isEmpty()
                && !jTFSRecTime.getText().isEmpty()
                && !jTFSDesc.getText().isEmpty()) {
            System.out.println("actionPerformed(). 更新");
            updateProcess();
        } else if (e.getActionCommand().equals("删除当前记录")) {
            System.out.println("actionPerformed(). 删除当前记录");
            deleteCurrentRecordProcess();
        } else if (e.getActionCommand().equals("删除所有记录")) {
            System.out.println("actionPerformed(). 删除所有记录");
            deleteAllRecordsProcess();
        }
    }

    public void queryProcess(String sQueryField) {
        try {
            // 建立查询条件
            String sql = "select * from reward inner join reward_levels on reward.Levels = reward_levels.code where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

            if (queryFieldStr.equals("StudentID") || queryFieldStr.equals("Levels")) {
                sql = sql + queryFieldStr;
                sql = sql + " = " + sQueryField;
            } else {
                sql = sql + queryFieldStr;
                sql = sql + " like ";
                sql = sql + "'%" + sQueryField + "%';";
            }

            System.out.println("queryProcess(). sql = " + sql);

            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);

            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(Integer.valueOf(rs.getInt("rid")));
                v.add(Integer.valueOf(rs.getInt("StudentID")));
                v.add(rs.getString("reward_levels.Description"));
                v.add(rs.getString("rRecTime"));
                v.add(rs.getString("rDescription"));
                studentVector.add(v);
            }

            studentJTable.updateUI();

            dbProcess.disconnect();
        } catch (SQLException sqle) {
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryAllProcess() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 建立查询条件
                    String sql = "select * from reward inner join reward_levels on reward.Levels = reward_levels.code;";
                    System.out.println("queryAllProcess(). sql = " + sql);

                    dbProcess.connect();
                    ResultSet rs = dbProcess.executeQuery(sql);

                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    studentVector.clear();
                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(Integer.valueOf(rs.getInt("rid")));
                        v.add(Integer.valueOf(rs.getInt("StudentID")));
                        v.add(rs.getString("reward_levels.Description"));
                        v.add(rs.getString("rRecTime"));
                        v.add(rs.getString("rDescription"));
                        studentVector.add(v);
                    }

                    studentJTable.updateUI();
                    dbProcess.disconnect();
                } catch (SQLException sqle) {
                    System.out.println("sqle = " + sqle);
                    JOptionPane.showMessageDialog(null,
                            "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void insertProcess() {
        String StudentID = jTFSStudentID.getText().trim();
        int sReward = jCBSelectRewardFieldTransfer(SelectRewardFieldStr);
        String sRecTime = jTFSRecTime.getText().trim();
        String sDesc = jTFSDesc.getText().trim();
        int trueReward = 0;

        /*String getClassDepartment = "select reward_levels.code from reward_levels where reward_levels.Description = '" + sReward + "';";
        System.out.println("getClassDepartment. sql = " + getClassDepartment);
        dbProcess.connect();
        ResultSet rs = dbProcess.executeQuery(getClassDepartment);
        try {
            while (rs.next()) {
                trueReward = rs.getInt("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbProcess.disconnect();*/

        // 建立插入条件
        String sql = "insert into reward(StudentID,Levels,rRecTime,rDescription) values(";
        sql = sql + StudentID + ",";
        sql = sql + sReward + ",'";
        sql = sql + sRecTime + "','";
        sql = sql + sDesc + "');";

        System.out.println("insertProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("insertProcess(). insert database failed.");
                JOptionPane.showMessageDialog(null,
                        "数据操作错误。请按照以下步骤排除：\n1. 请检查该学号学生是否存在于学生信息库中。\n2. 请检查填写时「时间」格式是否正确，鼠标悬浮在输入框上可以查看提示。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }


    public void updateProcess() {
        String ID = jTFSID.getText().trim();
        String StudentID = jTFSStudentID.getText().trim();
        int rReward = jCBSelectRewardFieldTransfer(SelectRewardFieldStr);
        String rRecTime = jTFSRecTime.getText().trim();
        String rDesc = jTFSDesc.getText().trim();
        int trueReward = -1;

        /*String getClassDepartment = "select reward_levels.code from reward_levels where reward_levels.Description = '" + rReward + "';";
        System.out.println("getClassDepartment. sql = " + getClassDepartment);
        dbProcess.connect();
        ResultSet rs = dbProcess.executeQuery(getClassDepartment);
        try {
            while (rs.next()) {
                trueReward = rs.getInt("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbProcess.disconnect();*/

        // 建立更新条件
        String sql = "update reward set StudentID = ";
        sql = sql + StudentID + ", Levels = ";
        sql = sql + rReward + ", rRecTime = '";
        sql = sql + rRecTime + "', rDescription = '";
        sql = sql + rDesc + "'";
        sql = sql + " WHERE rid = " + ID + ";";
        System.out.println("updateProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
                JOptionPane.showMessageDialog(null,
                        "数据操作错误。请按照以下步骤排除：\n1. 请检查该学号学生是否存在于学生信息库中。\n2. 请检查填写时「时间」格式是否正确，鼠标悬浮在输入框上可以查看提示。", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();

    }

    public void deleteCurrentRecordProcess() {

        String ID = jTFSID.getText().trim();

        // 建立删除条件
        String sql = "delete from reward where rid = " + ID + ";";
        System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("deleteCurrentRecordProcess(). delete database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();

    }

    public void deleteAllRecordsProcess() {
        // 建立删除条件
        String sql = "delete from reward;";
        System.out.println("deleteAllRecordsProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("deleteAllRecordsProcess(). delete database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();

    }

    public String jCBSelectQueryFieldTransfer(String InputStr) {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

        if (InputStr.equals("学号")) {
            outputStr = "StudentID";
        } else if (InputStr.equals("奖项")) {
            outputStr = "Levels";
        } else if (InputStr.equals("时间")) {
            outputStr = "rRecTime";
        } else if (InputStr.equals("详情")) {
            outputStr = "rDescription";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }

    public int jCBSelectRewardFieldTransfer(String InputStr) {
        int outputStr = -1;
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

        if (InputStr.equals("校特等奖学金")) {
            outputStr = 0;
        } else if (InputStr.equals("校一等奖学金")) {
            outputStr = 1;
        } else if (InputStr.equals("校二等奖学金")) {
            outputStr = 2;
        } else if (InputStr.equals("校三等奖学金")) {
            outputStr = 3;
        } else if (InputStr.equals("系一等奖学金")) {
            outputStr = 4;
        } else if (InputStr.equals("系二等奖学金")) {
            outputStr = 5;
        } else if (InputStr.equals("系三等奖学金")) {
            outputStr = 6;
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}
