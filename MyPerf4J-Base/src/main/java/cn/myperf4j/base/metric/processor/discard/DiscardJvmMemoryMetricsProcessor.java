package cn.myperf4j.base.metric.processor.discard;

import cn.myperf4j.base.metric.JvmMemoryMetrics;
import cn.myperf4j.base.metric.processor.JvmMemoryMetricsProcessor;

/**
 * Created by LinShunkang on 2018/8/21
 */
public class DiscardJvmMemoryMetricsProcessor implements JvmMemoryMetricsProcessor {

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void process(JvmMemoryMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

}
