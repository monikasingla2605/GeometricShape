package cp120.d_list;

/**
 * Encapsulation of a node in a doubly-linked list.
 * The state of a node includes three references:
 * <ul>
 * <li>A reference to the user data stored in the node.</li>
 * <li>A reference to the previous item in the list (if enqueued).</li>
 * <li>A reference to the next item in the list (if enqueued).</li>
 * </ul>
 * <p>
 * Definitions:
 * <blockquote>
 * <dl>
 * <dt>Enqueued node</dt>
 * <dd>A node is <em>enqueued</em> if its flink and blink point other items.
 * </dd>
 * <dt>Unenqueued node</dt>
 * <dd>A node is <em>unenqueued</em> if its flink and blink point to itself.
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Monika
 */
public class DNode
{
    /** Forward link; reference to next item */
    private DNode   flink;
    
    /** Backward link; reference to previous item */
    private DNode   blink;
    
    /** Data stored in this item */
    private Object  data;
    
    /**
     * Default constructor.
     * The node is constructed in the unenqueued state.
     */
    public DNode()
    {
        this( null );
    }
    
    /**
     * Constructs a new node with the given data.
     * The node is constructed in the unenqueued state.
     * @param data The given data.
     */
    public DNode( Object data )
    {
        flink = this;
        blink = this;
        this.data = data;
    }
    
    /**
     * Returns the next node. If the node is unenqueued the 
     * node itself is returned. If the node is at the tail of a DList, 
     * a reference to the DList is returned.
     * <p>
     * Sample usage:
     * <blockquote>
     * <pre>{@code
     * DNode next = list.getHead();
     * while ( next != list )
     * {
     *     System.out.println( next.getData() );
     *     next = node.getNext();
     * }
     * }</pre>
     * </blockquote>
     * 
     * @return The next node in the list, or itself if unenqueued.
     */
    public DNode getNext()
    {
        return flink;
    }
    
    
    /**
     * Returns the previous node. If the node is unenqueued the 
     * node itself is returned. If the node is at the head of a DList, 
     * a reference to the DList is returned.
     * <p>
     * Sample usage:
     * <blockquote>
     * <pre>{@code
     * DNode next = list.getTail();
     * while ( next != list )
     * {
     *     System.out.println( next.getData() );
     *     next = node.getPrevious();
     * }
     * }</pre>
     * </blockquote>
     * 
     * @return The previous node in the list, or itself if unenqueued.
     */
    public DNode getPrevious()
    {
        return blink;
    }
    
    /**
     * Adds a new node after this one. The new node must be in the 
     * unenqueued state; if it is not, an exception will be thrown.
     * 
     * @param node  The node to add.
     * 
     * @throws IllegalArgumentException if <em>node</em> is in the
     *         enqueued state.
     */
    public void addAfter( DNode node ) throws IllegalArgumentException
    {
        if ( node.isEnqueued() )
        {
            String  msg = "Attempt to enqueue already enqueued node: " + node;
            throw new IllegalArgumentException( msg );
        }
        node.flink = flink;
        node.blink = this;
        node.flink.blink = node;
        flink = node;
    }
    
    /**
     * Adds a new node before this one. The new node must be in the 
     * unenqueued state; if it is not, an exception will be thrown.
     * 
     * @param node  The node to add.
     * 
     * @throws IllegalArgumentException if <em>node</em> is in the
     *         enqueued state.
     */
    public void addBefore( DNode node ) throws IllegalArgumentException
    {
       blink.addAfter( node );
    }
    
    /**
     * Removes this node from the list it belongs to. If this node does
     * not belong to a list, no action is taken. This node is returned.
     * 
     * @return This node.
     */
    public DNode remove()
    {
        blink.flink = flink;
        flink.blink = blink;
        flink = this;
        blink = this;
        
        return this;
    }
    
    /**
     * The user data stored in this node.
     * @return The user data stored in this node.
     */
    public Object getData()
    {
        return data;
    }
    
    /**
     * Sets the user data in this node.
     * @param data The user data.
     */
    public void setData( Object data )
    {
        this.data = data;
    }
    
    /**
     * Returns true if this node is enqueued.
     * 
     * @return True if this node is enqueued, false otherwise.
     */
    public boolean isEnqueued()
    {
        return this != this.flink;
    }
}
