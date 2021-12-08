package snake_v2;

public class Coordinates {
    int row;
    int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(r=" + row +", c=" + col+")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Coordinates)) {
            return false;
        }
        Coordinates c = (Coordinates) o;

        return row == c.row
                && col == c.col;
    }


}
