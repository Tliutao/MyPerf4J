package cn.myperf4j.base.metric.processor.discard;

import cn.myperf4j.base.metric.JvmFileDescriptorMetrics;
import cn.myperf4j.base.metric.processor.JvmFileDescProcessor;

/**
 * Created by LinShunkang on 2019/11/09
 */
public class DiscardJvmFileDescProcessor  implements JvmFileDescProcessor {

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void process(JvmFileDescriptorMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

}
