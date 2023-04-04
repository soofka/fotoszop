//package com.company;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//
//
//public class FlipHorizontal implements ActionListener {
//
//    private View view;
//
//    FlipHorizontal(View view) {
//        super();
//        this.view = view;
//    }
//
//    public void actionPerformed(ActionEvent e)
//    {
//        BufferedImage oldImage = this.view.getInputFile();
//        int width = oldImage.getWidth();
//        int height = oldImage.getHeight();
//        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        try {
//
//            BufferedImage flipped = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
//
//            for (int x = 0; x < oldImage.getWidth(); x++) {
//                for (int y = 0; y < oldImage.getHeight(); y++) {
//                    newImage.setRGB(oldImage.getWidth() - x - 1, y, oldImage.getRGB(x, y));
//                }
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        this.view.displayImage(newImage);
//    }
//
//}