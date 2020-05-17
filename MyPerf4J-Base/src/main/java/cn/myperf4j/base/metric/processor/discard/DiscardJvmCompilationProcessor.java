package cn.myperf4j.base.metric.processor.discard;

import cn.myperf4j.base.metric.JvmCompilationMetrics;
import cn.myperf4j.base.metric.processor.JvmCompilationProcessor;

/**
 * Created by LinShunkang on 2019/11/09
 */
public class DiscardJvmCompilationProcessor  implements JvmCompilationProcessor {

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void process(JvmCompilationMetrics metrics, long processId, long startMillis, long stopMillis) {
        //empty
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        //empty
    }

}
