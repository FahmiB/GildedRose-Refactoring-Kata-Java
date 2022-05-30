package com.gildedrose;

public class ItemWrapper {

    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";

    private Item item;

    public static ItemWrapper createItemWrapper(Item item) {
        switch (item.name) {
            case AgedBrie.AGED_BRIE_ITEM_NAME:
                return new AgedBrie(item);
        }
        return new ItemWrapper(item);
    }

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

    protected void updateQuality() {
        switch (this.item.name) {
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

    protected void handleExpired() {
        switch (this.item.name) {
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

    protected boolean isExpired() {
        return this.item.sellIn < 0;
    }

    protected void updateExpiration() {
        if (!this.item.name.equals(SULFURAS_ITEM_NAME)) {
            this.item.sellIn--;
        }
    }

    protected void decreaseQuality() {
        if (this.item.quality > 0) {
            this.item.quality--;
        }
    }

    protected void increaseQuality() {
        if (this.item.quality < 50) {
            this.item.quality++;
        }
    }
}
