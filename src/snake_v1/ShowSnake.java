package snake_v1;

public class ShowSnake {
    public static void main(String[] args) {
        new SnakeViewControl(new SnakeModel(), 50);
    }
}/*

TODO:
 - ska inte kunna placera treats där ormen är generate random int not in snakeArray
 - flera stadier av spelet, meny i början, press any key to start, instruktioner,
 gameover meddelande, high score spara till fil, pause-funktion
 - byt allt till instanser av klassen Coordinates eller likn
 - ändra till paint component eller likn istället för att varje ruta är JPanel-objekt
 - ljud o musik
 - olika sorters treats (stora små, blinkade stora)
 - grafik

 */
