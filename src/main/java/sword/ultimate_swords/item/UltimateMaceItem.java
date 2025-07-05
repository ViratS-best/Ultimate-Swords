package sword.ultimate_swords.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import sword.ultimate_swords.enchantment.ModEnchantments;

public class UltimateMaceItem extends Item {

    public UltimateMaceItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world) {
        super.onCraft(stack, world);

        if (!world.isClient && !stack.contains(DataComponentTypes.ENCHANTMENTS)) {
            // Create enchantment builder with default base
            ItemEnchantmentsComponent.Builder enchantmentBuilder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);

            Registry<Enchantment> enchantmentRegistry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT);

            // Custom Mod Enchantment: Ultimate Mace
            RegistryEntry<Enchantment> ultimateMaceEntry = enchantmentRegistry.getEntry(ModEnchantments.ULTIMATE_MACE)
                    .orElseThrow(() -> new IllegalStateException("ULTIMATE_MACE not found"));
            enchantmentBuilder.add(ultimateMaceEntry, 1);

            // Vanilla 1.21 Enchantment: DENSITY
            RegistryEntry<Enchantment> densityEntry = enchantmentRegistry.getEntry(Enchantments.DENSITY)
                    .orElseThrow(() -> new IllegalStateException("DENSITY not found"));
            enchantmentBuilder.add(densityEntry, 1);

            // Vanilla 1.21 Enchantment: WIND_BURST
            RegistryEntry<Enchantment> windBurstEntry = enchantmentRegistry.getEntry(Enchantments.WIND_BURST)
                    .orElseThrow(() -> new IllegalStateException("WIND_BURST not found"));
            enchantmentBuilder.add(windBurstEntry, 2);

            // Apply to item
            stack.set(DataComponentTypes.ENCHANTMENTS, enchantmentBuilder.build());
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            float fallDistance = player.fallDistance;
            if (fallDistance > 0.5F) {
                float bonusDamage = Math.min(fallDistance * 1.5f, 50.0F);
                if (bonusDamage > 0.0F) {
                    target.damage(player.getDamageSources().playerAttack(player), bonusDamage);
                    double verticalVelocity = 0.7 + fallDistance * 0.15;
                    player.setVelocity(player.getVelocity().x, verticalVelocity, player.getVelocity().z);
                    player.velocityModified = true;

                    player.getWorld().addParticle(
                            ParticleTypes.EXPLOSION_EMITTER,
                            player.getX(), player.getY() + 0.5, player.getZ(),
                            1.0, 0.0, 0.0
                    );

                    target.getWorld().addParticle(
                            ParticleTypes.CRIT,
                            target.getX(), target.getY() + target.getHeight() / 2.0, target.getZ(),
                            0.0, 0.0, 0.0
                    );

                    player.fallDistance = 0.0F;

                    player.getWorld().playSound(
                            null,
                            player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ITEM_MACE_SMASH_GROUND,
                            SoundCategory.PLAYERS,
                            1.0F, 1.0F
                    );
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
