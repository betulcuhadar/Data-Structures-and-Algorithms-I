
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class UnixDirectory {

    public static TreeNode root;
    public static int sum;

    public void createDirectory(TreeNode node, String parentName, String childName, int parentSize, int childSize){
        TreeNode child = new TreeNode(childSize, childName);
        if(root == null){
            root = new TreeNode(parentSize, parentName);
            root.children.add(child);
            return;
        }
        if(node.name.equals(parentName)){
            node.children.add(child);
            return;
        }
        for(int i = 0; i < node.children.size(); i++){
            createDirectory(node.children.get(i), parentName, childName, parentSize, childSize);
        }
    }

    public void removeDirectory(TreeNode node, String dirName){
        if(node == null) return;
        if(node.name.equals(dirName)){
            node.name = "";
            node.size = 0;
            deleteLeafNodes(node);
            return;
        }
        for(int i = 0; i < node.children.size(); i++){
            if(root.children.get(i).name.equals(dirName)){
                deleteLeafNodes(root.children.get(i));
                root.children.remove(i);
                return;
            }
            removeDirectory(node.children.get(i), dirName);
        }
    }

    public void deleteLeafNodes(TreeNode node )
    {
        if(node == null) return;

        int i = 0;
        TreeNode temp = null;

        while(i < node.children.size())
        {
            temp = node.children.get(i);
            if(temp.children.size()==0)
            {
                // When node is leaf node
                node.children.remove(i);
            } else {
                deleteLeafNodes(temp);
                i++;
            }
        }
    }

    public void listDirectory(TreeNode node, String dirName){
        if(node == null) return;
        if(node.name.equals(dirName)) {
            for(int i = 0; i < node.children.size(); i++){
                System.out.print(node.children.get(i).name + " ");
            }
            return;
        }
        for(int i = 0; i < node.children.size(); i++){
            listDirectory(node.children.get(i), dirName);
        }
    }

    public void listDirectorySize(TreeNode node, String dirName){
        if(node == null) return;
        if(node.name.equals(dirName)) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(node);

            while (!q.isEmpty())
            {
                int n = q.size();

                // If this node has children
                while (n > 0)
                {
                    // Dequeue an item from queue and add it to variable "sum"
                    TreeNode p = q.peek();
                    q.remove();
                    sum += p.size;

                    // Enqueue all children of the dequeued item
                    for (int i = 0; i < p.children.size(); i++)
                        q.add(p.children.get(i));
                    n--;
                }
            }
            System.out.println(sum);
            return;
        }

        for(int i = 0; i < root.children.size(); i++) {
            listDirectorySize(root.children.get(i), dirName);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UnixDirectory ud = new UnixDirectory();
        String str;
        do{
            str = sc.next();
            if(str.equals("mkdir")){
                String parentName = sc.next();
                int parentSize = sc.nextInt();
                String childName= sc.next();
                int childSize = sc.nextInt();
                String endLine = sc.nextLine();
                ud.createDirectory(root, parentName, childName, parentSize, childSize);
            }
            else if(str.equals("ls")){
                String dirName = sc.next();
                String endLine = sc.nextLine();
                ud.listDirectory(root, dirName);
            }
            else if(str.equals("rmdir")){
                String dirName = sc.next();

                ud.removeDirectory(root, dirName);
                String endLine = sc.nextLine();
            }
            else if(str.equals("sizels")){
                sum = 0;
                String dirName = sc.next();
                String endLine = sc.nextLine();
                ud.listDirectorySize(root, dirName);
            }
        }while(!str.equals("quit"));
    }







}
