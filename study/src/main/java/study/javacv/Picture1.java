package study.javacv;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvContour;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class Picture1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 Loader.load( opencv_objdetect.class );

	        FrameGrabber grabber = FrameGrabber.createDefault( 1 );
	        grabber.start();
	        final OpenCVFrameConverter converter = new OpenCVFrameConverter.ToIplImage();
	        IplImage grabbedImage = (IplImage) converter.convert(grabber.grab());
	        int width = grabbedImage.width();
	        int height = grabbedImage.height();

	        IplImage grayImage = IplImage.create( width, height, opencv_core.IPL_DEPTH_8U, 1 );

	        CvMemStorage storage = CvMemStorage.create();

	        CanvasFrame filterProbe = new CanvasFrame( "Filtered", CanvasFrame.getDefaultGamma() / grabber.getGamma() );
	        CanvasFrame enhancedProbe = new CanvasFrame( "Enhanced", CanvasFrame.getDefaultGamma() / grabber.getGamma() );

	        while ( filterProbe.isVisible() && enhancedProbe.isVisible() && (grabbedImage = (IplImage) converter.convert(grabber.grab())) != null ) {
	        	opencv_core.cvClearMemStorage( storage );

	            // Convert to grayscale image...
	        	opencv_imgproc.cvCvtColor( grabbedImage, grayImage, opencv_imgproc.CV_BGR2GRAY );
	            // UNCOMMENT FIXES THE PROBLEM grabbedImage = grabbedImage.clone();

	            // Let's find some contours! but first some thresholding...
	        	opencv_imgproc.cvThreshold( grayImage, grayImage, 128, 255, opencv_imgproc.CV_THRESH_BINARY );

	            // To check if an output argument is null we may call either isNull() or equals(null).
	            CvSeq contour = new CvSeq( null );
	            // cvFindContours modifies the image so clone it first since we want to keep the grayscale version
	            opencv_imgproc.cvFindContours( grayImage.clone(), storage, contour, Loader.sizeof( CvContour.class ), opencv_imgproc.CV_RETR_LIST, opencv_imgproc.CV_CHAIN_APPROX_SIMPLE );
	            while ( contour != null && !contour.isNull() ) {
	                if ( contour.elem_size() > 0 ) {
	                    CvSeq points = opencv_imgproc.cvApproxPoly( contour, Loader.sizeof( CvContour.class ), storage, opencv_imgproc.CV_POLY_APPROX_DP, opencv_imgproc.cvContourPerimeter( contour ) * 0.02, 0 );
	                    opencv_imgproc.cvDrawContours( grabbedImage, points, CvScalar.BLUE, CvScalar.BLUE, -1, 1 /*CV_FILLED*/, opencv_imgproc.CV_AA );
	                }
	                contour = contour.h_next();
	            }

	            filterProbe.showImage( converter.convert(grayImage) );
	            enhancedProbe.showImage(  converter.convert(grabbedImage));
	        }
	        filterProbe.dispose();
	        enhancedProbe.dispose();
			grabber.stop();
	}

}
