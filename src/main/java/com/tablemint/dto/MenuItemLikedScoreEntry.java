package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Heuristic score: high table ratings(4-5) weighted toward menu lines on recent paid orders for the same table.
 * Not dish-Level feedback - see API note on insights response.
 */


@Data
@Builder
public class MenuItemLikedScoreEntry {

    private Long menuItemId;
    private String menuItemName;
    private BigDecimal LikeScore;
    /**
     * How many qualifying feedback events contributed ( private int contributingFeedbackCount;
     */

    private int contributingFeedbackCount;
}
