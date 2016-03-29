package space.inevitable.eventbus.post;

import space.inevitable.eventbus.beans.ExecutionBundle;
import space.inevitable.eventbus.collections.ExecutionBundleSet;
import space.inevitable.eventbus.collections.ExecutionBundleSetsByType;
import space.inevitable.eventbus.invoke.Invoker;

import java.lang.reflect.Type;


public final class ListenersInPoolProxyInvoker {
    private final ExecutionBundleSetsByType executionBundleSetsByType;

    public ListenersInPoolProxyInvoker(final ExecutionBundleSetsByType executionBundleSetsByType) {
        this.executionBundleSetsByType = executionBundleSetsByType;
    }

    public void invokeListenersForEvent(final Object eventInstance) {
        final ExecutionBundleSet listenersPool = getListenerPoolForEvent(eventInstance);

        if (listenersPool == null) {
            return;
        }

        invokeListenersInPool(listenersPool, eventInstance);
    }

    private void invokeListenersInPool(final Iterable<ExecutionBundle> executionBundles, final Object eventInstance) {
        for (final ExecutionBundle executionBundle : executionBundles) {

            final Invoker invoker = executionBundle.getInvoker();
            invoker.invoke(executionBundle, eventInstance);
        }
    }

    private ExecutionBundleSet getListenerPoolForEvent(final Object eventInstance) {
        final Type type = eventInstance.getClass();
        return executionBundleSetsByType.get(type);
    }
}
