package study.javacv;

import static org.bytedeco.javacpp.opencv_core.BORDER_DEFAULT;
import static org.bytedeco.javacpp.opencv_imgproc.Laplacian;

import java.io.File;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

public class DWPlayer {
	final static int INTERVAL = 40;// /you may use interval

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DWPlayer dp = new DWPlayer();
		dp.play(true, new File("d:/dd.mp4"), "d:/aa.mp4", "win");
	}

	public void play(boolean flag, File file, String name, String title) {

		CanvasFrame canvas = new CanvasFrame("after", 1.0);
		CanvasFrame canvas1 = new CanvasFrame("before", 1.0);
		canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		canvas1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(
				file.getAbsolutePath());
		IplImage captured_frame = null;
		FrameRecorder recorder = null;
		recorder = new FFmpegFrameRecorder(name, 426, 240,frameGrabber.getAudioChannels());
		recorder.setVideoCodec(13);
		recorder.setAudioCodec(frameGrabber.getAudioCodec());
		recorder.setFrameRate(40);
		recorder.setFormat("mp4");
		try {
			recorder.start();
			frameGrabber.start();
			while (true) {
				try{
					Frame frame = frameGrabber.grab();
					recorder.record(frame);
					if (frame == null) {
						System.out.println("GRAB IS NULL");
						break;
					}
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
					canvas1.showImage(convertor.convert(captured_frame));	
					Thread.sleep(INTERVAL);
				} catch (Exception e) {
					
				}
			}
			recorder.stop();
			frameGrabber.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
