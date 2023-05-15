public class Node {
    private State state;
    private Action action;
    private Node parent;

    /** the func constructs a new node
     * @param fatherS - the father node
     * @param lastAction the last action accrued
     */
    public Node(Node fatherS, Action lastAction, State newState){
        action = lastAction;
        state = newState;
        if(fatherS != null)
            parent = fatherS;
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
     * @return
     */
    public int heuristicValue(){
        return 0;
    }
}
