package study.javacv;

import java.awt.image.ColorModel;
import java.nio.ByteBuffer;

import org.bytedeco.javacpp.opencv_core.CvContour;
import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_imgproc.*;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.jasypt.normalization.Normalizer;

import javax.swing.*;

import static org.bytedeco.javacpp.opencv_core.BORDER_DEFAULT;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.Laplacian;

public class MyFirstOpenCVAppInJava {

	public static void main(String[] args) {

		// Read an image.
		final Mat src = imread("d://cat-talk-is-cheap.jpg");
		display(src, "Input");

		// Apply Laplacian filter
		Mat dest = new Mat();
//		opencv_imgproc.applyColorMap(src, dest, opencv_core.IPL_DEPTH_8U);
		
//		OpenCVFrameConverter.ToIplImage convertor = new ToIplImage();
		
		// opencv_imgproc.threshold(src, dest, 128, 255,
		// opencv_imgproc.CV_THRESH_BINARY);
//		 Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
		opencv_imgproc.cvtColor(src, dest, opencv_imgproc.CV_BGR2GRAY);
//		opencv_core.normalize(dest, dest, 0, 255, 0, -1, src);
//		 Laplacian(src, dest, opencv_core.IPL_DEPTH_8U, 1, 3, 0,
//		 BORDER_DEFAULT);
		display(dest, "Laplacian");
		opencv_imgproc.cvtColor(src, dest, opencv_imgproc.CV_BGR2HLS_FULL);
//		opencv_core.normalize(dest, dest, 0, 255, 0, -1, src);
//		 Laplacian(src, dest, opencv_core.IPL_DEPTH_8U, 1, 3, 0,
//		 BORDER_DEFAULT);
		display(dest, "Laplacian");
	}
	
	public static IplImage changesTyle(Mat mat) {
		    IplImage result = new IplImage(mat); 
		    return result;
	}
	
	
	// ---------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	static void display(Mat image, String caption) {
		// Create image window named "My Image".
		final CanvasFrame canvas = new CanvasFrame(caption, 1.0);

		// Request closing of the application when the image window is closed.
		canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Convert from OpenCV Mat to Java Buffered image for display
		final OpenCVFrameConverter converter = new OpenCVFrameConverter.ToMat();
		// Show image on window.
		canvas.showImage(converter.convert(image));
	}
	@SuppressWarnings("rawtypes")
	static void display(IplImage image, String caption) {
		// Create image window named "My Image".
		final CanvasFrame canvas = new CanvasFrame(caption, 1.0);

		// Request closing of the application when the image window is closed.
		canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Convert from OpenCV Mat to Java Buffered image for display
		final OpenCVFrameConverter converter = new OpenCVFrameConverter.ToMat();
		// Show image on window.
		canvas.showImage(converter.convert(image));
	}
}