package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE_ITEM_NAME)
                && !item.name.equals(BACKSTAGE_PASSES_ITEM_NAME)) {
                if (item.quality > 0) {
                    if (!item.name.equals(SULFURAS_ITEM_NAME)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(BACKSTAGE_PASSES_ITEM_NAME)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS_ITEM_NAME)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE_ITEM_NAME)) {
                    if (!item.name.equals(BACKSTAGE_PASSES_ITEM_NAME)) {
                        if (item.quality > 0) {
                            if (!item.name.equals(SULFURAS_ITEM_NAME)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
