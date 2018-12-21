package cp120.d_list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DNodeTest
{
    @Test
    public void test()
    {
        checkConstructor();
        checkBuildForward();
        checkBuildBackward();
        tearDownHead();
        tearDownTail();
        tearDownRandom();
        checkIsEnqed();
        checkGetSetData();
        negativeExercise();
    }

    private void checkConstructor()
    {
        String  data    = "test data";
        DNode    node1   = new DNode();
        DNode    node2   = new DNode( data );
        
        assertEquals( node1.getNext(), node1 );
        assertEquals( node1.getPrevious(), node1 );
        assertNull( node1.getData() );
        
        assertEquals( node2.getNext(), node2 );
        assertEquals( node2.getPrevious(), node2 );
        assertEquals( data, node2.getData() );
    }
    
    private void checkBuildForward()
    {
        List<DNode>  nodeList    = new ArrayList<>();
        DNode        base        = new DNode( "base" );
        DNode        next        = base;
        nodeList.add( base );
        for ( int inx = 0 ; inx < 25 ; ++inx )
        {
            DNode    temp    = new DNode( "node " + inx );
            next.addAfter( temp );
            nodeList.add( temp );
            validateListForward( base, nodeList );
            next = temp;
        }
        
        next = base;
        while ( (next = next.getNext()) != base )
            System.out.println( next.getData() );
    }
    
    private void checkBuildBackward()
    {
        List<DNode>  nodeList    = new ArrayList<>();
        DNode        base        = new DNode( "base" );
        DNode        prev        = base;
        
        nodeList.add( base );
        for ( int inx = 0 ; inx < 25 ; ++inx )
        {
            DNode    temp    = new DNode( "node " + inx );
            prev.addBefore( temp );
            nodeList.add( temp );
            validateListBackward( base, nodeList );
            prev = temp;
        }
        
        prev = base;
        while ( (prev = prev.getPrevious()) != base )
            System.out.println( prev.getData() );
    }
    
    private void tearDownHead()
    {
        int         max     = 25;
        DNode        base    = new DNode( "base" );
        List<DNode>  list    = new ArrayList<>();
        
        list.add( base );
        DNode        next    = base;
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            DNode    temp    = new DNode( "Node " + inx );
            next.addAfter( temp );
            list.add( temp );
            validateListForward( base, list );
            next = temp;
        }
        
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            assertTrue( list.size() > 1 );
            DNode    node    = list.remove( 1 );
            DNode    temp    = node.remove();
            assertEquals( node, temp );
            assertFalse( node.isEnqueued() );
            validateListForward( base, list );
        }
        
        assertEquals( 1, list.size() );
    }
    
    private void tearDownRandom()
    {
        int         max     = 25;
        DNode        base    = new DNode( "base" );
        List<DNode>  list    = new ArrayList<>();
        
        list.add( base );
        DNode        next    = base;
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            DNode    temp    = new DNode( "Node " + inx );
            next.addAfter( temp );
            list.add( temp );
            validateListForward( base, list );
            next = temp;
        }
        
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            int size    = list.size();
            int elem    = (int)(Math.random() * (size - 1) ) + 1;
            assertTrue( list.size() > 1 );
            DNode    node    = list.remove( elem );
            DNode    temp    = node.remove();
            assertEquals( node, temp );
            assertFalse( node.isEnqueued() );
            validateListForward( base, list );
        }
        
        assertEquals( 1, list.size() );
    }
    
    private void tearDownTail()
    {
        int         max     = 25;
        DNode        base    = new DNode( "base" );
        List<DNode>  list    = new ArrayList<>();
        
        list.add( base );
        DNode        next    = base;
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            DNode    temp    = new DNode( "Node " + inx );
            next.addAfter( temp );
            list.add( temp );
            validateListForward( base, list );
            next = temp;
        }
        
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            int size    = list.size();
            assertTrue( size > 1 );
            DNode    node    = list.remove( size - 1 );
            DNode    temp    = node.remove();
            assertEquals( node, temp );
            assertFalse( node.isEnqueued() );
            validateListForward( base, list );
        }
        
        assertEquals( 1, list.size() );
    }
    
    private void checkIsEnqed()
    {
        DNode    base    = new DNode( "Base" );
        assertFalse( base.isEnqueued() );
        
        DNode    temp    = new DNode( "temp" );
        base.addAfter( temp );
        
        assertTrue( base.isEnqueued() );
        assertTrue( temp.isEnqueued() );
    }
    
    private void checkGetSetData()
    {
        DNode    node    = new DNode();
        assertNull( node.getData() );
        
        String  test0   = "Test 0";
        node = new DNode( test0 );
        assertEquals( test0, node.getData() );
        
        String  test1   = "String 1";
        node.setData( test1 );
        assertEquals( test1, node.getData() );
        
        String  test2   = "String 2";
        node.setData( test2 );
        assertEquals( test2, node.getData() );
        
        node.setData( null );
        assertNull( node.getData() );

    }
    
    private void validateListForward( DNode base, List<DNode> list )
    {
        int     size    = list.size();
        DNode    next    = base;
        
        DNode    head    = base.getNext();
        DNode    tail    = base.getPrevious();
        assert( head.getPrevious() == base );
        assert( tail.getNext() == base );
        
        for ( int inx = 0 ; inx < size ; ++inx )
        {
            DNode    temp    = list.get( inx );
            assert( temp == next );
            next = next.getNext();
        }
    }
    
    private void validateListBackward( DNode base, List<DNode> list )
    {
        int     size    = list.size();
        DNode    prev    = base;
        
        DNode    head    = base.getNext();
        DNode    tail    = base.getPrevious();
        assert( head.getPrevious() == base );
        assert( tail.getNext() == base );
        
        for ( int inx = 0 ; inx < size ; ++inx )
        {
            DNode    temp    = list.get( inx );
            assert( temp == prev );
            prev = prev.getPrevious();
        }
    }
    
    private void negativeExercise()
    {
        DList   list    = new DList();
        DNode   node    = new DNode();
        list.addTail(  node );
        try
        {
            list.addTail( node );
            fail( "Expected exception not thrown" );
        }
        catch ( IllegalArgumentException exc )
        {
            // getting here is a successful test
        }
    }
}
