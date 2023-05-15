public class Tile {

    private int value;
    public Tile(int num){
        value= num;
    }
    public int getValue(){
        return value;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}