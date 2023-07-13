import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PriorityQueue {

    public static int[][] points;
    public static int[] distances;
    public static int size;

    private ArrayList<int[]> list = new ArrayList<>();

    public PriorityQueue(){
        points = new int[20][2];
        distances = new int[20];
        size = 0;
    }

    public void add(int x, int y){
        int distance = (x * x) + (y * y);
        int[] arr = new int[3];
//        int i;
//        if(distances[0] == 0) {
//            distances[0] = distance;
//            points[0][0] = x;
//            points[0][1] = y;
//            size++;
//            return;
//        }
//        for(i = distances.length - 2; i >= 0; i--){
//            if(distance > distances[i]){
//                distances[i + 1] = distances[i];
//                points[i + 1][0] = points[i][0];
//                points[i + 1][1] = points[i][1];
//            }
//            else break;
//        }
//        distances[i + 1] = distance;
//        points[i + 1][0] = x;
//        points[i + 1][1] = y;
//        size++;
        arr[0] = x;
        arr[1] = y;
        arr[2] = distance;
        list.add(arr);
    }

    public int[] poll(){
//        return points[][0] + "" + points[][1];
        int ind = 0;
        int max = Integer.MIN_VALUE;
        for (int[] arr : list) {
            int dis = arr[2];

            max = Math.max(max, dis);
            if(max == dis) ind = list.indexOf(arr);
        }

        int[] initialArray = list.remove(ind);
        return new int[] {initialArray[0], initialArray[1]};
    }

    public int size() {
        return list.size();
    }


    public static ArrayList findKClosest(int k, ArrayList<int[]> points){
        ArrayList<int[]> ans = new ArrayList();
        PriorityQueue pq = new PriorityQueue();


        for (int[] point : points) {
            pq.add(point[0], point[1]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (pq.size() > 0) {
            ans.add(0, pq.poll());
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue pq = new PriorityQueue();



        String str = sc.nextLine().replaceAll(" ", "");
        str = str.substring(2, str.length() - 1);






        String[] arr = str.split("(\\],\\[)|(,)|(\\[)|(])");


        ArrayList<int[]> points = new ArrayList();

        for (int i = 0; i < arr.length; i++){
            int x = Integer.valueOf(arr[i]);
            int y = Integer.valueOf(arr[i + 1]);

//            pq.add(x, y);
            points.add(new int[] {x, y});
            i++;
        }
        int k = sc.nextInt();
//        ArrayList<String> arr3 = findKClosest(k, points);
        ArrayList<int[]> closestPoints = findKClosest(k, points);
        for(int[] arr3: closestPoints){
            System.out.println(Arrays.toString(arr3));
        }

//        StringBuffer s = new StringBuffer("[");
//        for(String ss : arr3){
//
//            s.append(ss + ",");
//        }
//        s.deleteCharAt(s.length() - 1);
//        s.append("]");
//        System.out.println(arr3);


    }
}
