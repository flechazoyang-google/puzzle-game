package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //主游戏界面继承于JFrame类表示界面
    int[][] array = new int[4][4];
    int x0,y0;




    int step = 0;
    String picture="girl";
    int pictureIndex = getIndex();
    ArrayList<String> pictureList = new ArrayList<>();



    //功能菜单下的项目
    JMenuItem regameItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeGameItem = new JMenuItem("关闭游戏");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");


    //        关于我们菜单下的项目
    JMenuItem accountItem = new JMenuItem("创作者");


    public GameJFrame() {


//        初始化界面
        initJFrame();

//        初始化菜单
        initJMenuBar();

//        初始化数据
        initData();



//        初始化图片
        initImagin();

        //设置界面展示出来
        this.setVisible(true);

        game();
    }

    private void game() {
//        this.getContentPane();
//        getKeyListeners();
        this.addKeyListener(this);

    }

    //    初始化数据
    private void initData() {
        int[] arr = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                while(true){
                    int index = (int)(Math.random()*16);
                    if(index==0)
                    {
                        x0 = j;
                        y0 = i;
                    }
                    if(arr[index] == 1){
                        array[i][j] = index;
                        arr[index] = 0;
                        break;
                    }
                }

            }
        }
    }

//    初始化图片
    private void initImagin() {

//        删除隐藏容器中所有存在的图片
        this.getContentPane().removeAll();

        JLabel jLabel1 = new JLabel("步数：" + step);
        jLabel1.setBounds(50,30,100,20);
        this.getContentPane().add(jLabel1);


        if(gameIsOver()){
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame/image/win.png"));
            jLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(jLabel);
        }

        /*//创建一个ImageIcon对象
        ImageIcon icon = new ImageIcon("C:\\Users\\flechazo\\IdeaProjects\\basic-code\\puzzlegame\\image\\girl\\girl8\\5.jpg");

        //创建一个Jlable对象
        JLabel jLable = new JLabel(icon);

        //设置图片位置大小
        jLable.setBounds(0,0,105,105);

        //显示图片
        this.add(jLable);*/





        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {


                //创建一个Jlable对象
                JLabel jLable = new JLabel(new ImageIcon("puzzlegame\\image\\" + picture +"\\" + picture + pictureIndex + "\\" + array[i][j] + ".jpg"));

                //设置图片位置大小
                jLable.setBounds(105 * j + 83, 105 * i + 134, 105, 105);

                //        设置图片边框
                jLable.setBorder(new BevelBorder(BevelBorder.RAISED));


                //显示图片
                this.getContentPane().add(jLable);
            }
        }


        //创建一个Jlable对象
        JLabel jLable = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));

        //设置图片位置大小
        jLable.setBounds(39, 40, 508, 560);


        //显示图片
        this.getContentPane().add(jLable);

//        刷新界面
        this.getContentPane().repaint();

    }


//    初始化菜单栏
    private void initJMenuBar() {
        //设置菜单
        JMenuBar jmenuBar = new JMenuBar();

        //创建菜单栏的菜单对象
        JMenu funktionjMenu = new JMenu("功能");
        JMenu aboutjMenu = new JMenu("关于我们");

        JMenu changeImagejMenu = new JMenu("更换图片");

        funktionjMenu.add(changeImagejMenu);


        regameItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeGameItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        sportItem.addActionListener(this);
        animalItem.addActionListener(this);



//      将项目加入到菜单中
        changeImagejMenu.add(girlItem);
        changeImagejMenu.add(animalItem);
        changeImagejMenu.add(sportItem);

        funktionjMenu.add(regameItem);
        funktionjMenu.add(reloginItem);
        funktionjMenu.add(closeGameItem);

        aboutjMenu.add(accountItem);



//      添加菜单栏中的菜单
        jmenuBar.add(funktionjMenu);
        jmenuBar.add(aboutjMenu);

//      设置菜单栏
        this.setJMenuBar(jmenuBar);
    }

//    初始化界面
    private void initJFrame() {
        //设置界面大小
        this.setSize(603,680);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面标题
        this.setTitle("拼图小游戏1.0版本");

        //设置界面关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //取消默认的图片居中方式
        this.setLayout(null);

        pictureList.add("girl");
        pictureList.add("animal");
        pictureList.add("sport");

    }

    private int getIndex() {
        int index = 1;
        assert picture != null;
        index = switch (picture) {
            case "girl" -> (int) (Math.random() * 13 + 1);
            case "animal" -> (int) (Math.random() * 8 + 1);
            case "sport" -> (int) (Math.random() * 10 + 1);
            default -> index;
        };
        return index;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {



        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
//            System.out.println("上移");
//            int[][] array = new int[4][4];

            moving(0,1);

        } else if (code == KeyEvent.VK_DOWN) {
//            System.out.println("下移");
            moving(0,-1);
        }else if (code == KeyEvent.VK_LEFT) {
//            System.out.println("左移");
            moving(1,0);
        }else if (code == KeyEvent.VK_RIGHT) {
//            System.out.println("右移");
            moving(-1,0);
        } else if (code == 32){
//            System.out.println("yes");
            this.getContentPane().removeAll();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\" + picture + "\\" + picture + pictureIndex + "\\all.jpg"));
            jLabel.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jLabel);

            //创建一个Jlable对象
            JLabel jLable = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));

            //设置图片位置大小
            jLable.setBounds(39, 40, 508, 560);


            //显示图片
            this.getContentPane().add(jLable);

            this.setVisible(true);

            this.getContentPane().repaint();
        }
    }

    private boolean gameIsOver() {
        int[][] arr = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
                };
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(array[i][j] != arr[i][j])
                    return false;
            }
        }
        return true;
    }


    private void moving(int x,int y) {

/*        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }*/



        loop:for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(array[i][j] == 0){
                    if(i+y<0||i+y>=4||j+x<0||j+x>=4){
                        break loop;
                    }
                    array[i][j] = array[i+y][j+x];
                    array[i+y][j+x] = 0;
                    step ++;
                    break loop;
                }
            }
        }
        initImagin();

        /*for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
*/
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 32){
//            System.out.println(code);
            initImagin();
        }
        else if(code == 27){
//            System.out.println(code);
            array = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImagin();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();

        if (object == regameItem){
            System.out.println("重新开始");
            step = 0;
            initData();
            initImagin();
        }else if (object == reloginItem){
            //关闭当前界面
            this.setVisible(false);

            new LoginJFrame();

        }
        else if (object == closeGameItem){
            System.exit(0);
        }else if (object == accountItem){
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\aboutMe.jpg"));
            jLabel.setBounds(0,0,372,636);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(372,636);
            jDialog.setLocationRelativeTo(null);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (object == girlItem) {
            picture = "girl";
            pictureIndex = getIndex();
            initData();
            initImagin();
        }else if (object == animalItem){
            picture = "animal";
            pictureIndex = getIndex();
            initData();
            initImagin();
        }else if (object == sportItem){
            picture = "sport";
            pictureIndex = getIndex();
            initData();
            initImagin();
        }
    }
}
