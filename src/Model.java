import java.util.ArrayList;
import java.util.List;

/**
 * This is the Model class.
 * This class is responsible for the logic of our game and redraws the Viewer
 */
public class Model {
    private Viewer viewer;
    private LevelsReader levelsReader;
    private int[][] desktop;
    private int level;
    private int indexX;
    private int indexY;
    private int[][] arrayIndexies;
    private List<Integer> successLevels;
    private MusicReader musicReader;

    Model(Viewer viewer) {
        this.viewer = viewer;
        levelsReader = new LevelsReader();
        level = 1;
        desktop = levelsReader.getLevel(level);
        successLevels = new ArrayList<>();
        successLevels.add(level);
        initialization();
        musicReader = new MusicReader();
    }

    /**
     * This is the doAction method
     * This method is called in the controller in order to perform actions and verify the command.
     */
    public void doAction(String direction) {
        System.out.println(direction);
        stopthis:
        {
            switch (direction) {
                case "Left":
                    moveLeft();
                    break;
                case "Up":
                    moveUp();
                    break;
                case "Right":
                    moveRight();
                    break;
                case "Down":
                    moveDown();
                    break;
                case "Sound":
                    turnOnOrOffSounds();
                    break;
                case "Restart":
                    getNewLevel(level, false);
                    break stopthis;
                default:
                    System.out.println(Integer.valueOf(direction));
                    getNewLevel(Integer.valueOf(direction), false);
                    return;
            }
            viewer.update();
            check();
        }
    }


    /**
     * This is the initialization() method
     * This method is called in the controller in order to perform actions and verify the command.
     */
    private void initialization() {
        int counterFour = 0;
        int counterThree = 0;

         //Here we determine what position the player is on the map.
         //We als  determine how many boxes and crosses on the map.
        for(int i = 0; i < desktop.length; i++) {
            for(int j = 0; j < desktop[i].length; j++) {
                if(desktop[i][j] == 1) {
                    indexY = j;
                    indexX = i;
                }
                if(desktop[i][j] == 4) {
                    counterFour = counterFour + 1;
                }
                if(desktop[i][j] == 3) {
                    counterThree = counterThree + 1;
                }
            }
        }

        //Here we check that the horizon(counterFour) is the same as the crosses(counterThree).
        // If they are not equal, then we go to the next level.
        if(counterFour != counterThree){
            level++;
            desktop = levelsReader.getLevel(level);
            initialization();
        }


        arrayIndexies = new int[2][counterFour];
        int count = 0;
        for(int i = 0; i < desktop.length;i++) {
            for(int j = 0; j < desktop[i].length;j++) {
                if(desktop[i][j] == 4) {
                    arrayIndexies[0][count] = i;
                    arrayIndexies[1][count] = j;
                    count++;
                }
            }
        }

        for(int i = 0; i < arrayIndexies.length;i++) {
            for(int j = 0; j < arrayIndexies[i].length;j++) {
                System.out.print(arrayIndexies[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This is the getDesktop() method
     * This method method returns 2d array.
     */
    public int[][] getDesktop() {
        return desktop;
    }

    /**
     * This is the moveLeft() method
     * A method that implements a left shift in an array.
     */
    private void moveLeft() {
        if(desktop[indexX][indexY - 1] == 3) {
            if(desktop[indexX][indexY - 2] == 0  || desktop[indexX][indexY - 2] == 4) {
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }
        if(desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * This is the moveUp() method
     * A method that implements a up shift in an array.
     */
    private void moveUp() {
        if(desktop[indexX - 1][indexY] == 3) {
            if(desktop[indexX - 2][indexY] == 0  || desktop[indexX - 2][indexY] == 4) {
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }

        if(desktop[indexX - 1][indexY] == 0  || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * This is the moveRight() method
     * A method that implements a right shift in an array.
     */
    private void moveRight() {
        if(desktop[indexX][indexY + 1] == 3) {
            if(desktop[indexX][indexY + 2] == 0  || desktop[indexX][indexY + 2] == 4) {
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }

        if(desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * This is the moveDown() method
     * A method that implements a down shift in an array.
     */
    private void moveDown() {
        if(desktop[indexX + 1][indexY] == 3) {
            if(desktop[indexX + 2][indexY] == 0  || desktop[indexX + 2][indexY] == 4) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }


        if(desktop[indexX + 1][indexY] == 0  || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    /**
     * This is the check() method
     * A method where we check whether we won or not
     */
    private void check() {
        int t = 0;
        for (int j = 0; j < arrayIndexies[0].length; j++) {
            int x = arrayIndexies[0][t];
            int y = arrayIndexies[1][t];
            if (desktop[x][y] == 0) {
                desktop[x][y] = 4;
                break;
            }
            t++;
        }

        if(checkWin()){
            if(musicReader.isPlaying())
                musicReader.winLevel();
            viewer.showLabelWin();
            level = level + 1;
            getNewLevel(level,true);
        }
    }

    /**
     * This is the check() method
     * Here we turn off or on background music with musicReader
     */
    private void turnOnOrOffSounds() {
        try {
            if(musicReader.isPlaying()) {
                viewer.setTextOnJMenuMusicItem(true);
                musicReader.setBoolValueSound(false);
                musicReader.stopBackgroundMusic();
            } else{
                viewer.setTextOnJMenuMusicItem(false);
                musicReader.setBoolValueSound(true);
                musicReader.interrupt();
                musicReader.start();
            }
        }catch (IllegalThreadStateException illegalThreadStateException){
            System.out.println();
        }
    }

    /**
     * This is the getNewLevel() method
     * Here we can get a new level array from local or server
     */
    private void getNewLevel(int numOfLevel, boolean boolValueRestart){
        desktop = levelsReader.getLevel(numOfLevel);
        if(desktop != null){
            if(boolValueRestart) {
                if (!successLevels.contains(level))
                    viewer.addItem(String.valueOf(numOfLevel));
            }
            successLevels.add(level);
            level = numOfLevel;
            initialization();
            viewer.update();
        } else {
            level = 1;
            getNewLevel(level, false);
            viewer.update();
            return;
        }
    }

    /**
     * This is the checkWin() method
     * Here we turn off or on background music with musicReader
     */
    private boolean checkWin(){
        boolean flag = true;
        for(int z = 0; z<arrayIndexies[0].length; z++){
            int i = arrayIndexies[0][z];
            int x = arrayIndexies[1][z];
            if(desktop[i][x] == 4 || desktop[i][x] == 1){
                flag = false;
            }
        }
        return flag;
    }

}