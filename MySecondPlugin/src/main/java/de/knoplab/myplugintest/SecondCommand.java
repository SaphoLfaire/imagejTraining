/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.knoplab.myplugintest;


import ij.ImagePlus;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import static java.lang.Math.toIntExact;
import java.util.stream.Stream;
import net.imagej.Dataset;
import net.imagej.DatasetService;
import net.imagej.ImgPlus;
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
import net.imagej.ops.OpService;
import net.imagej.ops.Ops.Copy.Img;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.algorithm.neighborhood.RectangleShape;
import net.imglib2.algorithm.neighborhood.Shape;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.logic.BoolType;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.command.ContextCommand;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author cyril
 */
@Plugin(type = Command.class, menuPath = "Plugins > My Second command")
public class SecondCommand extends ContextCommand {

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
        System.out.println("Done ! ");
        

    }

    public <T extends RealType<T>> void process() {
        
       

        //output = datasetService.create(new FloatType(), dimension, "My output", types, true);
        //ImgPlus<? extends RealType<?>> imp = input.getImgPlus();
        /*
        ImageConverter(ImagePlus imp);
        ij.process.ImageConverter truc = 
        
        ImageProcessor input2 = input.getMask();
        //Object input2 = opService.run("convert.bit", input);
*/
        output = input.duplicate();
        RectangleShape shape = new RectangleShape(3,true);

        Ops.Morphology.FillHoles op = opService.op(Ops.Morphology.FillHoles.class, input, output, shape);
        
        op.run();
        output.update();
        //output = input.duplicate();
        
        
        
        
       

    }

}
