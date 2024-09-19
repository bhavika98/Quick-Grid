package com.app.mylib.stickerview;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * extracting Typeface from Assets is a heavy operation,
 * we want to make sure that we cache the typefaces for reuse
 */
public class FontProvider {

    private static final String DEFAULT_FONT_NAME = "Text";

    private final Map<String, Typeface> typefaces;
    private final Map<String, String> fontNameToTypefaceFile;
    private final Resources resources;
    private final List<String> fontNames;

    public FontProvider(Resources resources) {
        this.resources = resources;

        typefaces = new HashMap<>();

        // populate fonts
        fontNameToTypefaceFile = new HashMap<>();

        fontNameToTypefaceFile.put("Text1", "BILLD.TTF");
        fontNameToTypefaceFile.put("Text2", "BILLO.TTF");
        fontNameToTypefaceFile.put("Text3", "BirchStd.otf");
        fontNameToTypefaceFile.put("Text4", "Blackout.ttf");
        fontNameToTypefaceFile.put("Text5", "BrotherTattoo_Demo.ttf");
        fontNameToTypefaceFile.put("Text7", "bubble.ttf");
        fontNameToTypefaceFile.put("Text8", "champignonaltswash.ttf");
        fontNameToTypefaceFile.put("Text10", "danielbd.ttf");
        fontNameToTypefaceFile.put("Text11", "Denne_Shuffle.ttf");
        fontNameToTypefaceFile.put("Text12", "Filxgirl.TTF");
        fontNameToTypefaceFile.put("Text13", "Fonarto_XT.otf");
        fontNameToTypefaceFile.put("Text15", "GeosansLight.ttf");
        fontNameToTypefaceFile.put("Text16", "Gotham-Book.otf");
        fontNameToTypefaceFile.put("Text18", "IGLOO.TTF");
        fontNameToTypefaceFile.put("Text19", "Intro.otf");
        fontNameToTypefaceFile.put("Text20", "Jellyka_Estrya_Handwriting.ttf");
        fontNameToTypefaceFile.put("Text22", "JennaSue.ttf");
        fontNameToTypefaceFile.put("Text23", "Langdon.otf");
        fontNameToTypefaceFile.put("Text24", "LOVE-BOX-demo.ttf");
        fontNameToTypefaceFile.put("Text25", "LOVERBOY.TTF");
        fontNameToTypefaceFile.put("Text26", "Masaaki-Regular.otf");
        fontNameToTypefaceFile.put("Text30", "MidnightConstellations.ttf");
        fontNameToTypefaceFile.put("Text31", "NuptialScriptLTStd.otf");
        fontNameToTypefaceFile.put("Text32", "OhMyGodStars.ttf");
        fontNameToTypefaceFile.put("Text34", "Orial.ttf");
        fontNameToTypefaceFile.put("Text35", "Ostrich.ttf");
        fontNameToTypefaceFile.put("Text36", "POLYA.otf");
        fontNameToTypefaceFile.put("Text37", "PUSAB.otf");
        fontNameToTypefaceFile.put("Text38", "riesling.ttf");
        fontNameToTypefaceFile.put("Text39", "Sail-Regular.otf");
        fontNameToTypefaceFile.put("Text40", "SEASRN.ttf");
        fontNameToTypefaceFile.put("Text41", "the_King__26_Queen_font.ttf");
        fontNameToTypefaceFile.put("Text42", "Too_Freakin_Cute_Demo.ttf");
        fontNameToTypefaceFile.put("Text43", "Windsong.ttf");

        fontNames = new ArrayList<>(fontNameToTypefaceFile.keySet());
    }

    /**
     * @param typefaceName must be one of the font names provided from {@link FontProvider#getFontNames()}
     * @return the Typeface associated with {@code typefaceName}, or {@link Typeface#DEFAULT} otherwise
     */
    public Typeface getTypeface(String typefaceName) {
        if (TextUtils.isEmpty(typefaceName)) {
            return Typeface.DEFAULT;
        } else {
            //noinspection Java8CollectionsApi
            if (typefaces.get(typefaceName) == null) {
                typefaces.put(typefaceName,
                        Typeface.createFromAsset(resources.getAssets(), "fonts/" + fontNameToTypefaceFile.get(typefaceName)));
            }
            return typefaces.get(typefaceName);
        }
    }

    /**
     * use {@link FontProvider#getTypeface(String) to get Typeface for the font name}
     *
     * @return list of available font names
     */
    public List<String> getFontNames() {
        return fontNames;
    }

    /**
     * @return Default Font Name - <b>Helvetica</b>
     */
    public String getDefaultFontName() {
        return DEFAULT_FONT_NAME;
    }
}