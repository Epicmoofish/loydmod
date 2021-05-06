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

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@LoydmodModElements.ModElement.Tag
public class ChanceChanger extends Item {

	/**
	 * Do not remove this constructor
	 */
	protected float lucidchance;
	protected int dreamweight;
	protected int nightmareweight;
		public ChanceChanger(Properties properties,float lucid, int dream, int nightmare) {
			super(properties);
			lucidchance=lucid;
			dreamweight=dream;
			nightmareweight=nightmare;
		}
	public float getLucidDiff() {
		return lucidchance;
	}
	public int getDreamDiff() {
		return dreamweight;
	}
	public int getNightmareDiff() {
		return nightmareweight;
	}
}
