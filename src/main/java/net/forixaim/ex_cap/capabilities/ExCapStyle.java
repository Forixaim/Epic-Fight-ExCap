package net.forixaim.ex_cap.capabilities;


import yesman.epicfight.world.capabilities.item.Style;

public enum ExCapStyle implements Style
{
    CUSTOM_ONEHAND_1(true),
    CUSTOM_TWOHAND_1(false),

    CUSTOM_ONEHAND_2(true),
    CUSTOM_TWOHAND_2(false),

    CUSTOM_ONEHAND_3(true),
    CUSTOM_TWOHAND_3(false),

    CUSTOM_ONEHAND_4(true),
    CUSTOM_TWOHAND_4(false),

    CUSTOM_ONEHAND_5(true),
    CUSTOM_TWOHAND_5(false),

    CUSTOM_ONEHAND_6(true),
    CUSTOM_TWOHAND_6(false),

    CUSTOM_ONEHAND_7(true),
    CUSTOM_TWOHAND_7(false),

    CUSTOM_ONEHAND_8(true),
    CUSTOM_TWOHAND_8(false),

    CUSTOM_ONEHAND_9(true),
    CUSTOM_TWOHAND_9(false),

    CUSTOM_ONEHAND_10(true),
    CUSTOM_TWOHAND_10(false),

    CUSTOM_ONEHAND_11(true),
    CUSTOM_TWOHAND_11(false),

    CUSTOM_ONEHAND_12(true),
    CUSTOM_TWOHAND_12(false),

    CUSTOM_ONEHAND_13(true),
    CUSTOM_TWOHAND_13(false),

    CUSTOM_ONEHAND_14(true),
    CUSTOM_TWOHAND_14(false),

    CUSTOM_ONEHAND_15(true),
    CUSTOM_TWOHAND_15(false),

    CUSTOM_ONEHAND_16(true),
    CUSTOM_TWOHAND_16(false),

    CUSTOM_ONEHAND_17(true),
    CUSTOM_TWOHAND_17(false),

    CUSTOM_ONEHAND_18(true),
    CUSTOM_TWOHAND_18(false),

    CUSTOM_ONEHAND_19(true),
    CUSTOM_TWOHAND_19(false),

    CUSTOM_ONEHAND_20(true),
    CUSTOM_TWOHAND_20(false),

    CUSTOM_ONEHAND_21(true),
    CUSTOM_TWOHAND_21(false),

    CUSTOM_ONEHAND_22(true),
    CUSTOM_TWOHAND_22(false),

    CUSTOM_ONEHAND_23(true),
    CUSTOM_TWOHAND_23(false),

    CUSTOM_ONEHAND_24(true),
    CUSTOM_TWOHAND_24(false),

    CUSTOM_ONEHAND_25(true),
    CUSTOM_TWOHAND_25(false);
    final boolean canUseOffHand;
    final int id;
    ExCapStyle(boolean canUseOffHand)
    {
        this.canUseOffHand = canUseOffHand;
        id = ENUM_MANAGER.assign(this);
    }
    @Override
    public boolean canUseOffhand() {
        return canUseOffHand;
    }

    @Override
    public int universalOrdinal() {
        return id;
    }
}
