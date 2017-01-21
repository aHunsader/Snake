package main;


import java.applet.Applet;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Board extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		ArrayList<Snake> snakes = new ArrayList<>();
		Count count = new Count();
		JFrame frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		snakes.add(new Snake());
		frame.add(snakes.get(0));
		frame.setVisible(true);
		String startMessage = "After pressing 'ok,' press any arrow to begin.";
		JOptionPane.showMessageDialog(null, startMessage);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int size = Snake.size;
				boolean speedY = snakes.get(count.getCount()).getList().get(0).getSpeedY() == 0;
				boolean speedX = snakes.get(count.getCount()).getList().get(0).getSpeedX() == 0;
				boolean oneBox = snakes.get(count.getCount()).getList().size() == 1;
				boolean start = snakes.get(count.getCount()).getStart();

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (snakes.get(count.getCount()).getList().get(0).getSpeedY() != 1) {
						if (speedX && speedY && oneBox && start) {
							snakes.get(count.getCount()).getList().get(0).setPosY();
						} else {
							int firstX = snakes.get(count.getCount()).getFirstX();
							int firstY = snakes.get(count.getCount()).getFirstY();
							int mod;
							mod = firstX % size;
							int a = mod;

							if (snakes.get(count.getCount()).getFirstSpeedX() > 0) {
								a = size - mod;
							} else if (snakes.get(count.getCount()).getFirstSpeedX() < 0) {
								a = -1 * mod - size;
							}

							for (Box rect : snakes.get(count.getCount()).getList()) {
								rect.addDirection(new Up(firstX + a, firstY));
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (snakes.get(count.getCount()).getList().get(0).getSpeedY() != -1) {
						if (speedX && speedY && oneBox && start) {
							snakes.get(count.getCount()).getList().get(0).setNegY();
						} else {
							int firstX = snakes.get(count.getCount()).getFirstX();
							int firstY = snakes.get(count.getCount()).getFirstY();
							int mod;
							mod = firstX % size;
							int a = mod;

							if (snakes.get(count.getCount()).getFirstSpeedX() > 0) {
								a = size - mod;
							} else {
								a = -1 * mod - size;
							}

							for (Box rect : snakes.get(count.getCount()).getList()) {
								rect.addDirection(new Down(firstX + a, firstY));
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (snakes.get(count.getCount()).getList().get(0).getSpeedX() != -1) {
						if (speedX && speedY && oneBox && start) {
							snakes.get(count.getCount()).getList().get(0).setPosX();
						} else {
							int firstX = snakes.get(count.getCount()).getFirstX();
							int firstY = snakes.get(count.getCount()).getFirstY();
							int mod;
							mod = firstY % size;
							int a = mod;

							if (snakes.get(count.getCount()).getFirstSpeedY() > 0) {
								a = size - mod;
							} else {
								a = -1 * mod - size;
							}

							for (Box rect : snakes.get(count.getCount()).getList()) {
								rect.addDirection(new Right(firstX, firstY + a));
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (snakes.get(count.getCount()).getList().get(0).getSpeedX() != 1) {
						if (speedX && speedY && oneBox && start) {
							snakes.get(count.getCount()).getList().get(0).setPosX();
						} else {
							int firstX = snakes.get(count.getCount()).getFirstX();
							int firstY = snakes.get(count.getCount()).getFirstY();
							int mod;
							mod = firstY % size;
							int a = mod;

							if (snakes.get(count.getCount()).getFirstSpeedY() > 0) {
								a = size - mod;
							} else {
								a = -1 * mod - size;
							}

							for (Box rect : snakes.get(count.getCount()).getList()) {
								rect.addDirection(new Left(firstX, firstY + a));
							}
						}
					}

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.remove(snakes.get(count.getCount()));
					count.addOne();
					snakes.add(new Snake());
					frame.add(snakes.get(count.getCount()));
					frame.setVisible(true);
			
				} else if (e.getKeyCode() == KeyEvent.VK_Z) {
					/*
					 * int lastSpeedX =
					 * snakes.get(count.getCount()).getLastSpeedX(); int
					 * lastSpeedY =
					 * snakes.get(count.getCount()).getLastSpeedY(); int lastX =
					 * snakes.get(count.getCount()).getLastX(); int lastY =
					 * snakes.get(count.getCount()).getLastY(); if (lastSpeedX >
					 * 0) { snakes.get(count.getCount()).addBox(lastX - size,
					 * lastY, 1, lastSpeedY); } if (lastSpeedX < 0) {
					 * snakes.get(count.getCount()).addBox(lastX + size, lastY,
					 * -1, lastSpeedY); } if (lastSpeedY > 0) {
					 * snakes.get(count.getCount()).addBox(lastX, lastY - size,
					 * 0, 1); } if (lastSpeedY < 0) {
					 * snakes.get(count.getCount()).addBox(lastX, lastY + size,
					 * 0, -1); } int length =
					 * snakes.get(count.getCount()).getList().size(); for
					 * (Direction dir :
					 * snakes.get(count.getCount()).getList().get(length -
					 * 2).getDir()) {
					 * snakes.get(count.getCount()).getList().get(length -
					 * 1).addDirection(dir); }
					 */
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}
}
