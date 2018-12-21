package cp120.d_list;

import static org.junit.Assert.*;

import org.junit.Test;

public class DList2Test
{
    @Test
    public void testTail()
    {
        DList   list    = new DList();
        assertTrue( list.isEmpty() );
        assertEquals( list, list.getNext() );
        assertEquals( list, list.getPrevious() );
        
        int limit   = 10;
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            DNode   node    = new DNode( new Integer( inx ) );
            assertFalse( node.isEnqueued() );
            list.addTail( node );
            assertFalse( list.isEmpty() );
            assertTrue( node.isEnqueued() );
            assertEquals( node, list.getTail() );
            assertEquals( inx + 1, list.size() );
        }
        
        for ( int inx = limit - 1 ; inx >= 0 ; --inx )
        {
            DNode   node    = list.removeTail();
            assertFalse( node.isEnqueued() );
            assertEquals( inx, node.getData() );
        }
        
        assertTrue( list.isEmpty() );
    }

    @Test
    public void testHead()
    {
        DList   list    = new DList();
        assertTrue( list.isEmpty() );
        assertEquals( list, list.getNext() );
        assertEquals( list, list.getPrevious() );
        
        int limit   = 10;
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            DNode   node    = new DNode( new Integer( inx ) );
            assertFalse( node.isEnqueued() );
            list.addHead( node );
            assertFalse( list.isEmpty() );
            assertTrue( node.isEnqueued() );
            assertEquals( node, list.getHead() );
            assertEquals( inx + 1, list.size() );
        }
        
        for ( int inx = limit - 1 ; inx >= 0 ; --inx )
        {
            DNode   node    = list.removeHead();
            assertFalse( node.isEnqueued() );
            assertEquals( inx, node.getData() );
        }
        
        assertTrue( list.isEmpty() );
    }

    @Test
    public void testRemoveAll()
    {
        DList   list    = new DList();
        
        int limit   = 10;
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            for ( int jnx = 0 ; jnx < inx ; ++jnx )
                list.addTail( new DNode( null ) );
            assertEquals( inx, list.size() );
            list.removeAll();
            assertEquals( 0, list.size() );
            assertTrue( list.isEmpty() );
        }
    }
}
