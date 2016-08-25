// This file stores a Point

package puzzle;

public class Point {

    private int x; // Width
    private int y; // Height

    private int max_width;
    private int max_height;

    public Point(int x_axis, int y_axis, int width, int height)
    {
	x = x_axis;
	y = y_axis;
	max_width = width;
	max_height = height;
    }

    public int xAxis()
    {
	return x;
    }

    public int yAxis()
    {
	return y;
    }

    public boolean isValid()
    {
	return (x >= 0 && y >= 0 && x < max_width && y < max_height);
    }

}
