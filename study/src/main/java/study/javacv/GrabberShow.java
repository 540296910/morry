package study.javacv;

import java.io.File;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_videoio.CvCapture;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class GrabberShow{
	public static void main(String[] args) {
		  //Create canvas frame for displaying video.
		     CanvasFrame canvas = new CanvasFrame("VideoCanvas"); 
		   
		  //Set Canvas frame to close on exit
		     canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);   
		      
		     //Declare FrameGrabber to import video from "video.mp4"
		     FrameGrabber grabber = new OpenCVFrameGrabber("d://DD.mp4");  
		      
		      
		     try {      
		       
		      //Start grabber to capture video
		      grabber.start();      
		       
		      //Declare img as IplImage
		      IplImage img;
		       
		      while (true) {
		        
		       //inser grabed video fram to IplImage img
		    	  /*
		    	   * 
		    	   * Frame frame = grabber.grab();
OpenCvFrameConvertor convertor = new OpenCvFrameConvertor();
IplImage iplImage = convertor.convert(frame);
		    	   * 
		    	   */
		    	  Frame frame = grabber.grab();
		    	  OpenCVFrameConverter.ToIplImage convertor = new ToIplImage();
//		    	  OpenCvFrameConvertor<Frame> convertor = new OpenCvFrameConvertor<Frame>();
		    	  img = convertor.convert(frame);
//		       img = grabber.grab();
		        
		       //Set canvas size as per dimentions of video frame.
		       canvas.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight()); 
		        
		       if (img != null) {       
		        //Show video frame in canvas
		        canvas.showImage(convertor.convert(img));              
		        }
		       }
		      }
		     catch (Exception e) {      
		     }
		    }
}