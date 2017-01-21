package main;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JComponent {

	private static final long serialVersionUID = -8682013698749933489L;
	public static final int size = 10;

	Count random = new Count();
	Random rand = new Random();
	
	private ArrayList<Box> boxes = new ArrayList<>();
	private ArrayList<Integer> xcoord = new ArrayList<>();
	private ArrayList<Integer> ycoord = new ArrayList<>();
	private int randx = (rand.nextInt(76) + 1) * size;
	private int randy = (rand.nextInt(36) + 1) * size;
	private boolean start = true;
	private boolean alreadyAlerted = true;

	public Snake() {
		Thread animationThread = new Thread(new Runnable() {
			public void run() {
				boxes.add(new Box(0, 0));
				xcoord.add(20);
				ycoord.add(20);

				while (true) {

					repaint();

					try {
						Thread.sleep(7);
					} catch (Exception e) {
					}
					String gameOver = "";
					if (boxes.size() > 1) {
						gameOver = "You died with " + boxes.size() + " circles. "
								+ "Press 'enter' to play again.";
					} else {
						gameOver = "You died with 1 circle. " + "Press 'enter to play again.";
					}
					if (alreadyAlerted == false) {
						JOptionPane.showMessageDialog(null, gameOver);
						break;
					}
				}
			}
		});

		animationThread.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D gg = (Graphics2D) g;

		int width = getWidth();
		int height = getHeight();
		/*
		 * if(boxes.size() > 1) { gameOver = "You died with " + boxes.size() +
		 * " circles. " + "Press 'enter' to restart."; } else { gameOver =
		 * "You died with " + boxes.size() + " circle. " +
		 * "Press 'enter' to restart."; }
		 */
		for (int i = 0; i < boxes.size(); i++) {
			xcoord.set(i, xcoord.get(i) + boxes.get(i).getSpeedX());
			ycoord.set(i, ycoord.get(i) + boxes.get(i).getSpeedY());

			if (xcoord.get(0) > width - size || xcoord.get(0) < 0) {
				alreadyAlerted = false;
				start = false;
				for (Box box : boxes) {
					box.cutSpeedX();
				}
			}

			if (ycoord.get(0) > height - size || ycoord.get(0) < 0) {
				alreadyAlerted = false;
				start = false;
				for (Box box : boxes) {
					box.cutSpeedY();
				}
			}

			gg.setColor(Color.BLACK);
			gg.drawOval(xcoord.get(i), ycoord.get(i), size, size);

			gg.setColor(Color.MAGENTA);
			gg.fillOval(xcoord.get(i), ycoord.get(i), size, size);
			for (int j = 0; j < boxes.get(i).getDir().size(); j++) {
				boolean xx = xcoord.get(i) == boxes.get(i).getDir().get(j).getX();
				boolean yy = ycoord.get(i) == boxes.get(i).getDir().get(j).getY();
				if (xx && yy && boxes.get(i).getDir().get(j) instanceof Down) {
					boxes.get(i).setNegY();
					boxes.get(i).setDir(j, new Direction(8000, 8000));
				} else if (xx && yy && boxes.get(i).getDir().get(j) instanceof Up) {
					boxes.get(i).setPosY();
					boxes.get(i).setDir(j, new Direction(8000, 8000));
				} else if (xx && yy && boxes.get(i).getDir().get(j) instanceof Right) {
					boxes.get(i).setPosX();
					boxes.get(i).setDir(j, new Direction(8000, 8000));
				} else if (xx && yy && boxes.get(i).getDir().get(j) instanceof Left) {
					boxes.get(i).setNegX();
					boxes.get(i).setDir(j, new Direction(8000, 8000));
				}

			}
		}
		gg.setColor(Color.RED);
		gg.fillOval(randx, randy, size, size);
		gg.setColor(Color.BLACK);
		gg.drawOval(randx, randy, size, size);
		if (xcoord.get(0) == randx && ycoord.get(0) == randy) {

			for (int u = 0; u < 5; u++) {
				int lastSpeedX = getLastSpeedX();
				int lastSpeedY = getLastSpeedY();
				int lastX = getLastX();
				int lastY = getLastY();
				if (lastSpeedX > 0) {
					addBox(lastX - size, lastY, 1, lastSpeedY);
				}
				if (lastSpeedX < 0) {
					addBox(lastX + size, lastY, -1, lastSpeedY);
				}
				if (lastSpeedY > 0) {
					addBox(lastX, lastY - size, 0, 1);
				}
				if (lastSpeedY < 0) {
					addBox(lastX, lastY + size, 0, -1);
				}
				int length = boxes.size();
				for (Direction dir : boxes.get(length - 2).getDir()) {
					boxes.get(length - 1).addDirection(dir);
				}
				boolean random = true;
				while (random) {
					randx = (rand.nextInt(76) + 1) * size;
					randy = (rand.nextInt(36) + 1) * size;
					for (int q = 0; q < boxes.size(); q++) {
						if (randx == xcoord.get(q) && randy == ycoord.get(q)) {
							randx = (rand.nextInt(77) + 1) * size;
							randy = (rand.nextInt(37) + 1) * size;
						} else {
							random = false;
						}
					}

				}
			}
		}
		for (int r = 2; r < boxes.size(); r++) {
			boolean firsta = xcoord.get(r) < xcoord.get(0) && xcoord.get(r) + size > xcoord.get(0);
			boolean firstb = xcoord.get(0) < xcoord.get(r) && xcoord.get(0) + size > xcoord.get(r);
			boolean seconda = ycoord.get(r) < ycoord.get(0) && ycoord.get(r) + size > ycoord.get(0);
			boolean secondb = ycoord.get(0) < ycoord.get(r) && ycoord.get(0) + size > ycoord.get(r);
			if ((firsta || firstb) && (seconda || secondb)) {
				for (Box box : boxes) {
					box.cutSpeedX();
					box.cutSpeedY();
				}
				alreadyAlerted = false;
				start = false;
			}
		}
	}

	public int getLastSpeedX() {
		return boxes.get(boxes.size() - 1).getSpeedX();
	}

	public int getLastSpeedY() {
		return boxes.get(boxes.size() - 1).getSpeedY();
	}

	public void addBox(int x, int y, int speedX, int speedY) {
		xcoord.add(x);
		ycoord.add(y);
		boxes.add(new Box(speedX, speedY));
	}

	public int getLastX() {
		return xcoord.get(xcoord.size() - 1);
	}

	public int getLastY() {
		return ycoord.get(ycoord.size() - 1);
	}

	public ArrayList<Box> getList() {
		return boxes;
	}

	public int getFirstX() {
		return xcoord.get(0);
	}

	public int getFirstY() {
		return ycoord.get(0);
	}

	public int getFirstSpeedX() {
		return boxes.get(0).getSpeedX();
	}

	public int getFirstSpeedY() {
		return boxes.get(0).getSpeedY();
	}

	public boolean getStart() {
		return start;
	}
}