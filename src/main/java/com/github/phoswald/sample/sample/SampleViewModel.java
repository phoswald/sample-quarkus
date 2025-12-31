package com.github.phoswald.sample.sample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SampleViewModel {

    public final String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    public final String sampleConfig;
    public final String username;
    public final Map<String, ?> env = sortAndMaskSecrets(System.getenv());
    public final Map<Object, ?> props = sortAndMaskSecrets(System.getProperties());

    public SampleViewModel(String sampleConfig, String username) {
    	this.sampleConfig = sampleConfig;
    	this.username = username;
    }

    private static <K, V> Map<K, ?> sortAndMaskSecrets(Map<K, V> map) {
        return new TreeMap<K, Object>(map.entrySet().stream() //
                .map(e -> isSecret(e) ? Map.entry(e.getKey(), "???") : e) //
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private static boolean isSecret(Map.Entry<?, ?> e) {
        String key = e.getKey().toString().toLowerCase();
        return key.contains("password") || key.contains("secret") ||
                key.contains("encryption-key") || key.contains("encryption_key");
    }
}
