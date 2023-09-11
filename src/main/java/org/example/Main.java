package org.example;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.example.videoprocessor.VideoCompressor;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FFmpegFrameRecorder.Exception, FFmpegFrameGrabber.Exception {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        VideoCompressor.compressVideo("/Users/zekikadiroglu/personel-projects/video_compressor/src/main/java/org/example/zk.mp4");


        }
    }

