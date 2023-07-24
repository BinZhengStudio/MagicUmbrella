package com.bzgzs.magicumbrella.item;

import com.bzgzs.magicumbrella.MagicUmbrella;
import com.bzgzs.magicumbrella.annotation.RegisterScanner;
import com.bzgzs.magicumbrella.register.RegisterBaseInitialization;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@RegisterScanner
public class ItemRegister extends RegisterBaseInitialization {
    public static final RegisterBaseInitialization instance = new ItemRegister();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicUmbrella.MODID);
    public static final RegistryObject<Item> UMBRELLA = ITEMS.register("umbrella",()->new Item(new Item.Properties()));
    public void register(IEventBus bus){
        ITEMS.register(bus);
    }
    public ItemRegister(){
    }
}
