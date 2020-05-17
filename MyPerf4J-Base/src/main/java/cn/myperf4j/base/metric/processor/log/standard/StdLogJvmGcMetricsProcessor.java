package cn.myperf4j.base.metric.processor.log.standard;

import cn.myperf4j.base.metric.JvmGcMetrics;
import cn.myperf4j.base.metric.formatter.JvmGCMetricsFormatter;
import cn.myperf4j.base.metric.formatter.standard.StdJvmGcMetricsFormatter;
import cn.myperf4j.base.metric.processor.log.AbstractLogJvmGcMetricsProcessor;
import cn.myperf4j.base.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LinShunkang on 2018/8/25
 */
public class StdLogJvmGcMetricsProcessor extends AbstractLogJvmGcMetricsProcessor {

    private static final JvmGCMetricsFormatter METRICS_FORMATTER = new StdJvmGcMetricsFormatter();

    private final ConcurrentHashMap<Long, List<JvmGcMetrics>> metricsMap = new ConcurrentHashMap<>(8);

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        metricsMap.put(processId, new ArrayList<JvmGcMetrics>(1));
    }

    @Override
    public void process(JvmGcMetrics metrics, long processId, long startMillis, long stopMillis) {
        List<JvmGcMetrics> metricsList = metricsMap.get(processId);
        if (metricsList != null) {
            metricsList.add(metrics);
        } else {
            Logger.error("StdLogJvmGcMetricsProcessor.process(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        List<JvmGcMetrics> metricsList = metricsMap.remove(processId);
        if (metricsList != null) {
            logger.logAndFlush(METRICS_FORMATTER.format(metricsList, startMillis, stopMillis));
        } else {
            Logger.error("StdLogJvmGcMetricsProcessor.afterProcess(" + processId + ", " + startMillis + ", " + stopMillis + "): metricsList is null!!!");
        }
    }
}
