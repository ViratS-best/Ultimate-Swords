package sword.ultimate_swords.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sword.ultimate_swords.Ultimate_swords;
import sword.ultimate_swords.enchantment.custom.UltimateMaceEnchantmentEffect;
import sword.ultimate_swords.item.UltimateMaceItem;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> ULTIMATE_MACE =
            registerEntityEffect("ultimate_mace", UltimateMaceEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Ultimate_swords.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {

    }
}
