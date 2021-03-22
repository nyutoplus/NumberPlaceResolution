package viewer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class FieldKeyAdapter extends KeyAdapter {

    private JTextField field;

    private int maxnum;

    public FieldKeyAdapter(JTextField field, int maxnum) {
	this.field = field;
	this.maxnum = maxnum;
    }

    @Override
    public void keyTyped(KeyEvent e) {
	if (!Character.isDigit(e.getKeyChar())) {
	    e.consume();
	    return;
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
	int code = e.getKeyCode();
	String text = field.getText();
	text = text.equals("") ? "0" : text;
	if (code == KeyEvent.VK_PLUS || code == KeyEvent.VK_ADD) {
	    int tmp = Integer.parseInt(text) + 1;
	    tmp = tmp > maxnum ? maxnum : tmp;
	    field.setText(Integer.toString(tmp));
	    e.consume();
	    return;
	}
	if (code == KeyEvent.VK_MINUS || code == KeyEvent.VK_SUBTRACT) {
	    int tmp = Integer.parseInt(text) - 1;
	    tmp = tmp < 1 ? 1 : tmp;
	    field.setText(Integer.toString(tmp));
	    e.consume();
	    return;
	}

    }
}
