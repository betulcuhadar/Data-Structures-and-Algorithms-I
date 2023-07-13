
import java.util.Scanner;

public class PlayerDatabase {

    public static Node root;
    public static int count;


    public void addPlayer(String name, String surname, int transferFee) {
        Node node1 = new Node(name, surname, transferFee);
        if(this.root == null){
            root = node1;
            return;
        }
        Node prev = null;
        Node temp = root;
        while (temp != null){
            if(temp.data > transferFee){
                prev=temp;
                temp=temp.left;
            }
            else if (temp.data < transferFee){
                prev=temp;
                temp=temp.right;
            }
        }
        if(prev.data > transferFee) prev.left = node1;
        else prev.right = node1;
    }

    public void removePlayer(Node node, String name, String surname){
        if(node == null) return;
        // if the node which will be deleted has no child
        if(node.name.equals(name) && node.surname.equals(surname) && node.left == null && node.right == null){
            node = null;
            return;
        }
        // if the node which will be deleted has no left child
        else if(node.name.equals(name) && node.surname.equals(surname) && node.left == null){
            Node prev = findPrev(this.root, node);
            node = node.right;
            if(prev != null){
                if(prev.data < node.data) prev.right = node;
                else if(prev.data > node.data) prev.left = node;
            }
            return;
        }
        // if the node which will be deleted has no right child
        else if(node.name.equals(name) && node.surname.equals(surname) && node.right == null){
            Node prev = findPrev(this.root, node);
            node = node.left;
            if(prev != null){
                if(prev.data < node.data) prev.right = node;
                else if(prev.data > node.data) prev.left = node;
            }
            return;
        }
        // if the node which will be deleted has two child
        else if(node.name.equals(name) && node.surname.equals(surname)){
            Node temp = findMin(node.right);
            Node prev = findPrev(this.root, node);
            Node tempRight = node.right;
            node.name = temp.name;
            node.surname = temp.surname;
            node.data = temp.data;
            node.right = tempRight;
            if(prev != null){
                if(temp.data < node.data) prev.right = node;
                else prev.left = node;
            }
            removePlayer(node.right, temp.name, temp.surname);
        }
        if(node.left != null) removePlayer(node.left, name, surname);
        else if(node.right != null) removePlayer(node.right, name, surname);
    }


    public Node findMin(Node node){
        Node temp = node;
        while(node.left != null){
            temp = node;
            node = node.left;
        }
        return temp;
    }

    public Node findPrev(Node root, Node node){
        if(root == null) return null;
        Node tempRight;
        Node tempLeft;
        if(root == node) return null;
        if(root.right != null && root.right == node) {
            return root;
        }
        if(root.left != null && root.left == node) {
            return root;
        }
        tempLeft = findPrev(root.left, node);
        tempRight = findPrev(root.right, node);
        return tempLeft != null ? tempLeft : tempRight;
    }

    public void searchByName(Node node, String name, String surname){
        if(node == null) return;
        if(node.name.equals(name) && node.surname.equals(surname)){
            System.out.println("True");
            return;
        }
        if(node.left != null) searchByName(node.left, name, surname);
        else if(node.right != null) searchByName(node.right, name, surname);
        else System.out.println("False");
    }

    public void searchByRange(Node node, int minFee, int maxFee){
        if(node == null) return;

        if(minFee < node.data) searchByRange(node.left, minFee, maxFee);

        if(minFee < node.data && node.data < maxFee) System.out.println(node.name + " " + node.surname);

        searchByRange(node.right, minFee, maxFee);
    }

    public void printAllPlayers(Node node){
        if(node == null) return;

        if(node.left != null) printAllPlayers(node.left);
        System.out.println(node.name + " " + node.surname + " " + node.data + ", ");
        if(node.right != null) printAllPlayers(node.right);
    }

    public void FindKSmallest(Node node, int k)
    {
        // base case
        if (node == null) return;

        // search in left subtree
        FindKSmallest(node.left, k);
        if(count < k){
            count++;
            if(count == k){
                System.out.println(node.name + " " + node.surname);
                return;
            }
            // search in right subtree
            FindKSmallest(node.right, k);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerDatabase pd = new PlayerDatabase();
        int n;
        do{
            n = sc.nextInt();
            String comma = "";
            if(n == 1) {
                for(int i = 0; i < 6; i++){
                    String name = sc.next();
                    String surname = sc.next();
                    int fee = sc.nextInt();
                    //if(i < 5) comma = sc.next();
                    pd.addPlayer(name, surname, fee);
                }
            }
            else if(n == 2){
                String name = sc.next();
                String surname = sc.next();
                pd.removePlayer(root, name, surname);
            }
            else if(n == 3){
                String name = sc.next();
                String surname = sc.next();
                pd.searchByName(root, name, surname);
            }
            else if(n == 4){
                int minFee = sc.nextInt();
                int maxFee = sc.nextInt();
                pd.searchByRange(root, minFee, maxFee);
            }
            else if(n == 5){
                pd.printAllPlayers(root);
            }
            else if(n == 6){
                count = 0;
                int k = sc.nextInt();
                pd.FindKSmallest(root, k);
            }
        }while(n != 7);
    }


}
