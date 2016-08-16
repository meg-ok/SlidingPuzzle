// This file holds the main fxn and the frame GUI

package puzzle;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

class PuzzleFrame extends JFrame {

    public PuzzleFrame() {
	setTitle("Sliding Puzzle");
	setSize(600,600); // default size is 0,0

	PuzzleBoard puzzle_board = new PuzzleBoard(this);
	add(puzzle_board);
	puzzle_board.start();

	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
	JFrame frame = new PuzzleFrame();
	frame.setVisible(true);
    }
}
