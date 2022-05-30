package com.gildedrose;

public class ItemWrapper {


    protected Item item;

    public static ItemWrapper createItemWrapper(Item item) {
        switch (item.name) {
            case AgedBrie.AGED_BRIE_ITEM_NAME:
                return new AgedBrie(item);
            case BackstagePasses.BACKSTAGE_PASSES_ITEM_NAME:
                return new BackstagePasses(item);
            case Sulfuras.SULFURAS_ITEM_NAME:
                return new Sulfuras(item);
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
        decreaseQuality();
    }

    protected void handleExpired() {
        decreaseQuality();
    }

    protected boolean isExpired() {
        return this.item.sellIn < 0;
    }

    protected void updateExpiration() {
        if (!this.item.name.equals(Sulfuras.SULFURAS_ITEM_NAME)) {
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
