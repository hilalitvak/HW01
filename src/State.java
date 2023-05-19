import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class State {

    private Board board;

    public State(Board b) {
        board = new Board(null);
        board.setTiles(b.getTiles());
        board.setRow(b.getRow());
        board.setCol(b.getCol());
    }

    public Board getBoard() {
        return board;
    }

    /** check if the current state is the target state
     * @return false if it's not the target state and true if it's true.
     */
    public boolean isGoal () {
        Tile[][] tiles = board.getTiles();
        int row_size= board.getRow();
        int col_size= board.getCol();
        if (tiles[board.getRow() - 1][board.getCol() - 1].getValue() != 0)//if the last tile is not empty
            return false;
        for (int i = 0; i < row_size; i++) {//check if the tiles are in the right order
            for (int j = 0; j < (col_size-1); j++){
                if (!(tiles[i][j].getValue() == i * row_size + j + 1))
                    return false;
            }
        }
        return true;
    }

    /** checks if the actions up, down, right, left is possible and
     * if so adds to an array of possible action
     * @return array of possible actions
     */
    public Action[] actions () {
        Tile[][] tiles = board.getTiles();
        int col_size= board.getCol();
        int row_size= board.getRow();
        int empty = 0;//empty tile
        int[] indexes = board.findindex(empty);
        int row_empty = indexes[0];
        int col_empty = indexes[1];
        List<Action> actionList = new ArrayList<>();
        if (row_empty < row_size - 1) { //if up is possible
            actionList.add(new Action(tiles[row_empty + 1][col_empty], Direction.UP));//add the action to the list
        }
        if (0 < row_empty) { //if down is possible
            actionList.add(new Action(tiles[row_empty - 1][col_empty], Direction.DOWN));
        }
        if (0 < col_empty) { //if right is possible
            actionList.add(new Action(tiles[row_empty][col_empty - 1], Direction.RIGHT));
        }
        if (col_empty < col_size - 1) { //if left is possible
            actionList.add(new Action(tiles[row_empty][col_empty + 1], Direction.LEFT));//add the action to the list
        }
        Action[] actions =  actionList.toArray(new Action[actionList.size()]); //convert the list to array
        return actions;
    }


    /** the func gets an action, and changes accordingly
     * @param action - new action
     * @return the new state that received from doing the action
     */
    public State result(Action action) {
        Tile[][] tiles = board.getTiles();//the current state

        Direction direction = action.getDirection();//the direction of the action
        Tile tile = action.getTile();//the tile that we want to move
        int[] indexes = board.findindex(tile.getValue());//the indexes of the tile
        int row_tile = indexes[0];//the row of the tile
        int col_tile = indexes[1];//the col of the tile

        Tile[][] new_state = new Tile[tiles.length][];//the new state before the change
        for (int i = 0; i < tiles.length; i++) {//copy the current state to the new state
            new_state[i] = Arrays.copyOf(tiles[i], tiles[i].length);//copy the current state to the new state
        }
        // LET'S MAKE A MOVE
        if (direction.equals(Direction.UP)) {//if the direction is up
            new_state[row_tile - 1][col_tile] = new Tile(tile.getValue());//move the tile up
            new_state[row_tile][col_tile] = new Tile(0);//put 0 in the place of the tile
        }
        if (direction.equals(Direction.DOWN)){ //if the direction is down
            new_state[row_tile + 1][col_tile] = new Tile(tile.getValue());;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        if (direction.equals(Direction.RIGHT)) { //if the direction is right
            new_state[row_tile][col_tile + 1] = new Tile(tile.getValue());;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        if (direction.equals(Direction.LEFT)) {//if the direction is left
            new_state[row_tile][col_tile - 1] = new Tile(tile.getValue());;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        Board new_board = new Board(null);//create new board
        new_board.setCol(board.getCol());//set the number of col
        new_board.setRow(board.getRow());//set the number of row
        new_board.setTiles(new_state);//set the new tiles as we created
        return new State(new_board);//return the new state
    }//end of result


    @Override
public boolean equals(Object other) {
            if (!(other instanceof State)) {
                return false;
            }
            State otherState = (State) other;
            return board.equals(otherState.board);
        }

        @Override
        public int hashCode() {
            return board.hashCode();
        }
    }

