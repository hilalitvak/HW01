public class Action {
    private Tile tile;
    private Direction direction;
    /// should we check if the move i valid???
    Action(Tile t1, Direction d1){
        tile=t1;
        direction = d1;
    }
    public Tile getTile(){
        return tile;
    }
    public Direction getDirection(){
        return direction;
    }
    public void setTile(Tile t1){
        tile=t1;
    }
    public void setDirection(Direction d1){
        direction=d1;
    }

    /**
     * @return a string contains tile and direction
     */
    public String toString(){
        return "Move <"+tile+"> <"+direction+">";
    }
}
