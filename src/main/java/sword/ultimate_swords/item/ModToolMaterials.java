package sword.ultimate_swords.item;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    ULTIMATE_SWORD_MATERIAL(
            5000,
            16.0f,
            20.0f,
            25,
            () -> Ingredient.ofItems(Items.NETHER_STAR)
    );

    private final int durability;
    private final float miningSpeedMultiplier;
    private final float attackDamageBonus;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(int durability, float miningSpeedMultiplier, float attackDamageBonus, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }
    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        // This is the new method required by ToolMaterial.
        // It should return the flat attack damage provided by the material.
        // In your case, it's what you passed as 'attackDamageBonus' in the constructor.
        return this.attackDamageBonus;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return  BlockTags.INCORRECT_FOR_IRON_TOOL;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
