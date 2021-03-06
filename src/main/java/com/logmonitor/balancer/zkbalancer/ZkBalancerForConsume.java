package com.logmonitor.balancer.zkbalancer;

import com.logmonitor.balancer.ids.ConsumeId;
import com.logmonitor.balancer.node.ConsumeNode;
import com.logmonitor.balancer.node.SourceNode;
import org.apache.curator.RetryPolicy;

import java.util.List;

/**
 * Created by wanghaiyang on 16/3/12.
 */
public class ZkBalancerForConsume extends ZkBalancer {

    public ZkBalancerForConsume(String connectString, int sessionTimeoutMs, int connectTimeoutMs, RetryPolicy retryPolicy) {
        super(connectString, sessionTimeoutMs, connectTimeoutMs, retryPolicy);
    }

    public boolean registerConsume(ConsumeNode consumeNode) {
        ConsumeId consumeId = new ConsumeId();
        String path = parentPath + "/" + consumeId.prefix + consumeId.getUniqueId();
        boolean result = createNode(path);
        consumeNode.setNodePath(path);
        consumeNode.setNodeId(consumeId.getUniqueId());
        return result;
    }

    public boolean removeConsume(ConsumeNode consumeNode) {
        return deleteNode(consumeNode.getNodePath());
    }
}
