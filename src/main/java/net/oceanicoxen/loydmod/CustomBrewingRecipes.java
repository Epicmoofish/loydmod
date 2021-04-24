/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class LoydmodModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.oceanicoxen.loydmod as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.oceanicoxen.loydmod;

import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.oceanicoxen.loydmod.potion.DreamyPotion;

@LoydmodModElements.ModElement.Tag
public class CustomBrewingRecipes extends LoydmodModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public CustomBrewingRecipes(LoydmodModElements instance) {
		super(instance, 35);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		try {
			Class clazz = net.minecraft.potion.PotionBrewing.class;
			Method mth = ObfuscationReflectionHelper.findMethod(clazz, "func_193357_a", Potion.class, Item.class, Potion.class);
			mth.invoke(null, Potions.AWKWARD, Items.DIAMOND_PICKAXE, DreamyPotion.potionType);
			mth.invoke(null, DreamyPotion.potionType, Items.REDSTONE, DreamyPotion.potionTypeLong);
			// To add more recipes, you can copy the line above, for ex.
			// mth.invoke(null, potion2, item2, potion3);
		} catch (Throwable e) {
			System.err.println("ERROR: " + e); // You can change this part
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientLoad(FMLClientSetupEvent event) {
	}
}
