// This file creates the style of puzzle button

package puzzle;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class PuzzlePiece extends JButton {

    private int piece_number;
    private int y;
    private int x;

    public PuzzlePiece(int number, int height, int width) {
	super(String.valueOf(number));
	if (number == 0) {
	    setVisible(false); // Number 0 means the empty spot
	} else {
	    setBackground(Color.WHITE);
	    setForeground(Color.BLUE);
	    setFont(new Font("Arial", Font.BOLD, 24));
	}

	y = height;
	x = width;

	setActionCommand(y+","+x);
    }
}
