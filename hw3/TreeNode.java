
import java.util.ArrayList;

public class TreeNode {
    public int size;
    public String name;
    public ArrayList<TreeNode> children;

    public TreeNode(int size, String name){
        this.size = size;
        this.name = name;
        children = new ArrayList<>();
    }
}
