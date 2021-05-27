package cloud.erda.analyzer.alert.utils;

import cloud.erda.analyzer.common.constant.AlertConstants;
import cloud.erda.analyzer.common.models.MetricEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeUrl {
    private static String pattern = "(.*)-org.*";
    private static Pattern p = Pattern.compile(pattern);

    public static MetricEvent modifyMetricEvent(MetricEvent metricEvent) throws MalformedURLException {
        String orgName = metricEvent.getTags().get(AlertConstants.ORG_NAME);
        String displayUrl = metricEvent.getTags().get(AlertConstants.DISPLAY_URL);
        String recordUrl = metricEvent.getTags().get(AlertConstants.RECORD_URL);
        if (orgName == null) {
            orgName = getOrgName(displayUrl,recordUrl);
        }
        if (orgName != null) {
            if (displayUrl != null) {
                displayUrl = modifyUrl(orgName,displayUrl);
                metricEvent.getTags().put(AlertConstants.DISPLAY_URL,displayUrl);
            }
            if (recordUrl != null) {
                recordUrl = modifyUrl(orgName,recordUrl);
                metricEvent.getTags().put(AlertConstants.RECORD_URL,recordUrl);
            }
        }
        return metricEvent;
    }

    public static String modifyUrl(String orgName, String url) throws MalformedURLException {
        URL u = new URL(url);
        String protocol = u.getProtocol();
        String host = u.getHost();
        StringBuffer stringBuffer = new StringBuffer(url);
        String head = protocol + "://" + host + "/";
        String subString = url.substring(head.length() - 1, url.length() - head.length() - 1);
        String[] elements = subString.split("/");
        if (!elements[0].equals(orgName)) {
            stringBuffer.insert(head.length(), orgName + "/");
            return stringBuffer.toString();
        }
        return url;
    }

    public static String getOrgName(String displayUrl,String recordUrl) throws MalformedURLException {
        if (displayUrl == null || recordUrl == null) {
            return null;
        }
        String url = displayUrl == null ? recordUrl : displayUrl;
        URL u = new URL(url);
        String host = u.getHost();
        Matcher matcher = p.matcher(host);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
