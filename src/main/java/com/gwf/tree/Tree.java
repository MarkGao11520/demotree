package com.gwf.tree;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by gaowenfeng on 2017/8/30.
 */
@Data
@NoArgsConstructor
public class Tree{

    @NoArgsConstructor
    class TreeNode{
        private Comparable key;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Comparable key) {
            this.key = key;
        }
    }

    TreeNode mRoot;
    private static Integer i = 0;

    public Tree(String serialize){
        preOrderUnSerialize(serialize);
    }


    /**
     * 前序遍历序列化
     * @param root
     * @param result
     */
    private void preOrderSerialize(TreeNode root,StringBuilder result){
        if(root==null)
            result
                    .append("#!");
        else{
            result
                    .append(root.key)
                    .append("!");
            preOrderSerialize(root.leftChild,result);
            preOrderSerialize(root.rightChild,result);
        }
    }

    public String preOrderSerialize(){
        StringBuilder result = new StringBuilder();
        preOrderSerialize(mRoot,result);
        return result.toString();
    }

    /**
     * 前序遍历反序列化
     * @param result
     * @return
     */
    private TreeNode preOrderUnSerialize(String[] result){
        if(result[i].equals("#")){
            i++;
            return null;
        }
        TreeNode root;
        root = new TreeNode(Integer.parseInt(result[i++]));
        root.leftChild = preOrderUnSerialize(result);
        root.rightChild = preOrderUnSerialize(result);
        return root;
    }

    public void preOrderUnSerialize(String serialize){
        i = 0;
        String[] values = serialize.split("!");
        if(values==null||values.length<1)
            throw new IllegalArgumentException();
        mRoot = preOrderUnSerialize(values);
    }

    /**
     * 按层打印
     */
    public void hierarchicalPriorityTraversal(){
        if(mRoot==null)
            return;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        TreeNode node = mRoot;
        TreeNode last = node,nlast = null;
        queue.add(node);
        while(!queue.isEmpty()){
            node = queue.remove();
            System.out.print(node.key+" ");
            if(node.leftChild!=null){
                queue.add(node.leftChild);
                nlast = node.leftChild;
            }
            if(node.rightChild!=null){
                queue.add(node.rightChild);
                nlast = node.rightChild;
            }
            if(last.key.compareTo(node.key)==0){
                System.out.println();
                last = nlast;
            }
        }
    }

    private TreeNode insert(TreeNode root,Comparable key){
        if(root==null){
            root = new TreeNode(key);
            return root;
        }
        if(root.key.compareTo(key)==0)
            throw new IllegalArgumentException("不允许插入相同的值");
        if(key.compareTo(root.key)<0)
            root.leftChild = insert(root.leftChild,key);
        else
            root.rightChild = insert(root.rightChild,key);

        return root;
    }

    public void insert(Integer key){
        mRoot = insert(mRoot,key);
    }


    public static void main(String[] args) {
       // Integer[] tree = {0,1,2,3,4,5,6,7,8,9,10};
        Integer[] tree = {0,2,1,4,3,6,7,11,9,8,10};
        Queue<Integer> queue = new ArrayDeque<Integer>();
        Integer last = 1,nlast = 0;
        queue.add(1);
        Integer i;
        while (!queue.isEmpty()){
            i = queue.remove();
            System.out.print(tree[i]+" ");
            if(2*i<tree.length){
                queue.add(2*i);
                nlast = 2*i;
            }
            if(2*i+1<tree.length){
                queue.add(2*i+1);
                nlast = 2*i+1;
            }
            if(last == i){
                System.out.println();
                last = nlast;
            }
        }
    }
}
