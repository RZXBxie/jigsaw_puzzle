package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static com.itheima.ui.Const.*;

public class GameJFrame extends JFrame implements KeyListener {
	int[][] data = new int[4][4];
	// x和y记录了空白图片所在的位置
	int x = 0;
	int y = 0;
	// 新建一个游戏界面
	public GameJFrame() {
		initJFrame();
		initJMenuBar();

		// 初始化数据
		initData();

		// 初始化图片
		initImage();

		// 让界面显示出来
		this.setVisible(true);
	}

	private void initData() {
		int[] index = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

		for (int i = 0; i < index.length; ++i) {
			Random r = new Random();
			int idx = r.nextInt(index.length);
			if (index[idx] != 0) {
				swap(index, i, idx);
			} else {
				x = i / 4;
				y = i % 4;
			}
		}

		for (int i = 0; i < 4; ++i) {
			System.arraycopy(index, i * 4, data[i], 0, 4);
		}
	}

	private void initImage() {
		// 将图片添加到JFrame中
		// 先加载的图片在上方，后加载的图片在下方
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 新建一个ImageIcon对象，它用来存储图片
				String filename = "jigsawgame/image/girl/girl1/" + data[i][j] + ".jpg";
				ImageIcon icon = new ImageIcon(filename);

				// 新建一个JLabel对象，他可以管理图片和文字
				JLabel label = new JLabel(icon);

				// 设置图片的位置
				label.setBounds(j * GAME_PICTURE_WIDTH + 83, i * GAME_PICTURE_HEIGHT + 134, GAME_PICTURE_WIDTH, GAME_PICTURE_HEIGHT);

				// 给图片添加边框
				label.setBorder(new BevelBorder(BevelBorder.LOWERED));

				// 将图片添加到JFrame
				this.getContentPane().add(label);
			}
		}
		ImageIcon bg = new ImageIcon("jigsawgame\\image\\background.png");
		JLabel label = new JLabel(bg);
		label.setBounds(40, 40, BACKGROUND_IMAGE_WIDTH, BACKGROUND_IMAGE_HEIGHT);
		this.getContentPane().add(label);
	}

	public void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	private void initJMenuBar() {
		// 初始化菜单
		// 创建整个菜单对象
		JMenuBar menuBar = new JMenuBar();

		// 创建菜单上面的两个选项的对象（功能、关于我们）
		JMenu functionJMenu = new JMenu("功能");
		JMenu aboutJMenu = new JMenu("关于我们");

		// 创建功能列表
		JMenuItem restartItem = new JMenuItem("重新开始");
		JMenuItem reLoginItem = new JMenuItem("重新登录");
		JMenuItem closeItem = new JMenuItem("关闭游戏");
		JMenuItem accountItem = new JMenuItem("公众号");

		// 将相应功能添加到菜单中
		functionJMenu.add(restartItem);
		functionJMenu.add(reLoginItem);
		functionJMenu.add(closeItem);
		aboutJMenu.add(accountItem);
		menuBar.add(functionJMenu);
		menuBar.add(aboutJMenu);
		this.setJMenuBar(menuBar);
	}

	private void initJFrame() {
		// 设置界面的宽高
		this.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
		// 设置界面标题
		this.setTitle("拼图单机版 v1.0");
		// 设置界面置顶
		this.setAlwaysOnTop(true);
		// 设置界面居中
		this.setLocationRelativeTo(null);
		// 关闭游戏后直接结束虚拟机
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 取消默认布局，否则图片会被加载到正中间
		this.setLayout(null);
		// 给界面设置键盘监听事件
		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case LEFT -> System.out.println("向左移动");
			case UP -> System.out.println("向上移动");
			case RIGHT -> System.out.println("向右移动");
			case DOWN -> System.out.println("向下移动");
		}
	}
}
