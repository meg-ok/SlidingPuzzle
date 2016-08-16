// This file holds the puzzle board

package puzzle;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PuzzleBoard extends JPanel implements ActionListener {

    //TODO option to have 8 or 15 puzzle
    final int Width = 3;
    final int Height = 3;
    final int Shuffles = 20;

    private int[][] board = new int[Height][Width];
    private int empty_x;
    private int empty_y;

    public PuzzleBoard(PuzzleFrame parent) {

	setFocusable(true); // Allows the board to capture keyboard input
	setLayout(new GridLayout(Height, Width));

	board = new int[Height][Width];
    }

    public void start()
    {
	makePuzzle();
	printPuzzle();
	drawPuzzle();

	for (int i=0;i<Shuffles;i++) {
	    randomShuffle();
	}
    }

    // overrides ActionListener fxn
    public void actionPerformed(ActionEvent e) {
	String command = e.getActionCommand();
	java.util.List<String> items = Arrays.asList(command.split("\\s*,\\s*"));
	swapPieces(Integer.valueOf(items.get(0)), Integer.valueOf(items.get(1)));
	return;
    }

    // function that will shuffle the puzzle to a valid start
    public void makePuzzle()
    {
	int number = 1;
	for (int i=0; i<Height; i++) {
	    for (int j=0; j<Width; j++) {

		// Don't fill in the last square!
		if ((i == Height-1) && (j == Width-1)) {
		    empty_y = Height - 1;
		    empty_x = Width - 1;
		    break;
		}

		board[i][j] = number;
		number = number + 1;
	    }
	}

	assert number == Width * Height - 1;

    }

    public void printPuzzle()
    {
	for (int i=0; i<Height; i++) {
	    for (int j=0; j<Width; j++) {
		System.out.println(board[i][j]);
	    }
	}
    }

    public void drawPuzzle()
    {
	removeAll();
	repaint();
	for (int i=0; i<Height; i++) {
	    for (int j=0; j<Width; j++) {
		PuzzlePiece piece = new PuzzlePiece(board[i][j], i, j);
		piece.addActionListener(this); // We want to know when it's clicked
		add(piece);
	    }
	}
	validate();
    }

    private void randomShuffle()
    {
	// find all possible blocks to move into empty space
	java.util.List<Point> possible_squares = new ArrayList<Point>();
	possible_squares.add(new Point(empty_x, empty_y-1, Width, Height));//above
	possible_squares.add(new Point(empty_x-1, empty_y, Width, Height));//left
	possible_squares.add(new Point(empty_x+1, empty_y, Width, Height));//right
	possible_squares.add(new Point(empty_x, empty_y+1, Width, Height));//below

	for (java.util.Iterator<Point> iterator = possible_squares.iterator(); iterator.hasNext();) {
	    Point point = iterator.next();
	    if (!point.isValid()) {
		iterator.remove();
	    }
	}

	// pick random valid point to swap with the empty slot
	Point random_valid_point = possible_squares.get(new Random().nextInt(possible_squares.size()));
	int random_x = random_valid_point.xAxis();
	int random_y = random_valid_point.yAxis();

	swapEmptyAndRedraw(random_y, random_x);
    }

    private void swapPieces(int y, int x)
    {
	// Above, below, or to the left or right of empty space
	if ( (y == empty_y-1 && x == empty_x) ||
	     (y == empty_y+1 && x == empty_x) ||
	     (y == empty_y && x == empty_x-1) ||
	     (y == empty_y && x == empty_x+1)) {
	    swapEmptyAndRedraw(y, x);

	    if (isFinished()) {
		wonGame();
	    }
	}
    }

    private void swapEmptyAndRedraw(int y, int x)
    {
	//update the board
	board[empty_y][empty_x] = board[y][x];
	board[y][x] = 0;
	empty_y = y;
	empty_x = x;

	// re-draw the board
	drawPuzzle();

    }

    private boolean isFinished() {
	int count = 1;
	boolean matches = true;
	for (int i=0; i<Height; i++) {
	    for (int j=0; j<Width; j++) {
		if (board[i][j] != count && count < Height*Width) {
		    matches = false; // Out of order; not done
		    break;
		}

		count = count + 1;
	    }
	}

	return matches;
    }

    private void wonGame()
    {
	removeAll();
	repaint();

	JLabel won = new JLabel();
	won.setText("Puzzle won!");
	add(won);

	validate();
    }

}
