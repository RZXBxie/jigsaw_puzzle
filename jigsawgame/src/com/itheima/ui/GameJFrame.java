package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.io.File;
import java.util.Random;

import static com.itheima.ui.Const.*;

public class GameJFrame extends JFrame {
	// 新建一个游戏界面
	public GameJFrame() {
		initJFrame();
		initJMenuBar();

		// 初始化图片
		initImage();

		// 让界面显示出来
		this.setVisible(true);
	}

	private void initImage() {
		String[] pictures = new String[16];
		for (int i = 1; i <= 16; i++) {
			String filename = "jigsawgame\\image\\girl\\girl1\\" + i + ".jpg";
			pictures[i - 1] = filename;
		}
		pictures[15] = null;
		for (int i = 0; i <= 15; ++i) {
			Random rand = new Random();
			int index = rand.nextInt(16);
			swap(pictures, index, i);
		}

		// 将图片添加到JFrame中
		// 先加载的图片在上方，后加载的图片在下方
		int number = 1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 新建一个ImageIcon对象，它用来存储图片
				String filename = pictures[i * 4 + j];
				ImageIcon icon = new ImageIcon(filename);

				// 新建一个JLabel对象，他可以管理图片和文字
				JLabel label = new JLabel(icon);

				// 设置图片的位置
				label.setBounds(j * GAME_PICTURE_WIDTH + 83, i * GAME_PICTURE_HEIGHT + 134, GAME_PICTURE_WIDTH, GAME_PICTURE_HEIGHT);

				// 给图片添加边框
				label.setBorder(new BevelBorder(BevelBorder.LOWERED));

				// 将图片添加到JFrame
				this.getContentPane().add(label);
				number++;
			}
		}
		ImageIcon bg = new ImageIcon("jigsawgame\\image\\background.png");
		JLabel label = new JLabel(bg);
		label.setBounds(40, 40, BACKGROUND_IMAGE_WIDTH, BACKGROUND_IMAGE_HEIGHT);
		this.getContentPane().add(label);
	}

	public void swap(String[] arr, int x, int y) {
		String tmp = arr[x];
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
	}
}
