import java.util.*;

public class Main {
    public static void main(String[] args) {
        String input = "Stage 1\n" +
                        "#####\n" +
                        "#OoP#\n" +
                        "#####\n" +
                        "=====\n" +
                        "Stage 2\n" +
                    "  #######\n" +
                    "###  O  ###\n" +
                    "#    o    #\n" +
                    "# Oo P oO #\n" +
                    "###  o  ###\n" +
                    " #   O  # \n" +
                    " ########";


        String[] stages = input.split("=====");
        for (String stage : stages) {
            Map map = new Map(stage.trim());
            map.printInfo();
        }
    }
}

class Map {
    private String name;
    private int[][] map;
    private int width;
    private int height;
    private int holes;
    private int balls;
    private int X;
    private int Y;

    public Map(String stage) {
        String[] lines = stage.split("\n");
        name = lines[0].trim();
        height = lines.length - 1;

        width = Arrays.stream(lines).skip(1).mapToInt(String::length).max().orElse(0);

        map = new int[height][width];
        
        for (int i = 0; i < height; i++) {
            char[] row = Arrays.copyOf(lines[i + 1].toCharArray(), width);
            for (int j = 0; j < width; j++) {
                char c = row[j];
                switch (c) {
                    case 'O':
                        map[i][j] = 1;
                        holes++;
                        break;
                    case 'o':
                        map[i][j] = 2;
                        balls++;
                        break;
                    case 'P':
                        map[i][j] = 3;
                        X = i;
                        Y = j;
                        break;
                    case '#':
                        map[i][j] = 4;
                        break;
                    default:
                        map[i][j] = 0;
                        break;
                }
            }
        }
    }

    public void printInfo() {
        System.out.println(name);
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (map[i][j]) {
                    case 1:
                        System.out.print('O');
                        break;
                    case 2:
                        System.out.print('o');
                        break;
                    case 3:
                        System.out.print('P');
                        break;
                    case 4:
                        System.out.print('#');
                        break;
                    default:
                        System.out.print(' ');
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("가로크기: " + width);
        System.out.println("세로크기: " + height);
        System.out.println("구멍의 수: " + holes);
        System.out.println("공의 수: " + balls);
        System.out.println("플레이어 위치: " + (X + 1) + "행 " + (Y + 1) + "열\n");


    }
}
