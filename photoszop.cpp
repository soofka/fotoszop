#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"

#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "stb_image_write.h"

#include <iostream>
using namespace std;

#include <string>

struct Pixel {
    unsigned char r;
    unsigned char g;
    unsigned char b;
    unsigned char a;
};

class Image {
public:
    Image(string path) {
        data = stbi_load(path.c_str(), &width, &height, &channelCount, 0);

        if(data==nullptr) {
            cout<<"bad file path"<<endl;
        }
    }

    ~Image() {
        stbi_image_free(data);
    }

    Pixel getPixel(int x, int y) {
        Pixel result{};

        unsigned bytePerPixel = channelCount;
        unsigned char* pixelOffset = data + (x + width * y) * bytePerPixel;

        result.r = pixelOffset[0];
        result.g = pixelOffset[1];
        result.b = pixelOffset[2];
        result.a = channelCount >= 4 ? pixelOffset[3] : 0xff;

        return result;
    }

    void writePixel(Pixel pixel, int x, int y) {
        unsigned bytePerPixel = channelCount;
        unsigned char* pixelOffset = data + (x + width * y) * bytePerPixel;

        pixelOffset[0] = pixel.r;
        pixelOffset[1] = pixel.g;
        pixelOffset[2] = pixel.b;

        if (channelCount>=4) {
            pixelOffset[3] = pixel.a;
        }
    }

    void saveImage(string path) {
        stbi_write_png(path.c_str(), width, height, channelCount, data, width * channelCount);
    }

    int getWidth() { return width; }
    int getHeight() { return height; }

private:
    int width;
    int height;
    int channelCount;

    unsigned char* data;
};

int main() {
    
    Image image("kotek.png");

    for(int i = 0; i < image.getWidth(); i++) {
        for(int j = 0; j < image.getHeight(); j++) {
            Pixel pixel = image.getPixel(i, j);

            pixel.b = 255;

            image.writePixel(pixel, i, j);
        }
    }

    image.saveImage("kotek2.png");
    
    return 0;
}