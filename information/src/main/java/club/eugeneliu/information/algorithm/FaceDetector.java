package club.eugeneliu.information.algorithm;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {
    public void run(){
        CascadeClassifier faceDetector = new CascadeClassifier(
                "/home/eugeneliu/EEugeneSoft/EugeneLoan/information/src/main/resources/lbpcascade_frontalface.xml"
        );
        Mat image = Highgui.imread(
//                "/home/eugeneliu/EEugeneSoft/EugeneLoan/information/src/main/resources/images/libowen.jpeg"
                "/home/eugeneliu/EEugeneSoft/EugeneLoan/information/src/main/resources/images/people.jpeg"
        );
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image,faceDetections);
        System.out.println(String.format("检测到%s张人脸",faceDetections.toArray().length));
        for(Rect rect:faceDetections.toArray()){
            Core.rectangle(image,new Point(rect.x,rect.y),new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0));
        }
        String fileName = "/home/eugeneliu/EEugeneSoft/EugeneLoan/information/src/main/resources/images/people_detect.jpeg";
        Highgui.imwrite(fileName,image);
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new FaceDetector().run();
    }
}
