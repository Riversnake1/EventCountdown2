package net.riverafmod.eventcountdown;

import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class Countdowns {
    private ArrayList<CountdownEvent> events = new ArrayList<>();
    private static final Countdowns instance = new Countdowns();
    public static Countdowns getInstance() {
        return instance;
    }
    public CountdownEvent[] toArray() {
        return (CountdownEvent[]) events.toArray();
    }
    public int add(CountdownEvent o) {
        int id = toArray().length;
        events.add(o);
        return id;
    }
    public void rebuildAndPostAll() {
        events.forEach((e) -> MinecraftForge.EVENT_BUS.post(new CountdownEvent(e.executor, e.status)));
    }
    private int decrementPos(int oldPos) {
        return --events.get(oldPos).pos;
    }
    public void safeDelete(int where) {
        events.remove(where);
        events.forEach((e) -> {
            if (e.pos > where) {
                decrementPos(e.pos);
            }
        });
    }
    public String toString() {
        return "Countdowns object encapsulating: " + events.toString();
    }
    public void decrementStatus(int where) {
        events.get(where).status--;
    }
}
