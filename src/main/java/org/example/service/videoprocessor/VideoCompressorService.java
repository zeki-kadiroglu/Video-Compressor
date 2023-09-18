package org.example.service.videoprocessor;


import org.bytedeco.ffmpeg.global.avcodec;


import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.springframework.stereotype.Service;
import static org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_YUV420P;

@Service
public class VideoCompressorService {

    public static void compressVideo(String videoPath) throws FFmpegFrameRecorder.Exception, FFmpegFrameGrabber.Exception {

        FFmpegLogCallback.set();

        // Output video file
        String outputVideoFile = videoPath;


        // Open the input video file
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();

        // Create a video writer with H.264 codec
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideoFile,
                grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        recorder.setFrameRate(grabber.getFrameRate()); // Use the same frame rate as the input
        recorder.setVideoQuality(0);
        recorder.setVideoBitrate(40 * 40 * 512);
        recorder.setFormat(grabber.getFormat());
        recorder.setPixelFormat(AV_PIX_FMT_YUV420P);
        recorder.setFrameRate(grabber.getFrameRate());
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setVideoOption("preset", "ultrafast");
        recorder.setVideoOption("tune", "film");
        recorder.setVideoOption("lossless", "5");

        recorder.setVideoCodecName("libx264");
        recorder.start();


        Frame frame;
        try {
            // Read frames from the input video and write to the output video
            while ((frame = grabber.grab()) != null) {
                recorder.record(frame);
            }

            // Release resources
            grabber.stop();
            grabber.release();
            recorder.stop();
            recorder.release();
        } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
            e.printStackTrace();
        }
    }
}

