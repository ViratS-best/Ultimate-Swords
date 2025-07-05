// src/main/java/sword/ultimate_swords/Ultimate_swords.java
package sword.ultimate_swords;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Components & attributes
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

// Your custom tool materials & items
import sword.ultimate_swords.item.ModToolMaterials;
import sword.ultimate_swords.item.UltimateSwordItem;
import sword.ultimate_swords.item.UltimateMaceItem;
import sword.ultimate_swords.enchantment.ModEnchantmentEffects; // ADDED: Import for your enchantment effects

public class Ultimate_swords implements ModInitializer {
    public static final String MOD_ID = "ultimate_swords";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Identifiers for attribute modifiers
    private static final Identifier ULTIMATE_SWORD_ATTACK_DAMAGE_ID =
            Identifier.of(MOD_ID, "ultimate_sword_damage_bonus");
    private static final Identifier ULTIMATE_SWORD_ATTACK_SPEED_ID =
            Identifier.of(MOD_ID, "ultimate_sword_speed_boost");
    private static final Identifier ULTIMATE_MACE_ATTACK_DAMAGE_ID =
            Identifier.of(MOD_ID, "ultimate_mace_damage_bonus");
    private static final Identifier ULTIMATE_MACE_ATTACK_SPEED_ID =
            Identifier.of(MOD_ID, "ultimate_mace_speed_boost");

    // Ultimate Sword
    public static final Item ULTIMATE_SWORD_ITEM = new UltimateSwordItem(
            ModToolMaterials.ULTIMATE_SWORD_MATERIAL,
            new Item.Settings()
                    .fireproof()
                    .component(
                            DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            AttributeModifiersComponent.builder()
                                    .add(
                                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                                            new EntityAttributeModifier(
                                                    ULTIMATE_SWORD_ATTACK_DAMAGE_ID,
                                                    5.0,
                                                    EntityAttributeModifier.Operation.ADD_VALUE
                                            ),
                                            AttributeModifierSlot.MAINHAND
                                    )
                                    .add(
                                            EntityAttributes.GENERIC_ATTACK_SPEED,
                                            new EntityAttributeModifier(
                                                    ULTIMATE_SWORD_ATTACK_SPEED_ID,
                                                    -1.0,
                                                    EntityAttributeModifier.Operation.ADD_VALUE
                                            ),
                                            AttributeModifierSlot.MAINHAND
                                    )
                                    .build()
                    )
    );

    // Ultimate Mace
    public static final Item ULTIMATE_MACE_ITEM = new UltimateMaceItem(
            new Item.Settings()
                    .fireproof()
                    .component(
                            DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            AttributeModifiersComponent.builder()
                                    .add(
                                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                                            new EntityAttributeModifier(
                                                    ULTIMATE_MACE_ATTACK_DAMAGE_ID,
                                                    12.0,
                                                    EntityAttributeModifier.Operation.ADD_VALUE
                                            ),
                                            AttributeModifierSlot.MAINHAND
                                    )
                                    .add(
                                            EntityAttributes.GENERIC_ATTACK_SPEED,
                                            new EntityAttributeModifier(
                                                    ULTIMATE_MACE_ATTACK_SPEED_ID,
                                                    -3.5,
                                                    EntityAttributeModifier.Operation.ADD_VALUE
                                            ),
                                            AttributeModifierSlot.MAINHAND
                                    )
                                    .build()
                    )
                    .component(
                            DataComponentTypes.UNBREAKABLE,
                            new UnbreakableComponent(true)
                    )
    );

    // Creative Tab
    public static final RegistryKey<ItemGroup> ULTIMATE_SWORDS_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(MOD_ID, "ultimate_swords_tab")
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Ultimate Swords Mod!");

        // ADDED: Register custom enchantment effects
        ModEnchantmentEffects.registerEnchantmentEffects();

        Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "ultimate_sword"),
                ULTIMATE_SWORD_ITEM
        );
        Registry.register(Registries.ITEM,
                Identifier.of(MOD_ID, "ultimate_mace"),
                ULTIMATE_MACE_ITEM
        );

        Registry.register(Registries.ITEM_GROUP,
                ULTIMATE_SWORDS_TAB,
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ULTIMATE_SWORD_ITEM))
                        .displayName(Text.translatable("itemgroup." + MOD_ID + ".ultimate_swords_tab"))
                        .build()
        );

        ItemGroupEvents.modifyEntriesEvent(ULTIMATE_SWORDS_TAB).register(entries -> {
            entries.add(ULTIMATE_SWORD_ITEM);
            entries.add(ULTIMATE_MACE_ITEM);
        });

        LOGGER.info("Ultimate Swords Mod initialized successfully!");
    }
}