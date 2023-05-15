import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private int row;
    private int col;

    /** build the board according to the string
     *
     * @param string_board
     */
    public Board(String string_board){
        if(string_board==null){
            return;
        }
        String[] rows= string_board.split("\\|");//divide by diff rows
        // Create a 2D array to represent the matrix
        row=rows.length;
        String[][] current = new String[row][];//split the rows by diff col
        for (int i = 0; i < row; i++) {
            current[i] = rows[i].split(" ");
        }
        col= current[0].length;
        tiles = new Tile[row][col];//construct the tiles
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if(current[i][j].equals("_")){
                    tiles[i][j] = new Tile(0);//define the _ in tiles
                }
                else{
                    Tile currentTile = new Tile(Integer.parseInt(current[i][j]));
                    tiles[i][j] = currentTile;
                }
            }
        }
    }

    /**
     * @return the row length of the board
     */
    public int getRow(){
        return row;
    }
    /**
     * @return the columns length of the board
     */
    public int getCol(){
        return col;
    }
    public Tile[][] getTiles() {
        return tiles;
    }
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    /**find the tile in the board according to it's uniq num
     * @param num
     * @return the coordinates of the tile (i, j)
     */
    public int[] findindex(int num){
        int[] indexes= new int[2];
        Tile currentTile= new Tile(num);
        for(int i=0; i <row; i++){
            for (int j=0; j < col; j++){
                if(tiles[i][j].equals(currentTile)){
                    indexes[0]=i;
                    indexes[1]=j;
                    return indexes;
                }

            }
        }
        return indexes;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
