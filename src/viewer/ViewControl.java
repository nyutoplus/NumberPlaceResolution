package viewer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ViewControl {
	private JFrame mainFrame;

	private JTextArea[][] textarea;

	private int boxsize;

	public ViewControl(int number) {
		boxsize = number;
		mainFrame = new JFrame("ナンプレ解くやつ");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.cyan);
		textarea = new JTextArea[number][number];
		for(int i=0;i<textarea.length;i++) {
			for(int j=0;j<textarea[i].length;j++) {
				textarea[i][j] = new JTextArea("0");
				textarea[i][j].
				mainFrame.add(textarea[i][j]);
			}
		}
		mainFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				componentResize();
			}
		});
		mainFrame.setSize(1280,720);



	}


	public void componentResize() {
		Container ct = mainFrame.getContentPane();
		double width = ct.getWidth();
		double height = ct.getHeight();
		double mag = (width - 20) / 1.5 <= (height - 20) ? ((width - 20) / 1.5) / boxsize : (height - 20) / boxsize;

		for(int i=0;i<textarea.length;i++) {
			for(int j=0;j<textarea[i].length;j++) {
				textarea[i][j].setBounds((int)(10 + (i*mag)),(int)(10 + (j*mag)),(int)mag,(int)mag);
				Font font = textarea[i][j].getFont();
				Font fonts = new Font(font.getName(), font.getStyle(), (int)(mag*0.8));
				textarea[i][j].setFont(fonts);
			}
		}
	}
}
