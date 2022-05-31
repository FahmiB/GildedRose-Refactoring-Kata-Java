package com.gildedrose;

public class BackstagePasses extends ItemWrapper{

    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
        if (this.item.sellIn < 11) {
            increaseQuality();
        }

        if (this.item.sellIn < 6) {
            increaseQuality();
        }
    }

    @Override
    protected void handleExpired() {
        this.item.quality = 0;
    }
}
