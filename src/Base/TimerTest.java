package Base;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest {
	public static void main(String[] args) {
		ActionListener listener=new TimePrinter();
		
		Timer t=new Timer(10000,listener);
		t.start();
		
		JOptionPane.showMessageDialog(null, " Quit");
		System.exit(0);
	}
}

class TimePrinter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.out.println("AT the tone, time is" + now);
		Toolkit.getDefaultToolkit().beep();
	}

}