package Snake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game
implements KeyListener{
    private Snake player;
    private Food food;
    private Graphics graphics;

    private JFrame window;
    public static final int width = 40;
    public static final int height = 40;
    public static final int dimension = 20;

    public Game() {
        window = new JFrame();

        player = new Snake();
        food = new Food(player);
        graphics = new Graphics(this);

        window.add(graphics);

        window.setTitle("Snake");
        window.setSize(width * dimension, height * dimension);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        graphics.state = "RUNNING";
    }

    public void update(){
        if(graphics.state == "RUNNING" || graphics.state == "GROW"){
            if (check_food_collision()){
                player.grow();
                food.random_spawn(player);
                graphics.state = "RUNNING";
            }
            else if(check_wall_collision() || check_self_collision()){
                end();
            }
            else{
                player.move();
            }
        }
    }

    public void end(){
        graphics.state = "END";
        player.move = "NOTHING";

    }

    private boolean check_wall_collision() {
        if (player.getX() < 0 || player.getX() >= width * dimension
                || player.getY() < 0 || player.getY() >= height * dimension) {
            return true;
        }
        return false;
    }

    private boolean check_food_collision(){
        if(player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension){
            graphics.state = "GROW";
            return true;
        }
        return false;
    }

    private boolean check_self_collision(){
        for (int i = 1; i < player.getBody().size(); i++){
            if (player.getX() == player.getBody().get(i).x
            && player.getY() == player.getBody().get(i).y){
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(graphics.state == "RUNNING"){
            if (keyCode == KeyEvent.VK_W){
                player.up();
            }
            else if (keyCode == KeyEvent.VK_S){
                player.down();
            }
            else if (keyCode == KeyEvent.VK_A){
                player.left();
            }
            else if (keyCode == KeyEvent.VK_D){
                player.right();
            }
        }
        else if (graphics.state == "END"){
            if (keyCode == KeyEvent.VK_R){
                player.DeletePlayer();
                graphics.state = "RUNNING";
                player.move = "NOTHING";
                Snake player = new Snake();
            }
        }
        else{
            this.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public Snake getPlayer(){
        return player; //13:15 part 2
    }
    public void setPlayer(){
        this.player = player;
    }

    public Food getFood(){
        return food;
    }

    public void setFood(){
        this.food = food;
    }

    public JFrame getWindow(){
        return window;
    }

    public void setWindow(){
        this.window = window;
    }
}
