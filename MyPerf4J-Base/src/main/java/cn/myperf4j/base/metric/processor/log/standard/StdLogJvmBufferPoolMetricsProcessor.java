package cn.myperf4j.base.metric.processor.log.standard;

import cn.myperf4j.base.metric.JvmBufferPoolMetrics;
import cn.myperf4j.base.metric.formatter.JvmBufferPoolMetricsFormatter;
import cn.myperf4j.base.metric.formatter.standard.StdJvmBufferPoolMetricsFormatter;
import cn.myperf4j.base.metric.processor.log.AbstractLogJvmBufferPoolMetricsProcessor;
import cn.myperf4j.base.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LinShunkang on 2018/8/25
 */
public class StdLogJvmBufferPoolMetricsProcessor extends AbstractLogJvmBufferPoolMetricsProcessor {

    private static final JvmBufferPoolMetricsFormatter METRICS_FORMATTER = new StdJvmBufferPoolMetricsFormatter();

    private final ConcurrentHashMap<Long, List<JvmBufferPoolMetrics>> metricsMap = new ConcurrentHashMap<>(8);

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        metricsMap.put(processId, new ArrayList<JvmBufferPoolMetrics>(2));
    }

    @Override
    public void process(JvmBufferPoolMetrics metrics, long processId, long startMillis, long stopMillis) {
        List<JvmBufferPoolMetrics> metricsList = metricsMap.get(processId);
        if (metricsList != null) {
            metricsList.add(metrics);
        } else {
            Logger.error("StdLogJvmBufferPoolMetricsProcessor.process(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        List<JvmBufferPoolMetrics> metricsList = metricsMap.remove(processId);
        if (metricsList != null) {
            logger.logAndFlush(METRICS_FORMATTER.format(metricsList, startMillis, stopMillis));
        } else {
            Logger.error("StdLogJvmBufferPoolMetricsProcessor.afterProcess(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }
}
