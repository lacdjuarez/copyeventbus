package space.inevitable.eventbus.post

import space.inevitable.eventbus.ListenersStubsHolder.EventA
import space.inevitable.eventbus.ListenersStubsHolder.ListenerA
import space.inevitable.eventbus.beans.ExecutionBundle
import space.inevitable.eventbus.invoker.SameThreadInvoker

public class ExecutionsBundlesBuilder {
    public static ExecutionBundle buildExecutionBundleSameThread() {
        ListenerA listenerA = new ListenerA();
        def method = ListenerA.class.getMethod( "methodA", EventA.class )

        def sameThreadInvoker = new SameThreadInvoker();
        new ExecutionBundle( listenerA, method, sameThreadInvoker );
    }
}