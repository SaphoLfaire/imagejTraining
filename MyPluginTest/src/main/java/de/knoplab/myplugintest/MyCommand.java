/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.knoplab.myplugintest;

import java.util.stream.Stream;
import net.imagej.ImageJ;
import net.imagej.Dataset;
import net.imagej.DatasetService;
import net.imagej.axis.AxisType;
import net.imagej.axis.CalibratedAxis;
import net.imagej.ops.OpService;
import net.imagej.ops.Ops;
import ij.plugin.filter.MaximumFinder;

import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import net.imagej.DatasetFactory;
import net.imagej.ImgPlus;
import net.imagej.ops.stats.DefaultMedian;
import net.imglib2.Cursor;
import net.imglib2.Interval;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.gauss.Gauss;
import net.imglib2.algorithm.neighborhood.Neighborhood;
import net.imglib2.algorithm.neighborhood.RectangleShape;
import net.imglib2.algorithm.region.hypersphere.HyperSphere;
import net.imglib2.algorithm.stats.Max;
import net.imglib2.img.Img;
import net.imglib2.img.ImgFactory;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Intervals;

import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.command.ContextCommand;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author cyril
 */
@Plugin(type = Command.class, menuPath = "Plugins > My First command")
public class MyCommand extends ContextCommand {

    @Parameter(type = ItemIO.INPUT)
    Dataset input;

    @Parameter(type = ItemIO.OUTPUT)
    Dataset output;

    @Parameter
    DatasetService datasetService;

    
    @Parameter
    OpService opService;
    
    @Override
    public void run() {

        process();

    }

    public <T extends RealType<T>> void process() {
        
        //cast le dataset en img, format dont on a besoin pour la fonction
        Img< T > img = (Img< T >) input.copy();
        
        //Lancement de la fonction        
        
        Img< BitType > result =
            findAndDisplayLocalMinima( img, new ArrayImgFactory< BitType >(), new BitType() );
        
        //Affichage du resultat
        //ImageJFunctions.show( result );
        
        output = datasetService.create(result);
        
    }
        
        public static < T extends Comparable< T >, U extends RealType< U > > Img< U >
        findAndDisplayLocalMinima(
            RandomAccessibleInterval< T > source,
            ImgFactory< U > imageFactory, U outputType )
    {
        // Create a new image for the output
        Img< U > output = imageFactory.create( source, outputType );
 
        // define an interval that is one pixel smaller on each side in each dimension,
        // so that the search in the 8-neighborhood (3x3x3...x3) never goes outside
        // of the defined interval
        Interval interval = Intervals.expand( source, -1 );
 
        // create a view on the source with this interval
        source = Views.interval( source, interval );
 
        // create a Cursor that iterates over the source and checks in a 8-neighborhood
        // if it is a minima
        final Cursor< T > center = Views.iterable( source ).cursor();
 
        // instantiate a RectangleShape to access rectangular local neighborhoods
        // of radius 1 (that is 3x3x...x3 neighborhoods), skipping the center pixel
        // (this corresponds to an 8-neighborhood in 2d or 26-neighborhood in 3d, ...)
        final RectangleShape shape = new RectangleShape( 1, true );
 
        // iterate over the set of neighborhoods in the image
        for ( final Neighborhood< T > localNeighborhood : shape.neighborhoods( source ) )
        {
            // what is the value that we investigate?
            // (the center cursor runs over the image in the same iteration order as neighborhood)
            final T centerValue = center.next();
            
 
            // keep this boolean true as long as no other value in the local neighborhood
            // is larger or equal
            boolean isMinimum = false;
            
            
            
 
            // check if all pixels in the local neighborhood that are smaller
            for ( final T value : localNeighborhood )
            {
                
               
            
                // test if the center is smaller than the current pixel value
                if ( centerValue.compareTo( value ) >= -1 )
                {
                    isMinimum = true;
                    break;
                }
            }
 
            if ( isMinimum )
            {
                // draw a sphere of radius one in the new image
                HyperSphere< U > hyperSphere = new HyperSphere< U >( output, center, 1 );
 
                // set every value inside the sphere to 1
                for ( U value : hyperSphere )
                    value.setOne();
            }
        }
 
        return output;
    }

    }


