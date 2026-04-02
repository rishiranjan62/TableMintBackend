package com.tablemint.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Objects;

/**
 * Converts recipe "per portion" amounts into the inventory SKU's stock unit (e.g. 80 g → 0.08 kg).
 */

public class RecipePortionUnitConverter {

    private static final BigDecimal THOUSAND = new BigDecimal("1000");
    private static final int CALC_SCALE = 12;

    private RecipePortionUnitConverter() {
    }

    /**
     * @param portionUnit unit the user entered for {@code quantity}; null/blank means already in {@code stockUnit}
     * @param stockUnit   inventory item's {@code quantity_unit}; required when {@code portionUnit} is set
     */
    public static BigDecimal convertToStockUnit(BigDecimal quantity, String portionUnit, String stockUnit) {
        Objects.requireNonNull(quantity, "quantity");
        String p = normalize(portionUnit);
        String s = normalize(stockUnit);
        if (p == null) {
            return quantity;
        }
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException(
                    "This ingredient has no unit in Inventory. Add a stock unit there, or choose 'same as stock' for the recipe amount.");
        }
        if (p.equals(s)) {
            return quantity;
        }
        if (p.equals("g") && s.equals("kg")) {
            return quantity.divide(THOUSAND, CALC_SCALE, RoundingMode.HALF_UP);
        }
        if (p.equals("kg") && s.equals("g")) {
            return quantity.multiply(THOUSAND);
        }
        if (p.equals("ml") && s.equals("l")) {
            return quantity.divide(THOUSAND, CALC_SCALE, RoundingMode.HALF_UP);
        }
        if (p.equals("l") && s.equals("ml")) {
            return quantity.multiply(THOUSAND);
        }
        String rawP = portionUnit != null ? portionUnit.trim() : "";
        String rawS = stockUnit != null ? stockUnit.trim() : "";
        if (!rawP.isEmpty() && rawP.equalsIgnoreCase(rawS)) {
            return quantity;
        }
        throw new IllegalArgumentException(
                String.format(
                        "Cannot convert recipe unit \"%s\" to stock unit \"%s\". Use the same unit, or only g↔️kg and ml↔️L.",
                        rawP,
                        rawS));
    }

    /**
     * Canonical token for known units, or trimmed lowercase for pass-through (e.g. pcs must match stock).
     */
    private static String normalize(String u) {
        if (u == null) {
            return null;
        }
        String t = u.trim();
        if (t.isEmpty()) {
            return null;
        }
        String s = t.toLowerCase(Locale.ROOT);
        return switch (s) {
            case "g", "gram", "grams" -> "g";
            case "kg", "kilo", "kilogram", "kilograms" -> "kg";
            case "ml", "milliliter", "milliliters", "millilitre", "millilitres" -> "ml";
            case "l", "liter", "liters", "litre", "litres" -> "l";
            default -> s;
        };
    }


}
