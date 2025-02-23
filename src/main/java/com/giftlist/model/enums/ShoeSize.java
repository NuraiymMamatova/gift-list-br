package com.giftlist.model.enums;

import lombok.Getter;

@Getter
public enum ShoeSize {
    THIRTY_FIVE(35),
    THIRTY_SIX(36),
    THIRTY_SEVEN(37),
    THIRTY_EIGHT(38),
    THIRTY_NINE(39),
    FORTY(40),
    FORTY_ONE(41),
    FORTY_TWO(42),
    FORTY_THREE(43),
    FORTY_FOUR(44);

    private final int shoeSize;

    ShoeSize(int clothingSize) {
        this.shoeSize = clothingSize;
    }
}
