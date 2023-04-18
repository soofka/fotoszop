import java.awt.image.BufferedImage;

public class Sobel extends Filter {

    public Sobel(App app) {
        super(app);
    }

    private static final int[][] sobelX = {
            {1, 0, -1},
            {2, 0, -2},
            {1, 0, -1}
    };

    private static final int[][] sobelY = {
            {1, 2, 1},
            {0, 0, 0},
            {-1, -2, -1}
    };

    @Override
    protected BufferedImage transformImage(BufferedImage oldImage) {

        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixelX = 0;
                int pixelY = 0;

                // Compute Sobel X and Sobel Y for current pixel
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int neighborX = x + i;
                        int neighborY = y + j;

                        if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                            int neighborPixel = oldImage.getRGB(neighborX, neighborY);

                            int neighborRed = (neighborPixel >> 16) & 0xff;
                            int neighborGreen = (neighborPixel >> 8) & 0xff;
                            int neighborBlue = neighborPixel & 0xff;

                            int grayValue = (neighborRed + neighborGreen + neighborBlue) / 3;

                            pixelX += sobelX[j + 1][i + 1] * grayValue;
                            pixelY += sobelY[j + 1][i + 1] * grayValue;
                        }
                    }
                }

                // Compute magnitude of gradient
                int magnitude = (int) Math.sqrt(pixelX * pixelX + pixelY * pixelY);
                magnitude = Math.min(255, magnitude);

                // Set new pixel value
                int newPixel = (magnitude << 16) | (magnitude << 8) | magnitude;
                newImage.setRGB(x, y, newPixel);
            }
        }

        return newImage;
    }
}
