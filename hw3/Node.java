public class Node {
    public int data;
    public String name;
    public String surname;
    public Node right;
    public Node left;
    public Node(String name, String surname, int x){
        this.data = x;
        this.name = name;
        this.surname = surname;
        this.right = null;
        this.left = null;
    }
    public Node(){};
}
