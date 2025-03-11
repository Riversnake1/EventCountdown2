package net.riverafmod.eventcountdown;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CounterHandler {
    @SubscribeEvent
    public void ticker(CountdownEvent event) {
        if (--event.status == 0) event.executor.doIt();
        else { MinecraftForge.EVENT_BUS.post(new CountdownEvent(event.executor, event.status, event.isContinual));

        }
    }
}
