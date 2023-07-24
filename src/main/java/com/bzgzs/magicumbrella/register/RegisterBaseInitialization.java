package com.bzgzs.magicumbrella.register;

import com.bzgzs.magicumbrella.annotation.RegisterScanner;
import com.bzgzs.magicumbrella.util.ScannerAnnotation;
import net.minecraftforge.eventbus.api.IEventBus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class RegisterBaseInitialization extends AbstractRegisterFactory{
    public static List<RegisterBaseInitialization> REGISTER_FACTORY_LIST = new CopyOnWriteArrayList<>();

    public RegisterBaseInitialization(){
        REGISTER_FACTORY_LIST.add(this);
    }
    public void register(IEventBus bus){}
    public static void registerEvent(IEventBus bus) throws IOException, ClassNotFoundException {
        ScannerAnnotation<RegisterBaseInitialization> scanner = new ScannerAnnotation<>(RegisterScanner.class);
        scanner.scannerClasses();
        List<Class<RegisterBaseInitialization>> classList = scanner.getScanner();
        REGISTER_FACTORY_LIST = classList.stream().map(registerBaseInitializationClass -> {
            try {
                RegisterBaseInitialization registerBaseInitialization = registerBaseInitializationClass.getDeclaredConstructor().newInstance();
                registerBaseInitialization.register(bus);
                return registerBaseInitialization;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();

    }

}
