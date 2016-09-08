package study.javacv;

import static org.bytedeco.javacpp.opencv_core.BORDER_DEFAULT;
import static org.bytedeco.javacpp.opencv_imgproc.Laplacian;

import java.io.File;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

public class Test {
	final static int INTERVAL = 40;// /you may use interval
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	play(true,new File("d:/dd1.mp4"), "d:/aa1.mp4","win1");
		Thread th = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(this.getClass().getName());
				Test t = new Test();
				t.play(true,new File("d:/dd.mp4"), "d:/aa.mp4","win");
				notifyAll();
			}
		});
		Thread th1 = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(this.getClass().getName());
				Test t = new Test();
				t.play(false,new File("d:/dd1.mp4"), "d:/aa1.mp4","win1");
				notifyAll();
			}
		});
		th.start();
		th1.start();
		/*Test t = new Test();
		t.play(true,new File("d:/dd1.mp4"), "d:/aa1.mp4","win1");
		t.play(false,new File("d:/dd1.mp4"), "d:/aa1.mp4","win0");*/
	}
	
	public  void play(boolean flag, File file, String name,String title) {
		
		CanvasFrame canvas = new CanvasFrame(title,1.0);
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
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
}
