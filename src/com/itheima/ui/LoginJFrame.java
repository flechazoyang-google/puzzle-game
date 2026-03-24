package com.itheima.ui;

import com.itheima.domain.User;
import com.itheima.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    //登录界面

    String login = "登录按钮";
    String register = "注册按钮";


    JButton jButtonlogin;
    JButton jButtonregister;
    JLabel jLabel = new JLabel();


    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("杨根豪","123"));
        allUsers.add(new User("郭雨欣","5201314"));
        allUsers.add(new User("1","1"));
    }


    JTextField textField1 = new JTextField();
    JPasswordField jPasswordField = new JPasswordField();
    JTextField textField3 = new JTextField();

    String rightCode = CodeUtil.getCode();


    public LoginJFrame() {

        initJFrame();


//        界面初始化
        initLogin();

        //设置界面显示
        this.setVisible(true);
    }

    private void initJFrame() {
        //创建登入界面并初始化长宽和设置显示
        this.setSize(488,430);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面标题
        this.setTitle("登录");

        //设置界面关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        取消图片默认居中显示
        this.setLayout(null);
    }

    private void initLogin() {

        this.getContentPane().removeAll();

        JLabel label1 = new JLabel(new ImageIcon("puzzlegame\\image\\login\\用户名.png"));
        label1.setBounds(100,120,47,17);
        this.getContentPane().add(label1);


        JLabel label2 = new JLabel(new ImageIcon("puzzlegame\\image\\login\\密码.png"));
        label2.setBounds(100,180,47,17);
        this.getContentPane().add(label2);


        JLabel label3 = new JLabel(new ImageIcon("puzzlegame\\image\\login\\验证码.png"));
        label3.setBounds(100,240,47,17);
        this.getContentPane().add(label3);


        /*JLabel label4 = new JLabel(new ImageIcon("image\\login\\登录按钮.png"));
        label4.setBounds(100,280,128,47);
        this.getContentPane().add(label4);*/


        textField1.setBounds(160,115,200,25);
        this.getContentPane().add(textField1);


        jPasswordField.setBounds(160,175,200,25);
        this.getContentPane().add(jPasswordField);


        textField3.setBounds(160,235,100,25);
        this.getContentPane().add(textField3);




        jLabel.setText(rightCode);
        jLabel.setBounds(280,235,100,25);
        jLabel.addMouseListener(this);
        this.getContentPane().add(jLabel);



        jButtonlogin = new JButton();
        jButtonlogin.setBounds(100,280,128,47);
        jButtonlogin.setIcon(new ImageIcon("puzzlegame\\image\\login\\" + login + ".png"));
        jButtonlogin.setBorderPainted(false);
        jButtonlogin.setContentAreaFilled(false);
        this.getContentPane().add(jButtonlogin);
        jButtonlogin.addMouseListener(this);




        jButtonregister= new JButton(new ImageIcon("puzzlegame\\image\\login\\" + register + ".png"));
        jButtonregister.setBounds(250,280,128,47);
        jButtonregister.setBorderPainted(false);
        jButtonregister.setContentAreaFilled(false);
        this.getContentPane().add(jButtonregister);
        jButtonregister.addMouseListener(this);


        getMouseListeners();
//        addMouseListener(this);



        JLabel label = new JLabel(new ImageIcon("puzzlegame\\image\\login\\background.png"));
        label.setBounds(0,0,470,390);
        this.getContentPane().add(label);

        this.getContentPane().repaint();

        this.setVisible(true);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if(o == jButtonlogin) {
            String username = textField1.getText();
            String password = jPasswordField.getText();
            String code = rightCode;
            System.out.println(code);

            //创建一个User对象
            User userInfo = new User(username, password);
            System.out.println("用户输入的用户名为" + username);
            System.out.println("用户输入的密码为" + password);

            if (code.isEmpty()) {
                showJDialog("验证码不能为空");
            } else if (username.isEmpty() || password.isEmpty()) {
                //校验用户名和密码是否为空
                System.out.println("用户名或者密码为空");

                //调用showJDialog方法并展示弹框
                showJDialog("用户名或者密码为空");


            } else if (!code.equalsIgnoreCase(jLabel.getText())) {
                showJDialog("验证码输入错误");
            } else if (contains(userInfo)) {
                System.out.println("用户名和密码正确可以开始玩游戏了");
                //关闭当前登录界面
                this.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                new GameJFrame();
            } else {
                System.out.println("用户名或密码错误");
                showJDialog("用户名或密码错误");
            }
        } else if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
        } else if (e.getSource() == rightCode) {
            System.out.println("更换验证码");
            //获取一个新的验证码
            String code = CodeUtil.getCode();
            jLabel.setText(code);

        } else if(o == jButtonregister) {
            this.setVisible(false);
            new RegisterJFrame();
        }
    }

    private boolean contains(User userInfo) {
        for (User rightUser : allUsers) {
            if (userInfo.getUsername().equals(rightUser.getUsername()) && userInfo.getPassword().equals(rightUser.getPassword())) {
                //有相同的代表存在，返回true，后面的不需要再比了
                return true;
            }
        }
        //循环结束之后还没有找到就表示不存在
        return false;

    }


    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == jButtonlogin) {
            jButtonlogin.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按下.png"));
//            login = "登录按下";
//            initLogin();
        } else if (e.getSource() == jButtonregister) {
//            register = "注册按下";
            jButtonregister.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按下.png"));
//            initLogin();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object o = e.getSource();
        if (o == jButtonlogin) {
            jButtonlogin.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));

//            initLogin();
        } else if (o == jButtonregister) {
//            register = "注册按钮";
            jButtonregister.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));


//            initLogin();
        } else if (o == jLabel) {
            rightCode = CodeUtil.getCode();
            jLabel.setText(rightCode);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
