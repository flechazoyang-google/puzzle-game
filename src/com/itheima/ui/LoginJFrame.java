package com.itheima.ui;

import com.itheima.domain.User;
import com.itheima.util.CodeUtil;
import com.itheima.util.UserUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class LoginJFrame extends JFrame implements MouseListener {

    JButton jButtonlogin;
    JButton jButtonregister;
    JLabel jLabel = new JLabel();

    JTextField textField1 = new JTextField();
    JPasswordField jPasswordField = new JPasswordField();
    JTextField textField3 = new JTextField();

    String rightCode = CodeUtil.getCode();

    private ImageIcon res(String path) {
        return new ImageIcon(getClass().getResource("/" + path));
    }

    public LoginJFrame() {
        initJFrame();
        initLogin();
        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setTitle("登录");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    private void initLogin() {
        this.getContentPane().removeAll();

        JLabel label1 = new JLabel(res("image/login/用户名.png"));
        label1.setBounds(100, 120, 47, 17);
        this.getContentPane().add(label1);

        JLabel label2 = new JLabel(res("image/login/密码.png"));
        label2.setBounds(100, 180, 47, 17);
        this.getContentPane().add(label2);

        JLabel label3 = new JLabel(res("image/login/验证码.png"));
        label3.setBounds(100, 240, 47, 17);
        this.getContentPane().add(label3);

        textField1.setBounds(160, 115, 200, 25);
        this.getContentPane().add(textField1);

        jPasswordField.setBounds(160, 175, 200, 25);
        this.getContentPane().add(jPasswordField);

        textField3.setBounds(160, 235, 100, 25);
        this.getContentPane().add(textField3);

        jLabel.setText(rightCode);
        jLabel.setBounds(280, 235, 100, 25);
        jLabel.addMouseListener(this);
        this.getContentPane().add(jLabel);

        jButtonlogin = new JButton();
        jButtonlogin.setBounds(100, 280, 128, 47);
        jButtonlogin.setIcon(res("image/login/登录按钮.png"));
        jButtonlogin.setBorderPainted(false);
        jButtonlogin.setContentAreaFilled(false);
        this.getContentPane().add(jButtonlogin);
        jButtonlogin.addMouseListener(this);

        jButtonregister = new JButton(res("image/login/注册按钮.png"));
        jButtonregister.setBounds(250, 280, 128, 47);
        jButtonregister.setBorderPainted(false);
        jButtonregister.setContentAreaFilled(false);
        this.getContentPane().add(jButtonregister);
        jButtonregister.addMouseListener(this);

        JLabel label = new JLabel(res("image/login/background.png"));
        label.setBounds(0, 0, 470, 390);
        this.getContentPane().add(label);

        this.getContentPane().repaint();
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o == jButtonlogin) {
            String username = textField1.getText();
            String password = new String(jPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                showJDialog("用户名或者密码为空");
            } else if (!textField3.getText().equalsIgnoreCase(rightCode)) {
                showJDialog("验证码输入错误");
            } else if (checkLogin(username, password)) {
                this.setVisible(false);
                new GameJFrame();
            } else {
                showJDialog("用户名或密码错误");
            }

            rightCode = CodeUtil.getCode();
            jLabel.setText(rightCode);
        } else if (o == jButtonregister) {
            this.setVisible(false);
            new RegisterJFrame();
        } else if (o == jLabel) {
            rightCode = CodeUtil.getCode();
            jLabel.setText(rightCode);
        }
    }

    private boolean checkLogin(String username, String password) {
        List<User> users = UserUtil.loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == jButtonlogin) {
            jButtonlogin.setIcon(res("image/login/登录按下.png"));
        } else if (e.getSource() == jButtonregister) {
            jButtonregister.setIcon(res("image/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object o = e.getSource();
        if (o == jButtonlogin) {
            jButtonlogin.setIcon(res("image/login/登录按钮.png"));
        } else if (o == jButtonregister) {
            jButtonregister.setIcon(res("image/login/注册按钮.png"));
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
