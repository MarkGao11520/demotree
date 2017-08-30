package com.gwf.tree;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by gaowenfeng on 2017/8/30.
 */
public class TreeTest {
    Tree tree = new Tree();
    Integer[] data = {0,2,1,4,3,6,7,11,9,8,10};

    @Before
    public void before(){
        for(int i=0;i<data.length;i++)
            tree.insert(data[i]);
    }

    @Test
    public void hierarchicalPriorityTraversalTest(){
        tree.hierarchicalPriorityTraversal();
    }

    @Test
    public void preOrderSerializeTest(){
        tree.hierarchicalPriorityTraversal();
        Tree tree1 = new Tree(tree.preOrderSerialize());
        tree1.hierarchicalPriorityTraversal();
    }

}
