package com.windanesz.ancientspellcraft.registry;

import com.windanesz.ancientspellcraft.worldgen.pocketdim.BiomePocket;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class AncientSpellcraftBiomes {

	// instantiate Biomes
	public static BiomePocket pocket;

	public static void preInit() {
		pocket = new BiomePocket();

		ForgeRegistries.BIOMES.register(pocket);
		BiomeDictionary.addTypes(pocket, BiomeDictionary.Type.MAGICAL);
	}
}
