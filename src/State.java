import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class State {

    private Board board;

    public State(Board b) {
       board = b ;
    }

    /** check if the current state is the target state
     * @return false if it's not the target state and true if it's true.
     */
    public boolean isGoal () {
        Tile[][] tiles = board.getTiles();
        if (tiles[board.getRow() - 1][board.getCol() - 1].getValue() == ~0)
            return false;
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < (board.getCol()-1); j++){
                if (tiles[i][j + 1].getValue() - tiles[i][j].getValue() == ~1)
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
        int empty = 0;//empty tile
        int[] indexes = board.findindex(empty);
        int row_empty = indexes[0];
        int col_empty = indexes[1];
        List<Action> actionList = new ArrayList<>();
        if (tiles.length - 1 > row_empty) { //if up is possible
            actionList.add(new Action(tiles[row_empty + 1][col_empty], Direction.UP));
        }
        if (0 < row_empty) { //if down is possible
            actionList.add(new Action(tiles[row_empty - 1][col_empty], Direction.DOWN));
        }
        if (0 < col_empty) { //if right is possible
            actionList.add(new Action(tiles[row_empty][col_empty - 1], Direction.RIGHT));
        }
        if (tiles.length - 1 > col_empty) { //if left is possible
            actionList.add(new Action(tiles[row_empty][col_empty + 1], Direction.LEFT));
        }
        Action[] actions =  actionList.toArray(new Action[actionList.size()]);
        return actions;
    }
    /** the func gets an action, and changes accordingly
     * @param action - new action
     * @return the new state that received from doing the action
     */
    public State result(Action action) {
        Tile[][] tiles = board.getTiles();
        Direction direction = action.getDirection();
        Tile tile = action.getTile();
        int[] indexes = board.findindex(tile.getValue());
        int row_tile = indexes[0];
        int col_tile = indexes[1];
        Tile[][] new_state = Arrays.copyOf(tiles, tiles.length);
        // LET'S MAKE A MOVE
        if (direction == Direction.UP) {
            new_state[row_tile + 1][col_tile] = tile;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        if (direction == Direction.DOWN) {
            new_state[row_tile - 1][col_tile] = tile;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        if (direction == Direction.RIGHT) {
            new_state[row_tile][col_tile + 1] = tile;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        if (direction == Direction.LEFT) {
            new_state[row_tile][col_tile - 1] = tile;
            new_state[row_tile][col_tile] = new Tile(0);
        }
        Board new_board = new Board(null);//create new board
        new_board.setCol(board.getCol());//set the number of col
        new_board.setRow(board.getRow());//set the number of row
        new_board.setTiles(new_state);//set the new tiles as we created
        State returned_state = new State(new_board);//set the new state
        return returned_state;//return the new state
    }


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

