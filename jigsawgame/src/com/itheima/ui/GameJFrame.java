package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static com.itheima.ui.Const.*;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
	// data记录了每张图片的下标
	int[][] data = new int[4][4];

	// x和y记录了空白图片所在的位置
	int x = 0;
	int y = 0;

	// step记录游戏步数
	int step = 0;

	// path记录了图片的位置
	String path = "jigsawgame/image/girl/girl1/";

	JMenuItem restartItem = new JMenuItem("重新开始");
	JMenuItem reLoginItem = new JMenuItem("重新登录");
	JMenuItem closeItem = new JMenuItem("关闭游戏");
	JMenuItem accountItem = new JMenuItem("联系方式");
	JMenuItem girl = new JMenuItem("美女");
	JMenuItem animal = new JMenuItem("动物");
	JMenuItem sport = new JMenuItem("运动");
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
		Random r = new Random();
		for (int i = 0; i < index.length; ++i) {
			int idx = r.nextInt(index.length);
			swap(index, i, idx);
		}

		for (int i = 0; i < index.length; ++i) {
			if (index[i] == 0) {
				x = i / 4;
				y = i % 4;
			}
		}

		for (int i = 0; i < 4; ++i) {
			System.arraycopy(index, i * 4, data[i], 0, 4);
		}

		// 重新开始游戏的时候，步数置为空
		step = 0;
	}

	private void initImage() {
		// 删除原有的全部图片
		this.getContentPane().removeAll();

		if (isWin()) {
			JLabel label = new JLabel(new ImageIcon(WIN_PICTURE_PATH));
			label.setBounds(203, 283, 197, 73);
			this.getContentPane().add(label);
			System.out.println("游戏胜利！");
		}
		loadImage();
	}

	private void loadImage() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 新建一个ImageIcon对象，它用来存储图片
				String filename = path + data[i][j] + ".jpg";
				ImageIcon icon = new ImageIcon(filename);

				// 新建一个JLabel对象，他可以管理图片和文字
				JLabel label = new JLabel(icon);

				// 设置图片的位置
				label.setBounds(j * GAME_IMAGE_WIDTH + 83, i * GAME_IMAGE_HEIGHT + 134, GAME_IMAGE_WIDTH, GAME_IMAGE_HEIGHT);

				// 给图片添加边框
				label.setBorder(new BevelBorder(BevelBorder.LOWERED));

				// 将图片添加到JFrame
				this.getContentPane().add(label);
			}
		}
		// 将图片添加到JFrame中
		// 先加载的图片在上方，后加载的图片在下方
		ImageIcon bg = new ImageIcon(BACK_GROUND_PICTURE_PATH);
		JLabel label = new JLabel(bg);
		label.setBounds(40, 40, BACKGROUND_IMAGE_WIDTH, BACKGROUND_IMAGE_HEIGHT);
		this.getContentPane().add(label);

		// 增加步数显示的功能
		label = new JLabel("步数：" + step);
		label.setBounds(50, 30, 100, 20);
		this.getContentPane().add(label);

		// 刷新一下界面
		this.getContentPane().repaint();
	}

	private boolean isWin() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; ++j) {
				if (data[i][j] != CORRECT_INDEX[i][j]) return false;
			}
		}
		return true;
	}

	private void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	private void initJMenuBar() {
		// 初始化菜单
		// 创建整个菜单对象
		JMenuBar menuBar = new JMenuBar();

		// 创建菜单上面的三个选项的对象（功能、关于我们、游戏说明）
		JMenu functionJMenu = new JMenu("功能");
		JMenu aboutJMenu = new JMenu("关于作者");
		JMenu instructionsJMenu = new JMenu("游戏说明");
		JMenu changeImage = new JMenu("更换图片");

		// 创建功能条目
		JMenuItem moveUp = new JMenuItem("上移：↑键");
		JMenuItem moveDown = new JMenuItem("下移：↓键");
		JMenuItem moveLeft = new JMenuItem("左移：←键");
		JMenuItem moveRight = new JMenuItem("右移：→键");
		JMenuItem viewOriginalImage = new JMenuItem("查看原图：a键");
		JMenuItem cheatCode = new JMenuItem("一键通关：c键");

		// 给相关条目绑定事件
		restartItem.addActionListener(this);
		reLoginItem.addActionListener(this);
		closeItem.addActionListener(this);
		accountItem.addActionListener(this);
		girl.addActionListener(this);
		animal.addActionListener(this);
		sport.addActionListener(this);

		// 将相应功能添加到菜单中
		functionJMenu.add(changeImage);
		functionJMenu.add(restartItem);
		functionJMenu.add(reLoginItem);
		functionJMenu.add(closeItem);

		aboutJMenu.add(accountItem);

		instructionsJMenu.add(moveUp);
		instructionsJMenu.add(moveDown);
		instructionsJMenu.add(moveLeft);
		instructionsJMenu.add(moveRight);
		instructionsJMenu.add(viewOriginalImage);
		instructionsJMenu.add(cheatCode);

		changeImage.add(girl);
		changeImage.add(animal);
		changeImage.add(sport);

		menuBar.add(functionJMenu);
		menuBar.add(aboutJMenu);
		menuBar.add(instructionsJMenu);
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

	// 按下不松显示完整图片
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == ALL) loadCompletePicture();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 如果不加这个判断，在游戏胜利后还是可以移动空白格，这是bug
		if (isWin()) {
			return;
		}
		int code = e.getKeyCode();
		switch (code) {
			case LEFT -> moveLeft();
			case UP -> moveUp();
			case RIGHT -> moveRight();
			case DOWN -> moveDown();
			case ALL -> initImage();
			case CHEAT_CODE -> {
				for (int i = 0; i < 4; ++i) {
					System.arraycopy(CORRECT_INDEX[i], 0, data[i], 0, 4);
				}
				initImage();
			}
		}
	}

	public void loadCompletePicture() {
		this.getContentPane().removeAll();
		JLabel label = new JLabel(new ImageIcon(path + "all.jpg"));
		label.setBounds(83, 134, COMPLETE_IMAGE_WIDTH, COMPLETE_IMAGE_HEIGHT);
		this.getContentPane().add(label);
		label = new JLabel(new ImageIcon(BACK_GROUND_PICTURE_PATH));
		label.setBounds(40, 40, BACKGROUND_IMAGE_WIDTH, BACKGROUND_IMAGE_HEIGHT);
		this.getContentPane().add(label);
		this.getContentPane().repaint();
	}

	public void moveLeft() {
		if (y == 0) {
			System.out.println("已到达左边界，无法继续移动");
			return;
		}
		data[x][y] = data[x][y - 1];
		data[x][y - 1] = 0;
		y--;
		step++;
		System.out.println("向左移动");
		initImage();
	}

	public void moveRight() {
		if (y == 3) {
			System.out.println("已到达右边界，无法继续移动");
			return;
		}
		data[x][y] = data[x][y + 1];
		data[x][y + 1] = 0;
		y++;
		step++;
		System.out.println("向右移动");
		initImage();
	}

	public void moveUp() {
		if (x == 0) {
			System.out.println("已到达上边界，无法继续移动");
			return;
		}
		data[x][y] = data[x - 1][y];
		data[x - 1][y] = 0;
		x--;
		step++;
		System.out.println("向上移动");
		initImage();
	}

	public void moveDown() {
		if (x == 3) {
			System.out.println("已到达下边界，无法继续移动");
			return;
		}
		data[x][y] = data[x + 1][y];
		data[x + 1][y] = 0;
		x++;
		step++;
		System.out.println("向下移动");
		initImage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == restartItem) {
			System.out.println("重新开始游戏");
			initData();
			initImage();
		} else if (obj == reLoginItem) {
			System.out.println("重新登录");
			// this.setVisible(false);
			// new LoginJFrame();
		} else if (obj == closeItem) {
			System.out.println("退出游戏");
			System.exit(0);
		} else if (obj == accountItem) {
			System.out.println("二维码");

			// 新建一个弹框，并添加图片
			JDialog dialog = new JDialog();
			JLabel label = new JLabel(new ImageIcon("jigsawgame\\image\\about.jpg"));
			label.setBounds(0, 0, ABOUT_IMAGE_WIDTH, ABOUT_IMAGE_HEIGHT);
			dialog.getContentPane().add(label);

			// 设置弹框大小
			dialog.setSize(400, 400);
			// 设置弹框置顶
			dialog.setAlwaysOnTop(true);
			// 设置弹框居中
			dialog.setLocationRelativeTo(null);
			// 设置“不关闭弹框就无法继续操作”
			dialog.setModal(true);
			// 让弹框可视化
			dialog.setVisible(true);
		} else if (obj == girl) {
			path = "jigsawgame/image/girl/girl" + getOffset(13) + "/";
			initData();
			initImage();
		} else if (obj == animal) {
			path = "jigsawgame/image/animal/animal" + getOffset(8) + "/";
			initData();
			initImage();
		} else if (obj == sport) {
			path = "jigsawgame/image/sport/sport" + getOffset(10) + "/";
			initData();
			initImage();
		}
	}

	private int getOffset(int seed) {
		Random r = new Random();
		return r.nextInt(seed) + 1;
	}
}
