package me.jezza.thaumicpipes.common.transport;

import java.util.ArrayList;
import java.util.Iterator;

import me.jezza.thaumicpipes.common.core.TPLogger;
import me.jezza.thaumicpipes.common.transport.connection.TransportState;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;

import com.google.common.collect.Lists;

public class AspectContainerList implements Iterable<TransportState> {

    private ArrayList<TransportState> containerList;
    private Aspect filter = null;

    public AspectContainerList() {
        containerList = Lists.newArrayList();
    }

    public void add(TileEntity tileEntity) {
        add(new TransportState(tileEntity));
    }

    public void add(TransportState transportState) {
        if (filter == null) {
            TPLogger.severe("Filter not set");
            return;
        }
        containerList.add(transportState);
    }

    public void addAll(AspectContainerList otherList) {
        for (TransportState state : otherList.containerList)
            containerList.add(state);
    }

    public void clear(Aspect filter) {
        containerList.clear();
        setFilter(filter);
    }

    public AspectContainerList setFilter(Aspect filter) {
        this.filter = filter;
        return this;
    }

    public Aspect getFilter() {
        return filter;
    }

    public int size() {
        return containerList.size();
    }

    public boolean isEmpty() {
        return containerList.isEmpty();
    }

    public static class AspectIterator implements Iterator<TransportState> {

        private ArrayList<TransportState> containerList;
        int currentIndex = 0;

        public AspectIterator(ArrayList<TransportState> containerList) {
            this.containerList = containerList;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < containerList.size();
        }

        @Override
        public TransportState next() {
            return containerList.get(currentIndex++);
        }

        @Override
        public void remove() {
            containerList.remove(currentIndex);
        }
    }

    @Override
    public Iterator<TransportState> iterator() {
        return new AspectIterator(containerList);
    }
}