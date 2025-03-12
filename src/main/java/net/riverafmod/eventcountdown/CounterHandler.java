package net.riverafmod.eventcountdown;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.io.*;

@Mod.EventBusSubscriber
public class CounterHandler {
    @SubscribeEvent
    public void ticker(CountdownEvent event) {
        if (--event.status == 0) {event.executor.run(); Countdowns.getInstance().safeDelete(event.pos);}
        else { MinecraftForge.EVENT_BUS.post(new CountdownEvent(event.executor, event.status));
            Countdowns.getInstance().decrementStatus(event.pos);
        }
    }
    private static boolean hasSerialized = false;
    private static boolean hasDeserialized = false;
    private static Level overworld;
    @SubscribeEvent
    public static void serialize(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() == null) {
            if(!(hasSerialized || overworld == null)) {
                hasSerialized = true;
                try(var s1 = new ByteArrayOutputStream(); var s2 = new ObjectOutputStream(s1)) {
                    s2.writeObject(Countdowns.getInstance());
                } catch (IOException e) {
                    hasSerialized = false;
                    BigOldMostlyUselessHub.LOGGER.info("An IO exception has occurred");
                }
            }
        }
    }
}
