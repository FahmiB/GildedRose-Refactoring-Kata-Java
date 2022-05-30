package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void standard_item_quality_and_sellIn_decrease_eachDay() {
        int startingsellIn = 5;
        int startingQuality = 7;
        final Item standardItem = new Item("Elixir of the Mongoose", startingsellIn, startingQuality);
        GildedRose app = new GildedRose(new Item[] {standardItem});

        app.updateQuality();

        assertEquals(startingsellIn - 1, standardItem.sellIn);
        assertEquals(startingQuality - 1, standardItem.quality);
    }

    @Test
    void multiple_items_degrade_each_day() {
        Item firstItem = new Item("First Standard Item", 5, 4);
        Item secondItem = new Item("Second Standard Item", 3, 2);
        GildedRose app = new GildedRose(new Item[] { firstItem, secondItem });

        app.updateQuality();

        assertEquals(4, firstItem.sellIn);
        assertEquals(3, firstItem.quality);
        assertEquals(2, secondItem.sellIn);
        assertEquals(1, secondItem.quality);
    }

    @Test
    void item_quality_degrades_twice_as_fast_passed_sellIn_date() {
        Item item = new Item("First Standard Item", -1, 4);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(2, item.quality);
    }

    @Test
    void item_quality_is_never_negative() {
        Item item = new Item("First Standard Item", 4, 0);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void aged_items_increase_in_quality_over_time() {
        Item item = new Item("Aged Brie", 5, 6);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(7, item.quality);
    }

    @Test
    void item_quality_is_never_greater_than_50() {
        Item item = new Item("Aged Brie", 5, 50);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void legendary_items_never_have_to_be_sold() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(-1, item.sellIn);
    }

    @Test
    void legendary_items_never_decrease_in_quality() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(80, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_as_sellIn_date_approaches() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(21, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_2_when_there_are_10_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(22, item.quality);
    }

    @Test
    void backstage_passes_increase_in_quality_by_3_when_there_are_5_days_or_less() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(23, item.quality);
    }

    @Test
    void backstage_passes_quality_is_0_after_concert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void conjured_item_quality_degrades_twice_as_fast_prior_to_sellIn_date_expiry() {
        Item item = new Item("Conjured", 10, 20);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(18, item.quality);
    }

    @Test
    void conjured_item_quality_degrades_twice_as_fast_after_sellIn_date_expiry() {
        Item item = new Item("Conjured", 10, 20);
        GildedRose app = new GildedRose(new Item[] { item });

        app.updateQuality();

        assertEquals(16, item.quality);
    }
}
