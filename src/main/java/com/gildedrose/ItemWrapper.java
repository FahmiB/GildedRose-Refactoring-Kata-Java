package com.gildedrose;

public class ItemWrapper {

    public static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";

    private Item item;

    public ItemWrapper(Item item) {
        this.item = item;
    }

    public void updateItem() {
        updateQuality();
        updateExpiration();

        if (isExpired()) {
            handleExpired();
        }
    }

    private void updateQuality() {
        switch (this.item.name) {
            case AGED_BRIE_ITEM_NAME:
                if (this.item.quality < 50) {
                    this.item.quality++;
                }
                break;
            case BACKSTAGE_PASSES_ITEM_NAME:
                increaseQuality();
                if (this.item.sellIn < 11) {
                    increaseQuality();
                }

                if (this.item.sellIn < 6) {
                    increaseQuality();
                }
                break;
            case SULFURAS_ITEM_NAME:
                return;
            default:
                decreaseQuality();
                break;
        }
    }

    private void handleExpired() {
        switch (this.item.name) {
            case AGED_BRIE_ITEM_NAME:
                increaseQuality();
                break;
            case BACKSTAGE_PASSES_ITEM_NAME:
                this.item.quality = 0;
                break;
            case SULFURAS_ITEM_NAME:
                break;
            default:
                decreaseQuality();
                break;
        }
    }

    private boolean isExpired() {
        return this.item.sellIn < 0;
    }

    private void updateExpiration() {
        if (!this.item.name.equals(SULFURAS_ITEM_NAME)) {
            this.item.sellIn--;
        }
    }

    private void decreaseQuality() {
        if (this.item.quality > 0) {
            this.item.quality--;
        }
    }

    private void increaseQuality() {
        if (this.item.quality < 50) {
            this.item.quality++;
        }
    }
}
