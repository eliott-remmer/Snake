package snake_v1;

import java.util.ArrayList;

public class test{
    public static void main(String[] args) {
        ArrayList<Coordinates> snakeArray = new ArrayList<>();
        Coordinates head = new Coordinates(20, 0);
        Coordinates head2 = new Coordinates(20, 0);
        snakeArray.add(head);
        System.out.println(snakeArray.contains(head2));
    }
}
