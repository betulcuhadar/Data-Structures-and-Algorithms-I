import java.util.*;

class Graph
{


    public List<List<Integer>> graph = new ArrayList<>();

    public List<List<Integer>> cycles = new ArrayList<>();
    public int cyclenumber;

    public Graph()
    {
        cyclenumber = 0;
        addAdjList();
    }
    private void addAdjList()
    {
        graph = new ArrayList<>();
        cycles = new ArrayList<>();
        for (int i = 0; i < 26; i++)
        {
            graph.add(i, new ArrayList<>());
            cycles.add(i, new ArrayList<>());
        }
    }


    public void dfsColor(int ind, int n, int[] color,int[] temp)
    //--------------------------------------------------------
    // Summary: Finding all cycles which consists of minimum 5 cities.
    // Precondition: ind is int, int ,color is int array and temp is int array.
    // Postcondition: All cycles which consists of minimum 5 cities are found.
    //--------------------------------------------------------
    {
        if (color[ind] == 2) return;

        if (color[ind] == 1)
        {
            List<Integer> adj = new ArrayList<Integer>();
            int num = n;
            adj.add(num);
            while (num != ind)
            {
                num = temp[num];
                adj.add(num);
            }
            cycles.set(cyclenumber, adj);
            cyclenumber++;
            return;
        }

        temp[ind] = n;
        // partially visited.
        color[ind] = 1;

        // simple dfs on graph
        for (int v : graph.get(ind))
        {
            // if it has not been visited previously
            if (v == temp[ind]) continue;
            dfsColor(v, ind, color, temp);
        }
        // completely visited.
        color[ind] = 2;
    }

    // add the edges to the graph
    public void addEdge(int source, int dest)
    //--------------------------------------------------------
    // Summary: Adding edges.
    // Precondition: source is int and dest is int.
    // Postcondition: Edges are added.
    //--------------------------------------------------------
    {
        graph.get(source).add(dest);
        graph.get(dest).add(source);
    }

    public void printValidCycles()
    //--------------------------------------------------------
    // Summary: Printing valid cycles.
    // Precondition: There is no parameter.
    // Postcondition: Valid cycles are printed.
    //--------------------------------------------------------
    {
        int count = 0;
        for (int i = 0; i < cyclenumber; i++)
        {
            List<Integer> list = cycles.get(i);
            //if cycles size greater than 5, print
            if(list.size() >= 5) {
                Collections.sort(list);
                System.out.println(list);
                for(int j = 0; j < list.size() - 1; j++){
                    System.out.print((char) (list.get(j) + 96) + "-");
                    count++;
                }
                System.out.print((char) (list.get(list.size() - 1) + 96));
            }
            System.out.println();
        }
        //if there is no valid cycle, print no cycle
        if(count == 0) System.out.println("No cycle");
    }



    /*
    Given two integer lists nums1 and nums2, return a list of their intersection.
    Elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once.
    Our memory can store up to 10000 elements.
    Therefore, nums1 cannot stored on the memory.
     */
    public static ArrayList<Integer> result = new ArrayList<Integer>();
    public static void findCount(List<Integer> nums1, List<Integer> nums2){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums1.size(); i++)
        {
            if(map.containsKey(nums1.get(i))) map.put(nums1.get(i), map.get(nums1.get(i))+1);
            else map.put(nums1.get(i), 1);
        }

        for(int i = 0; i < nums2.size(); i++)
        {
            if(map.containsKey(nums2.get(i)) && map.get(nums2.get(i)) > 0)
            {
                result.add(nums2.get(i));
                map.put(nums2.get(i), map.get(nums2.get(i))-1);
            }
        }
    }
    public static void split(ArrayList<Integer> nums1, ArrayList<Integer> nums2){
        for(int i = 0; i < nums1.size(); i++){
            List<Integer> subList = new ArrayList<>();
            if(i + 10000 < nums1.size()) {
                subList = nums1.subList(i, 10000 + i);
                i = 10000 + i;
            }
            else{
                subList = nums1.subList(i, nums1.size());
            }
            findCount(subList, nums2);
        }
    }




 
    public static void main(String[] args)
    {
        Graph g = new Graph();
        Scanner sc = new Scanner(System.in);
        String val = sc.nextLine();
        String[] cities = val.split("[^a-zA-Z0-9]");
        for(int i = 0; i < cities.length - 1; i++){
            System.out.println(cities[i]);
            g.addEdge(cities[i].charAt(0) - 96, cities[i + 1].charAt(0) - 96);
            i++;
        }

        int[] color = new int[26];
        int[] temp = new int[26];


        g.dfsColor(1, 0, color, temp);
        g.printValidCycles();
    }
}
