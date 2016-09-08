package study.javacv;

import static org.bytedeco.javacpp.opencv_core.BORDER_DEFAULT;
import static org.bytedeco.javacpp.opencv_imgproc.Laplacian;

import java.io.File;

import org.bytedeco.javacpp.FlyCapture2.Image;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

public class T1 implements Runnable {
	final static int INTERVAL = 40;// /you may use interval
	IplImage image;
	CanvasFrame canvas;
	boolean flag;
	String path;
	String name;

	public T1(boolean flag, String path, String name,String title) {
		canvas = new CanvasFrame(title);
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.flag = flag;
		this.path = path;
		this.name = name;
	}

	public void convert(File file, boolean flag) {
		FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(
				file.getAbsolutePath());
		IplImage captured_frame = null;
		FrameRecorder recorder = null;
		recorder = new FFmpegFrameRecorder(name, 300, 300);
		recorder.setVideoCodec(13);
		recorder.setFrameRate(30);
		recorder.setFormat("mp4");
		try {
			recorder.start();
			frameGrabber.start();
			while (true) {
				try {
					Frame frame = frameGrabber.grab();
					OpenCVFrameConverter.ToIplImage convertor = new ToIplImage();
					captured_frame = convertor.convert(frame);
					if (flag) {
						Mat mat = convertor.convertToMat(frame);
						final Mat dest = new Mat();
						Laplacian(mat, dest, mat.depth(), 1, 3, 0,
								BORDER_DEFAULT);
						canvas.showImage(convertor.convert(dest));
					} else {
						canvas.showImage(convertor.convert(captured_frame));
					}

					Thread.sleep(INTERVAL);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		convert(new File(path), flag);
	}

	public static void main(String[] args) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		T1 gs = new T1(true, "d:/dd.mp4", "d:/aa.mp4","win1");
		Thread th = new Thread(gs);
		th.start();
		/*T1 gs1 = new T1(false, "d:/dd1.mp4", "d:/aa1.mp4");
		Thread th1 = new Thread(gs1);
		th1.start();*/
	}
}