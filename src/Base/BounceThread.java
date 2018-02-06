package Base;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BounceThread {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new BounceFrameThread();
				frame.setTitle("BounceThread");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class BallRunnable implements Runnable {
	private Ball ball;
	private BallComponent component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;

	@Override
	public void run() {

		try {// TODO Auto-generated method stub
			for (int i = 1; i <= STEPS; i++) {

				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block

		}

	}

	public BallRunnable(Ball aBall, BallComponent aComponent) {
		ball = aBall;
		component = aComponent;
	}
}

class BounceFrameThread extends JFrame {
	private BallComponent comp;

	public BounceFrameThread() {
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addBall();
			}
		});
		addButton(buttonPanel, "Close", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}

	public void addBall() {
		// TODO Auto-generated method stub
		Ball b = new Ball();
		comp.add(b);
		Runnable r = new BallRunnable(b, comp);
		Thread t = new Thread(r);
		t.start();
	}

	public void addButton(Container c, String text, ActionListener l) {
		// TODO Auto-generated method stub
		JButton button = new JButton(text);
		c.add(button);
		button.addActionListener(l);
	}
}
