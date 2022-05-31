package com.gildedrose;

public class Conjured extends ItemWrapper{

    public static final String CONJURED_ITEM_NAME = "Conjured";

    public Conjured(Item item) {
        super(item);
    }

    @Override
    protected void decreaseQuality() {
        if (this.item.quality > 0) {
            this.item.quality -= 2 ;
        }
    }
}
