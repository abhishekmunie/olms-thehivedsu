package cf.thehivedsu.olms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utilities {

	public static String urlEncodeUTF8(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public static String urlEncodeUTF8(Map<?, ?> map) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", urlEncodeUTF8(entry.getKey().toString()),
					urlEncodeUTF8(entry.getValue().toString())));
		}
		return sb.toString();
	}

	public static String urlEncodeUTF8ParameterMap(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> parameterMap = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", urlEncodeUTF8(entry.getKey()), urlEncodeUTF8(entry.getValue()[0])));
		}
		return sb.toString();
	}

	public static String setParametersAsAttributes(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> parameterMap = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			req.setAttribute(entry.getKey(), entry.getValue()[0]);
		}
		return sb.toString();
	}

}
