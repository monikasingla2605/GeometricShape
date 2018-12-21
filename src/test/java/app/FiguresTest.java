package app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.junit.Test;

import cp120.assignments.geo_shape.GeoPlane;

// TODO: Auto-generated Javadoc
/**
 * A test class for Figures.java. This test facilitates testing
 * of the raster stored in Figures' GeoPlane. It can be run as an
 * application, or as a JUnit test case. The specifics of the 
 * operation are determined by the "image mode" property, which may
 * be any of the following:
 * 
 * <dl>
 * <dt>"save"</dt>
 * <dd>Display the Figures dialog, then obtain the dialog's raster;
 *     save the raster in a binary file for later testing. Save mode can
 *     only be used when the test is run as an application; the mode
 *     is determined by examining the first command line argument.
 * </dd>
 * <dt>"test"</dt>
 * <dd>Display the Figures dialog, then obtain the dialog's raster.
 *     Compare the dialog's raster to a previously saved raster;
 *     for a successful test, the rasters must be equal. When run
 *     as a JUnit test, "test" mode is always used.
 * </dd>
 * <dt>null or any other string</dt>
 * <dd>No raster processing is executed.</dd>
 * </dl>
 * 
 * @see #testImageForEquality
 */
public class FiguresTest
{
    
    /** The Constant IMAGE_FILE_NAME. */
    public static final String  IMAGE_FILE_NAME         = "finalImage.ser";
    
    /** The Constant IMAGE_FORMAT. */
    private static final String IMAGE_FORMAT            = "png";
    
    /** The Constant IMAGE_TEST_MODE. */
    private static final String IMAGE_TEST_MODE         = "test";
    
    /** The Constant IMAGE_SAVE_MODE. */
    private static final String IMAGE_SAVE_MODE         = "save";
    
    /** The Constant DEFAULT_PAUSE_MILLIS. */
    private static final int    DEFAULT_PAUSE_MILLIS    = 2000;
    
    /** The plane. */
    private final GeoPlane      plane;
    
    /** The image mode. */
    private String              imageMode;
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main( String[] args )
    {
        System.out.println( args.length );
        FiguresTest test    = new FiguresTest();
        test.imageMode   = args.length == 0 ? null : args[0];
        test.test();
    }
    
    /**
     * Instantiates a new figures test. Explicitly sets image mode to "test."
     * To change the image mode, run the test as an application and specify
     * the mode using the first command line argument.
     */
    public FiguresTest()
    {
       plane = new GeoPlane();
       imageMode = "test";
    }
    
    /**
     * Execute the test.
     */
    @Test
    public void test()
    {
        // Use the Figures constructor that allows us to pass in the plane
        // to use. This is so we can later call getContentPane() on the 
        // plane
        Figures figures = new Figures( plane );
        figures.execute();
        pause( DEFAULT_PAUSE_MILLIS );
        processImage();
    }

    /**
     * Process image. If the image mode is not set, or is not value, 
     * no processing will take place.
     */
    private void processImage()
    {
        if ( imageMode != null )
        {
            BufferedImage image = getPanelImage();
            if ( imageMode.equalsIgnoreCase( IMAGE_SAVE_MODE ) )
                saveImage(image);
            else if ( imageMode.equalsIgnoreCase( IMAGE_TEST_MODE ) )
                testImageForEquality(image);
            else
                System.out.println("image mode: " + imageMode );
        }
        else
            System.out.println( "no image processing performed"  );
    }

    /**
     * Save the image.
     *
     * @param image the image to save.
     * 
     * @see #IMAGE_FILE_NAME
     * @see #IMAGE_FORMAT
     */
    private void saveImage(BufferedImage image)
    {
        try
        {
            File outFile = new File(IMAGE_FILE_NAME);
            if ( ImageIO.write(image, IMAGE_FORMAT, outFile) )
                System.out.println("image file saved");
            else
                throw new IOException("image write failure");
        }
        catch (IOException exc)
        {
            System.err.println("Unable to save image file");
            System.err.println("\"" + exc.getMessage() + "\"");
            exc.printStackTrace();
            System.exit(1);
        }
        System.out.println("image saved in " + IMAGE_FILE_NAME);
    }

    /**
     * Test image for equality. The expected image is read from the file
     * created by save(BufferedImage).
     *
     * @param actImage  the actual image; i.e., the image presently displayed
     *                  on the GeoPlane.
     *                  
     * @see #IMAGE_FILE_NAME
     * @see #imagesEqual(BufferedImage, BufferedImage)
     */
    private void testImageForEquality(BufferedImage actImage)
    {
        try
        {
            File            inFile      = new File( IMAGE_FILE_NAME );
            BufferedImage   expImage    = ImageIO.read( inFile );
            StringBuilder   bldr        = new StringBuilder();

            // Print the width and height of the images, just to get feedback
            // that the algorithm is doing something.
            String newLine = System.lineSeparator();
            bldr.append(expImage.getWidth()).append(" / ").append(actImage.getWidth()).append(newLine);
            bldr.append(expImage.getHeight()).append(" / ").append(actImage.getHeight()).append(newLine);
            System.out.print(bldr);

            if ( imagesEqual( expImage, actImage ) )
                System.out.println("equal");
            else
                System.out.println("not equal");
        }
        catch (IOException exc)
        {
            System.err.println("Unable to read image file");
            System.err.println("\"" + exc.getMessage() + "\"");
            exc.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Test two images for equality. The images are equal if they have
     * the same width and height, and if every element of their 
     * respective rasters are equal.
     *
     * @param buf1 the first image to test
     * @param buf2 the second image to test
     * @return true if successful
     */
    private boolean imagesEqual( BufferedImage buf1, BufferedImage buf2 )
    {
        boolean rval    = true;
        
        if ( buf1 == null || buf2 == null )
            throw new IllegalArgumentException( "argument may not be null" );
        
        int     width1  = buf1.getWidth();
        int     height1 = buf1.getHeight();
        int     width2  = buf2.getWidth();
        int     height2 = buf2.getHeight();
        
        if ( width1 != width2 )
            rval = false;
        else if ( height1 != height2 )
            rval = false;
        else
        {
            for ( int xco = 0 ; xco < width1 && rval ; ++xco )
                for ( int yco = 0 ; yco < height1 && rval ; ++yco )
                    rval = buf1.getRGB( xco, yco ) ==buf2.getRGB( xco,  yco );
        }
        
        return rval;
    }

    /**
     * Gets the image currently displayed in the GeoPlane.
     *
     * @return the panel image
     */
    private BufferedImage getPanelImage()
    {
        JPanel panel = plane.getContentPane();
        int width = panel.getWidth();
        int height = panel.getHeight();
        BufferedImage image =
            new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics2D gtx = image.createGraphics();
        panel.paint(gtx);
        pause( DEFAULT_PAUSE_MILLIS );
        return image;
    }

    /**
     * Pauses this thread for the given number of milliseconds. This is a subroutine
     * just to handle the try/catch block for InterruptedException. If thrown, the
     * InterruptedException is ignored.
     *
     * @param millis    the given number of milliseconds
     */
    private void pause(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException exc)
        {
        }
    }

}
