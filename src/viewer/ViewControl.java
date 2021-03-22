package viewer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.Resolver;
import util.NumberState;

public class ViewControl {
    private JFrame mainFrame;

    private JTextField[][] textfield;

    private JButton start;

    private JButton reset;

    private int numsize;

    private boolean resolving;

    private final Border defborder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);

    private final Border toponly = BorderFactory.createMatteBorder(4, 2, 2, 2, Color.BLACK);

    private final Border bottomonly = BorderFactory.createMatteBorder(2, 2, 4, 2, Color.BLACK);

    private final Border leftonly = BorderFactory.createMatteBorder(2, 4, 2, 2, Color.BLACK);

    private final Border rightonly = BorderFactory.createMatteBorder(2, 2, 2, 4, Color.BLACK);

    private final Border topleft = BorderFactory.createMatteBorder(4, 4, 2, 2, Color.BLACK);

    private final Border topright = BorderFactory.createMatteBorder(4, 2, 2, 4, Color.BLACK);

    private final Border bottomleft = BorderFactory.createMatteBorder(2, 4, 4, 2, Color.BLACK);

    private final Border bottomright = BorderFactory.createMatteBorder(2, 2, 4, 4, Color.BLACK);

    public ViewControl(int number) {
	numsize = number;
	int boxsize = (int) Math.sqrt(number);
	resolving = false;
	mainFrame = new JFrame("ナンプレ解くやつ");
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setLayout(null);
	mainFrame.setVisible(true);
	mainFrame.getContentPane().setBackground(Color.cyan);
	textfield = new JTextField[number][number];
	for (int i = 0; i < textfield.length; i++) {
	    for (int j = 0; j < textfield[i].length; j++) {
		textfield[i][j] = new JTextField("");
		textfield[i][j].addKeyListener(new FieldKeyAdapter(textfield[i][j], numsize));
		textfield[i][j].setHorizontalAlignment(JTextField.CENTER);
		int firsttmp = i % boxsize;
		int secondtmp = j % boxsize;
		if (firsttmp == 0) {
		    if (secondtmp == 0) {
			textfield[i][j].setBorder(topleft);
		    } else if (secondtmp == (boxsize - 1)) {
			textfield[i][j].setBorder(bottomleft);
		    } else {
			textfield[i][j].setBorder(leftonly);
		    }
		} else if (firsttmp == (boxsize - 1)) {
		    if (secondtmp == 0) {
			textfield[i][j].setBorder(topright);
		    } else if (secondtmp == (boxsize - 1)) {
			textfield[i][j].setBorder(bottomright);
		    } else {
			textfield[i][j].setBorder(rightonly);
		    }
		} else {
		    if (secondtmp == 0) {
			textfield[i][j].setBorder(toponly);
		    } else if (secondtmp == (boxsize - 1)) {
			textfield[i][j].setBorder(bottomonly);
		    } else {
			textfield[i][j].setBorder(defborder);
		    }
		}

		mainFrame.add(textfield[i][j]);
	    }
	}
	start = new JButton("計算");
	start.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
		    if (resolving) {
		    	start.setText("停止");
		    	NumberState state = new NumberState(numsize);
		    	for(int i=0;i<textfield.length;i++) {
		    		for(int j=0;j<textfield[i].length;i++) {
		    			String text = textfield[i][j].getText();
		    			text = text == "" ? "0" : text;
		    			state.setNum(i, j,Integer.parseInt(text));
		    		}
		    	}
		    	NumberState finalstate = Resolver.resolve(state);
		    	for(int i=0;i<textfield.length;i++) {
		    		for(int j=0;j<textfield[i].length;j++) {
		    			textfield[i][j].setText(Integer.toString(finalstate.getNum(i, j)));
		    		}
		    	}
		    	start.setText("計算");
		    } else {
		    	start.setText("計算");
		    	Resolver.cancel();
		    }
		}
	    }
	});
	mainFrame.add(start);
	reset = new JButton("リセット");
	reset.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
		    if (resolving) {
			Resolver.cancel();
		    }
		    resetArrayValue();
		}
	    }
	});
	mainFrame.add(reset);

	mainFrame.addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		componentResize();
	    }
	});
	mainFrame.setSize(1280, 720);

    }

    public void componentResize() {
	Container ct = mainFrame.getContentPane();
	double width = ct.getWidth();
	double height = ct.getHeight();
	int mag =(int)( (width - 20) / 1.35 <= (height - 20) ? ((width - 20) / 1.35) / numsize : (height - 20) / numsize);

	for (int i = 0; i < textfield.length; i++) {
	    for (int j = 0; j < textfield[i].length; j++) {
		textfield[i][j].setBounds(10 + (i * mag),10 + (j * mag),mag,mag);
		Font font = textfield[i][j].getFont();
		Font fonts = new Font(font.getName(), font.getStyle(), (int) (mag * 0.5d));
		textfield[i][j].setFont(fonts);
	    }
	}

	start.setBounds(20 + (textfield.length * mag), 10, mag * 3,  mag);
	Font startfont = start.getFont();
	Font startfonts = new Font(startfont.getName(), startfont.getStyle(), (int) (mag * 0.5d));
	start.setFont(startfonts);

	reset.setBounds(20 + (textfield.length * mag), 20 + mag,mag * 3, mag);
	Font resetfont = start.getFont();
	Font resetfonts = new Font(resetfont.getName(), resetfont.getStyle(), (int) (mag * 0.5d));
	reset.setFont(resetfonts);
    }

    public void resetArrayValue() {
	for (int i = 0; i < textfield.length; i++) {
	    for (int j = 0; j < textfield[i].length; j++) {
		textfield[i][j].setText("");
	    }
	}
    }
}
