package pgdp.adventuin;

import pgdp.color.ExceptionUtil;
import pgdp.color.RgbColor;

public class Adventuin {
    private final String name;
    private final int height;
    private final RgbColor color;
    private final HatType hat;
    private final Language language;
    public Adventuin(String name, int size, RgbColor color, HatType hat, Language language) {
        if(size<=0){
            ExceptionUtil.unsupportedOperation("height can not be negative or zero ");
        }
        this.name = name;
        this.height = size;
        this.color = color;
        this.hat = hat;
        this.language = language;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Adventuin{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

//        @Override
//    public String toString() {
//        return "Adventuin[" + "name='" + name + ", height=" + height + ", color=" + color +
//                ", hat=" + hat + ", language=" + language + ']';
//    }





    public HatType getHatType() {
        return hat;
    }
    public int getHeight() {
        return height;
    }

    public RgbColor getColor() {
        return color;
    }

    public Language getLanguage() {
        return language;
    }
//    Then we can create the class Adventuin which contains the attributes mentioned.
//    Make the public getters getName, getHeight, getColor, getHatType and getLanguage
//        available for this. You also need a public constructor that accepts the name, size, color, hat
//    type and language in exactly this order. For your own testing it is advisable to overwrite toString ().
//    A validity check (e.g. for zero) is not tested, but can be added.
}
