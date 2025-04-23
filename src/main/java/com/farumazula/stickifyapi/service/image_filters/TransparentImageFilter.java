package com.farumazula.stickifyapi.service.image_filters;

import net.coobird.thumbnailator.filters.ImageFilter;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Ma1iket
 **/

@Component("transparent")
public class TransparentImageFilter implements ImageFilter {
    @Override
    public BufferedImage apply(BufferedImage bufferedImage) {
        BufferedImage transparent = new BufferedImage(
                bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int rgb = bufferedImage.getRGB(x, y);
                Color color = new Color(rgb);
                if (color.getRed() > 240 && color.getGreen() > 240 && color.getBlue() > 240) {
                    transparent.setRGB(x, y, 0);
                } else {
                    transparent.setRGB(x, y, rgb);
                }
            }
        }

        return transparent;
    }
}
