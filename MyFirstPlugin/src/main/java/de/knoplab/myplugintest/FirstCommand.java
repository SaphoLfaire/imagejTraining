/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.knoplab.myplugintest;

import java.util.stream.Stream;
import net.imagej.Dataset;
import net.imagej.DatasetService;
import net.imagej.axis.AxisType;
import net.imagej.axis.CalibratedAxis;
import net.imagej.ops.OpService;
import net.imagej.ops.Ops;
import net.imagej.ops.stats.DefaultMedian;
import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;
import net.imglib2.type.numeric.real.FloatType;
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
public class FirstCommand extends ContextCommand {

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

        // getting the number of dimension of the image
        int numDimension = input.numDimensions();

        // creating a array that will hold the dimensions
        long[] dimension = new long[input.numDimensions()];

        // creating an array that will hold the dimensions type
        CalibratedAxis[] axes = new CalibratedAxis[numDimension];

        // copying the data in the arrays
        input.dimensions(dimension);
        input.axes(axes);

        // use stream to map the CalibratedAxis
        // to their axis type
        AxisType[] types = Stream.of(axes)
                .map(axe -> axe.type())
                .toArray(size -> new AxisType[size]);

        output = datasetService.create(new FloatType(), dimension, "My output", types, true);

        // create a cursor that will go through the input image and 
        // the output image
        Cursor<RealType<?>> inputCursor = input.cursor();

        RandomAccess<? extends RealType> ra = output.randomAccess();
        inputCursor.reset();
        while (inputCursor.hasNext()) {
            inputCursor.fwd();

            ra.setPosition(inputCursor);
            ra.get().setReal(inputCursor.get().getRealDouble());
        }
        
        
        IntervalView<RealType<?>> interval = Views.interval(output, new long[] { 100,100,0}, new long[] {200,200,2});
        
        
        Cursor<RealType<?>> intervalCursor = interval.cursor();
        while(intervalCursor.hasNext()) {
            intervalCursor.fwd();
            intervalCursor.get().mul(3.0);
        }
        
        // Now let's calculate the median of this particular part of the image
        DoubleType result = new DoubleType();
        
        Ops.Stats.Mean op = opService.op(Ops.Stats.Mean.class,result,interval);
        
       
       
        
        op.run();
        System.out.println(result);
        

    }

}
