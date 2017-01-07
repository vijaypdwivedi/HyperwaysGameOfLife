package com.hyperway.production.hyperwaysgameoflife;

/**
 * Created by vijay on 19-07-2016.
 */
import android.content.Context;



public class Life {

    private  Grid _gridview;
    private int CELL_SIZE;
    //width is the number of cells wide
    protected int WIDTH;
    //height is the number of cells tall
    protected int HEIGHT;

    GridActivity gridActivity;

    private int[][] _lifeGrid;
    private Context _context;

    public Life(Context context, int width,int height) {
        CELL_SIZE = 16;
        WIDTH = width / CELL_SIZE;
        HEIGHT = height / CELL_SIZE;
        //WIDTH = 1280 / CELL_SIZE;
        //HEIGHT = 840 / CELL_SIZE;
        this._context = context;
        _lifeGrid = new int[HEIGHT][WIDTH];

        initializeGrid();

    }

    public int[][] getGrid() {

        return _lifeGrid;

    }

    //this method initilize the grid
    public void initializeGrid() {
        resetGrid(_lifeGrid);

        _lifeGrid[8][(WIDTH / 2) - 1] = 1;
        _lifeGrid[8][(WIDTH / 2) + 1] = 1;
        _lifeGrid[9][(WIDTH / 2) - 1] = 1;
        _lifeGrid[9][(WIDTH / 2) + 1] = 1;
        _lifeGrid[10][(WIDTH / 2) - 1] = 1;
        _lifeGrid[10][(WIDTH / 2)] = 1;
        _lifeGrid[10][(WIDTH / 2) + 1] = 1;

    }

    //this method is responsible to generate continuously grid
    public void generateNextGeneration() {
        int neighbours;
        int minimum = Integer.parseInt(PreferencesActivity
                .getMinimumVariable(this._context));
        int maximum = Integer.parseInt(PreferencesActivity
                .getMaximumVariable(this._context));
        int spawn = Integer.parseInt(PreferencesActivity
                .getSpawnVariable(this._context));

       minimum = 2;
        maximum = 3;
       spawn = 3;

        int[][] nextGenerationLifeGrid = new int[HEIGHT][WIDTH];



        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                neighbours = calculateNeighbours(h, w);


                    if (_lifeGrid[h][w] != 0) {
                        if ((neighbours >= minimum) && (neighbours <= maximum)) {
                            nextGenerationLifeGrid[h][w] = neighbours;
                        }

                    }  else  {
                        if (neighbours == spawn) {
                            nextGenerationLifeGrid[h][w] = spawn;

                        }
                    }

                }

        }

        copyGrid(nextGenerationLifeGrid, _lifeGrid);

    }


    private void resetGrid(int[][] grid) {
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                grid[h][w] = 0;

            }
        }
    }

    private int calculateNeighbours(int y, int x) {
        int total = 0;
        if (y - 1 >= 0 && y + 1 < HEIGHT && x - 1 >= 0 && x + 1 < WIDTH)
            for (int row = y - 1; row <= y + 1; row++) {
                for (int col = x - 1; col <= x + 1; col++) {
                    if (!(row == y && col == x) && _lifeGrid[row][col] != 0){
                        total++;

                    }
                }
            }

        return total;
    }

    private void copyGrid(int[][] source, int[][] destination) {

        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {

                destination[h][w] = source[h][w];

            }
        }


    }

    public void fill(int x, int y) {
        _lifeGrid[y][x] = 1;

    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getCellSize() {

        return CELL_SIZE;
    }
}
