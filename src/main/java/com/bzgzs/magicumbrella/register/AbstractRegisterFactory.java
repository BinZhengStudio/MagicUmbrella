package com.bzgzs.magicumbrella.register;

import net.minecraftforge.eventbus.api.IEventBus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractRegisterFactory {
    public abstract void register(IEventBus bus);
    public AbstractRegisterFactory(){
    }
}
