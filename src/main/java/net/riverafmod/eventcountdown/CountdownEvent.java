package net.riverafmod.eventcountdown;

import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public class CountdownEvent extends Event {
    public final Runnable executor;
    int pos;
    public int status;
    public CountdownEvent(Runnable executor, int ticksUntilActivation) {
        this.executor = executor;
        pos = Countdowns.getInstance().add(this);
        status = ticksUntilActivation;
    }
}
