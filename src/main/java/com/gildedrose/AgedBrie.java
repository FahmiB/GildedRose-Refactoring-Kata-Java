package com.gildedrose;

public class AgedBrie extends ItemWrapper {

    public static final String AGED_BRIE_ITEM_NAME = "Aged Brie";

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
    }

    @Override
    protected void handleExpired() {
        increaseQuality();
    }
}
