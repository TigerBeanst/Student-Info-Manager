package com.jakting;


import com.jakting.fragment.Change;
import com.jakting.fragment.Punishment;
import com.jakting.fragment.Reward;
import com.jakting.fragment.StudentInfo;
import com.jakting.utils.InitGlobalFont;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DatabaseCourseDesign extends JFrame {
    // 定义组件
    private int count = 0;
    JTabbedPane jTabbedPane = null;

    JButton jToolbar_Student = null;//查询
    JButton jToolbar_Reward = null;//查询
    JButton jToolbar_Punish = null;//查询
    JButton jToolbar_Change = null;//查询


    public static JPanel jStu, jReward, jPunish, jChange;

    // 构造函数
    public DatabaseCourseDesign() {
        // 创建组件
        jTabbedPane = new JTabbedPane();

        jToolbar_Student = new JButton("学生信息");
        jToolbar_Reward = new JButton("获奖情况");
        jToolbar_Punish = new JButton("处分情况");
        jToolbar_Change = new JButton("学籍变更");

        jStu = new JPanel();
        jReward = new JPanel();
        jPunish = new JPanel();
        jChange = new JPanel();

        StudentInfo getjStu = new StudentInfo();
        jStu = getjStu.addAndSelect(jStu);
        Reward getjReward = new Reward();
        jReward = getjReward.addAndSelect(jReward);
        Punishment getjPunish = new Punishment();
        jPunish = getjPunish.addAndSelect(jPunish);
        Change getjChange = new Change();
        jChange = getjChange.addAndSelect(jChange);

        jTabbedPane.addTab("学生信息", jStu);
        jTabbedPane.addTab("获奖情况", jReward);
        jTabbedPane.addTab("处分情况", jPunish);
        jTabbedPane.addTab("学籍变更", jChange);
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                int n = jTabbedPane.getSelectedIndex();
                loadTab(n);
            }
        });
        loadTab(0);

        this.add(jTabbedPane, "Center");
        //this.add("North", jStu);

        //this.setLayout(new BoxLayout(jStu, BoxLayout.PAGE_AXIS));
        this.setTitle("学生管理系统");
        this.setSize(900, 700);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InitGlobalFont.InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 16));
        DatabaseCourseDesign getcon = new DatabaseCourseDesign();
    }

    private void loadTab(int n) {
        String title = jTabbedPane.getTitleAt(n);
        String countString = String.valueOf(count++);
        //String msg = "this is " + title + ", load at " + countString + " times";
        //jTabbedPane.setComponentAt(n, new JLabel(msg));
    }


}

