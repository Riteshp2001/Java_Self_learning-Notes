import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class OneFile {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setTitle("One File Challenge - 2D Adventure");
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameLoop();

    }

}


@SuppressWarnings({"serial", "removal"})
class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 2;
    final int tileSize = originalTileSize * scale;
    final int tilesHorizontal = 32;
    final int tilesVertical = 24;
    final int screenWidth = tileSize * tilesHorizontal;
    final int screenHeight = tileSize * tilesVertical;
    final int totalItems = 7;

    final int FPS = 60;

    int keysCollected = 0;
    boolean endScreen = false;

    Thread gameLoop;
    Player player = new Player(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Image image = new Image(this);
    World world = new World(this);
    Item[] items = new Item[totalItems];

    int[][] currentWorld = world.world_center;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 0, 0));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);

    }

    public void startGameLoop() {

        gameLoop = new Thread(this);
        gameLoop.start();

        items[0] = new Item(this, 17, 4, image.key, world.world_left);
        items[1] = new Item(this, 5, 5, image.key, world.world_up_house_left);
        items[2] = new Item(this, 26, 5, image.key, world.world_up_house_right);
        items[3] = new Item(this, 30, 1, image.key, world.world_center);
        items[4] = new Item(this, 30, 1, image.key, world.world_right);
        items[5] = new Item(this, 15, 22, image.diamond, world.world_down);
        items[6] = new Item(this, 19, 9, image.speed_power_up, world.world_left);

    }

    public void stopGameLoop() {

        gameLoop.stop();
        gameLoop = null;

    }

    @Override
    public void run() {

        while (gameLoop != null) {

            update();
            repaint();
            wait(FPS);

        }

    }

    public void update() {

        if (keyHandler.upPressed) {

            player.direction = "up";

            if (checkCollision()) {

                player.y -= player.speed;

                checkItemCollision();

            }

        } else if (keyHandler.downPressed) {

            player.direction = "down";

            if (checkCollision()) {

                player.y += player.speed;

                checkItemCollision();

            }

        } else if (keyHandler.leftPressed) {

            player.direction = "left";

            if (checkCollision()) {

                player.x -= player.speed;

                checkItemCollision();

            }

        } else if (keyHandler.rightPressed) {

            player.direction = "right";

            if (checkCollision()) {

                player.x += player.speed;

                checkItemCollision();

            }

        }

        checkWorldChange();

        if (keysCollected == 5) {

            world.world_center[22][15] = 2;
            world.world_center[22][16] = 2;

        }

    }

    public void checkWorldChange() {

        int xGrid = (player.x + (tileSize / 2)) / tileSize;
        int yGrid = (player.y + (tileSize / 2)) / tileSize;

        //CENTER

        //center to up
        if ((xGrid == 15 && yGrid == 1 && currentWorld == world.world_center) || (xGrid == 16 && yGrid == 1 && currentWorld == world.world_center)) {

            currentWorld = world.world_up;
            player.y = screenHeight - (tileSize * 3);

        }

        //center to left
        if ((xGrid == 1 && yGrid == 11 && currentWorld == world.world_center) || (xGrid == 1 && yGrid == 12 && currentWorld == world.world_center)) {

            currentWorld = world.world_left;
            player.x = screenWidth - (tileSize * 3);

        }

        if ((xGrid == 30 && yGrid == 11 && currentWorld == world.world_center) || (xGrid == 30 && yGrid == 12 && currentWorld == world.world_center)) {

            currentWorld = world.world_right;
            player.x = (tileSize * 2);

        }


        //UP

        //up to center
        if ((xGrid == 15 && yGrid == 22 && currentWorld == world.world_up) || (xGrid == 16 && yGrid == 22 && currentWorld == world.world_up)) {

            currentWorld = world.world_center;
            player.y = tileSize * 2;

        }

        //up to up left house
        if ((xGrid == 4 && yGrid == 13 && currentWorld == world.world_up) || (xGrid == 5 && yGrid == 13 && currentWorld == world.world_up)) {

            currentWorld = world.world_up_house_left;
            player.x = (screenWidth / 2) - (tileSize / 2);
            player.y = screenHeight - (tileSize * 7);

        }

        //up left house to up
        if ((xGrid == 15 && yGrid == 18 && currentWorld == world.world_up_house_left) || (xGrid == 16 && yGrid == 18 && currentWorld == world.world_up_house_left)) {

            currentWorld = world.world_up;
            player.y = screenHeight - (tileSize * 10);
            player.x = (int) (tileSize * 4.5);

        }

        //up to up right house
        if ((xGrid == 26 && yGrid == 13 && currentWorld == world.world_up) || (xGrid == 27 && yGrid == 13 && currentWorld == world.world_up)) {

            currentWorld = world.world_up_house_right;
            player.x = (screenWidth / 2) - (tileSize / 2);
            player.y = screenHeight - (tileSize * 7);

        }

        //up right house to up
        if ((xGrid == 15 && yGrid == 18 && currentWorld == world.world_up_house_right) || (xGrid == 16 && yGrid == 18 && currentWorld == world.world_up_house_right)) {

            currentWorld = world.world_up;
            player.y = screenHeight - (tileSize * 10);
            player.x = (int) (tileSize * 26.5);

        }


        //LEFT

        //left to center
        if ((xGrid == 30 && yGrid == 11 && currentWorld == world.world_left) || (xGrid == 30 && yGrid == 12 && currentWorld == world.world_left)) {

            currentWorld = world.world_center;
            player.x = (tileSize * 2);

        }


        //RIGHT

        //right to center
        if ((xGrid == 1 && yGrid == 11 && currentWorld == world.world_right) || (xGrid == 1 && yGrid == 12 && currentWorld == world.world_right)) {

            currentWorld = world.world_center;
            player.x = screenWidth - (tileSize * 3);

        }


        //DOWN - END GAME

        //center to down - end
        if ((xGrid == 15 && yGrid == 22 && currentWorld == world.world_center && keysCollected == 5) || (xGrid == 16 && yGrid == 22 && currentWorld == world.world_center && keysCollected == 5)) {

            currentWorld = world.world_down;
            player.y = tileSize * 2;
            keysCollected = 0;

        }

    }

    public void checkItemCollision() {

        int xGrid = (player.x + (tileSize / 2)) / tileSize;
        int yGrid = (player.y + (tileSize / 2)) / tileSize;

        for (int x = 0; x < totalItems; x++) {

            if (items[x].xGrid == xGrid && items[x].yGrid == yGrid && items[x].world == currentWorld && !items[x].collected && items[x].img == image.key) {

                keysCollected++;
                items[x].collected = true;

            } else if (items[x].xGrid == xGrid && items[x].yGrid == yGrid && items[x].world == currentWorld && !items[x].collected && items[x].img == image.diamond) {

                items[x].collected = true;

                endScreen = true;

            } else if (items[x].xGrid == xGrid && items[x].yGrid == yGrid && items[x].world == currentWorld && !items[x].collected && items[x].img == image.speed_power_up) {

                items[x].collected = true;

                player.speed += 2;

            }

        }

    }

    public boolean checkCollision() {

        boolean allGood = true;
        boolean collision1 = false, collision2 = false;

        int leftX = player.x + player.hitbox.x;
        int rightX = player.x + player.hitbox.x + player.hitbox.width;
        int topY = player.y + player.hitbox.y;
        int bottomY = player.y + player.hitbox.y + player.hitbox.height;

        int leftGridX = leftX / tileSize;
        int rightGridX = rightX / tileSize;
        int topGridY = topY / tileSize;
        int bottomGridY = bottomY / tileSize;

        int tileNum1, tileNum2;

        if (Objects.equals(player.direction, "up")) {

            topGridY = (topY - player.speed) / tileSize;
            tileNum1 = currentWorld[topGridY][leftGridX];
            tileNum2 = currentWorld[topGridY][rightGridX];

            collision1 = isTileCollision(tileNum1);
            collision2 = isTileCollision(tileNum2);

            if (collision1 || collision2) {

                allGood = false;

            }

        } else if (Objects.equals(player.direction, "down")) {

            bottomGridY = (bottomY + player.speed) / tileSize;
            tileNum1 = currentWorld[bottomGridY][leftGridX];
            tileNum2 = currentWorld[bottomGridY][rightGridX];

            collision1 = isTileCollision(tileNum1);
            collision2 = isTileCollision(tileNum2);

            if (collision1 || collision2) {

                allGood = false;

            }

        } else if (Objects.equals(player.direction, "left")) {

            leftGridX = (leftX - player.speed) / tileSize;
            tileNum1 = currentWorld[topGridY][leftGridX];
            tileNum2 = currentWorld[bottomGridY][leftGridX];

            collision1 = isTileCollision(tileNum1);
            collision2 = isTileCollision(tileNum2);

            if (collision1 || collision2) {

                allGood = false;

            }

        } else if (Objects.equals(player.direction, "right")) {

            rightGridX = (rightX + player.speed) / tileSize;
            tileNum1 = currentWorld[topGridY][rightGridX];
            tileNum2 = currentWorld[bottomGridY][rightGridX];

            collision1 = isTileCollision(tileNum1);
            collision2 = isTileCollision(tileNum2);

            if (collision1 || collision2) {

                allGood = false;

            }

        }

        return allGood;

    }

    public boolean isTileCollision(int tileNum) {

        if (tileNum == 1 || tileNum == 3 || tileNum == 4 || tileNum == 6 || tileNum == 7) {

            return true;

        } else {

            return false;

        }

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBackground(g2d);
        drawItems(g2d);
        drawPlayer(g2d);
        drawStatistics(g2d);

        if (endScreen) {

            g2d.setColor(new Color(0, 0, 0));
            g2d.fillRect(0, 0, 100000, 100000);

            g2d.setColor(new Color(255, 255, 255));
            g2d.setFont(new Font("Courier New", Font.BOLD, 20));
            g2d.drawString("Good Job. You Win!", 100, 100);

            stopGameLoop();

        }

        g2d.dispose();

    }

    public void drawEntityTile(Color[][] image_, Graphics2D g2d, String direction, Entity entity) {

        for (int x = 0; x < originalTileSize; x++) {

            for (int y = 0; y < originalTileSize; y++) {

                if (Objects.equals(entity.direction, direction)) {

                    if (image_[y][x] == null) {

                        //null

                    } else if (image_ != null) {

                        g2d.setColor(image_[y][x]);
                        g2d.fillRect(entity.x + (x * scale), entity.y + (y * scale), scale, scale);

                    }

                }

            }

        }

    }

    public void drawDefaultTile(Color[][] image_, Graphics2D g2d, int x_, int y_) {

        for (int x = 0; x < originalTileSize; x++) {

            for (int y = 0; y < originalTileSize; y++) {

                if (image_[y][x] == null) {

                    //null

                } else if (image_ != null) {

                    g2d.setColor(image_[y][x]);
                    g2d.fillRect(x_ + (x * scale), y_ + (y * scale), scale, scale);

                }

            }

        }

    }

    public void loadWorld(int[][] world, Graphics2D g2d) {

        for (int y = 0; y < tilesVertical; y++) {

            for (int x = 0; x < tilesHorizontal; x++) {

                if (world[y][x] == 0) {

                    //null

                } else if (world[y][x] == 1) {

                    drawDefaultTile(image.brick, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 2) {

                    drawDefaultTile(image.grass, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 3) {

                    drawDefaultTile(image.sand, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 4) {

                    drawDefaultTile(image.tree, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 5) {

                    drawDefaultTile(image.water, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 6) {

                    drawDefaultTile(image.end_door_1, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 7) {

                    drawDefaultTile(image.end_door_2, g2d, x * tileSize, y * tileSize);

                } else if (world[y][x] == 8) {

                    drawDefaultTile(image.pathway_sand, g2d, x * tileSize, y * tileSize);

                }

            }

        }

    }

    public void loadWorldItems(Item[] items, Graphics2D g2d) {

        for (int x = 0; x < totalItems; x++) {

            if (currentWorld == items[x].world && !items[x].collected) {

                drawDefaultTile(items[x].img, g2d, items[x].xGrid * tileSize, items[x].yGrid * tileSize);

            }

        }

    }

    public void drawBackground(Graphics2D g2d) {

        loadWorld(currentWorld, g2d);

    }

    public void drawItems(Graphics2D g2d) {

        loadWorldItems(items, g2d);

    }

    public void drawPlayer(Graphics2D g2d) {

        if (player.direction == "up") {

            drawEntityTile(image.player_up, g2d, player.direction, player);

        }

        if (player.direction == "down") {

            drawEntityTile(image.player_down, g2d, player.direction, player);

        }

        if (player.direction == "left") {

            drawEntityTile(image.player_left, g2d, player.direction, player);

        }

        if (player.direction == "right") {

            drawEntityTile(image.player_right, g2d, player.direction, player);

        }

    }

    public void drawStatistics(Graphics2D g2d) {

        g2d.setColor(new Color(255, 255, 255));
        g2d.setFont(new Font("Courier New", Font.BOLD, 20));
        g2d.drawString("Keys : " + keysCollected, 10, 20);

    }

    public void wait(int fps) {

        try {

            Thread.sleep(1000 / FPS);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

}


class Entity {

    public int x, y;
    public int speed;
    public String direction;
    public Rectangle hitbox;

}


class Player extends Entity {

    GamePanel gamePanel;

    public Player(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        setDefaultValues();

    }

    public void setDefaultValues() {

        x = (gamePanel.screenWidth / 2) - (gamePanel.tileSize / 2);
        y = (gamePanel.screenHeight / 2) - (gamePanel.tileSize / 2);
        speed = 2;
        direction = "down";

        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 12;
        hitbox.width = 30;
        hitbox.height = 18;

    }

}


class KeyHandler implements KeyListener {

    boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {

            upPressed = true;

        } else if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {

            downPressed = true;

        } else if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {

            leftPressed = true;

        } else if (e.getKeyChar() == 'd' || e.getKeyCode() == 39) {

            rightPressed = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {

            upPressed = false;

        }

        if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {

            downPressed = false;

        }

        if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {

            leftPressed = false;

        }

        if (e.getKeyChar() == 'd' || e.getKeyCode() == 39) {

            rightPressed = false;

        }

    }

}


class Image {

    GamePanel gamePanel;

    Color c0 = null;

    public Image(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    Color c1 = new Color(255, 255, 0);
    Color c2 = new Color(255, 128, 0);
    Color c3 = new Color(0, 0, 0);

    Color[][] player_left = {
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c1, c3, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0, c0, c0},
            {c2, c2, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0, c0},
            {c2, c2, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c1, c1, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c1, c1, c1, c1, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c0, c0, c1, c1, c1, c1, c1, c1},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0}
    };

    Color[][] player_right = {
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c3, c1, c0},
            {c0, c0, c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c2, c2},
            {c0, c0, c1, c1, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c2, c2},
            {c0, c1, c1, c1, c1, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c0},
            {c1, c1, c1, c1, c1, c1, c0, c0, c1, c1, c1, c1, c1, c1, c1, c0},
            {c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0},
    };

    Color[][] player_up = {
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c0, c1, c1, c1, c0, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0},
            {c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0}
    };

    Color[][] player_down = {
            {c0, c0, c0, c0, c0, c0, c1, c1, c1, c1, c0, c0, c0, c0, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0},
            {c0, c0, c0, c1, c1, c3, c1, c1, c1, c1, c3, c1, c1, c0, c0, c0},
            {c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0},
            {c0, c0, c0, c1, c1, c1, c1, c2, c2, c1, c1, c1, c1, c0, c0, c0},
            {c0, c0, c0, c0, c1, c1, c1, c2, c2, c1, c1, c1, c0, c0, c0, c0},
            {c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0},
            {c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0},
            {c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1},
            {c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1},
            {c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0},
            {c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0},
            {c0, c0, c0, c1, c1, c1, c1, c1, c1, c1, c1, c1, c1, c0, c0, c0},
            {c0, c0, c0, c0, c0, c1, c1, c1, c1, c1, c1, c0, c0, c0, c0, c0}
    };

    Color c4 = new Color(190, 110, 0);
    Color c5 = new Color(90, 55, 0);

    Color[][] brick = {
            {c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4}
    };

    Color c6 = new Color(0, 220, 0);
    Color c7 = new Color(0, 150, 0);

    Color[][] grass = {
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c7, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c7, c6, c6, c6, c6, c6, c6, c6, c6, c6, c7, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c7, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c7, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c7, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c7, c6, c6, c6, c7, c6, c6, c6, c6, c6, c6, c6, c6, c7, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6}
    };

    Color c8 = new Color(200, 200, 0);
    Color c9 = new Color(120, 120, 0);

    Color[][] sand = {
            {c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9},
            {c9, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8},
            {c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8}
    };

    Color[][] pathway_sand = {
            {c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9},
            {c9, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8},
            {c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8, c8},
            {c8, c8, c9, c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8},
            {c8, c8, c8, c8, c8, c8, c8, c8, c8, c9, c8, c8, c8, c8, c8, c8}
    };

    Color[][] tree = {
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c7, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c7, c7, c6, c6, c6, c6, c6, c6, c6},
            {c6, c6, c7, c6, c6, c6, c7, c7, c7, c7, c6, c6, c7, c6, c6, c6},
            {c6, c6, c6, c6, c6, c7, c7, c7, c7, c7, c7, c6, c6, c6, c6, c6},
            {c6, c6, c6, c6, c7, c7, c7, c6, c6, c7, c7, c7, c6, c6, c6, c6},
            {c6, c6, c6, c6, c7, c7, c6, c6, c6, c6, c7, c7, c6, c6, c6, c6},
            {c6, c6, c6, c7, c7, c6, c6, c6, c6, c6, c6, c7, c7, c6, c6, c6},
            {c6, c6, c6, c7, c7, c6, c6, c6, c6, c6, c6, c7, c7, c6, c6, c6},
            {c6, c6, c7, c7, c7, c7, c6, c6, c6, c6, c7, c7, c7, c7, c6, c6},
            {c6, c6, c7, c7, c7, c7, c7, c7, c7, c7, c7, c7, c7, c7, c6, c6},
            {c6, c6, c6, c7, c7, c7, c7, c7, c7, c7, c7, c7, c7, c6, c6, c6},
            {c6, c6, c6, c6, c6, c6, c6, c5, c5, c6, c6, c6, c6, c6, c6, c6},
            {c6, c7, c6, c6, c6, c7, c6, c5, c5, c6, c6, c6, c6, c6, c7, c6},
            {c6, c6, c6, c6, c6, c6, c6, c5, c5, c6, c6, c6, c6, c6, c6, c6}
    };

    Color c10 = new Color(50, 230, 230);
    Color c11 = new Color(50, 170, 230);

    Color[][] water = {
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c11, c10, c10, c10, c10, c10, c10, c10, c11, c10},
            {c10, c11, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c11, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c11, c10, c10, c10},
            {c10, c10, c10, c10, c10, c11, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c11, c10, c10, c10, c10, c10, c10, c10, c10, c10, c11, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c11, c10, c10, c10, c10, c10, c10},
            {c10, c10, c11, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c11, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
            {c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10},
    };

    Color c00 = null;
    Color c12 = new Color(225, 225, 0);

    Color[][] key = {
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c12, c12, c12, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c12, c12, c12, c12, c12, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c12, c12, c12, c12, c12, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c12, c12, c12, c00, c12, c12, c12, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c12, c12, c00, c00, c00, c12, c12, c12, c12, c12, c12, c12, c12, c12, c12, c12},
            {c12, c12, c00, c00, c00, c12, c12, c12, c12, c12, c12, c12, c12, c12, c12, c12},
            {c12, c12, c12, c00, c12, c12, c12, c00, c00, c00, c00, c12, c12, c00, c12, c12},
            {c00, c12, c12, c12, c12, c12, c00, c00, c00, c00, c00, c12, c12, c00, c12, c12},
            {c00, c12, c12, c12, c12, c12, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c12, c12, c12, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
    };

    Color[][] diamond = {
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c10, c10, c10, c10, c10, c10, c10, c10, c00, c00, c00, c00},
            {c00, c00, c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00, c00, c00},
            {c00, c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00, c00},
            {c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00},
            {c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00},
            {c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00},
            {c00, c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00, c00},
            {c00, c00, c00, c10, c10, c10, c10, c10, c10, c10, c10, c10, c10, c00, c00, c00},
            {c00, c00, c00, c00, c10, c10, c10, c10, c10, c10, c10, c10, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c10, c10, c10, c10, c10, c10, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c10, c10, c10, c10, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c10, c10, c00, c00, c00, c00, c00, c00, c00},
            {c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00, c00},
    };

    Color c_ = c12;
    Color ca = new Color(255, 255, 255);

    Color[][] end_door_1 = {
            {c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, ca, ca, ca, ca, ca, ca, ca, c5, c4, c4, c4, c5},
            {c5, c5, c5, c5, ca, ca, ca, ca, ca, ca, ca, c5, c5, c5, c5, c5},
            {c4, c5, c4, c4, ca, ca, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, ca, ca, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, ca, ca, ca, ca, ca, ca, ca, c4, c4, c5, c4, c4},
            {c5, c5, c5, c5, ca, ca, ca, ca, ca, ca, ca, c5, c5, c5, c5, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, ca, ca, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, ca, ca, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, ca, ca, ca, ca, ca, ca, ca, c5, c4, c4, c4, c5},
            {c5, c5, c5, c5, ca, ca, ca, ca, ca, ca, ca, c5, c5, c5, c5, c5},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4}
    };

    Color[][] end_door_2 = {
            {c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c4, c4, c_, c_, c_, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c5, c_, c_, c_, c_, c_, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c_, c_, c_, c_, c_, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c_, c_, c_, c4, c_, c_, c_, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c_, c_, c4, c4, c4, c_, c_, c_, c_, c_, c_, c_, c_, c_, c_, c_},
            {c_, c_, c5, c5, c5, c_, c_, c_, c_, c_, c_, c_, c_, c_, c_, c_},
            {c_, c_, c_, c5, c_, c_, c_, c5, c4, c4, c4, c_, c_, c4, c_, c_},
            {c4, c_, c_, c_, c_, c_, c4, c5, c4, c4, c4, c_, c_, c4, c_, c_},
            {c4, c_, c_, c_, c_, c_, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5},
            {c5, c5, c_, c_, c_, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5, c5},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4},
            {c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4, c4, c5, c4, c4}
    };

    Color cb = new Color(255, 0, 0);

    Color[][] speed_power_up = {
            {cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, c0},
            {cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, c0},
            {c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0},
            {c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0},
            {c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0},
            {c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0},
            {c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0},
            {c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0},
            {c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0},
            {c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0},
            {c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0},
            {c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0},
            {c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0},
            {c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0},
            {cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, cb, cb, c0, c0, c0, c0},
            {cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, cb, c0, c0, c0, c0, c0},
    };

}


class World {

    //0 - null
    //1 - brick -> solid
    //2 - grass -> not solid
    //3 - sand -> solid
    //4 - tree -> solid
    //5 - water -> not solid
    //8 - pathway sand -> not solid

    GamePanel gamePanel;

    public World(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    int[][] world_center = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 4, 1, 5, 5, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 2, 2, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 4, 1, 5, 5, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 3, 5, 3, 3, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 3, 5, 5, 3, 2, 2, 2, 2, 3, 3, 3, 1},
            {1, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 2, 2, 3, 3, 3, 3, 2, 2, 2, 3, 3, 5, 3, 1},
            {1, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 5, 5, 3, 1},
            {1, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 5, 5, 3, 1},
            {1, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 3, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 3, 5, 5, 3, 3, 1},
            {1, 3, 3, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 1},
            {1, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4, 4, 3, 3, 2, 2, 3, 3, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 3, 3, 2, 2, 3, 3, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 3, 2, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 3, 2, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 3, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 4, 4, 4, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 3, 6, 7, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    int[][] world_up = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
            {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
            {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
            {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
            {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 8, 8, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
            {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 8, 8, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
            {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 8, 8, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
            {1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1},
            {1, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 1},
            {1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 8, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1},
            {1, 2, 1, 0, 1, 1, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 1, 1, 0, 1, 2, 1},
            {1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 8, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1},
            {1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1},
            {1, 2, 2, 1, 8, 8, 1, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 1, 8, 8, 1, 2, 2, 1},
            {1, 2, 2, 1, 8, 8, 1, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 1, 8, 8, 1, 2, 2, 1},
            {1, 2, 2, 1, 8, 8, 1, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 1, 8, 8, 1, 2, 2, 1},
            {1, 2, 2, 1, 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 1, 2, 2, 1},
            {1, 2, 2, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1, 2, 2, 1},
            {1, 2, 2, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1, 2, 2, 1},
            {1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 8, 8, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 8, 8, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] world_up_house_left = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    int[][] world_up_house_right = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    int[][] world_left = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 4, 2, 2, 4, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 4, 2, 2, 4, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 4, 2, 2, 2, 2, 2, 2, 4, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 4, 4, 2, 2, 2, 2, 4, 4, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 4, 4, 4, 4, 4, 4, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 5, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] world_right = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 1, 1, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 1, 1, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    int[][] world_down = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

}


class Item {

    int xGrid, yGrid;
    Color[][] img;
    GamePanel gamePanel;
    boolean collected;
    int[][] world;

    public Item(GamePanel gamePanel, int xGrid, int yGrid, Color[][] img, int[][] world) {

        this.xGrid = xGrid;
        this.yGrid = yGrid;
        this.gamePanel = gamePanel;
        this.img = img;
        this.collected = false;
        this.world = world;

    }

}