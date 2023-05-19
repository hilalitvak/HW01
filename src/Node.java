public class Node {
    private State state;
    private Action action;
    private Node parent;

    /** the func constructs a new node
     * @param fatherS - the father node
     * @param lastAction the last action accrued
     */
    public Node(Node fatherS, Action lastAction, State newState){
        state = new State(newState.getBoard());
        if (fatherS != null)
            parent = new Node(fatherS.getParent(), fatherS.getAction(), fatherS.getState());
        if(lastAction != null)
            action = new Action(lastAction.getTile(), lastAction.getDirection());
    }

    public Action getAction() {
        return action;
    }

    public Node getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }

    /** the func expands the current node to the next action nodes
     * @return array of nodes that resulted out of the expansion
     */
    public Node[] expand(){
        Action[] actions = state.actions();
        Node[] nodes = new Node[actions.length];
        for (int i = 0; i < actions.length; i++){
            nodes[i] = new Node(this, actions[i], state.result(actions[i]));
        }
        return nodes;


    }


    /** the func checks the value of the heu the current state of the node
     *
     * @return num of tiles that are not in place
     */
    public int heuristicValue(){
       /**int num_not_in_place = 0;
        Tile[][] tiles = state.getBoard().getTiles();
        int row_size= state.getBoard().getRow();
        int col_size= state.getBoard().getCol();
        for(int i=0; i < row_size; i++){
            for(int j=0; j < col_size-1; j++){
                if(tiles[i][j].getValue() != i * row_size + j + 1)//if the tile is not in place
                    num_not_in_place++;//add 1 to the counter
            }
        }//for
        return num_not_in_place;**/
       return 0;
    }

}
