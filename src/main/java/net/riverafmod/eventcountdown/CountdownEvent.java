package net.riverafmod.eventcountdown;

import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public class CountdownEvent extends Event {
    public final boolean isContinual;
    public final CustomExecutor executor;
    @Nullable
    public final UUID uuid;
    public int status;
    public CountdownEvent(CustomExecutor executor, int ticksUntilActivation, boolean ensureContinuality) {
        isContinual = ensureContinuality;
        this.executor = executor;
        /*if (isContinual) {
            uuid = UUID.randomUUID();
        } else {*/
            uuid = null;
        //}
        status = ticksUntilActivation;
    }
}
