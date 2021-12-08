package snake;

public class ShowSnake {
    public static void main(String[] args) {
        new SnakeViewControl(new SnakeModel(), 40);
    }
}/*

TODO:
 - ska inte gå att ändra direction innan start
 - implementera gränssnittet runnable när klassen måste ärva från en annan klass (JFrame)
 - enkla animeringar använd javax.swing.Timer
 - flera stadier av spelet, meny i början, press any key to start, instruktioner,
 gameover meddelande, high score spara till fil, pause-funktion
 - buggig isTailBitten vid snabba changeDirection
 - flytta inläggning i snakeArray från viewcontrol till model
 - ändra till paint component/swing.jcomponent.box.filler eller likn istället för att varje ruta är JPanel-objekt (awt snabbare)
 - ljud o musik
 - olika sorters treats (stora små, blinkade stora)
 - grafik


 */
