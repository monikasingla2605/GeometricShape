package cp120.d_list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class DListTest
{
    @Test
    public void test()
    {
        buildHead();
        buildTail();
        buildRandom1();
        buildRandom2();
        traverseList();
        removeAll();
    }

    private void buildHead()
    {
        List<DNode>  nodeList    = new ArrayList<>();
        DList       list        = new DList();
        
        assertTrue( list.isEmpty() );
        validateList( list, nodeList );
        
        for ( int inx = 0 ; inx < 25 ; ++inx )
        {
            DNode    node    = new DNode( "Node " + inx );
            list.addHead( node );
            nodeList.add( 0, node );
            assertFalse( list.isEmpty() );
            validateList( list, nodeList );
        }
        printList( list );
        
        while ( list.getHead() != list )
        {
            DNode    node    = list.removeHead();
            DNode    temp    = nodeList.remove( 0 );
            assertTrue( node == temp );
            validateList( list, nodeList );
        }
        printList( list );
        
        assertTrue( list.isEmpty() );
        assertTrue( nodeList.isEmpty() );
    }

    private void buildTail()
    {
        List<DNode>  nodeList    = new ArrayList<>();
        DList       list        = new DList();
        
        assertTrue( list.isEmpty() );
        validateList( list, nodeList );
        
        for ( int inx = 0 ; inx < 25 ; ++inx )
        {
            DNode    node    = new DNode( "Node " + inx );
            list.addTail( node );
            nodeList.add( node );
            assertFalse( list.isEmpty() );
            validateList( list, nodeList );
        }
        printList( list );
        
        while ( list.getTail() != list )
        {
            DNode    node    = list.removeTail();
            DNode    temp    = nodeList.remove( nodeList.size() - 1 );
            assertTrue( node == temp );
            validateList( list, nodeList );
        }
        printList( list );
        
        assertTrue( list.isEmpty() );
        assertTrue( nodeList.isEmpty() );
    }

    private void buildRandom1()
    {
        Random      randy       = new Random( 0 );
        List<DNode>  nodeList    = new ArrayList<>();
        DList       list        = new DList();
        
        assertTrue( list.isEmpty() );
        validateList( list, nodeList );
        
        DNode    first   = new DNode( "Node 0" );
        list.addAfter( first );
        nodeList.add( first );
        for ( int inx = 1 ; inx < 25 ; ++inx )
        {
            int     next    = randy.nextInt( nodeList.size() );
            DNode    target  = nodeList.get( next );
            DNode    node    = new DNode( "Node " + inx );
            
            target.addAfter( node );
            nodeList.add( next + 1, node );
            assertFalse( list.isEmpty() );
            validateList( list, nodeList );
        }
        printList( list );
        
        while ( list.getTail() != list )
        {
            int     next    = randy.nextInt( nodeList.size() );
            DNode    node    = nodeList.remove( next );
            node.remove();
            assertFalse( node.isEnqueued() );
            validateList( list, nodeList );
        }
        printList( list );
        
        assertTrue( list.isEmpty() );
        assertTrue( nodeList.isEmpty() );
    }

    private void buildRandom2()
    {
        Random      randy       = new Random( 0 );
        List<DNode>  nodeList    = new ArrayList<>();
        DList       list        = new DList();
        
        assertTrue( list.isEmpty() );
        validateList( list, nodeList );
        
        DNode    first   = new DNode( "Node 0" );
        list.addBefore( first );
        nodeList.add( first );
        for ( int inx = 1 ; inx < 25 ; ++inx )
        {
            int     next    = randy.nextInt( nodeList.size() );
            DNode    target  = nodeList.get( next );
            DNode    node    = new DNode( "Node " + inx );
            
            target.addBefore( node );
            nodeList.add( next, node );
            assertFalse( list.isEmpty() );
            validateList( list, nodeList );
        }
        printList( list );
        
        while ( list.getTail() != list )
        {
            int     next    = randy.nextInt( nodeList.size() );
            DNode    node    = nodeList.remove( next );
            node.remove();
            assertFalse( node.isEnqueued() );
            validateList( list, nodeList );
        }
        printList( list );
        
        assertTrue( list.isEmpty() );
        assertTrue( nodeList.isEmpty() );
    }
    
    private void validateList( DList list, List<DNode> nodes )
    {
        DNode    node    = list.getHead();
        int     size    = nodes.size();
        assertEquals( size, list.size() );
//        System.out.println( size + ", " + list.size() );
        
        for ( int inx = 0 ; inx < size ; ++inx )
        {
            DNode    temp    = nodes.get( inx );
            assertTrue( temp == node );
            node = node.getNext();
        }
        assertTrue( node == list );
    }
    
    private void traverseList()
    {
        String[]        data        = { "A", "B", "C", "D", "E", "F", "G" };
        List<String>    dataList    = new ArrayList<>();
        DList           list        = new DList();
        
        validateTraverseList( list, dataList );
        for ( String str : data )
        {
            list.addTail( new DNode( str ) );
            dataList.add( str );
            validateTraverseList( list, dataList );
        }
    }
    
    private void validateTraverseList( DList list, List<String> values )
    {
        int count   = 0;
        for ( DNode node = list.getHead() ;
              node != list ;
              node = node.getNext()
            )
        {
            String  exp = values.get( count );
            Object  act = node.getData();
            assertEquals( act, exp );
            count++;
        }
        assertEquals( values.size(), count );
        
        count = 0;
        int     size    = values.size();
        for ( DNode node = list.getTail() ;
              node != list ;
              node = node.getPrevious()
            )
        {
            count++;
            String  exp = values.get( size - count );
            Object  act = node.getData();
            assertEquals( act, exp );
        }
        assertEquals( values.size(), count );       
    }
    
    private void removeAll()
    {
        List<DNode> nodeList    = new ArrayList<>();
        DList       list        = new DList();
        
        assertTrue( list.isEmpty() );
        validateList( list, nodeList );
        
        for ( int inx = 0 ; inx < 25 ; ++inx )
        {
            DNode    node    = new DNode( "Node " + inx );
            list.addTail( node );
            nodeList.add( node );
            assertFalse( list.isEmpty() );
            validateList( list, nodeList );
        }
        printList( list );
        list.removeAll();
        assertTrue( list.isEmpty() );
    }
    
    private void printList( DList list )
    {
        System.out.print( "Nodes: " );
        for ( DNode node = list.getHead(); 
              node != list  ;
              node = node.getNext()
            )
            System.out.print( node.getData() + " " );
        System.out.println();
    }
}
