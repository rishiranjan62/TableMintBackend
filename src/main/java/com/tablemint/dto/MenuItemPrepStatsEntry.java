package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuItemPrepStatsEntry {
    private Long menuItemId;
    private String menuItemName;
    /**
     * Average seconds from prep started to prepared (kitchen), when both timestamps exist.
     */
    private double avgPrepSeconds;
    private Long sampleCount;
}
