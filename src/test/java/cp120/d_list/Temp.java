package cp120.d_list;

import org.junit.Test;

public class Temp
{
    @Test
    public void traverseDemo1()
    {
        DList   list    = new DList();
        for ( int inx = 0 ; inx < 10 ; ++inx )
        {
            DNode   node = new DNode( new Integer( inx ) );
            list.addTail( node );
        }
        
        for ( DNode node = list.getHead() ;
              node != list ; 
              node = node.getNext()
            )
            System.out.print(  node.getData() + ", " );
        System.out.println();
    }
    
    @Test
    public void traverseDemo2()
    {
        DList   list    = new DList();
        for ( int inx = 0 ; inx < 10 ; ++inx )
        {
            DNode   node = new DNode( new Integer( inx ) );
            list.addTail( node );
        }
        
        DNode   node    = list.getHead();
        while (node != list )
        {
            System.out.print(  node.getData() + ", " );
            node = node.getNext();
        }
        System.out.println();
    }

}
