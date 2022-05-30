package com.gildedrose;

public class ItemWrapper {

    public static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";

    private Item item;

    public ItemWrapper(Item item) {
        this.item = item;
    }

    public void updateItem(Item item) {
        updateQuality(item);
        updateExpiration(item);

        if (isExpired(item)) {
            handleExpired(item);
        }
    }

    private void updateQuality(Item item) {
        switch (item.name) {
            case AGED_BRIE_ITEM_NAME:
                if (item.quality < 50) {
                    item.quality++;
                }
                break;
            case BACKSTAGE_PASSES_ITEM_NAME:
                increaseQuality(item);
                if (item.sellIn < 11) {
                    increaseQuality(item);
                }

                if (item.sellIn < 6) {
                    increaseQuality(item);
                }
                break;
            case SULFURAS_ITEM_NAME:
                return;
            default:
                decreaseQuality(item);
                break;
        }
    }

    private void handleExpired(Item item) {
        switch (item.name) {
            case AGED_BRIE_ITEM_NAME:
                increaseQuality(item);
                break;
            case BACKSTAGE_PASSES_ITEM_NAME:
                item.quality = 0;
                break;
            case SULFURAS_ITEM_NAME:
                break;
            default:
                decreaseQuality(item);
                break;
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private void updateExpiration(Item item) {
        if (!item.name.equals(SULFURAS_ITEM_NAME)) {
            item.sellIn--;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
